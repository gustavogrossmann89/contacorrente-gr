package com.gr.contacorrente.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ContaCorrenteException extends RuntimeException {

	private static final long serialVersionUID = 2421939789162433112L;

	public ContaCorrenteException(String message) {
		super(message);
	}
	
	public ContaCorrenteException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
