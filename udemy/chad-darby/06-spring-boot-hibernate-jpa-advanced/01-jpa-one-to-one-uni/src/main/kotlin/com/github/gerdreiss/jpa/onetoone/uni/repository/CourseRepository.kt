package com.github.gerdreiss.jpa.onetoone.uni.repository

import com.github.gerdreiss.jpa.onetoone.uni.entity.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Int>