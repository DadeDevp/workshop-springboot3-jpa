package com.davidprojects.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidprojects.course.entities.Category;

//User = tipo de Entidade
//Long = tipo da chave primaria
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
