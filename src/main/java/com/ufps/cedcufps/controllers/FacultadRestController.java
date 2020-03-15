package com.ufps.cedcufps.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IFacultadService;

@RestController
public class FacultadRestController {

	@Autowired
	private IFacultadService facultadService;
	
	@RequestMapping(value = "/facultades/listar")
	public List<Facultad> listar(Map<String, Object> model) {
		return facultadService.findAll();
	}
	
	@GetMapping(value="/facultad/search/{id}", produces = "application/json")
    public Facultad buscarPorFacultad(@PathVariable Long id) {
        return facultadService.findOne(id).get();
    }
	
	@PostMapping(value = "/facultad/save")
	public String guardarFacultadRest(@RequestBody Facultad facultad) {
		facultadService.save(facultad);
		return "sisas";
	}
}