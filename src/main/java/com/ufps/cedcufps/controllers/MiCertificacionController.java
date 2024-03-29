package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.SpringSecurityConfig;
import com.ufps.cedcufps.modelos.SessionWebGoogle;
import com.ufps.cedcufps.services.IParticipanteService;

@Controller
@RequestMapping("/certificaciones-educacion-continua")
public class MiCertificacionController {

	@Autowired
	private IParticipanteService participanteService;
	
	@GetMapping
	public String certificacionesParticipante( Map<String, Object> model) {
		model.put("participaciones",participanteService.findCertificaciones());
		SessionWebGoogle session=SpringSecurityConfig.getInfoSession();
		if(session!=null) {
			model.put("photoUser", session.getPhoto());
			model.put("nameUser", session.getName());
		}
		return "educacion_continua/certificados_asistentes/mis_certificaciones";
	}
}
