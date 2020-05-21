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
public class FacultadRestController {

	@Autowired
	private IFacultadService facultadService;
	
	@RequestMapping(value = "/facultades/listar")
	public List<Facultad> listar(Map<String, Object> model) {
		return facultadService.findAll();
	}
	
	@GetMapping(value="/facultad/search/{id}", produces = "application/json")
    public ResponseEntity<?> buscarPorFacultad(@PathVariable Long id) {
		Facultad f=facultadService.findOne(id).get();
		if(f==null) {
			return new ResponseEntity<>("No se encontró el Programa Académico",HttpStatus.BAD_REQUEST);
		}
		return  new ResponseEntity<>(f,HttpStatus.OK); 
    }
	
	
	
	@PostMapping(value = "/facultad/save")
	public ResponseEntity<?> guardarFacultadRest(@RequestBody @Valid Facultad facultad, BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		facultadService.save(facultad);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}