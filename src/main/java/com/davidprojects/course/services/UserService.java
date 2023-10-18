package com.davidprojects.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidprojects.course.entities.User;
import com.davidprojects.course.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){	
		return repository.findAll();
	}
	
	public User findById(long id) {
		//o findById retorna um objeto do tipo optional, o metodo get do optional retorna o objeto dentro do optional
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	public User insert(User obj) {
		
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		
		//Cria um objeto monitorado através do getReferenceById(id)
		User entity = repository.getReferenceById(id);
		
		updateData(entity, obj);
		
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
	
}
