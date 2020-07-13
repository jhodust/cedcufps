package com.ufps.cedcufps.controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.TipoParticipante;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.services.IProgramaService;
import com.ufps.cedcufps.utils.CodigoQR;
import com.ufps.cedcufps.utils.Encrypt;

@RestController
public class PonenteRestController {

	@Autowired
	private IParticipanteService participanteService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping(value = "/educacion-continua/{id}/ponentes", produces = "application/json")
	public List<Participante> listarPonentes(@PathVariable Long id) {
		return participanteService.findAllPonentesOfOneEducacionContinua(id);
	}
	
	@GetMapping(value="/educacion-continua/ponente/search/{id}", produces = "application/json")
    public Participante buscarPonente(@PathVariable Long id) {
		return  participanteService.findParticipante(id);
    }
	
	
	
	
	@PostMapping(value = "/educacion-continua/ponente/save")
	public ResponseEntity<?> guardarPonenteRest(@RequestBody @Valid Ponente ponente, BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		participanteService.savePonente(ponente);
		return new ResponseEntity<>(participanteService.findByIdEducacionContinuaAndIdPersona(ponente.getEducacionContinua().getId(), ponente.getPersona().getId()),HttpStatus.OK);
	}
	
	@PostMapping(value="/educacion-continua/ponente/delete")
    public ResponseEntity<?> deletePonente(@RequestBody Ponente ponente) {
		participanteService.deleteParticipante(ponente);
		return new ResponseEntity<>("Se ha eliminado el ponente exitosamente",HttpStatus.OK);
		
    }

}
