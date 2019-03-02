package com.br.cldelias.services.exceptions;

public class ValidationFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ValidationFieldException(String msg) {
		super(msg);
	}
	
	public ValidationFieldException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
