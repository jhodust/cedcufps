package com.ufps.cedcufps.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufps.cedcufps.dto.CertificacionDto;
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
	
	
	@GetMapping(value = "/realizar-inscripcion",produces = "application/json")
	public ResponseEntity<ParticipanteDto> realizarInscripcion(@RequestParam(name = "idEduContinua") String idEduContinua, 
			@RequestParam(name = "idTipoPersona",required = false,defaultValue = "0") String idTipoPersona, Map<String, Object> model) {
		
		return ResponseEntity.ok(participanteService.saveAsistente(Long.parseLong(idEduContinua),Long.parseLong(idTipoPersona)));
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
	
	@PostMapping(value = "/educacion-continua/certificarParticipacion")
	@ResponseBody
	public ResponseEntity<?> certificarParticipante(MultipartFile file, String tokenParticipante, String idEduContinua, String documentoParticipante) {
		System.out.println("imagen filename: " + file.getName());
		System.out.println(tokenParticipante);
		System.out.println(idEduContinua);
		System.out.println(documentoParticipante);
		participanteService.certificarParticipante(file, Long.parseLong(idEduContinua), tokenParticipante, documentoParticipante);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/educacion-continua/visualizar-diploma","/certificaciones-educacion-continua/visualizar"}, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> verDiplomaParticipante(@RequestParam(name = "token") String token,
    		 Map<String, Object> model, RedirectAttributes redirectAttributes) {

        ByteArrayInputStream bis = participanteService.generarPdfDiplomas(token);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=certificado-asistencia.pdf");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@PostMapping(value = "/educacion-continua/cancelarCertificacionParticipacion")
	@ResponseBody
	public ResponseEntity<?> cancelarCertificacionParticipante(String tokenParticipante) {
		System.out.println(tokenParticipante);
		participanteService.cancelarCertificacionParticipante(tokenParticipante);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/educacion-continua/search-certificacion",produces = "application/json")
	public ResponseEntity<CertificacionDto> searchCertificacion(@RequestParam(value = "token") String tokenParticipante) {
		return new ResponseEntity<>(participanteService.findCertificacionByToken(tokenParticipante),HttpStatus.OK);
	}
	
	@PostMapping(value = "/educacion-continua/actualizarCertificado")
	@ResponseBody
	public ResponseEntity<?> updateCertificacionParticipante(MultipartFile file, String filename, String tokenParticipante, 
			String idEduContinua, String documentoParticipante) {
		System.out.println("UPDATTTTTTTTTTTTEEEEEEEEEEEEEEEEEE CERRRRRRRRRRTTTTTTTTTTTTTIIIIIIIIIIFFFFFFFFFFIIIIIIIICAAAAACCCCCIIIIONNNNNNN");
		System.out.println("imagen filename: " + file.getName());
		System.out.println(tokenParticipante);
		System.out.println(idEduContinua);
		System.out.println(documentoParticipante);
		participanteService.updateCertificado(file, filename, tokenParticipante, Long.parseLong(idEduContinua), documentoParticipante);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
