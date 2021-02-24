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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.PonenteDto;
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
@RequestMapping(value ="/educacion-continua/ponentes")
public class PonenteRestController {

	@Autowired
	private IParticipanteService participanteService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	/*@GetMapping(value = "/educacion-continua/{id}/ponentes", produces = "application/json")
	public List<Participante> listarPonentes(@PathVariable Long id) {
		return participanteService.findAllPonentesOfOneEducacionContinuaById(id);
	}*/
	
	@GetMapping(value="/search/{id}", produces = "application/json")
    public ResponseEntity<PonenteDto> buscarPonente(@PathVariable Long id) {
		 return new ResponseEntity<>(participanteService.findPonente(id),HttpStatus.OK);
    }
	
	
	
	
	@PostMapping(value = "/save")
	public ResponseEntity<?> guardarPonenteRest(@RequestBody  Ponente ponente) {
		/*if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(),HttpStatus.BAD_REQUEST);
		}*/
		System.out.println("va a guardar ponente");
		ParticipanteDto dto = participanteService.savePonente(ponente);
		System.out.println("dtooooooooooooooo");
		System.out.println(dto);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@GetMapping(value="/delete")
    public ResponseEntity<?> deletePonente(@RequestParam(value = "id",required = true) String idParticipante) {
		participanteService.deleteParticipante(Long.parseLong(idParticipante));
		return new ResponseEntity<>("Se ha eliminado el ponente exitosamente",HttpStatus.OK);
		
    }
	
	@GetMapping(value = "/filtro", produces = "application/json")
	public ResponseEntity<?> findPossiblePonentes(@RequestParam(value = "tipo_busqueda",required = false) String tipoBusqueda, 
			@RequestParam(value = "value",required = false) String valor) {
		
		return new ResponseEntity<>(personaService.findPossiblePonente(Integer.parseInt(tipoBusqueda), valor),HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/persona", produces = "application/json")
	public ResponseEntity<PersonaDto> findPersona(@RequestParam(value = "id",required = true) String id) {
		return new ResponseEntity<>(personaService.findOne(Long.parseLong(id)),HttpStatus.OK);
	}

}
