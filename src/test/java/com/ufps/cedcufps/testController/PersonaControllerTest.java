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
		USUARIO_ESTUDIANTE=UsuarioDto.builder()
				.id(1L)
				.idTipoDocumento(1L)
				.tipoDocumento("CC")
				.numeroDocumento("515194")
				.fechaExpedicionDocumento(new Date())
				.primerNombre("Carla")
				.segundoNombre("Valentina")
				.primerApellido("Perez")
				.segundoApellido("Quintero")
				.idGenero(2L)
				.genero("Femenino")
				.idEstadoCivil(1L)
				.estadoCivil("Soltero(a)")
				.fechaNacimiento(new Date())
				.idPaisNacimiento("57")
				.paisNacimiento("Colombia")
				.idDepartamentoNacimiento("54")
				.deptoNacimiento("Norte de Santander")
				.idMunicipioNacimiento("001")
				.mpioNacimiento("Cucuta")
				.email("carlavalentina@gmail.com")
				.telefono("464484")
				.estudiante(true)
				.docente(false)
				.administrativo(false)
				.graduado(false)
				.externo(false)
				.codigoProgramaEstudiante("1548779")
				.idProgramaEstudiante(1L)
				.programaEstudiante("Ingeniería de Sistemas")
				.isSuperAdmin(false)
				.isDirPrograma(false)
				.build();
		
		USUARIO_DOCENTE= UsuarioDto.builder()
				.id(1L)
				.idTipoDocumento(1L)
				.tipoDocumento("CC")
				.numeroDocumento("515194")
				.fechaExpedicionDocumento(new Date())
				.primerNombre("Carla")
				.segundoNombre("Valentina")
				.primerApellido("Perez")
				.segundoApellido("Quintero")
				.idGenero(2L)
				.genero("Femenino")
				.idEstadoCivil(1L)
				.estadoCivil("Soltero(a)")
				.fechaNacimiento(new Date())
				.idPaisNacimiento("57")
				.paisNacimiento("Colombia")
				.idDepartamentoNacimiento("54")
				.deptoNacimiento("Norte de Santander")
				.idMunicipioNacimiento("001")
				.mpioNacimiento("Cucuta")
				.email("carlavalentina@gmail.com")
				.telefono("464484")
				.estudiante(false)
				.docente(true)
				.administrativo(false)
				.graduado(false)
				.externo(false)
				.idDeptoAdscrito(1L)
				.deptoAdscrito("Sistemas e Informática")
				.codigoDocente("016158")
				.isSuperAdmin(false)
				.isDirPrograma(false)
				.build(); 
		USUARIO_ADMINISTRATIVO= UsuarioDto.builder()
				.id(1L)
				.idTipoDocumento(1L)
				.tipoDocumento("CC")
				.numeroDocumento("515194")
				.fechaExpedicionDocumento(new Date())
				.primerNombre("Carla")
				.segundoNombre("Valentina")
				.primerApellido("Perez")
				.segundoApellido("Quintero")
				.idGenero(2L)
				.genero("Femenino")
				.idEstadoCivil(1L)
				.estadoCivil("Soltero(a)")
				.fechaNacimiento(new Date())
				.idPaisNacimiento("57")
				.paisNacimiento("Colombia")
				.idDepartamentoNacimiento("54")
				.deptoNacimiento("Norte de Santander")
				.idMunicipioNacimiento("001")
				.mpioNacimiento("Cucuta")
				.email("carlavalentina@gmail.com")
				.telefono("464484")
				.estudiante(false)
				.docente(false)
				.administrativo(true)
				.graduado(false)
				.externo(false)
				.idDependencia(1L)
				.dependencia("Registro y Control")
				.cargo("Secretaria")
				.isSuperAdmin(false)
				.isDirPrograma(false)
				.build(); 
		USUARIO_GRADUADO= UsuarioDto.builder()
				.id(1L)
				.idTipoDocumento(1L)
				.tipoDocumento("CC")
				.numeroDocumento("515194")
				.fechaExpedicionDocumento(new Date())
				.primerNombre("Carla")
				.segundoNombre("Valentina")
				.primerApellido("Perez")
				.segundoApellido("Quintero")
				.idGenero(2L)
				.genero("Femenino")
				.idEstadoCivil(1L)
				.estadoCivil("Soltero(a)")
				.fechaNacimiento(new Date())
				.idPaisNacimiento("57")
				.paisNacimiento("Colombia")
				.idDepartamentoNacimiento("54")
				.deptoNacimiento("Norte de Santander")
				.idMunicipioNacimiento("001")
				.mpioNacimiento("Cucuta")
				.email("carlavalentina@gmail.com")
				.telefono("464484")
				.estudiante(false)
				.docente(false)
				.administrativo(false)
				.graduado(true)
				.externo(false)
				.idProgramaGraduado(2L)
				.programaGraduado("Ingeniería Civil")
				.anioGraduado("2013")
				.isSuperAdmin(false)
				.isDirPrograma(false)
				.build(); 
				
		USUARIO_EXTERNO= UsuarioDto.builder()
				.id(1L)
				.idTipoDocumento(1L)
				.tipoDocumento("CC")
				.numeroDocumento("515194")
				.fechaExpedicionDocumento(new Date())
				.primerNombre("Carla")
				.segundoNombre("Valentina")
				.primerApellido("Perez")
				.segundoApellido("Quintero")
				.idGenero(2L)
				.genero("Femenino")
				.idEstadoCivil(1L)
				.estadoCivil("Soltero(a)")
				.fechaNacimiento(new Date())
				.idPaisNacimiento("57")
				.paisNacimiento("Colombia")
				.idDepartamentoNacimiento("54")
				.deptoNacimiento("Norte de Santander")
				.idMunicipioNacimiento("001")
				.mpioNacimiento("Cucuta")
				.email("carlavalentina@gmail.com")
				.telefono("464484")
				.estudiante(false)
				.docente(false)
				.administrativo(false)
				.graduado(false)
				.externo(true)
				.profesion("Ingeniero Eléctrico")
				.empresa("CENS")
				.isSuperAdmin(false)
				.isDirPrograma(false)
				.build(); 
		
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
