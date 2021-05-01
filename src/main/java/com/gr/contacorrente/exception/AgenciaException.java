package com.gr.contacorrente.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class AgenciaException extends RuntimeException {

	private static final long serialVersionUID = 4600049395620898008L;

	public AgenciaException(String message) {
		super(message);
	}
	
	public AgenciaException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
