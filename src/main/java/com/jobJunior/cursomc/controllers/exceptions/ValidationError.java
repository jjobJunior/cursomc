package com.jobJunior.cursomc.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> messageErrors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timesTamp, Integer status, String messageError) {
		super(timesTamp, status, messageError);
	}

	public List<FieldMessage> getErrors() {
		return messageErrors;
	}

	public void addErrors(String fieldName, String message) {
		this.messageErrors.add(new FieldMessage(fieldName, message));
	}

}
