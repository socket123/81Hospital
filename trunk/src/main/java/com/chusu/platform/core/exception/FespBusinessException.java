package com.chusu.platform.core.exception;


public class FespBusinessException extends FespException {

	private static final long serialVersionUID = 1L;

	public FespBusinessException() {
		super();
	}

	public FespBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public FespBusinessException(String message) {
		super(message);
	}

	public FespBusinessException(Throwable cause) {
		super(cause);
	}
}
