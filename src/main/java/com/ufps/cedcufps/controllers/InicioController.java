package com.ufps.cedcufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.cedcufps.services.IEducacionContinuaService;

@Controller
public class InicioController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@RequestMapping(value = "/")
	public String listar(Model model) {
		model.addAttribute("educacionesContinuas",educacionContinuaService.findAll());
		return "/index";
	}
	
	
}
