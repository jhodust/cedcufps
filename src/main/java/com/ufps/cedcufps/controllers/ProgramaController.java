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

import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IProgramaService;

@Controller
@SessionAttributes("programa")
public class ProgramaController {

	@Autowired
	private IProgramaService programaService;
	
	@RequestMapping(value = "/programa/listar")
	public String listar(Model model) {
		model.addAttribute("titulo","PROGRAMAS");
		model.addAttribute("programas",programaService.findAll());
		return "programa/index";
	}
	
	@RequestMapping(value = "/programa/registro")
	public String agregar(Map<String, Object> model) {
		Programa t= new Programa(); 
		model.put("titulo","FORMULARIO PROGRAMAS");
		model.put("programa",t);
		return "programa/form";
	}
	
	@RequestMapping(value = "/programa/registro", method = RequestMethod.POST)
	public String save(Programa p, SessionStatus status) {
		programaService.save(p);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/programa/registro/{id}")
	public String editar(@PathVariable(value = "id") String id, Map<String, Object> model) {
		Optional<Programa> p= programaService.findOne(id); 
		model.put("titulo","FORMULARIO PROGRAMAS");
		model.put("programa",p.get());
		return "programa/form";
	}
}
