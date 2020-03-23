package com.ufps.cedcufps.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

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
	public String guardarProgramaRest(@RequestBody Jornada jornada) {

		jornadaService.save(jornada);

		return "sisas";
	}
	
	@GetMapping(value="/educacion-continua/jornada/search/{id}", produces = "application/json")
    public Jornada buscarJornada(@PathVariable Long id) {
		return  jornadaService.findOne(id).get();
    }
	
}
