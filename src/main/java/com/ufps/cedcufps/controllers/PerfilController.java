package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.cedcufps.SpringSecurityConfig;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.services.IProgramaService;

@Controller
@RequestMapping(value = "/perfil")
public class PerfilController {

	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IProgramaService programaService;
	
	
	@RequestMapping
	public String perfil(Map<String, Object> model) {
		UsuarioDto dto= personaService.findMyInfo();
		model.put("persona", dto);
		model.put("programa", programaService.findProgramaByDirector(dto.getId()));
		model.put("newSuperAdmin", null);
		model.put("newDirPrograma", null);
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "persona/perfil";
	}
	
	@RequestMapping(value = "/editar")
	public String editar(Map<String, Object> model) {
		model.put("titulo","FORMULARIO PERSONA");
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		//model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		//model.put("departamentos",personaService.findAllDepartamentos());
		UsuarioDto dto = personaService.findMyInfo();
		model.put("persona", dto);
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		model.put("propiedadesPerfiles",personaService.findPermisosRegistrarPersonas(dto.getId(),false));
		return "persona/updatePerfil";
	}
	
	@RequestMapping(value = "/search-new-superadmin/{documento}")
	public String buscarPosibleSuperAdmin(@PathVariable(value = "documento") String documento, Map<String, Object> model) {
		model.put("newSuperAdmin",personaService.findUsuarioAppByDocumento(documento));
		return "persona/perfil :: divDatosNewAdmin";
	}
	
	@RequestMapping(value = "/search-new-dirprograma/{documento}")
	public String buscarPosibleDirPrograma(@PathVariable(value = "documento") String documento, Map<String, Object> model) {
		model.put("newDirPrograma",personaService.findUsuarioAppByDocumento(documento));
		return "persona/perfil :: divDatosNewDirPrograma";
	}
}
