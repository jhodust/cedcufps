package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.modelos.Asistente;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.services.IRolService;
import com.ufps.cedcufps.utils.CodigoQR;
import com.ufps.cedcufps.utils.Encrypt;


@RestController
public class RoleRestController {

	@Autowired
	private IRolService roleService;
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping(value = "/asignar-role/{role}/{id_persona}",produces = "application/json")
	public ResponseEntity<?> asignarRole(@PathVariable(value = "role") String role,@PathVariable(value = "id_persona") Long idPersona, Map<String, Object> model) {
		Persona p= personaService.findOne(idPersona).get();
		if(p!=null) {
			Rol r=new Rol();
			r.setAuthority(role);
			p.addRol(r);
			personaService.save(p);
		}
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/quitar-role/{role}/{id_persona}",produces = "application/json")
	public ResponseEntity<?> quitarRole(@PathVariable(value = "role") String role,@PathVariable(value = "id_persona") Long idPersona, Map<String, Object> model) {
		roleService.deleteRol(role, idPersona);
		return  new ResponseEntity<>(HttpStatus.OK);
	}
}
