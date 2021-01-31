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
	public ResponseEntity<?> nuevoUsuarioEstudiante(@RequestBody UsuarioDto u) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!**********************************************************");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!**********************************************************");
		System.out.println(u.isEstudiante());
		System.out.println(u.isDocente());
		System.out.println(u.isAdministrativo());
		System.out.println(u.isGraduado());
		System.out.println(u.isExterno());
		personaService.guardar(u);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
