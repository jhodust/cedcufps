package com.ufps.cedcufps.controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.Asistente;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.utils.Archivo;
import com.ufps.cedcufps.utils.CodigoQR;
import com.ufps.cedcufps.utils.Encrypt;

@RestController
public class AsistenteRestController {

	@Autowired
	private IParticipanteService participanteService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	
	@GetMapping(value = "/realizar-inscripcion/{id_evento}",produces = "application/json")
	public ResponseEntity<ParticipanteDto> realizarInscripcion(@PathVariable(value = "id_evento") Long id, Map<String, Object> model) {
		
		return ResponseEntity.ok(participanteService.saveAsistente(id));
	}
	
	@PostMapping(value = "/realizar-inscripcion/generar-tarjeta-inscripcion" ,produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> guardarTarjetaInscripcion(MultipartFile file, String idParticipante){
		System.out.println("imagen: " + file.getName());
		participanteService.saveTarjetaInscripcion(file, Long.parseLong(idParticipante));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/cancelar-inscripcion/{id_evento}",produces = "application/json")
	public ResponseEntity<Participante> cancelarInscripcion(@PathVariable(value = "id_evento") Long id, Map<String, Object> model) {
		
		//Archivo.deleteImage(p.getImagenQr());
		//Archivo.deleteImage(p.getTarjetaInscripcion());
		participanteService.cancelarInscripcion(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
