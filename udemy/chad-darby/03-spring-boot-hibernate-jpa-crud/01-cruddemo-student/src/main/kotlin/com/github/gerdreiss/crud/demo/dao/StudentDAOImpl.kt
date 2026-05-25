package com.github.gerdreiss.crud.demo.dao

import com.github.gerdreiss.crud.demo.entity.Student
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class StudentDAOImpl : StudentDAO {
    private val entityManager: EntityManager

    @Autowired
    constructor(entityManager: EntityManager) {
        this.entityManager = entityManager
    }

    @Transactional
    override fun save(student: Student) {
        entityManager.persist(student)
    }
}