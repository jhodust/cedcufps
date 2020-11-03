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

import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IEducacionContinuaService;

@RestController
public class EducacionContinuaRestController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@PostMapping(value = "/educacion-continua/save")
	public ResponseEntity<?> save(@RequestParam(name="imagen", required=false) MultipartFile file, 
			@RequestParam String id, @RequestParam String nombre, @RequestParam String fechaInicio, 
			@RequestParam String fechaFin, @RequestParam String duracion, @RequestParam String cantMaxParticipantes,
			@RequestParam String fechaLimInscripcion, @RequestParam String costoInscripcion, @RequestParam String lugar,
			@RequestParam String costoEducacionContinua, @RequestParam String requisitos, @RequestParam String objetivo,
			@RequestParam String porcentajeAsistencia, @RequestParam String resumen, @RequestParam String contenidoGeneral,
			@RequestParam String idTipoEduContinua, @RequestParam String tipoEduContinua, @RequestParam String idProgramaResponsable,
			@RequestParam String idDocenteResponsable, @RequestParam String idClasificacionCine, @RequestParam(required = false) String consecutivo,
			@RequestParam String idTipoBeneficiarios) {
		
		educacionContinuaService.saveEducacionContinua(file, id, nombre, fechaInicio, fechaFin, duracion, cantMaxParticipantes,
				fechaLimInscripcion, costoInscripcion, lugar, costoEducacionContinua, requisitos, objetivo, 
				porcentajeAsistencia, resumen, contenidoGeneral, idTipoEduContinua, tipoEduContinua, idProgramaResponsable, 
				idDocenteResponsable, idClasificacionCine, consecutivo, idTipoBeneficiarios);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/educacion-continua/search-base")
	public ResponseEntity<?> save(@RequestParam(name="idEducacionContinua", required=true)  String id) {
		
		
		return new ResponseEntity<>(educacionContinuaService.findEducacionContinuaBase(Long.parseLong(id)),HttpStatus.OK);
	}
}
