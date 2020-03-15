package com.ufps.cedcufps.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.ufps.cedcufps.services.IDepartamentoService;
import com.ufps.cedcufps.services.IFacultadService;

@Controller
@SessionAttributes("departamento")
public class DepartamentoController {

	@Autowired
	private IDepartamentoService departamentoService;
	
	@Autowired
	private IFacultadService facultadService;
	
	@RequestMapping(value = "/departamentos-academicos")
	public String listar(Map<String, Object> model) {
		model.put("titulo","PROGRAMAS");
		model.put("departamentos",departamentoService.findAll());
		model.put("facultades",facultadService.findAll());
		return "departamento/index";
	}
	
	

}
