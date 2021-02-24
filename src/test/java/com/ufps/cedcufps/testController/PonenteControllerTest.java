package com.ufps.cedcufps.testController;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.cedcufps.controllers.JornadaRestController;
import com.ufps.cedcufps.controllers.PonenteRestController;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IJornadaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;

public class PonenteControllerTest {

	@Mock
	private IParticipanteService participanteService;
	
	@Mock
	private IPersonaService personaService;
	
	@InjectMocks
	private PonenteRestController ponenteController;
	
	private static  Ponente PONENTE;
	private static  PonenteDto PONENTE_DTO;
	private static  PersonaDto PERSONA_DTO;
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		PONENTE_DTO= new PonenteDto();
		ParticipanteDto participante= new ParticipanteDto(); 
		participante.setIdEducacionContinua(1L);
		participante.setIdPersona(1L);
		participante.setIdTipoParticipante(2L);
		PONENTE_DTO.setParticipante(participante);
		PONENTE_DTO.setTema("Fundamentos de Programación");
		
		PERSONA_DTO=new PersonaDto();
		PERSONA_DTO.setId(1L);
		PERSONA_DTO.setDocumento("54677845");
		PERSONA_DTO.setNombre("Francisco Perez");
		
		PONENTE= new Ponente();
		PONENTE.setTema("Ciclos en Java");
	}
	
	@Test
	public void savePonente() {
		final ResponseEntity<?> response = ponenteController.guardarPonenteRest(PONENTE);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	
	@Test
	public void searchPersona() {
		Mockito.when(personaService.findOne(1L)).thenReturn(PERSONA_DTO);
		final ResponseEntity<PersonaDto> response = ponenteController.findPersona("1");
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getDocumento(), PERSONA_DTO.getDocumento());
	}
	
	@Test
	public void deletPonente() {
		final ResponseEntity<?> response = ponenteController.deletePonente("1");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
		
	
	@Test
	public void buscarPonente() {
		Mockito.when(participanteService.findPonente(1L)).thenReturn(PONENTE_DTO);
		final ResponseEntity<PonenteDto> response = ponenteController.buscarPonente(1L);
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getTema(),PONENTE_DTO.getTema());
	}
	
	
	
	
	
	
	
	
}
