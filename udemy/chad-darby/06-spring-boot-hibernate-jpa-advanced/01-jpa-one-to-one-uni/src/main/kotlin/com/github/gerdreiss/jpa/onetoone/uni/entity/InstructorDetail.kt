package com.github.gerdreiss.jpa.onetoone.uni.entity

import jakarta.persistence.*

@Entity
@Table(name = "instructor_detail")
data class InstructorDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int = 0,

    @Column(name = "youtube_channel")
    val youtubeChannel: String,

    @Column(name = "hobby")
    val hobby: String,
)
