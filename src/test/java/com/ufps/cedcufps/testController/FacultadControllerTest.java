package com.ufps.cedcufps.testController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IFacultadService;

public class FacultadControllerTest {

	@Mock
	private IFacultadService facultadService;
	
	@InjectMocks
	private FacultadRestController facultadController;
	
	private static  List<Facultad> LIST_FACULTADES;
	
	private static  Facultad FACULTAD1;
	private static  Facultad FACULTAD2;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		LIST_FACULTADES= new ArrayList<>();
		
		FACULTAD1=new Facultad(1L, "Ingeniería");
		FACULTAD2=new Facultad(2L, "Ciencias Empresariales");
		LIST_FACULTADES.add(FACULTAD1);
		LIST_FACULTADES.add(FACULTAD2);
		
	}
	@Test
	public void listFacultadTest() {
		Mockito.when(facultadService.findAll()).thenReturn(LIST_FACULTADES);
		final List<Facultad> response = facultadController.listar();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 2);
	}
	
	
	@Test
	public void findFacultadExistedTest() {
		Mockito.when(facultadService.findOne(1L)).thenReturn(FACULTAD1);
		final ResponseEntity<Facultad> response = facultadController.buscarPorFacultad(1L);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void findFacultadNotFoundTest() {
		Mockito.when(facultadService.findOne(1L)).thenReturn(FACULTAD1);
		final ResponseEntity<Facultad> response = facultadController.buscarPorFacultad(2L);
		assertNull(response.getBody());
	}
	
	@Test
	public void saveFacultadTest() {
		final ResponseEntity<?> response = facultadController.guardarFacultadRest(FACULTAD1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
}
