package com.ufps.cedcufps.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IProgramaService;

@Controller
@SessionAttributes("programa")
public class ProgramaController {

	@Autowired
	private IProgramaService programaService;
	
	@RequestMapping(value = "/programas-academicos")
	public String listar(Map<String, Object> model) {
		model.put("titulo","PROGRAMAS");
		model.put("programas",programaService.findAll());
		//Programa t= new Programa(); 
		//model.put("programa",t);		
		return "programa/index";
	}
	
	/*@RequestMapping(value = "/programas-academicos/registro")
	public String agregar(Map<String, Object> model) {
		Programa t= new Programa(); 
		model.put("titulo","FORMULARIO PROGRAMAS");
		model.put("programa",t);
		return "programa/form";
	}
	
	@RequestMapping(value = "/programas-academicos/registro", method = RequestMethod.POST)
	public String save(Programa p, SessionStatus status) {
		programaService.save(p);
		status.setComplete();
		return "redirect:/programas-academicos";
	}
	
	@RequestMapping(value = "/programas-academicos/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Optional<Programa> p= programaService.findOne(id); 
		model.put("titulo","FORMULARIO PROGRAMAS");
		model.put("programa",p.get());
		return "programa/form";
	}
	
*/
}
