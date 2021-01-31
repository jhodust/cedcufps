package com.ufps.cedcufps.controllers;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufps.cedcufps.services.IParticipanteService;

@RestController
public class CertificadoRestController {

	@Autowired
	private IParticipanteService participanteService;
	
	@RequestMapping(value = {"/educacion-continua/visualizar-diploma","/certificaciones-educacion-continua/visualizar-diploma"}, method = RequestMethod.GET,
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
}
