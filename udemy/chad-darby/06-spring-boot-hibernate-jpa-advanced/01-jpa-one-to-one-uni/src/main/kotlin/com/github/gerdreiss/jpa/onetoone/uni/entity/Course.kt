package com.github.gerdreiss.jpa.onetoone.uni.entity

import jakarta.persistence.*

@Entity
@Table(name = "course")
class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "title")
    var title: String = ""

    // Many-to-one with Instructor
    @ManyToOne(
        cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
        ]
    )
    @JoinColumn(name = "instructor_id")
    var instructor: Instructor? = null

    constructor(title: String) {
        this.title = title
    }

    override fun toString(): String {
        return "Course(id=$id, title='$title')"
    }
}