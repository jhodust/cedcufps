package com.ufps.cedcufps.testController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.cedcufps.controllers.AsistenteRestController;
import com.ufps.cedcufps.controllers.ReportesSniesRestController;
import com.ufps.cedcufps.dto.InformeSniesDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.services.IInformeSniesService;
import com.ufps.cedcufps.services.IParticipanteService;

public class ReporteSniesController {

	@Mock
	private IInformeSniesService informeSniesService;
	
	@InjectMocks
	private ReportesSniesRestController reporteSniesController;
	
	private static InformeSniesDto INFORME_SNIES_DTO;
	private static String FECHA_INICIO;
	private static String FECHA_FIN;
	private static String DESCRIPCION;
	private static List<Object[]> RESULT_STATISTICS_CONTEO_GRAL;
	private static List<Object[]> RESULT_STATISTICS_CONTEO_PERSONAS;
	private static List<Object[]> RESULT_STATISTICS_CONTEO_GENERO;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		INFORME_SNIES_DTO= new InformeSniesDto();
		FECHA_INICIO="2020-01-05";
		FECHA_FIN="2020-06-05";
		DESCRIPCION="Reporte Educación Continua Primer Semestre 2020";
		INFORME_SNIES_DTO.setFechaInicio(FECHA_INICIO);
		INFORME_SNIES_DTO.setFechaFin(FECHA_FIN);
		INFORME_SNIES_DTO.setDescripcion(DESCRIPCION);
		
		RESULT_STATISTICS_CONTEO_GRAL=new ArrayList<Object[]>();
		RESULT_STATISTICS_CONTEO_GRAL.add(new Object[] {"Educación Continua","Total"});
		RESULT_STATISTICS_CONTEO_GRAL.add(new Object[] {"Curso",0});
		RESULT_STATISTICS_CONTEO_GRAL.add(new Object[] {"Taller",0});
		RESULT_STATISTICS_CONTEO_GRAL.add(new Object[] {"Seminario",0});
		RESULT_STATISTICS_CONTEO_GRAL.add(new Object[] {"Congreso",0});
		RESULT_STATISTICS_CONTEO_GRAL.add(new Object[] {"Simposio",0});
		RESULT_STATISTICS_CONTEO_GRAL.add(new Object[] {"Diplomado",0});
		RESULT_STATISTICS_CONTEO_GRAL.add(new Object[] {"Otro",0});
		
		RESULT_STATISTICS_CONTEO_PERSONAS=new ArrayList<Object[]>();
		RESULT_STATISTICS_CONTEO_PERSONAS.add(new Object[] {"Educación Continua",
				"Estudiante", "Docente", "Administrativo", "Graduado", "Externo"});
		RESULT_STATISTICS_CONTEO_PERSONAS.add(new Object[] {"Curso",0,0,0,0,0});
		RESULT_STATISTICS_CONTEO_PERSONAS.add(new Object[] {"Taller",0,0,0,0,0});
		RESULT_STATISTICS_CONTEO_PERSONAS.add(new Object[] {"Seminario",0,0,0,0,0});
		RESULT_STATISTICS_CONTEO_PERSONAS.add(new Object[] {"Congreso",0,0,0,0,0});
		RESULT_STATISTICS_CONTEO_PERSONAS.add(new Object[] {"Simposio",0,0,0,0,0});
		RESULT_STATISTICS_CONTEO_PERSONAS.add(new Object[] {"Diplomado",0,0,0,0,0});
		RESULT_STATISTICS_CONTEO_PERSONAS.add(new Object[] {"Otro",0,0,0,0,0});
		
		RESULT_STATISTICS_CONTEO_GENERO=new ArrayList<Object[]>();
		RESULT_STATISTICS_CONTEO_GENERO.add(0,new Object [] {"Género","Total"} );
		RESULT_STATISTICS_CONTEO_GENERO.add(0,new Object [] {"Masculino","15"} );
		RESULT_STATISTICS_CONTEO_GENERO.add(0,new Object [] {"Femenino","20"} );
	}
	
	
	@Test
	public void generarReporteTest() {
		Mockito.doNothing().when(informeSniesService).generarReporteSNIES
		(FECHA_INICIO, FECHA_FIN, DESCRIPCION);
		final ResponseEntity<?> response = reporteSniesController.informeExcel
				(INFORME_SNIES_DTO);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void estadisticasConteoGeneralEduContinuaTest() {
		Mockito.when(informeSniesService.generarStatisticsConteoGeneralEduContinua
				(FECHA_INICIO, FECHA_FIN,"1")).thenReturn(RESULT_STATISTICS_CONTEO_GRAL);
		final ResponseEntity<List<Object[]>> response = reporteSniesController.
				estadisticasConteoGeneralEduContinua(FECHA_INICIO, FECHA_FIN,"1");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertFalse(response.getBody().isEmpty());
	}
	
	@Test
	public void estadisticasConteoGeneralEduContinuaPersonasTest() {
		Mockito.when(informeSniesService.generarStatisticsConteoGeneralPersonas
				(FECHA_INICIO, FECHA_FIN,"1")).thenReturn(RESULT_STATISTICS_CONTEO_PERSONAS);
		final ResponseEntity<List<Object[]>> response = reporteSniesController
				.estadisticasConteoGeneralEduContinuaPersonas(FECHA_INICIO, FECHA_FIN,"1");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertFalse(response.getBody().isEmpty());
	}
	
	@Test
	public void estadisticasConteoGeneralEduContinuaGenerosTest() {
		Mockito.when(informeSniesService.generarStatisticsConteoGeneralGenero
				(FECHA_INICIO, FECHA_FIN,"1")).thenReturn(RESULT_STATISTICS_CONTEO_GENERO);
		final ResponseEntity<List<Object[]>> response = reporteSniesController.
				estadisticasConteoGeneralEduContinuaGeneros(FECHA_INICIO, FECHA_FIN,"1");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertFalse(response.getBody().isEmpty());
	}
	
	
	
	
	
}
