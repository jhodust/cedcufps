package com.ufps.cedcufps.controllers;

import java.util.List;
import java.util.Map;

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
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Programa;
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
    public ResponseEntity<?> buscarPorFacultad(@PathVariable Long id) {
        
        return  new ResponseEntity<>(departamentoService.findOne(id),HttpStatus.OK);
    }
	
	
	

	@PostMapping(value = "/departamento/save")
	public ResponseEntity<?> guardarDepartamentoRest(@RequestBody @Valid Departamento departamento, BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		departamentoService.save(departamento);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
