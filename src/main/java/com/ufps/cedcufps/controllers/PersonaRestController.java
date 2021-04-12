package com.ufps.cedcufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.services.IPersonaService;

@RestController
public class PersonaRestController {

	@Autowired
	private IPersonaService personaService;
	
	@PostMapping(value = "/registrarse",produces = "application/json")
	public ResponseEntity<?> nuevoUsuario(@RequestBody UsuarioDto u) {
		
		personaService.guardar(u);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
