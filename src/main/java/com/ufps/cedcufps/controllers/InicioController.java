package com.ufps.cedcufps.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.services.IEducacionContinuaService;

@Controller
public class InicioController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	
	
	@RequestMapping(value = "/")
	public String listar(Model model) {
		List<EducacionContinua> edc = educacionContinuaService.educacionContinuaRecientes();
		EducacionContinua [] ec = new EducacionContinua[5];
		for(int i=0; i<ec.length; i++) {
			ec[i]=edc.get(i);
		}
		model.addAttribute("educacionesContinuas",educacionContinuaService.findAll());
		model.addAttribute("educacionesRecientes",ec);
		return "/index";
	}
	
	
}
