package com.ufps.cedcufps.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ufps.cedcufps.SpringSecurityConfig;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.InformeSnies;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IInformeSniesService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.services.IProgramaService;
import com.ufps.cedcufps.utils.ReportesExcel;

@Controller
@SessionAttributes("informeSnies")
@RequestMapping(value = "/reportes-SNIES")
public class ReportesSniesController {

	
	
	@Autowired
	private IInformeSniesService informeSniesService;
	
	@Autowired
	private IProgramaService programaService;
	
	@Autowired
	private IPersonaService personaService;
	
	
	@RequestMapping
	public String reportesSnies(Map<String, Object> model) {
		model.put("informesSnies",informeSniesService.findAll());
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "reportes-snies/index";
	}
	
	
	
	@RequestMapping(value = "/dashboard")
	public String dashboard(Map<String, Object> model) {
		
		Persona p= this.personaService.findPersonaLogueada();
		model.put("programas",programaService.findAllProgramasDashboard(p));
		model.put("isSuperAdmin", this.personaService.isSuperAdmin(p));
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "reportes-snies/dashboard";
	}
	
	
	
	
}
