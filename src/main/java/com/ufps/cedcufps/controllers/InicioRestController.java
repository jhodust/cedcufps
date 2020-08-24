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
public class InicioRestController {

	@Autowired
	private IPersonaService personaService;
	
	@PostMapping(value = "/registrarse",produces = "application/json")
	public ResponseEntity<?> nuevoUsuarioEstudiante(@RequestBody UsuarioDto u) {
		System.out.println("Entra al metodo");
		System.out.println(u.getIsEstudiante());
		System.out.println(u.getIsDocente());
		System.out.println(u.getIsAdministrativo());
		System.out.println(u.getIsGraduado());
		System.out.println(u.getIsExterno());
		personaService.registrarse(u);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}