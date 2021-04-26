package com.ufps.cedcufps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ufps.cedcufps.services.ICambiarEmailService;

@RestController
public class CambioEmailRestController {

	@Autowired
	private ICambiarEmailService cambiarEmailService;
	
	@RequestMapping(value = "/change-email")
	public ResponseEntity<?> guardarSolicitudCambioEmail(@RequestParam(name = "documento", required = true) String documento,
			@RequestParam(name = "email", required = true) String email) {
		this.cambiarEmailService.guardarSolicitudCambioEmail(documento, email);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
