package com.chusu.platform.core.exception;


public class FespException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public FespException() {
		super();
	}

	public FespException(String message, Throwable cause) {
		super(message, cause);
	}

	public FespException(String message) {
		super(message);
	}

	public FespException(Throwable cause) {
		super(cause);
	}
	
}

