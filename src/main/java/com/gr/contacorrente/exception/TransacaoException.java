package com.gr.contacorrente.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class TransacaoException extends RuntimeException {

	private static final long serialVersionUID = 5955649891901189625L;

	public TransacaoException(String message) {
		super(message);
	}
	
	public TransacaoException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
