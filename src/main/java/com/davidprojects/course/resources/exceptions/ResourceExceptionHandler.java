package com.davidprojects.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.davidprojects.course.services.exceptions.DatabaseException;
import com.davidprojects.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

//Isso que vai interceptar as possíveis excecoes
@ControllerAdvice
public class ResourceExceptionHandler {

	//Com essa notation eu digo que meu metodo "resourceNotFound" vai capturar toda excecao do tipo ResourceNotFoundException
	// e vai fazer o tratamento
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		//status.value -> Retorna o inteiro, neste caso o 404
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}
}
