package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.modelos.InformeSnies;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IInformeSniesService;

@RestController
public class ReportesSniesRestController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IInformeSniesService informeSniesService;
	
	
	
	@PostMapping(value = "/reportes-SNIES/generar")
	public InformeSnies informeExcel(@RequestBody InformeSnies i) {
		educacionContinuaService.generarReporteSNIESEducacionContinua(i.getAnio());
		i.setInformeCurso("/reportes_snies/informe_cursos_snies/"+i.getAnio()+".xlsx");
		i.setInformeEducacionContinua("/reportes_snies/informe_educacion_continua_snies/"+i.getAnio()+".xlsx");
		informeSniesService.save(i);
		return i;
	}
	/*CREATE EVENT test_event_02
ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 MINUTE
ON COMPLETION PRESERVE
DO
   UPDATE educacion_continua
        SET activo = '0'
        WHERE fecha_inicio < (select now());*/
}
