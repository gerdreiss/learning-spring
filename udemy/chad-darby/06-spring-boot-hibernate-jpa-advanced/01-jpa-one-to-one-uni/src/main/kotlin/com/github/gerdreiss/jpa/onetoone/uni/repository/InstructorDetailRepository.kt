package com.github.gerdreiss.jpa.onetoone.uni.repository

import com.github.gerdreiss.jpa.onetoone.uni.entity.InstructorDetail
import org.springframework.data.jpa.repository.JpaRepository

interface InstructorDetailRepository : JpaRepository<InstructorDetail, Int>