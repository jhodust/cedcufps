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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.controllers.EducacionContinuaRestController;
import com.ufps.cedcufps.controllers.FacultadRestController;
import com.ufps.cedcufps.dto.DiplomaDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.services.IDiplomaService;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.services.IParticipanteService;

public class EducacionContinuaControllerTest {

	
	@Mock
	private IEducacionContinuaService educacionContinuaService;
	
	@Mock
	private IDiplomaService diplomaService;
	
	@Mock
	private IParticipanteService participanteService;
	
	@InjectMocks
	private EducacionContinuaRestController educacionContinuaController;
	
	private static EducacionContinuaWebDto EDC_WEB_DTO;
	
	private static List<String> LIST_EDUCACIONES_CONTINUAS;
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		EDC_WEB_DTO= new EducacionContinuaWebDto();
		EDC_WEB_DTO.setNombre("eisi");
		EDC_WEB_DTO.setCantidadInscritos(20);
		EDC_WEB_DTO.setConsecutivo("001");
		EDC_WEB_DTO.setFechaInicio(new Date());
		EDC_WEB_DTO.setFechaFin(new Date());
		EDC_WEB_DTO.setFechaLimInscripcion(new Date());
		
		LIST_EDUCACIONES_CONTINUAS=new ArrayList<String>();
		LIST_EDUCACIONES_CONTINUAS.add("eisi");
		LIST_EDUCACIONES_CONTINUAS.add("ciinatic");
		LIST_EDUCACIONES_CONTINUAS.add("semilleros de investigación");
		
	}
	
	@Test
	public void saveEducacionContinuaTest() {
		final ResponseEntity<?> response = educacionContinuaController.save(null, "1", "eisi", 
				"15-02-2021", "18-02-2021", "35", "20", "14-02-2021", 
				"35600", "Auditorio JJ Maldonado", "600000", "20", null, 
				"1", "Taller", "1", "1", "5", "001", "2");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	
	@Test
	public void searchEducacionContinuaBaseTest() {
		Mockito.when(educacionContinuaService.findEducacionContinuaBase("eisi")).
		thenReturn(EDC_WEB_DTO);
		final ResponseEntity<EducacionContinuaWebDto> response = educacionContinuaController.
				searchEducacionContinuaBase("eisi");
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getCantidadInscritos(), 20);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void searchListEducacionesContinuasByProgramaTest() {
		Mockito.when(educacionContinuaService.findEducacionesContinuasBaseByIdPrograma(Long.parseLong("1")))
		.thenReturn(LIST_EDUCACIONES_CONTINUAS);
		final ResponseEntity<List<String>> response = educacionContinuaController.
				searchListEducacionesContinuasByPrograma("1");
		assertNotNull(response);
		assertEquals(response.getBody().size(), 3);
		
	}
	
	@Test
	public void deleteEducacionContinuaTest() {
		final ResponseEntity<?> response = educacionContinuaController.delete("106487987");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
