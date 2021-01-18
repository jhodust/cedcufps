package com.ufps.cedcufps.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IJornadaService;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@SessionAttributes("jornada")
public class JornadaController {

	@Autowired
	private IJornadaService jornadaService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	
	
	@RequestMapping(value = "/educacion-continua/{id}/jornadas")
	public String reloadListJornadas(@PathVariable(value = "id") Long idEducacionContinua, Model model) {
		
		model.addAttribute("jornadas",jornadaService.findAllByIdEducacionContinua(idEducacionContinua));
		return "educacion_continua/jornada/index :: indexJornadas";
	}
	
	@RequestMapping(value = "/educacion-continua/detalles/{id}/jornadas")
	public String reloadListJornadasDetallesEducacionContinua(@PathVariable(value = "id") Long idEducacionContinua, Model model) {
		
		model.addAttribute("jornadas",jornadaService.findAllByIdEducacionContinua(idEducacionContinua));
		return "educacion_continua/detalles :: detallesEcJornadas";
	}
	
	/*
	@RequestMapping(value = "/educacion-continua/jornadas/registro", method = RequestMethod.POST)
	public String save(Jornada j, SessionStatus status) {
		jornadaService.save(j);
		status.setComplete();
		String url="/educacion-continua/"+j.getEducacionContinua().getId()+"/jornadas";
		return "redirect:"+url;
	}
	
	
	
	@RequestMapping(value = "/educacion-continua/{id_ec}/jornadas/registro/{id_jornada}")
	public String editar(@PathVariable(value = "id_ec") Long idEducacionContinua, @PathVariable(value = "id_jornada") Long idJornada, Map<String, Object> model) {
		Optional<Jornada> j= jornadaService.findOne(idJornada); 
		model.put("titulo","FORMULARIO JORNADA");
		model.put("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinua());
		model.put("jornada",j.get());
		return "educacion_continua/jornada/form";
	}*/
}
