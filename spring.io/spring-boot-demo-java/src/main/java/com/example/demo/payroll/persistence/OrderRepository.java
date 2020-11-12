package com.example.demo.payroll.persistence;

import com.example.demo.payroll.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}