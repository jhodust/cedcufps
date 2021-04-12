package com.ufps.cedcufps.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufps.cedcufps.dto.DiplomaDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IDiplomaService;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;

@RestController
@RequestMapping(value = "/educacion-continua")
public class EducacionContinuaRestController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IDiplomaService diplomaService;
	
	@Autowired
	private IParticipanteService participanteService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestParam(name="imagen", required=false) MultipartFile file, 
			@RequestParam String id, @RequestParam String nombre, @RequestParam String fechaInicio, 
			@RequestParam String fechaFin, @RequestParam String duracion, @RequestParam(required = false) String cantMaxParticipantes,
			@RequestParam String fechaLimInscripcion, @RequestParam(required = false) String costoInscripcion, @RequestParam String lugar,
			@RequestParam String costoEducacionContinua, @RequestParam String porcentajeAsistencia, 
			@RequestParam String infoAdicional,
			@RequestParam String idTipoEduContinua, @RequestParam String tipoEduContinua, @RequestParam String idProgramaResponsable,
			@RequestParam String idDocenteResponsable, @RequestParam String idClasificacionCine, @RequestParam(required = false) String consecutivo,
			@RequestParam String idTipoBeneficiarios) {
		
		educacionContinuaService.saveEducacionContinua(file, id, nombre, fechaInicio, fechaFin, duracion, cantMaxParticipantes,
				fechaLimInscripcion, costoInscripcion, lugar, costoEducacionContinua, 
				porcentajeAsistencia, infoAdicional, idTipoEduContinua, tipoEduContinua, idProgramaResponsable, 
				idDocenteResponsable, idClasificacionCine, consecutivo, idTipoBeneficiarios);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/search-base")
	public ResponseEntity<EducacionContinuaWebDto> searchEducacionContinuaBase(@RequestParam(name="nombreEdC", required=true)  String nombreEducacionContinua) {
		return new ResponseEntity<>(educacionContinuaService.findEducacionContinuaBase(nombreEducacionContinua),HttpStatus.OK);
	}
	
	@GetMapping(value = "/search-educaciones-continuas-base")
	public ResponseEntity<List<String>> searchListEducacionesContinuasByPrograma(@RequestParam(name="idPrograma", required=true)  String id) {
		return new ResponseEntity<>(educacionContinuaService.findEducacionesContinuasBaseByIdPrograma(Long.parseLong(id)),HttpStatus.OK);
	}
	
	@PostMapping(value = "/delete")
	public ResponseEntity<?> delete(@RequestParam(name="idAcceso") String idAcceso) {
		
		educacionContinuaService.deleteEducacionContinua(idAcceso);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/generar-estructura-diploma")
	public ResponseEntity<Long> guardarDiplomaRest(@RequestBody DiplomaDto diploma) {
		
		return new ResponseEntity<>(diplomaService.save(diploma), HttpStatus.OK);
	}
	
	@PostMapping(value = "/certificarParticipacion")
	@ResponseBody
	public ResponseEntity<?> certificarParticipante(MultipartFile file, String tokenParticipante, String idEduContinua, String documentoParticipante) {
		
		participanteService.certificarParticipante(file, Long.parseLong(idEduContinua), tokenParticipante, documentoParticipante);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/cancelarCertificacionParticipacion")
	@ResponseBody
	public ResponseEntity<?> cancelarCertificacionParticipante(String tokenParticipante) {
		
		participanteService.cancelarCertificacionParticipante(tokenParticipante);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
