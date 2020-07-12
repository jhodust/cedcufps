package com.ufps.cedcufps.exception;

public class ResponseDto<T> {

	private T data;
	private String message;
	private StackTraceElement[] trace;
	public ResponseDto(T data) {
		this.data = data;
	}

	public ResponseDto(T data, String message) {
		this.data = data;
		this.message = message;
	}

	public static <T> ResponseDto<T> ok(T data, String mensaje) {
		return new ResponseDto(data,mensaje);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StackTraceElement[] getTrace() {
		return trace;
	}

	public void setTrace(StackTraceElement[] trace) {
		this.trace = trace;
	}
	
	

}