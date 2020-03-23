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
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IJornadaService;

@Controller
@SessionAttributes("jornada")
public class JornadaController {

	@Autowired
	private IJornadaService jornadaService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@RequestMapping(value = "/educacion-continua/{id}/jornadas")
	public String listar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		model.put("titulo","JORNADAS");
		model.put("educacionContinua",educacionContinuaService.findOne(id).get());
		return "educacion_continua/jornada/index";
	}
	
	/*@RequestMapping(value = "/educacion-continua/{id}/jornadas/registro")
	public String agregar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		EducacionContinua ec= educacionContinuaService.findOne(id).get();
		Jornada j= new Jornada(); 
		j.setEducacionContinua(ec);
		model.put("titulo","FORMULARIO JORNADA");
		model.put("jornada",j);
		return "educacion_continua/jornada/form";
	}
	
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
