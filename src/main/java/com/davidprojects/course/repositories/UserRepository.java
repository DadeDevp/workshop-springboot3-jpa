package com.davidprojects.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidprojects.course.entities.User;

//User = tipo de Entidade
//Long = tipo da chave primaria
public interface UserRepository extends JpaRepository<User, Long> {

}
