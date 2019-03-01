package com.br.cldelias.resources.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private LocalDateTime dateTime;
	private String error;
	private String message;
	private String path;
	
	public StandardError(LocalDateTime dateTime, Integer status, String error, String message, String path) {
		super();
		this.dateTime = dateTime;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
