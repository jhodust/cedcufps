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

import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IProgramaService;

@RestController
public class ProgramaRestController {

	@Autowired
	private IProgramaService programaService;
	
	@RequestMapping(value = "/programas-academicos/listar")
	public List<Programa> listar(Map<String, Object> model) {
		return programaService.findAll();
	}
	
	@GetMapping(value="/programa/{id}", produces = "application/json")
    public Optional<Programa> programass(@PathVariable Long id) {
        Optional<Programa> p= programaService.findOne(id); 
        return p;
    }
	
	

	@PostMapping(value = "/programa/save")
	public String guardarProgramaRest(@RequestBody Programa programa) {

		programaService.save(programa);

		return "sisas";
	}

}
