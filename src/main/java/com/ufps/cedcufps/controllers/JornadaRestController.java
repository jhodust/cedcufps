package com.ufps.cedcufps.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IJornadaService;

@RestController
@RequestMapping(value ="/educacion-continua/jornadas")
public class JornadaRestController {

	@Autowired
	private IJornadaService jornadaService;
	
	
	@PostMapping(value ="/save")
	public ResponseEntity<?> guardarProgramaRest(@RequestBody @Valid Jornada jornada, BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		jornadaService.save(jornada);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/search/{id}", produces = "application/json")
    public ResponseEntity<?> buscarJornada(@PathVariable Long id) {
		
        return  new ResponseEntity<>(jornadaService.findOne(id),HttpStatus.OK);
    }
	
	@GetMapping(value="/delete")
    public ResponseEntity<?> deleteJornada(@RequestParam(value = "id",required = true) String idJornada) {
		jornadaService.deleteJornada(Long.parseLong(idJornada));
		return new ResponseEntity<>("Se ha eliminado la jornada exitosamente",HttpStatus.OK);
		
    }
	
	
}
