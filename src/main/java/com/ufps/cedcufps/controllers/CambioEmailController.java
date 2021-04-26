package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.cedcufps.SpringSecurityConfig;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.modelos.SessionWebGoogle;
import com.ufps.cedcufps.services.ICambiarEmailService;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
public class CambioEmailController {

	@Autowired
	private ICambiarEmailService cambiarEmailService;
	
	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/update-email/{token}")
	public String realizarSolicitudCambioEmail(@PathVariable(name = "token") String token,
			Map<String, Object> model) {
		model.put("titulo","FORMULARIO PERSONA");
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		//model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		model.put("propiedadesPerfiles",personaService.findPermisosRegistrarPersonas(0L,false));
		//model.put("departamentos",personaService.findAllDepartamentos());
		try {
			UsuarioDto p=this.cambiarEmailService.realizarSolicitudGenerada(token);
			model.put("persona", p);
			model.put("recoveryEmail",false);
		}catch(Exception e) {
			model.put("recoveryEmail",true);
			model.put("error", "El token para actualizar su información ha caducado o es inválido");
			model.put("persona", new UsuarioDto());
		}
		
		
		return "registrarse";
	}
}
