package com.github.gerdreiss.jpa.onetoone.uni.repository

import com.github.gerdreiss.jpa.onetoone.uni.entity.Instructor
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface InstructorRepository : JpaRepository<Instructor, Int> {
    @EntityGraph(attributePaths = ["courses"])
    fun findByEmail(email: String): Instructor?
}