package com.ufps.cedcufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.UsuarioAppDto;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IPersonaService;

@RestController
@RequestMapping(value = "/perfil")
public class PerfilRestController {

	@Autowired
	private IPersonaService personaService;
	
	@PostMapping(value = "/updateSuperAdmin")
	public ResponseEntity<?> actualizarSuperAdmin(@RequestParam String documento) {
		
		personaService.updateSuperAdmin(documento);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/updateDirPrograma")
	public ResponseEntity<?> actualizarDirPrograma(@RequestParam String documento) {
		
		personaService.updateDirPrograma(documento);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
