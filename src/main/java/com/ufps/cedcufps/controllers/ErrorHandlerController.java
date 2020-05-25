package com.ufps.cedcufps.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ufps.cedcufps.errores.ViolationResponse;

@ControllerAdvice
public class ErrorHandlerController {

	/*@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	public ViolationResponse handleConflictException(DataIntegrityViolationException ex) throws Exception {
	    return new ViolationResponse(ex.getMessage());
	}*/
}
