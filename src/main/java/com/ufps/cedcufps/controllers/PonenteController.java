package com.ufps.cedcufps.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@SessionAttributes("ponente")
public class PonenteController {
	
	@Autowired
	private IParticipanteService participanteService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;

	@RequestMapping(value = "/educacion-continua/{id}/ponentes")
	public String listar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		model.put("titulo","PONENTES");
		model.put("educacionContinua",educacionContinuaService.findOne(id).get());
		
	    model.put("persona",personaService.findPersonaLogueada());
		return "educacion_continua/ponente/index";
	}
	
	@RequestMapping(value = "/educacion-continua/{id}/ponentes/registro")
	public String agregar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		EducacionContinua ec= educacionContinuaService.findOne(id).get();
		Ponente p= new Ponente(); 
		p.setEducacionContinua(ec);
		model.put("titulo","FORMULARIO PONENTE");
		model.put("ponente",p);
		model.put("personas",personaService.findAllPersonas());
		return "educacion_continua/ponente/form";
	}
	
	@RequestMapping(value = "/educacion-continua/ponentes/registro", method = RequestMethod.POST)
	public String save(Ponente p, SessionStatus status) {
		p.setTipoParticipante(participanteService.findByTipoParticipante("Ponente"));
		participanteService.save(p);
		status.setComplete();
		String url="/educacion-continua/"+p.getEducacionContinua().getId()+"/ponentes";
		return "redirect:"+url;
	}
	
	
	
	@RequestMapping(value = "/educacion-continua/{id_ec}/ponentes/registro/{id_ponente}")
	public String editar(@PathVariable(value = "id_ec") Long idEducacionContinua, @PathVariable(value = "id_ponente") Long idPonente, Map<String, Object> model) {
		Optional<Participante> p= participanteService.findOne(idPonente); 
		model.put("titulo","FORMULARIO PONENTE");
		model.put("personas",personaService.findAllPersonas());
		model.put("ponente",p.get());
		return "educacion_continua/ponente/form";
	}
}
