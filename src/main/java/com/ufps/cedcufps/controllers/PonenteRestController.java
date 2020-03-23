package com.ufps.cedcufps.controllers;

import java.util.List;
import java.util.Map;

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
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IProgramaService;

@RestController
public class PonenteRestController {

	@Autowired
	private IParticipanteService participanteService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@GetMapping(value = "/educacion-continua/{id}/ponentes", produces = "application/json")
	public List<Participante> listarPonentes(@PathVariable Long id) {
		return participanteService.findAllPonentesOfOneEducacionContinua(id);
	}
	
	@GetMapping(value="/educacion-continua/ponente/search/{id}", produces = "application/json")
    public Participante buscarPonente(@PathVariable Long id) {
		return  participanteService.findParticipante(id);
    }
	
	
	
	
	@PostMapping(value = "/educacion-continua/ponente/save")
	public String guardarPonenteRest(@RequestBody Ponente ponente) {

		participanteService.save(ponente);

		return "sisas";
	}
	
	@PostMapping(value="/educacion-continua/ponente/delete")
    public String deletePonente(@RequestBody Ponente ponente) {
		participanteService.deleteParticipante(ponente);
		 return "sisas";
		
    }

}
