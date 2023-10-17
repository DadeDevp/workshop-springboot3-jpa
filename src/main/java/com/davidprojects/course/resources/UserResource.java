package com.davidprojects.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.davidprojects.course.entities.User;
import com.davidprojects.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	// Criar um metodo get no endpoint /users
	@GetMapping
	public ResponseEntity<List<User>> findAll() {

		List<User> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	// criar um endpoint get que recebe como parametro um id
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) { //Colocar o @PathVariable para indicar que o parametro vem da url requiscao
		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Post
	//Header Content-Type application/json
	@PostMapping
	public ResponseEntity <User> insert(@RequestBody User obj){
		 obj = service.insert(obj);
		 
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				 .buildAndExpand(obj.getId()).toUri();
		 
		 //Para retornar O cod 201 - Created precisa como parametro a uri do obj recém criado, para isso criamos um URI na linha acima
		 return ResponseEntity.created(uri).body(obj);
		 
	}
}
