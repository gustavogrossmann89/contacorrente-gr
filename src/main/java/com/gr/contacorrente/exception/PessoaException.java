package com.gr.contacorrente.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PessoaException extends RuntimeException {

	private static final long serialVersionUID = -4524532849154838656L;

	public PessoaException(String message) {
		super(message);
	}
	
	public PessoaException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
