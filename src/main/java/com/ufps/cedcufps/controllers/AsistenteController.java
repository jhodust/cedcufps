package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.cedcufps.modelos.Asistente;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
public class AsistenteController {

	@Autowired
	private IParticipanteService participanteService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/educacion-continua/{id}/realizar-inscripcion")
	public String realizarInscripcion(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		EducacionContinua ec= educacionContinuaService.findOne(id).get();
		Asistente a= new Asistente(); 
		a.setEducacionContinua(ec);
		a.setTipoParticipante(participanteService.findByTipoParticipante("Asistente"));
		a.setPersona(personaService.findPersonaLogueada());
		participanteService.save(a);
		String url="/educacion-continua/"+id+"/detalles";
		return "redirect:"+url;
	}
	
	@RequestMapping(value = "/educacion-continua/{id}/cancelar-inscripcion")
	public String cancelarInscripcion(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		participanteService.deleteParticipante(participanteService.findByIdEducacionContinuaAndIdPersona(id, personaService.findPersonaLogueada().getId()));
		String url="/educacion-continua/"+id+"/detalles";
		return "redirect:"+url;
	}
}
