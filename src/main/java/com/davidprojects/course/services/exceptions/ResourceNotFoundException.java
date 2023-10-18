package com.davidprojects.course.services.exceptions;

//RunTimeException excecao que o compilador nao te obriga a tratar
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
}
