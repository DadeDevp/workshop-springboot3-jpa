package com.davidprojects.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidprojects.course.entities.Category;
import com.davidprojects.course.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	// Criar um metodo get no endpoint /users
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {

		List<Category> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	// criar um endpoint get que recebe como parametro um id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) { //Colocar o @PathVariable para indicar que o parametro vem da url requiscao
		
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
