package com.github.gerdreiss.jpa.onetoone.uni.entity

import jakarta.persistence.*


@Entity
@Table(name = "instructor")
class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "first_name", nullable = false)
    var firstName: String = ""

    @Column(name = "last_name", nullable = false)
    var lastName: String = ""

    @Column(nullable = false, unique = true)
    var email: String = ""

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "instructor_detail_id")
    private var instructorDetail: InstructorDetail? = null

    @OneToMany(
        mappedBy = "instructor",
        cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
        ]
    )
    var courses: MutableList<Course> = mutableListOf()

    constructor(firstName: String, lastName: String, email: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }

    // Helper methods to maintain bidirectional consistency
    fun addCourse(course: Course) {
        courses.add(course)
        course.instructor = this
    }

    fun removeCourse(course: Course) {
        courses.remove(course)
        course.instructor = null
    }

    fun setInstructorDetail(detail: InstructorDetail) {
        instructorDetail = detail
        detail.instructor = this
    }

    override fun toString(): String {
        return "Instructor(id=$id, firstName='$firstName', lastName='$lastName', email='$email')"
    }
}