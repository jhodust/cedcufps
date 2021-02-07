package com.ufps.cedcufps.testController;

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

import com.ufps.cedcufps.controllers.DepartamentoRestController;
import com.ufps.cedcufps.controllers.ProgramaRestController;
import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IDepartamentoService;
import com.ufps.cedcufps.services.IProgramaService;

public class ProgramaControllerTest {

	@Mock
	private IProgramaService programaService;
	
	@InjectMocks
	private ProgramaRestController programaController;
	
	private static  List<Programa> LIST_PROGRAMAS;
	private static  Facultad FACULTAD1;
	private static  Persona DIRECTOR1;
	private static  Persona DIRECTOR2;
	private static  Programa PROGRAMA1;
	private static  Programa PROGRAMA2;
	
	private static  ProgramaDto PROGRAMA_DTO_1;
	private static  ProgramaDto PROGRAMA_DTO_2;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		LIST_PROGRAMAS= new ArrayList<>();
		FACULTAD1=new Facultad(1L, "Ingeniería");
		DIRECTOR1= new Docente();
		DIRECTOR1.setId(1L);
		DIRECTOR1.setPrimerNombre("Pilar");
		DIRECTOR1.setPrimerApellido("Rodriguez");
		
		DIRECTOR2=  new Docente();
		DIRECTOR2.setId(2L);
		DIRECTOR2.setPrimerNombre("Javier");
		DIRECTOR2.setPrimerApellido("Cardenas");
		PROGRAMA1=new Programa(1L, "Ingeniería de Sistemas",FACULTAD1,(Docente)DIRECTOR1);
		PROGRAMA2=new Programa(2L, "Ingeniería Civil", FACULTAD1,(Docente)DIRECTOR2);
		LIST_PROGRAMAS.add(PROGRAMA1);
		LIST_PROGRAMAS.add(PROGRAMA2);
		
		PROGRAMA_DTO_1=new ProgramaDto(PROGRAMA1.getId(), PROGRAMA1.getPrograma(), PROGRAMA1.getCodigo(),
				PROGRAMA1.getDirectorPrograma().getId(), PROGRAMA1.getFacultad().getId());
		
	}
	@Test
	public void listProgramaTest() {
		Mockito.when(programaService.findAll()).thenReturn(LIST_PROGRAMAS);
		final List<Programa> response = programaController.listar();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 2);
	}
	
	
	@Test
	public void findProgramaExistedTest() {
		Mockito.when(programaService.searchProgramaById(1L)).thenReturn(PROGRAMA_DTO_1);
		final ResponseEntity<ProgramaDto> response = programaController.buscarPorPrograma(1L);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void findProgramaNotFoundTest() {
		Mockito.when(programaService.searchProgramaById(1L)).thenReturn(PROGRAMA_DTO_1);
		final ResponseEntity<ProgramaDto> response = programaController.buscarPorPrograma(2L);
		assertNull(response.getBody());
	}
	
	@Test
	public void saveProgramaTest() {
		final ResponseEntity<?> response = programaController.guardarProgramaRest(PROGRAMA1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void buscarDirectorProgramaTest() {
		Mockito.when(programaService.findProgramaByDirector(1L,1L)).thenReturn(PROGRAMA_DTO_1);
		final ResponseEntity<ProgramaDto> response = programaController.buscarDirectorPrograma
				(PROGRAMA1.getDirectorPrograma().getId(),PROGRAMA1.getId());
		assertNotNull(response.getBody());
	}
	
	@Test
	public void buscarDirectorProgramaNotFountTest() {
		Mockito.when(programaService.findProgramaByDirector(1L,1L)).thenReturn(PROGRAMA_DTO_1);
		final ResponseEntity<ProgramaDto> response = programaController.buscarDirectorPrograma
				(PROGRAMA2.getDirectorPrograma().getId(),PROGRAMA2.getId());
		assertNull(response.getBody());
	}
}
