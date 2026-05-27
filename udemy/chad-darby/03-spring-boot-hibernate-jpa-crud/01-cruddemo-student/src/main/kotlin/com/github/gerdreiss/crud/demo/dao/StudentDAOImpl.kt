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

    override fun findById(id: Int): Student? {
        return entityManager.find(Student::class.java, id)
    }

    override fun findAll(): List<Student> {
        return entityManager
            .createQuery("from Student", Student::class.java)
            .resultList
    }

    override fun findByLastName(name: String): List<Student> {
        return entityManager
            .createQuery("from Student where lastName = :data", Student::class.java)
            .setParameter("data", name)
            .resultList
    }

    @Transactional
    override fun update(student: Student) {
        entityManager.merge(student)
    }

    @Transactional
    override fun delete(student: Student) {
//        this.entityManager.remove(student)
        this.entityManager
            .createQuery("delete from Student where id = :data")
            .setParameter("data", student.id)
            .executeUpdate()
    }

    @Transactional
    override fun deleteAll(): Int {
        return this.entityManager.createQuery("delete from Student").executeUpdate()
    }

}
