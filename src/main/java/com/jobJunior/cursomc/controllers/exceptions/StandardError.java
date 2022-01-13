package com.jobJunior.cursomc.controllers.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timesTamp;
	private Integer status;
	private String error;

	public StandardError() {
		super();
	}

	public StandardError(Long timesTamp, Integer status, String error) {
		super();
		this.timesTamp = timesTamp;
		this.status = status;
		this.error = error;
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
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
