package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.cedcufps.SpringSecurityConfig;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@RequestMapping(value = "/perfil")
public class PerfilController {

	@Autowired
	private IPersonaService personaService;
	
	
	@RequestMapping
	public String perfil(Map<String, Object> model) {
		model.put("persona", personaService.findMyInfo());
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "persona/perfil";
	}
	
	@RequestMapping(value = "/editar")
	public String editar(Map<String, Object> model) {
		model.put("titulo","FORMULARIO PERSONA");
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		model.put("departamentos",personaService.findAllDepartamentos());
		model.put("persona", personaService.findMyInfo());
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "persona/updatePerfil";
	}
}
