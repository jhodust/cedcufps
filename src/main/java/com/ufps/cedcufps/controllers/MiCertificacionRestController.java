package com.ufps.cedcufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dto.CertificacionDto;
import com.ufps.cedcufps.services.IParticipanteService;

@RestController
@RequestMapping("/certificaciones-educacion-continua")
public class MiCertificacionRestController {

	@Autowired
	private IParticipanteService participanteService;
	
	@GetMapping(value = "/search-certificacion",produces = "application/json")
	public ResponseEntity<CertificacionDto> searchCertificacion(@RequestParam(value = "token") String tokenParticipante) {
		return new ResponseEntity<>(participanteService.findCertificacionByToken(tokenParticipante),HttpStatus.OK);
	}
	
	@PostMapping(value = "/actualizarCertificado")
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
