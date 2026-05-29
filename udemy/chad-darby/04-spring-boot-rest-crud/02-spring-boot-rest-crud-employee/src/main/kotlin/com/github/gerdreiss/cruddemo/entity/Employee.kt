package com.github.gerdreiss.cruddemo.entity

import jakarta.persistence.*

@Entity
@Table(name = "employee")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int = 0,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column(name = "email")
    val email: String,
)