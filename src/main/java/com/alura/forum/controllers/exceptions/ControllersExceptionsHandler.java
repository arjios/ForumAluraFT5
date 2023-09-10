package com.alura.forum.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alura.forum.services.exceptions.ServiceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllersExceptionsHandler {

	@ExceptionHandler(ServiceNotFoundException.class)
	public ResponseEntity<StandardErrors> entityNotFound(ServiceNotFoundException snfe,
															HttpServletRequest hsr) {
		StandardErrors se = new StandardErrors();
		se.setTimestamp(Instant.now());
		se.setStatus(HttpStatus.NOT_FOUND.value());
		se.setError("Recurso não encontrado.");
		se.setMessage(snfe.getMessage());
		se.setPath(hsr.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
	}
}
