package com.jobJunior.cursomc.service.exceptions;

public class AuthorizationExcepton extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthorizationExcepton(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorizationExcepton(String message) {
		super(message);
	}

}
