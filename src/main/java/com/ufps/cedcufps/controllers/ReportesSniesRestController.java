package com.ufps.cedcufps.controllers;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.modelos.InformeSnies;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IInformeSniesService;

@RestController
public class ReportesSniesRestController {

	
	
	@Autowired
	private IInformeSniesService informeSniesService;
	
	
	
	@GetMapping(value = "/reportes-SNIES/generar")
	public ResponseEntity<?> informeExcel(@RequestParam(name="fechaInicio", required=true) String fechaInicio, 
			@RequestParam(name="fechaFin", required=true) String fechaFin) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$444");
		System.out.println(fechaInicio);
		System.out.println(fechaFin);
		informeSniesService.generarReporteSNIES(fechaInicio, fechaFin);
		//i.setInformeCurso(informeSniesService.generarReporteSNIESFormatoCurso(fechaInicio, fechaFin));
		//i.setInformeEducacionContinua(informeSniesService.generarReporteSNIESFormatoEducacionContinua(fechaInicio, fechaFin));
		//informeSniesService.save(i);
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
