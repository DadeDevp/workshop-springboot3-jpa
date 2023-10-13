package com.davidprojects.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidprojects.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
