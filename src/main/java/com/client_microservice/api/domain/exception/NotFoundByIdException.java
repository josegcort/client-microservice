package com.client_microservice.api.domain.exception;

public class NotFoundByIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundByIdException(Long valueId) {
		super("No se encontro el registro con el id: " + valueId);
	}
}