package com.ufps.cedcufps.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ufps.cedcufps.dto.InformeSniesDto;
import com.ufps.cedcufps.modelos.InformeSnies;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IInformeSniesService;

@RestController
@RequestMapping(value = "/reportes-SNIES")
public class ReportesSniesRestController {

	
	
	@Autowired
	private IInformeSniesService informeSniesService;
	
	
	
	@PostMapping(value = "/generar")
	public ResponseEntity<?> informeExcel(@RequestBody InformeSniesDto informeSniesDto) {
		informeSniesService.generarReporteSNIES(informeSniesDto.getFechaInicio(), informeSniesDto.getFechaFin(), informeSniesDto.getDescripcion());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/dashboard/totalGeneralEduContinua")
	public ResponseEntity<List<Object[]>> estadisticasConteoGeneralEduContinua(@RequestParam(name="fechaInicio", required=true) String fechaInicio, 
			@RequestParam(name="fechaFin", required=true) String fechaFin, @RequestParam(name="idPrograma", required=false) String idPrograma) {
		return new ResponseEntity<>(informeSniesService.generarStatisticsConteoGeneralEduContinua(fechaInicio, fechaFin,idPrograma),HttpStatus.OK);
	}
	
	@GetMapping(value = "/dashboard/totalGeneralEduContinuaPersonas")
	public ResponseEntity<List<Object[]>> estadisticasConteoGeneralEduContinuaPersonas(@RequestParam(name="fechaInicio", required=true) String fechaInicio, 
			@RequestParam(name="fechaFin", required=true) String fechaFin, @RequestParam(name="idPrograma", required=false) String idPrograma) {
		return new ResponseEntity<>(informeSniesService.generarStatisticsConteoGeneralPersonas(fechaInicio, fechaFin, idPrograma),HttpStatus.OK);
	}
	
	@GetMapping(value = "/dashboard/totalGeneralEduContinuaGenero")
	public ResponseEntity<List<Object[]>> estadisticasConteoGeneralEduContinuaGeneros(@RequestParam(name="fechaInicio", required=true) String fechaInicio, 
			@RequestParam(name="fechaFin", required=true) String fechaFin, @RequestParam(name="idPrograma", required=false) String idPrograma) {
		return new ResponseEntity<>(informeSniesService.generarStatisticsConteoGeneralGenero(fechaInicio, fechaFin, idPrograma),HttpStatus.OK);
	}
	

}
