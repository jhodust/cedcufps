package com.ufps.cedcufps.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@SessionAttributes("educacionContinua")
public class EducacionContinuaController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IParticipanteService participanteService;
	
	@RequestMapping(value = "/educacion-continua/listar")
	public String listar(Model model) {
		model.addAttribute("titulo","EDUCACIÓN CONTINUA");
		model.addAttribute("educacionesContinuas",educacionContinuaService.findAll());
		return "educacion_continua/index";
	}
	
	@RequestMapping(value = "/educacion-continua/registro")
	public String agregar(Map<String, Object> model) {
		EducacionContinua ec= new EducacionContinua(); 
		model.put("titulo","FORMULARIO EDUCACIÓN CONTINUA");
		model.put("educacionContinua",ec);
		model.put("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinua());
		return "educacion_continua/form";
	}
	
	@RequestMapping(value = "/educacion-continua/registro", method = RequestMethod.POST)
	public String save(EducacionContinua ec, SessionStatus status) {
		educacionContinuaService.save(ec);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/educacion-continua/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Optional<EducacionContinua> ec= educacionContinuaService.findOne(id); 
		model.put("titulo","FORMULARIO EDUCACIÓN CONTINUA");
		model.put("educacionContinua",ec.get());
		model.put("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinua());
		return "educacion_continua/form";
	}
	
	@RequestMapping(value = "/educacion-continua/{id}/detalles")
	public String mostrar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Optional<EducacionContinua> ec= educacionContinuaService.findOne(id); 
		model.put("titulo","DETALLES EDUCACIÓN CONTINUA");
		model.put("educacionContinua",ec.get());
		try {
		model.put("participante",participanteService.findByIdEducacionContinuaAndIdPersona(id,personaService.findPersonaLogueada().getId()));
		}catch(Exception e) {
			model.put("participante",null);
		}
		return "educacion_continua/detalles";
	}
	
	
}
