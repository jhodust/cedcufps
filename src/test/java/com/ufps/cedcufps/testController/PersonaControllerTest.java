package com.ufps.cedcufps.testController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufps.cedcufps.controllers.FacultadRestController;
import com.ufps.cedcufps.controllers.PersonaRestController;
import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.services.IPersonaService;

public class PersonaControllerTest {

	@Mock
	private IPersonaService personaService;
	
	@Mock
	private IPersonaDao personaDao;
	
	@InjectMocks
	private PersonaRestController personaController;
	
	private static UsuarioDto USUARIO_ESTUDIANTE;
	private static UsuarioDto USUARIO_DOCENTE;
	private static UsuarioDto USUARIO_ADMINISTRATIVO;
	private static UsuarioDto USUARIO_GRADUADO;
	private static UsuarioDto USUARIO_EXTERNO;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		USUARIO_ESTUDIANTE= new UsuarioDto(1L, 1L, "CC", "515194", new Date(),
				"Carla", "Valentina", "Perez", "Quintero", 2L, "Femenino",	1L, "Soltero(a)", new Date(),
				"57", "Colombia", "54", "Norte de Santander", "001", "Cucuta", "carlavalentina@gmail.com", null, 
				"464484", null, null, true, false,false, false, false, "1548779", 1L, "Ingeniería de Sistemas", 
				null, null, null, null, null,null, null, null,false,false);
		USUARIO_DOCENTE= new UsuarioDto(1L, 1L, "CC", "515194", new Date(), "Carla", "Valentina", "Perez",
				"Quintero", 2L, "Femenino",	1L, "Soltero(a)", new Date(), "57", "Colombia", "54", 
				"Norte de Santander", "001", "Cucuta", "carlavalentina@gmail.com", null, "464484", 
				null, null, false, true,false, false, false, null, null, null, null, null, null, 1L, 
				"Sistemas e Informática","016158", null, null,false,false);
		USUARIO_ADMINISTRATIVO= new UsuarioDto(1L, 1L, "CC", "515194", new Date(), "Carla", "Valentina", "Perez",
				"Quintero", 2L, "Femenino",	1L, "Soltero(a)", new Date(), "57", "Colombia", "54", 
				"Norte de Santander", "001", "Cucuta", "carlavalentina@gmail.com", null, "464484", null, null, 
				false, false,true, false, false, null,null,null, null, null, null, null, null,null, "Secretaria", 
				"Registro y Control",false,false);
		USUARIO_GRADUADO= new UsuarioDto(1L, 1L, "CC", "515194", new Date(), "Carla", "Valentina", "Perez",
				"Quintero", 2L, "Femenino",	1L, "Soltero(a)", new Date(), "57", "Colombia", "54", 
				"Norte de Santander", "001", "Cucuta", "carlavalentina@gmail.com", null, "464484", null, 
				null, false, false,false, true, false,null,null,null, 2L, "Ingeniería Civil", "2013", 
				null, null,null, null, null,false,false);
		USUARIO_EXTERNO= new UsuarioDto(1L, 1L, "CC", "515194", new Date(), "Carla", "Valentina", "Perez",
				"Quintero", 2L, "Femenino",	1L, "Soltero(a)", new Date(), "57", "Colombia", "54", 
				"Norte de Santander","001", "Cucuta", "carlavalentina@gmail.com", null, "464484", "CENS", 
				"Ingeniero Eléctrico", false, false,false, false, true,	null, null, null, null, 
				null, null, null, null,null, null, null,false,false);
		
	}
	
	@Test
	public void saveEstudiante() {
		Mockito.doNothing().when(personaService).guardar(USUARIO_ESTUDIANTE);
		final ResponseEntity<?> response = personaController.nuevoUsuario(USUARIO_ESTUDIANTE);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void saveDocente() {
		Mockito.doNothing().when(personaService).guardar(USUARIO_DOCENTE);
		final ResponseEntity<?> response = personaController.nuevoUsuario(USUARIO_DOCENTE);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void saveAdministrativo() {
		Mockito.doNothing().when(personaService).guardar(USUARIO_ADMINISTRATIVO);
		final ResponseEntity<?> response = personaController.nuevoUsuario(USUARIO_ADMINISTRATIVO);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void saveGraduado() {
		Mockito.doNothing().when(personaService).guardar(USUARIO_GRADUADO);
		final ResponseEntity<?> response = personaController.nuevoUsuario(USUARIO_GRADUADO);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void saveExterno() {
		Mockito.doNothing().when(personaService).guardar(USUARIO_EXTERNO);
		final ResponseEntity<?> response = personaController.nuevoUsuario(USUARIO_EXTERNO);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
