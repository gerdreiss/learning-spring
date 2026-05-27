package com.github.gerdreiss.crud.demo.entity

import jakarta.persistence.*

@Entity
@Table(name = "student")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int? = null,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column(name = "email", unique = true)
    val email: String
) {
    constructor(firstName: String, lastName: String, email: String) : this(null, firstName, lastName, email)
}