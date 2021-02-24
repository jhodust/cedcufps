package com.ufps.cedcufps.testController;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ufps.cedcufps.controllers.AppRestController;
import com.ufps.cedcufps.controllers.PonenteRestController;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;

public class AppControllerTest {

	
	@Mock
	private IEducacionContinuaService educacionContinuaService;
	
	@Mock
	private IPersonaService personaService;
	
	@InjectMocks
	private AppRestController appRestController;
	
	private static  List<EducacionContinuaAppDto> LIST_EDUCACIONES_CONTINUAS;
	private static  List<JornadaAppDto> JORNADAS;
	private static  Map<Integer, ParticipanteDto> MAP_PARTICIPANTE;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		LIST_EDUCACIONES_CONTINUAS=new ArrayList<EducacionContinuaAppDto>();
		
		EducacionContinuaAppDto eduContinua1= new EducacionContinuaAppDto();
		eduContinua1.setId(1L);
		eduContinua1.setNombre("CIINATIC");
		eduContinua1.setFechaInicio(new Date());
		
		EducacionContinuaAppDto eduContinua2= new EducacionContinuaAppDto();
		eduContinua2.setId(2L);
		eduContinua2.setNombre("EISE");
		eduContinua2.setFechaInicio(new Date());
		
		LIST_EDUCACIONES_CONTINUAS.add(eduContinua1);
		LIST_EDUCACIONES_CONTINUAS.add(eduContinua2);
		
		JORNADAS= new ArrayList<JornadaAppDto>();
		
		JornadaAppDto jornada1= new JornadaAppDto();
		jornada1.setId(1L);
		jornada1.setHoraInicio(new Date());
		
		JornadaAppDto jornada2= new JornadaAppDto();
		jornada2.setId(2L);
		jornada2.setHoraInicio(new Date());
		
		JORNADAS.add(jornada1);
		JORNADAS.add(jornada2);
		
		ParticipanteDto dto= new ParticipanteDto();
		dto.setId(1L);
		dto.setNombrePersona("Juan Manuel Martinez");
		dto.setNumeroDocumento("106597416");
		MAP_PARTICIPANTE= new HashMap<Integer, ParticipanteDto>();
		MAP_PARTICIPANTE.put(200, dto);
		
	}
	
	@Test
	public void searchCursosYEventos() {
		Mockito.when(educacionContinuaService.findAllEducacionesApp(1L)).
		thenReturn(LIST_EDUCACIONES_CONTINUAS);
		final ResponseEntity<List<EducacionContinuaAppDto>> response = appRestController.
				searchCursosYEventos(1L);
		assertEquals(response.getBody().size(), 2);
	}
	
	@Test
	public void searchJornadasCursosYEventos() {
		Mockito.when(educacionContinuaService.findAllJornadasByEduContinuaApp(1L)).
		thenReturn(JORNADAS);
		final ResponseEntity<List<JornadaAppDto>> response = appRestController.
				searchJornadasCursosYEventos(1L);
		assertEquals(response.getBody().size(), 2);
	}
	
	@Test
	public void tomarAsistenciaApp() {
		Mockito.when(educacionContinuaService.tomarAsistencia(1L, 1L, 
				"MTUwXzNfNV8xXzEwODQ4NF8xNS0wNC0yMDIwIDA0OjA1")).
		thenReturn(MAP_PARTICIPANTE);
		final ResponseEntity<?> response = appRestController.tomarAsistenciaApp(1L, 1L, 
				"MTUwXzNfNV8xXzEwODQ4NF8xNS0wNC0yMDIwIDA0OjA1");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	
	
	
	
	
}
