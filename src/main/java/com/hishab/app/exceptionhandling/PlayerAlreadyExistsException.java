package com.hishab.app.exceptionhandling;

//@ResponseStatus(value = HttpStatus.CONFLICT)
public class PlayerAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PlayerAlreadyExistsException() {
		super();
	}

	public PlayerAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlayerAlreadyExistsException(String message) {
		super(message);
	}

	public PlayerAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}