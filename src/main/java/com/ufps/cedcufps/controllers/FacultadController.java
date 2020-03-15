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
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.services.IProgramaService;

@Controller
@SessionAttributes("facultad")
public class FacultadController {

	@Autowired
	private IFacultadService facultadService;
	
	@RequestMapping(value = "/facultades")
	public String listar(Map<String, Object> model) {
		model.put("facultades",facultadService.findAll());	
		return "facultad/index";
	}
	
	

}
