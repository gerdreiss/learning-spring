package com.github.gerdreiss.jpa.onetoone.uni

import com.github.gerdreiss.jpa.onetoone.uni.entity.Course
import com.github.gerdreiss.jpa.onetoone.uni.entity.Instructor
import com.github.gerdreiss.jpa.onetoone.uni.entity.InstructorDetail
import com.github.gerdreiss.jpa.onetoone.uni.repository.CourseRepository
import com.github.gerdreiss.jpa.onetoone.uni.repository.CustomRepository
import com.github.gerdreiss.jpa.onetoone.uni.repository.InstructorDetailRepository
import com.github.gerdreiss.jpa.onetoone.uni.repository.InstructorRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CLI(
    val instructorRepository: InstructorRepository,
    val instructorDetailRepository: InstructorDetailRepository,
    val courseRepository: CourseRepository,
    val customRepository: CustomRepository
) : CommandLineRunner {

    override fun run(vararg args: String) {
        val timestamp = System.currentTimeMillis()

        val instructor = Instructor("Chad", "Darby", "$timestamp@luv2code.com")
        val instructorDetail = InstructorDetail(youtubeChannel = "https://www.luv2code.com/youtube", hobby = "coding")
        val course1 = Course(title = "Spring Boot 4, String 7 & Hibernate for Beginners $timestamp")
        val course2 = Course(title = "Spring Boot 4, String 7 & Hibernate for Advanced $timestamp")

        instructor.setInstructorDetail(instructorDetail)
        instructor.addCourse(course1)
        instructor.addCourse(course2)

        val persisted = instructorRepository.save(instructor)

        println("Saved instructor: $persisted")

        val foundById = instructorRepository.findById(persisted.id!!)

        println("Found by id: $foundById")

        val foundByEmail = instructorRepository.findByEmail(instructor.email)
//        val foundCourses = foundByEmail?.courses

        println("Found by email: $foundByEmail")
//        println("Found courses: $foundCourses")

        val withCourses = customRepository.findInstructorJoinFetchCoursesById(persisted.id!!)
        println("With courses: $withCourses: [${withCourses.courses}]")

        courseRepository.deleteAllInBatch()
        instructorRepository.deleteAllInBatch()
        instructorDetailRepository.deleteAllInBatch()

        val instructors = instructorRepository.count()
        println("Instructors should be 0: $instructors")

        val instructorDetails = instructorDetailRepository.count()
        println("Instructor details should be 0: $instructorDetails")

        val courses = courseRepository.count()
        println("Courses should be 0: $courses")
    }
}
