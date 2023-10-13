package com.davidprojects.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidprojects.course.entities.Order;
import com.davidprojects.course.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll(){	
		return repository.findAll();
	}
	
	public Order findById(long id) {
		//o findById retorna um objeto do tipo optional, o metodo get do optional retorna o objeto dentro do optional
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
	
}
