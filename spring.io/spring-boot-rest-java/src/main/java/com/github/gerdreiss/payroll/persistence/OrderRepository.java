package com.github.gerdreiss.payroll.persistence;

import com.github.gerdreiss.payroll.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}