package com.alura.forum.services.exceptions;

public class ServiceDatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceDatabaseException(String msg) {
		super(msg);
	}
}
