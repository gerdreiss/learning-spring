package com.github.gerdreiss.cruddemo.dao

import com.github.gerdreiss.cruddemo.entity.Employee
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class EmployeeDAOJpaImpl(val entityManager: EntityManager) : EmployeeDAO {

    override fun findAll(): List<Employee> =
        this.entityManager
            .createQuery("from Employee", Employee::class.java)
            .resultList

    override fun findById(id: Int): Employee? =
        this.entityManager.find(Employee::class.java, id)

    override fun save(employee: Employee): Employee? {
        return this.entityManager.merge(employee)
    }

    override fun delete(id: Int) {
        // this.entityManager
        //     .createQuery("delete from Employee where id=:id", Int::class.java)
        //     .setParameter("id", id)
        //     .executeUpdate()
        this.entityManager
            .find(Employee::class.java, id)
            .let {
                this.entityManager.remove(it)
            }
    }

}