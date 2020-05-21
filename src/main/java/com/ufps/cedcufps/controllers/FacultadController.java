package com.ufps.cedcufps.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.services.IProgramaService;
import com.ufps.cedcufps.utils.paginator.PageRender;

@Controller
@SessionAttributes("facultad")
public class FacultadController {

	@Autowired
	private IFacultadService facultadService;
	
	@RequestMapping(value = "/facultades")
	public String listar(@RequestParam(name="page", defaultValue = "0") int page, Map<String, Object> model) {
		Pageable pageRequest=PageRequest.of(page, 2);
		Page<Facultad> facultades=facultadService.findAll(pageRequest);
		PageRender<Facultad> pageRender= new PageRender<Facultad>("/facultades", facultades);
		model.put("facultades",facultades);	
		model.put("facultadesTotales",facultadService.findAll());	
		model.put("page",pageRender);	
		return "facultad/index";
	}
	
	

}
