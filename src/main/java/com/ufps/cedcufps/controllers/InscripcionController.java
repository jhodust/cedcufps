package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.cedcufps.SpringSecurityConfig;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;

@Controller
public class InscripcionController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IParticipanteService participanteService;
	
	
	
	@RequestMapping(value = "/preinscripcion")
	public String preinscripcionEducacionContinua(@RequestParam(name = "educacionContinua") String nombreEvento,
			@RequestParam(name = "fecha") String fechaEvento, @RequestParam(name = "id") String idAcceso,
			Map<String, Object> model) {
		//EducacionContinua ec= educacionContinuaService.findOneByNombreAndFecha(nombreEvento,fechaEvento);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		model.put("titulo","DETALLES EDUCACIÓN CONTINUA");
		model.put("requisitosInscripcion",educacionContinuaService.consultarRequisitosInscripcion(idAcceso));
		//System.out.println(ec.getId());
		//model.put("listTipoPersonaValidInscripcion",educacionContinuaService.tiposPersonaParaInscripcion(ec.getTipoBeneficiarios()));
		//try {
		//model.put("participante",participanteService.findByIdEducacionContinuaAndIdPersona(ec.getId(),personaService.findPersonaLogueada().getId()));
		//}catch(Exception e) {
		//	model.put("participante",null);
		//}
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "preinscripcion";
	}
	
	@RequestMapping(value = "/participaciones-educacion-continua")
	public String eventosActivosParticipante( Map<String, Object> model) {
		model.put("participaciones",participanteService.findAllParticipacionesActivasByParticipante());
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "educacion_continua/tarjetas_inscripcion/index";
	}
	
	
}
