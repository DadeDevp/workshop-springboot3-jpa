package com.davidprojects.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidprojects.course.entities.Product;
import com.davidprojects.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	// Criar um metodo get no endpoint /users
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {

		List<Product> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	// criar um endpoint get que recebe como parametro um id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) { //Colocar o @PathVariable para indicar que o parametro vem da url requiscao
		
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
