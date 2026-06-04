package com.github.gerdreiss.jpa.onetoone.uni.repository

import com.github.gerdreiss.jpa.onetoone.uni.entity.Instructor
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class CustomRepository(val entityManager: EntityManager) {
    fun findInstructorJoinFetchCoursesById(id: Int): Instructor =
        this.entityManager
            .createQuery(
                "select i from Instructor i JOIN FETCH i.courses where i.id = :id",
                Instructor::class.java
            )
            .setParameter("id", id)
            .singleResult
}