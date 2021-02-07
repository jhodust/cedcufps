package com.ufps.cedcufps.testController;

import com.ufps.cedcufps.modelos.Departamento;

import com.ufps.cedcufps.controllers.DepartamentoRestController;

import com.ufps.cedcufps.services.IDepartamentoService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import com.ufps.cedcufps.modelos.Facultad;

public class DepartamentoControllerTest {

	@Mock
	private IDepartamentoService departamentoService;
	
	@InjectMocks
	private DepartamentoRestController departamentoController;
	
	private static  List<Departamento> LIST_DEPARTAMENTOS;
	private static  Facultad FACULTAD1;
	private static  Departamento DEPARTAMENTO1;
	private static  Departamento DEPARTAMENTO2;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		LIST_DEPARTAMENTOS= new ArrayList<>();
		FACULTAD1=new Facultad(1L, "Ingeniería");
		DEPARTAMENTO1=new Departamento(1L, "Sistemas e Informática",FACULTAD1);
		DEPARTAMENTO2=new Departamento(2L, "Departamento Fluidos y Térmicas", FACULTAD1);
		LIST_DEPARTAMENTOS.add(DEPARTAMENTO1);
		LIST_DEPARTAMENTOS.add(DEPARTAMENTO2);
		
	}
	
	@Test
	public void listDepartamentoTest() {
		Mockito.when(departamentoService.findAll()).thenReturn(LIST_DEPARTAMENTOS);
		final List<Departamento> response = departamentoController.listar();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 2);
	}
	
	
	@Test
	public void findDepartamentoExistedTest() {
		Mockito.when(departamentoService.findOne(1L)).thenReturn(DEPARTAMENTO1);
		final ResponseEntity<Departamento> response = departamentoController.buscarPorFacultad(1L);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void findDepartamentoNotFoundTest() {
		Mockito.when(departamentoService.findOne(1L)).thenReturn(DEPARTAMENTO1);
		final ResponseEntity<Departamento> response = departamentoController.buscarPorFacultad(2L);
		assertNull(response.getBody());
	}
	
	@Test
	public void saveDepartamentoTest() {
		final ResponseEntity<?> response = departamentoController.guardarDepartamentoRest(DEPARTAMENTO1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
