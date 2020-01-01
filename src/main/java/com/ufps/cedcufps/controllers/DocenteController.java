package com.ufps.cedcufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.services.IPersonaService;


@Controller
@SessionAttributes("docente")
public class DocenteController {

	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/persona/docente/registro", method = RequestMethod.POST)
	public String save(Docente d, SessionStatus status) {
		d.setTipoPersona(personaService.findByTipoPersona("Docente"));
		personaService.save(d);
		status.setComplete();
		return "redirect:/persona/listar";
	}
}
