package com.github.gerdreiss.jpa.onetoone.uni

import com.github.gerdreiss.jpa.onetoone.uni.entity.Course
import com.github.gerdreiss.jpa.onetoone.uni.entity.Instructor
import com.github.gerdreiss.jpa.onetoone.uni.entity.InstructorDetail
import com.github.gerdreiss.jpa.onetoone.uni.repository.CourseRepository
import com.github.gerdreiss.jpa.onetoone.uni.repository.InstructorDetailRepository
import com.github.gerdreiss.jpa.onetoone.uni.repository.InstructorRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CLI(
    val instructorRepository: InstructorRepository,
    val instructorDetailRepository: InstructorDetailRepository,
    val courseRepository: CourseRepository
) : CommandLineRunner {
    override fun run(vararg args: String) {

        val instructor = Instructor("Chad", "Darby", "darby@luv2code.com")
        val instructorDetail = InstructorDetail(youtubeChannel = "https://www.luv2code.com/youtube", hobby = "coding")
        val course = Course(title = "Spring Boot 4, String 7 & Hibernate for Beginners")

        instructor.setInstructorDetail(instructorDetail)
        instructor.addCourse(course)

        val persisted = instructorRepository.save(instructor)

        println("Saved instructor: $persisted")

        val foundById = instructorRepository.findById(persisted.id!!)

        println("Found by email: $foundById")

        val foundByEmail = instructorRepository.findByEmail(instructor.email)

        println("Found by email: $foundByEmail")

        courseRepository.deleteAll();
        instructorRepository.deleteById(persisted.id!!)

        println("Deleted instructor: $instructor")
    }
}
