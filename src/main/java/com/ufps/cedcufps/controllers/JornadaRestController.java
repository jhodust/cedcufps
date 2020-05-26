package com.ufps.cedcufps.controllers;

import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IJornadaService;

@RestController
public class JornadaRestController {

	@Autowired
	private IJornadaService jornadaService;
	
	
	@PostMapping(value ="/educacion-continua/jornada/save")
	public ResponseEntity<?> guardarProgramaRest(@RequestBody @Valid Jornada jornada, BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		jornadaService.save(jornada);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/educacion-continua/jornada/search/{id}", produces = "application/json")
    public ResponseEntity<?> buscarJornada(@PathVariable Long id) {
		Jornada j=jornadaService.findOne(id).get();
        if(j==null) {
        	return new ResponseEntity<>("No se encontró la jornada",HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(j,HttpStatus.OK);
    }
	
}
