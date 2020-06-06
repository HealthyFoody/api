package com.healthyfoody.exception;

public class InvalidOperationException extends BusinessException {

	public InvalidOperationException() {
		super("Invalid operation");
	}
	
	public InvalidOperationException(String message) {
		super(message);
	}
	
	public InvalidOperationException(String message, Throwable cause) {
		super(message, cause);
	}

}
