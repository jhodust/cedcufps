package com.ufps.cedcufps.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.services.IAsistenciaService;
import com.ufps.cedcufps.services.IJornadaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.utils.Archivo;

@RestController
@RequestMapping(value = "/educacion-continua/attendance")
public class AsistenciaRestController {
	
	@Autowired
	private IAsistenciaService asistenciaService;
	
	
	@GetMapping(value = "/general/check",produces = "application/json")
	public ResponseEntity<?> marcarAsistenciaGeneral(@RequestParam(value = "id_jornada",required = true) Long id) {
		asistenciaService.saveAsistencias(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/general/uncheck",produces = "application/json")
	public ResponseEntity<?> desmarcarAsistenciaGeneral(@RequestParam(value = "id_jornada",required = true) Long id) {
		
			asistenciaService.deleteAsistenciasByJornada(id);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/check",produces = "application/json")
	public ResponseEntity<?> marcarAsistencia(@RequestParam(value = "id_jornada",required = true) Long idJornada,
			@RequestParam(value = "id_participante",required = true) Long idParticipante,
			@RequestParam(value = "cant_participantes",required = true) String cantParticipantes) {
		
			return new ResponseEntity<>(asistenciaService.marcarAsistencia(idJornada, idParticipante,Integer.parseInt(cantParticipantes)),HttpStatus.OK);
	}
	
	@GetMapping(value = "/uncheck",produces = "application/json")
	public ResponseEntity<?> desmarcarAsistencia(@RequestParam(value = "id_jornada",required = true) Long idJornada,
			@RequestParam(value = "id_participante",required = true) Long idParticipante) {
		asistenciaService.deleteAsistencia(idJornada, idParticipante);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
