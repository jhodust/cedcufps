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

import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.InformeSnies;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IInformeSniesService;
import com.ufps.cedcufps.utils.ReportesExcel;

@Controller
@SessionAttributes("informeSnies")
public class ReportesSniesController {

	
	
	@Autowired
	private IInformeSniesService informeSniesService;
	
	
	
	@RequestMapping(value = "/reportes-SNIES")
	public String listar(Map<String, Object> model) {
		model.put("informesSnies",informeSniesService.findAll());
		return "reportes-snies/index";
	}
	
	
	
	
	
	
}
