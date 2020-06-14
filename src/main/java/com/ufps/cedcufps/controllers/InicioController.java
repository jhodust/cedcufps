package com.ufps.cedcufps.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.utils.ReportesExcel;
import com.ufps.cedcufps.utils.paginator.PageRender;

@Controller
public class InicioController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	
	
	@RequestMapping(value = "/")
	public String listar(@RequestParam(name="page", defaultValue = "0") int page,Model model) {
		Pageable pageRequest=PageRequest.of(page, 3);
		Page<EducacionContinua> edc=educacionContinuaService.educacionContinuaNoTerminadas(pageRequest);
		PageRender<EducacionContinua> pageRender= new PageRender<EducacionContinua>("/", edc);
		model.addAttribute("educacionesRecientes",educacionContinuaService.educacionContinuaRecientes());
		model.addAttribute("educacionesContinuas",edc);
		model.addAttribute("page",pageRender);
		//educacionContinuaService.generarReporteSNIESEducacionContinua(new Date(), 0);
		return "/index";
	}
	
	@RequestMapping(value = "/dashboard")
	public String listar(Model model) {
		
		return "reportes-snies/dashboard";
	}
	
	
	
	
	
	
}
