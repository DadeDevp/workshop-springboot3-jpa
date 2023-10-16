package com.davidprojects.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidprojects.course.entities.Product;
import com.davidprojects.course.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){	
		return repository.findAll();
	}
	
	public Product findById(long id) {
		//o findById retorna um objeto do tipo optional, o metodo get do optional retorna o objeto dentro do optional
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	
}
