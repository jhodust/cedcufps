package com.ufps.cedcufps.testController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.cedcufps.controllers.FacultadRestController;
import com.ufps.cedcufps.controllers.JornadaRestController;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.services.IJornadaService;

public class JornadaControllerTest {

	@Mock
	private IJornadaService jornadaService;
	
	@InjectMocks
	private JornadaRestController jornadaController;
	
	
	private static  Jornada JORNADA1;
	private static  JornadaAppDto JORNADA_APP_DTO;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		JORNADA1= new Jornada();
		JORNADA1.setHoraInicio(new Date());
		JORNADA1.setHoraFin(new Date());
		
		JORNADA_APP_DTO= new JornadaAppDto();
		JORNADA_APP_DTO.setId(1L);
		JORNADA_APP_DTO.setHoraInicio(new Date());
		JORNADA_APP_DTO.setHoraFin(new Date());
		
	}
	
	/*@Test
	public void saveJornada() {
		final ResponseEntity<?> response = jornadaController.guardarJornada(JORNADA1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}*/
	
	
	@Test
	public void searchJornada() {
		Mockito.when(jornadaService.findOne(1L)).thenReturn(JORNADA_APP_DTO);
		final ResponseEntity<JornadaAppDto> response = jornadaController.buscarJornada(1L);
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getHoraFin(), JORNADA_APP_DTO.getHoraFin());
	}
	
	@Test
	public void deleteJornada() {
		final ResponseEntity<?> response = jornadaController.deleteJornada("1");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	
	
}
