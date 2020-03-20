package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@SessionAttributes("externo")
public class ExternoController {

	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/usuarios/externo/registro")
	public String agregar(Map<String, Object> model) {
		Externo e= new Externo();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("externo",e);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/formRegistroExterno";
	}
	
	@RequestMapping(value = "/usuarios/externo/registro", method = RequestMethod.POST)
	public String save(Externo e, SessionStatus status) {
		e.setTipoPersona(personaService.findByTipoPersona("Externo"));
		personaService.save(e);
		status.setComplete();
		return "redirect:/usuarios";
	}
}