package com.ufps.cedcufps.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IDepartamentoService;
import com.ufps.cedcufps.services.IFacultadService;


@RestController
public class DepartamentoRestController {

	@Autowired
	private IDepartamentoService departamentoService;
	
	@Autowired
	private IFacultadService facultadService;
	
	@RequestMapping(value = "/departamentos/listar")
	public List<Departamento> listar(Map<String, Object> model) {
		return departamentoService.findAll();
	}
	
	@GetMapping(value="/departamento/search/{id}", produces = "application/json")
    public Departamento buscarPorFacultad(@PathVariable Long id) {
        return departamentoService.findOne(id).get(); 
    }
	
	

	@PostMapping(value = "/departamento/save")
	public String guardarDepartamentoRest(@RequestBody Departamento departamento) {
		departamentoService.save(departamento);
		return "sisas";
	}
}
