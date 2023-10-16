package com.davidprojects.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidprojects.course.entities.OrderItem;

//User = tipo de Entidade
//Long = tipo da chave primaria
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
