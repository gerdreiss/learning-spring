package com.github.gerdreiss.jpa.onetoone.uni.entity

import jakarta.persistence.*

@Entity
@Table(name = "instructor_detail")
class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null

    @Column(name = "youtube_channel")
    private var youtubeChannel: String = ""

    @Column(nullable = false)
    private var hobby: String = ""

    // One-to-one with Instructor (owning side)
    @OneToOne(
        mappedBy = "instructorDetail",
        cascade = [
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
        ],
        orphanRemoval = true
    )
    var instructor: Instructor? = null

    constructor(youtubeChannel: String, hobby: String) {
        this.youtubeChannel = youtubeChannel
        this.hobby = hobby
    }

    override fun toString(): String {
        return "InstructorDetail(id=$id, youtubeChannel='$youtubeChannel', hobby='$hobby')"
    }

}