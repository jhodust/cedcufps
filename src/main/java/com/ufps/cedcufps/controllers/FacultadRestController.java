package com.ufps.cedcufps.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IFacultadService;

@RestController
@RequestMapping(value = "/facultades")
public class FacultadRestController {

	@Autowired
	private IFacultadService facultadService;
	
	@RequestMapping(value = "/listar")
	public List<Facultad> listar() {
		return facultadService.findAll();
	}
	
	@GetMapping(value="/search/{id}", produces = "application/json")
    public ResponseEntity<Facultad> buscarPorFacultad(@PathVariable Long id) {
		
		return  new ResponseEntity<>(facultadService.findOne(id),HttpStatus.OK); 
    }
	
	@PostMapping(value = "/save")
	public ResponseEntity<?> guardarFacultadRest(@RequestBody Facultad facultad) {
		
		facultadService.save(facultad);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}