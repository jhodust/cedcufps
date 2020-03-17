package com.ufps.cedcufps.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ufps.cedcufps.modelos.Facultad;
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
		model.put("facultad",new Facultad());//para cuando el filtro es todos
		return "departamento/index";
	}
	
	@RequestMapping(value = "/departamentos-academicos/filter/{facultad}")
	public String filtrarByFacultad(@PathVariable(value = "facultad") String facultad, Map<String, Object> model) {
		model.put("titulo","PROGRAMAS");
		model.put("departamentos",departamentoService.findByFacultad(facultad));
		model.put("facultad",facultadService.findByFacultad(facultad));
		model.put("facultades",facultadService.findAll());
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("facultad " + facultadService.findByFacultad(facultad).getFacultad());
		return "departamento/index";
	}
	
	

}
