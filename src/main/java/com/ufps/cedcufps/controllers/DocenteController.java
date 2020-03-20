package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IPersonaService;


@Controller
@SessionAttributes("docente")
public class DocenteController {

	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/usuarios/docente/registro")
	public String agregar(Map<String, Object> model) {
		Docente d= new Docente();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("docente",d);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("departamentos",personaService.findAllDepartamentos());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/formRegistroDocente";
	}
	
	@RequestMapping(value = "/usuarios/docente/registro", method = RequestMethod.POST)
	public String save(Docente d, SessionStatus status) {
		d.setTipoPersona(personaService.findByTipoPersona("Docente"));
		personaService.save(d);
		Rol r= new Rol();
		r.setAuthority("Docente");
		d.addRol(r);
		status.setComplete();
		return "redirect:/usuarios";
	}
}
