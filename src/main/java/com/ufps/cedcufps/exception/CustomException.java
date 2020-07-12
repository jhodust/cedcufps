package com.ufps.cedcufps.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
	
	private HttpStatus status;

	public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(String message) {
		super(message);
	}

	public CustomException(Throwable cause) {
		super(cause);
	}
	public CustomException(String msg,HttpStatus status) {
		super(msg);
		this.status=status;
	}

	private static final long serialVersionUID = 1L;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	

}
