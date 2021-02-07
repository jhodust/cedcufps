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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IProgramaService;

@RestController
@RequestMapping("/programas-academicos")
public class ProgramaRestController {

	@Autowired
	private IProgramaService programaService;
	
	@RequestMapping(value = "/listar")
	public List<Programa> listar() {
		return programaService.findAll();
	}
	
	
	@GetMapping(value="/search/{id}", produces = "application/json")
    public ResponseEntity<ProgramaDto> buscarPorPrograma(@PathVariable Long id) {
		
		return  new ResponseEntity<>(programaService.searchProgramaById(id),HttpStatus.OK);
    }
	
	
	@PostMapping(value = "/save")
	public ResponseEntity<?> guardarProgramaRest(@RequestBody Programa programa) {
		programaService.save(programa);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/search-director", produces = "application/json")
    public ResponseEntity<ProgramaDto> buscarDirectorPrograma(@RequestParam(name = "id", required = true) Long idDir, @RequestParam(name = "idP", required = true) Long idPrograma) {
		
		return  new ResponseEntity<>(programaService.findProgramaByDirector(idDir,idPrograma),HttpStatus.OK);
    }
	

}
