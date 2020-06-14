package com.ufps.cedcufps.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.modelos.InformeSnies;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IInformeSniesService;

@RestController
public class ReportesSniesRestController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IInformeSniesService informeSniesService;
	
	
	
	@PostMapping(value = "/reportes-SNIES/generar")
	public ResponseEntity<?> informeExcel(@RequestBody @Valid InformeSnies i,BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		
		i.setInformeCurso(educacionContinuaService.generarReporteSNIESFormatoCurso(i.getAnio()));
		i.setInformeEducacionContinua(educacionContinuaService.generarReporteSNIESFormatoEducacionContinua(i.getAnio()));
		informeSniesService.save(i);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/*CREATE EVENT test_event_02
ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 MINUTE
ON COMPLETION PRESERVE
DO
   UPDATE educacion_continua
        SET activo = '0'
        WHERE fecha_inicio < (select now());*/
}
