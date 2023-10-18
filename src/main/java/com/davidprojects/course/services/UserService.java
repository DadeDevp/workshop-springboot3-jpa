package com.davidprojects.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.davidprojects.course.entities.User;
import com.davidprojects.course.repositories.UserRepository;
import com.davidprojects.course.services.exceptions.DatabaseException;
import com.davidprojects.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(long id) {
		// o findById retorna um objeto do tipo optional, o metodo get do optional
		// retorna o objeto dentro do optional
		Optional<User> obj = repository.findById(id);

		/*
		 * orElseThrow vou tentar fazr o obj.get() que irá retorna um objeto do tipo
		 * User, mas se nao tiver nada, ou seja nao existir um User com esse ID, devo
		 * entao lançar a excecao personalizada
		 */
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {

		return repository.save(obj);
	}

	public void delete(Long id) {

		 /*
		 	O metodo "deleteById" nao lança mais excecao quando o objeto do id nao existe,
		 	portanto neste caso temos que verificar se o objeto do id existe com o metodo "existsById"
		 */
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		/*
			Por outro lado o "deleteById" pode lançar um DataIntegrityViolationException por isso devemos usar o try-catch
			para capturar a excecao
		 */
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public User update(Long id, User obj) {

		// Cria um objeto monitorado através do getReferenceById(id)
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
