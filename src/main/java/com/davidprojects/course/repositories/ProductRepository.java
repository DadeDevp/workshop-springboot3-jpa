package com.davidprojects.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidprojects.course.entities.Product;

//User = tipo de Entidade
//Long = tipo da chave primaria
public interface ProductRepository extends JpaRepository<Product, Long> {

}
