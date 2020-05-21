package com.ufps.cedcufps.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IDepartamentoService;
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.utils.paginator.PageRender;

@Controller
@SessionAttributes("departamento")
public class DepartamentoController {

	@Autowired
	private IDepartamentoService departamentoService;
	
	@Autowired
	private IFacultadService facultadService;
	
	@RequestMapping(value = "/departamentos-academicos")
	public String listar(@RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="facultad", defaultValue = "") String facultad,Map<String, Object> model) {
		
		Pageable pageRequest=PageRequest.of(page, 8);
		
		if(facultad.equalsIgnoreCase("")) {
			Page<Departamento> departamentos=departamentoService.findAll(pageRequest);
			PageRender<Departamento> pageRender= new PageRender<Departamento>("/departamentos-academicos", departamentos);
			model.put("departamentos",departamentos);	
			model.put("page",pageRender);
			model.put("departamentosTotales",departamentoService.findAll());
			model.put("facultad",new Facultad());//para cuando el filtro es todos
		}else {
			Page<Departamento> departamentos=departamentoService.findByFacultad(facultad, pageRequest);
			PageRender<Departamento> pageRender= new PageRender<Departamento>("/departamentos-academicos", departamentos);
			model.put("departamentos",departamentos);
			model.put("page",pageRender);
			model.put("departamentosTotales",departamentoService.findByFacultad(facultad));
			model.put("facultad",facultadService.findByFacultad(facultad));
		}
		model.put("facultades",facultadService.findAll());
		return "departamento/index";
	}
	
	/*
	@RequestMapping(value = "/departamentos-academicos/filter/{facultad}")
	public String filtrarByFacultad(@PathVariable(value = "facultad") String facultad, Map<String, Object> model) {
		model.put("titulo","PROGRAMAS");
		model.put("departamentos",departamentoService.findByFacultad(facultad));
		model.put("facultad",facultadService.findByFacultad(facultad));
		model.put("facultades",facultadService.findAll());
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("facultad " + facultadService.findByFacultad(facultad).getFacultad());
		return "departamento/index";
	}*/
	
	

}
