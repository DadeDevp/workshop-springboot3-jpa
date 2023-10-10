package com.davidprojects.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidprojects.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
