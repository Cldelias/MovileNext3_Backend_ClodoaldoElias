package com.br.cldelias.resources.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.cldelias.enums.EnumPatternFormatDate;
import com.br.cldelias.services.exceptions.DataIntegrityException;
import com.br.cldelias.services.exceptions.ObjectNotFoundException;
import com.br.cldelias.services.exceptions.ValidationFieldException;

import br.com.cldelias.utils.DateUtil;


@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(this.getDateFormat(), HttpStatus.NOT_FOUND.value(), "Not found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getStandarError(e, request, "Data integrity"));
	}
	
	@ExceptionHandler(ValidationFieldException.class)
	public ResponseEntity<StandardError> dataIntegrity(ValidationFieldException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getStandarError(e, request, "Validation error"));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(this.getDateFormat(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error", e.getMessage(), request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	private StandardError getStandarError(RuntimeException e, HttpServletRequest request, String message) {
		return new StandardError(this.getDateFormat(), HttpStatus.BAD_REQUEST.value(), message, e.getMessage(), request.getRequestURI());
		
	}
	
	private String getDateFormat() {
		return DateUtil.formatLocalDateTime(LocalDateTime.now(), EnumPatternFormatDate.FORMAT_01);
	}

}
