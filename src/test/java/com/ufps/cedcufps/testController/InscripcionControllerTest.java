package com.ufps.cedcufps.testController;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.cedcufps.controllers.AsistenteRestController;
import com.ufps.cedcufps.controllers.PersonaRestController;
import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;

public class InscripcionControllerTest {

	
	@Mock
	private IParticipanteService participanteService;
	
	@InjectMocks
	private AsistenteRestController asistenteRestController;
	
	private static String ID_EDU_CONTINUA;
	private static String ID_TIPO_PERSONA;
	private static ParticipanteDto PARTICIPANTE_DTO;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		ID_EDU_CONTINUA="1";
		ID_TIPO_PERSONA="1";
		PARTICIPANTE_DTO=new ParticipanteDto();
		PARTICIPANTE_DTO.setId(1L);
		PARTICIPANTE_DTO.setIdEducacionContinua(Long.parseLong(ID_EDU_CONTINUA));
		PARTICIPANTE_DTO.setIdTipoPersona(Long.parseLong(ID_TIPO_PERSONA));
		PARTICIPANTE_DTO.setIdPersona(1L);
		
	}
	
	
	/*@Test
	public void realizarInscripcionTest() {
		Mockito.when(participanteService.saveAsistente(Long.parseLong(ID_EDU_CONTINUA), Long.parseLong(ID_TIPO_PERSONA))).thenReturn(PARTICIPANTE_DTO);
		final ResponseEntity<?> response = asistenteRestController.realizarInscripcion(ID_EDU_CONTINUA, ID_TIPO_PERSONA);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}*/
	
	@Test
	public void cancelarInscripcionTest() {
		Mockito.doNothing().when(participanteService).cancelarInscripcion
		(Long.parseLong(ID_EDU_CONTINUA));
		final ResponseEntity<?> response = asistenteRestController.cancelarInscripcion
				(Long.parseLong(ID_EDU_CONTINUA));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	
	
	
}
