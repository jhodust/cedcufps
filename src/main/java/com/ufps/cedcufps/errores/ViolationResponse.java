package com.ufps.cedcufps.errores;

public class ViolationResponse {

	private String message;

	public ViolationResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}