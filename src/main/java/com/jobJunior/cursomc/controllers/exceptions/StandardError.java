package com.jobJunior.cursomc.controllers.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timesTamp;
	private Integer status;
	private String messageError;

	public StandardError() {
		super();
	}

	public StandardError(Long timesTamp, Integer status, String messageError) {
		super();
		this.timesTamp = timesTamp;
		this.status = status;
		this.messageError = messageError;
	}

	public Long getTimesTamp() {
		return timesTamp;
	}

	public void setTimesTamp(Long timesTamp) {
		this.timesTamp = timesTamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return messageError;
	}

	public void setError(String messageError) {
		this.messageError = messageError;
	}

}
