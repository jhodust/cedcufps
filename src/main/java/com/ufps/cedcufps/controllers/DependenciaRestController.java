package com.ufps.cedcufps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.dto.DependenciaDto;
import com.ufps.cedcufps.modelos.Dependencia;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IDependenciaService;
import com.ufps.cedcufps.services.IFacultadService;


@RestController
@RequestMapping(value = "/dependencias")
public class DependenciaRestController {

	@Autowired
	private IDependenciaService dependenciaService;
	
	@RequestMapping(value = "/listar")
	public List<DependenciaDto> listar() {
		return dependenciaService.listAll();
	}
	
	@GetMapping(value="/search/{id}", produces = "application/json")
    public ResponseEntity<DependenciaDto> buscarDependencia(@PathVariable Long id) {
		
		return  new ResponseEntity<>(dependenciaService.findById(id),HttpStatus.OK); 
    }
	
	@PostMapping(value = "/save")
	public ResponseEntity<?> guardarDependenciaRest(@RequestBody DependenciaDto dependencia) {
		
		dependenciaService.save(dependencia);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}