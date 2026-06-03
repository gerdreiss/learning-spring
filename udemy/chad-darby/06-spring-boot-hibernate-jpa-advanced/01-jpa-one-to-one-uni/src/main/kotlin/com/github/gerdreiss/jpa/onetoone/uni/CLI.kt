package com.github.gerdreiss.jpa.onetoone.uni

import com.github.gerdreiss.jpa.onetoone.uni.entity.Instructor
import com.github.gerdreiss.jpa.onetoone.uni.entity.InstructorDetail
import com.github.gerdreiss.jpa.onetoone.uni.repository.InstructorRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CLI(val instructorRepository: InstructorRepository) : CommandLineRunner {
    override fun run(vararg args: String) {

        instructorRepository.deleteAll()

        val instructorDetail = InstructorDetail(
            youtubeChannel = "https://www.luv2code.com/youtube",
            hobby = "coding"
        )
        val instructor = Instructor(
            firstName = "Chad",
            lastName = "Darby",
            email = "darby@luv2code.com",
            instructorDetail = instructorDetail
        )

        val persisted = instructorRepository.save(instructor)

        println("Saved instructor: $persisted")

        val foundById = instructorRepository.findById(persisted.id)

        println("Found by email: $foundById")

        val foundByEmail = instructorRepository.findByEmail(instructor.email)

        println("Found by email: $foundByEmail")

        instructorRepository.deleteById(persisted.id)

        println("Deleted instructor: $instructor")
    }
}
