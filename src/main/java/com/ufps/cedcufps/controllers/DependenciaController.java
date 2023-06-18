package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ufps.cedcufps.SpringSecurityConfig;
import com.ufps.cedcufps.modelos.Dependencia;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.SessionWebGoogle;
import com.ufps.cedcufps.services.IDependenciaService;
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.utils.paginator.PageRender;

@Controller
@SessionAttributes("dependencia")
@RequestMapping(value = "/dependencias")
public class DependenciaController {

	@Autowired
	private IDependenciaService dependenciaService;
	
	@RequestMapping
	public String listar(Map<String, Object> model) {
		Pageable pageRequest=PageRequest.of(0, 16);
		Page<Dependencia> dependencias=dependenciaService.listAll(pageRequest);
		PageRender<Dependencia> pageRender= new PageRender<Dependencia>("/dependencias/reload", dependencias);
		model.put("dependencias",dependenciaService.listAll(pageRequest));	
		model.put("dependenciasTotales",dependenciaService.listAll());	
		model.put("page",pageRender);
		SessionWebGoogle session=SpringSecurityConfig.getInfoSession();
		if(session!=null) {
			model.put("photoUser", session.getPhoto());
			model.put("nameUser", session.getName());
		}
		return "dependencia/index";
	}
	
	
	@RequestMapping(value = "/reload")
	public String reloadList(@RequestParam(name="page", defaultValue = "0") int page, Map<String, Object> model) {
		Pageable pageRequest=PageRequest.of(page, 16);
		Page<Dependencia> dependencias=dependenciaService.listAll(pageRequest);
		PageRender<Dependencia> pageRender= new PageRender<Dependencia>("/dependencias/reload", dependencias);
		model.put("dependencias",dependencias);	
		model.put("dependenciasTotales",dependenciaService.listAll());	
		model.put("page",pageRender);
		return "dependencia/index :: listDependencias";
	}
}
