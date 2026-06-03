#!/bin/bash

# Script: prepend_lines.sh
# Description: Reads first X lines from a source file and prepends them to all files in a directory (recursively)
# Usage: ./prepend_lines.sh <source_file> <num_lines> <target_directory>

set -e  # Exit on error

# Color codes for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to display usage
usage() {
    echo "Usage: $0 <source_file> <num_lines> <target_directory>"
    echo ""
    echo "Parameters:"
    echo "  source_file     - File to read the header lines from"
    echo "  num_lines       - Number of lines to read from source file"
    echo "  target_directory - Directory to search for files (recursive)"
    echo ""
    echo "Options:"
    echo "  -h, --help      - Show this help message"
    echo "  -d, --dry-run   - Preview changes without modifying files"
    echo "  -p, --pattern   - File pattern to match (default: *)"
    echo "  -b, --backup    - Create backups with .bak extension"
    echo "  -v, --verbose   - Show detailed output"
    echo ""
    echo "Examples:"
    echo "  $0 header.txt 5 /path/to/project"
    echo "  $0 license.txt 10 ./src -p \"*.kt\""
    echo "  $0 header.txt 3 . -d --verbose"
    exit 1
}

# Default values
DRY_RUN=false
VERBOSE=false
CREATE_BACKUP=false
FILE_PATTERN="*"
BACKUP_SUFFIX=".bak"

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -h|--help)
            usage
            ;;
        -d|--dry-run)
            DRY_RUN=true
            shift
            ;;
        -v|--verbose)
            VERBOSE=true
            shift
            ;;
        -b|--backup)
            CREATE_BACKUP=true
            shift
            ;;
        -p|--pattern)
            FILE_PATTERN="$2"
            shift 2
            ;;
        *)
            if [ -z "$SOURCE_FILE" ]; then
                SOURCE_FILE="$1"
            elif [ -z "$NUM_LINES" ]; then
                NUM_LINES="$1"
            elif [ -z "$TARGET_DIR" ]; then
                TARGET_DIR="$1"
            else
                echo "Unknown parameter: $1"
                usage
            fi
            shift
            ;;
    esac
done

# Validate parameters
if [ -z "$SOURCE_FILE" ] || [ -z "$NUM_LINES" ] || [ -z "$TARGET_DIR" ]; then
    echo -e "${RED}Error: Missing required parameters${NC}"
    usage
fi

# Check if source file exists
if [ ! -f "$SOURCE_FILE" ]; then
    echo -e "${RED}Error: Source file '$SOURCE_FILE' does not exist${NC}"
    exit 1
fi

# Check if num_lines is a positive integer
if ! [[ "$NUM_LINES" =~ ^[0-9]+$ ]] || [ "$NUM_LINES" -lt 1 ]; then
    echo -e "${RED}Error: num_lines must be a positive integer${NC}"
    exit 1
fi

# Check if target directory exists
if [ ! -d "$TARGET_DIR" ]; then
    echo -e "${RED}Error: Target directory '$TARGET_DIR' does not exist${NC}"
    exit 1
fi

# Read the header lines from source file
HEADER_LINES=$(head -n "$NUM_LINES" "$SOURCE_FILE")

# Check if we actually read any lines
if [ -z "$HEADER_LINES" ] && [ "$NUM_LINES" -gt 0 ]; then
    echo -e "${YELLOW}Warning: Source file is empty or has less than $NUM_LINES lines${NC}"
fi

# Counters for statistics
TOTAL_FILES=0
MODIFIED_FILES=0
SKIPPED_FILES=0

echo -e "${GREEN}=== Prepending $NUM_LINES lines from '$SOURCE_FILE' ===${NC}"
echo -e "Target directory: $TARGET_DIR"
echo -e "File pattern: $FILE_PATTERN"
echo -e "Recursive search: enabled"
echo ""

if [ "$DRY_RUN" = true ]; then
    echo -e "${YELLOW}*** DRY RUN MODE - No files will be modified ***${NC}"
    echo ""
fi

# Find all files matching pattern in target directory recursively
while IFS= read -r file; do
    # Skip directories
    [ -d "$file" ] && continue
    
    # Skip the source file if it's inside the target directory
    if [ "$(realpath "$file")" = "$(realpath "$SOURCE_FILE")" ]; then
        echo -e "${YELLOW}Skipping source file itself: $file${NC}"
        ((SKIPPED_FILES++))
        continue
    fi
    
    TOTAL_FILES=$((TOTAL_FILES + 1))
    
    if [ "$VERBOSE" = true ]; then
        echo -e "Processing: $file"
    fi
    
    # Create backup if requested
    if [ "$CREATE_BACKUP" = true ] && [ "$DRY_RUN" = false ]; then
        cp "$file" "$file$BACKUP_SUFFIX"
        if [ "$VERBOSE" = true ]; then
            echo -e "  ${GREEN}✓ Backup created: $file$BACKUP_SUFFIX${NC}"
        fi
    fi
    
    # Prepend header lines to the file
    if [ "$DRY_RUN" = false ]; then
        # Method 1: Using printf and cat (preserves formatting)
        {
            printf "%s\n" "$HEADER_LINES"
            # Add an extra newline if header doesn't end with one
            if [ -n "$HEADER_LINES" ] && [[ ! "$HEADER_LINES" =~ \n$ ]]; then
                echo ""
            fi
            cat "$file"
        } > "${file}.tmp" && mv "${file}.tmp" "$file"
        
        if [ $? -eq 0 ]; then
            MODIFIED_FILES=$((MODIFIED_FILES + 1))
            if [ "$VERBOSE" = true ]; then
                echo -e "  ${GREEN}✓ Prepended successfully${NC}"
            fi
        else
            echo -e "  ${RED}✗ Failed to prepend${NC}"
        fi
    else
        # Dry run - show what would be done
        echo -e "  ${YELLOW}[DRY RUN] Would prepend to: $file${NC}"
        echo "  Header content:"
        echo "$HEADER_LINES" | sed 's/^/    /'
        MODIFIED_FILES=$((MODIFIED_FILES + 1))
    fi
    
done < <(find "$TARGET_DIR" -type f -name "$FILE_PATTERN" ! -path "*/\.*" 2>/dev/null | sort)

# Summary
echo ""
echo -e "${GREEN}=== Summary ===${NC}"
echo -e "Total files found: $TOTAL_FILES"
echo -e "Files processed: $MODIFIED_FILES"
echo -e "Files skipped: $SKIPPED_FILES"

if [ "$DRY_RUN" = true ]; then
    echo -e "\n${YELLOW}This was a DRY RUN. Remove -d flag to actually modify files.${NC}"
fi

if [ "$CREATE_BACKUP" = true ] && [ "$DRY_RUN" = false ]; then
    echo -e "\n${GREEN}Backups created with '$BACKUP_SUFFIX' extension${NC}"
fi
