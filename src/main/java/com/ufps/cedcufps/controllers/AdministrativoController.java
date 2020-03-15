package com.ufps.cedcufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@SessionAttributes("administrativo")
public class AdministrativoController {

	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/usuarios/administrativo/registro", method = RequestMethod.POST)
	public String save(Administrativo a, SessionStatus status) {
		a.setTipoPersona(personaService.findByTipoPersona("Administrativo"));
		personaService.save(a);
		status.setComplete();
		return "redirect:/usuarios";
	}
}