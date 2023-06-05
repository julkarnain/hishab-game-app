package com.hishab.app.exceptionhandling;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlayerOverflowException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PlayerOverflowException() {
		super();
	}

	public PlayerOverflowException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlayerOverflowException(String message) {
		super(message);
	}

	public PlayerOverflowException(Throwable cause) {
		super(cause);
	}
}