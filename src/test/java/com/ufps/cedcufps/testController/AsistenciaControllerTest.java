package com.ufps.cedcufps.testController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.cedcufps.controllers.AsistenciaRestController;
import com.ufps.cedcufps.controllers.FacultadRestController;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IAsistenciaService;
import com.ufps.cedcufps.services.IFacultadService;

public class AsistenciaControllerTest {

	@Mock
	private IAsistenciaService asistenciaService;
	
	@InjectMocks
	private AsistenciaRestController asistenciaRestController;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void marcarAsistenciaGeneralTest() {
		final ResponseEntity<?> response = asistenciaRestController.marcarAsistenciaGeneral(1L);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void desmarcarAsistenciaGeneralTest() {
		final ResponseEntity<?> response = asistenciaRestController.desmarcarAsistenciaGeneral(1L);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void marcarAsistenciaParticipanteTest() {
		final ResponseEntity<?> response = asistenciaRestController.desmarcarAsistenciaGeneral(1L);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void desmarcarAsistenciaParticipanteTest() {
		final ResponseEntity<?> response = asistenciaRestController.desmarcarAsistenciaGeneral(1L);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	
	
	
	
}
