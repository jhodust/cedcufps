package com.ufps.cedcufps.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.modelos.Departamento;
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
	
	
	
	@GetMapping(value="/programa/search/{id}", produces = "application/json")
    public ResponseEntity<?> buscarPorPrograma(@PathVariable Long id) {
		Programa p=programaService.findOne(id).get();
		if(p==null) {
			return new ResponseEntity<>("No se encontró el Programa Académico",HttpStatus.BAD_REQUEST);
		}
		return  new ResponseEntity<>(p,HttpStatus.OK);
    }
	
	

	@PostMapping(value = "/programa/save")
	public ResponseEntity<?> guardarProgramaRest(@RequestBody @Valid Programa programa,BindingResult result) {
		if(result.hasErrors()) {
			
			return new ResponseEntity<>(result.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		programaService.save(programa);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
