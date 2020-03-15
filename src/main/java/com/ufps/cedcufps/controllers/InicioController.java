package com.ufps.cedcufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ufps.cedcufps.services.IEducacionContinuaService;

@Controller
public class InicioController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@GetMapping(value = "/home")
	public String login(Model model) {
		model.addAttribute("titulo","EDUCACIÓN CONTINUA");
		model.addAttribute("educacionesContinuas",educacionContinuaService.findAll());
		return "layouts/layout3";
	}
	
	
	
}
