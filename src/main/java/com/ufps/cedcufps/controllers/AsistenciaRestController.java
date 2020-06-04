package com.ufps.cedcufps.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.services.IAsistenciaService;
import com.ufps.cedcufps.services.IJornadaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.utils.Archivo;

@RestController
public class AsistenciaRestController {
	
	@Autowired
	private IJornadaService jornadaService;
	
	@Autowired
	private IAsistenciaService asistenciaService;
	
	@Autowired
	private IParticipanteService participanteService;
	
	@GetMapping(value = "/asistencia-general-marcar/{id_jornada}",produces = "application/json")
	public ResponseEntity<?> marcarAsistenciaGeneral(@PathVariable(value = "id_jornada") Long id, Map<String, Object> model) {
		
		Jornada j= jornadaService.findOne(id).get();
		if(j!=null) {
			ArrayList <Asistencia> asistencias=new ArrayList<>();
			for(Participante p:j.getEducacionContinua().getParticipantes()) {
				Asistencia a=new Asistencia();
				a.setJornada(j);
				a.setParticipante(p);
				if(asistenciaService.findAsistenciaByJornadaAndParticipante(a.getJornada().getId(), a.getParticipante().getId())==null){
					asistencias.add(a);
				}
			}
			asistenciaService.saveAsistencias(asistencias);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/asistencia-general-desmarcar/{id_jornada}",produces = "application/json")
	public ResponseEntity<?> desmarcarAsistenciaGeneral(@PathVariable(value = "id_jornada") Long id, Map<String, Object> model) {
		
		Jornada j= jornadaService.findOne(id).get();
		if(j!=null) {
			asistenciaService.deleteAll(j.getAsistencias());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/asistencia-marcar/{id_jornada}/{id_participante}",produces = "application/json")
	public ResponseEntity<?> marcarAsistencia(@PathVariable(value = "id_jornada") Long idJornada,@PathVariable(value = "id_participante") Long idParticipante, Map<String, Object> model) {
		
		Asistencia a= new Asistencia();
		Jornada j= jornadaService.findOne(idJornada).get();
		Participante p=participanteService.findOne(idParticipante).get();
		if(j!=null && p != null) {
			a.setJornada(j);
			a.setParticipante(p);
			asistenciaService.save(a);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/asistencia-desmarcar/{id_jornada}/{id_participante}",produces = "application/json")
	public ResponseEntity<?> desmarcarAsistencia(@PathVariable(value = "id_jornada") Long idJornada,@PathVariable(value = "id_participante") Long idParticipante, Map<String, Object> model) {
		asistenciaService.deleteAsistencia(idJornada, idParticipante);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
