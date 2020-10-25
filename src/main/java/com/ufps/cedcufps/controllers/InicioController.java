package com.ufps.cedcufps.controllers;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.utils.ReportesExcel;
import com.ufps.cedcufps.utils.paginator.PageRender;

@Controller
public class InicioController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	
	
	@RequestMapping(value = "/")
	public String listar(@RequestParam(name="page", defaultValue = "0") int page,Model model) {
		Pageable pageRequest=PageRequest.of(page, 3);
		Page<EducacionContinua> edc=educacionContinuaService.educacionContinuaNoTerminadas(pageRequest);
		PageRender<EducacionContinua> pageRender= new PageRender<EducacionContinua>("/", edc);
		model.addAttribute("educacionesRecientes",educacionContinuaService.educacionContinuaRecientes());
		model.addAttribute("educacionesContinuas",edc);
		model.addAttribute("page",pageRender);
		//educacionContinuaService.generarReporteSNIESEducacionContinua(new Date(), 0);
		return "index";
	}
	
	@GetMapping(value = "/registrarse")
	public String nuevoUsuario(Map<String, Object> model) {
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("departamentos",personaService.findAllDepartamentos());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		model.put("persona",new UsuarioDto());
		//educacionContinuaService.generarReporteSNIESEducacionContinua(new Date(), 0);
		return "registrarse";
	}
	
	@GetMapping(value = "/email")
	public String plantillaEmailparainscripcion(Map<String, Object> model) {
		 
		model.put("participante","ccccccccccccc");
		model.put("contenido", "La inscripción al Simposio Bienal3 se ha realizado con exitósamente. Recuerde que la educación continua inica 18/08/2020 12:00 a. m.. A continuación se adjunta su respectiva tarjeta de inscripción.");
		model.put("logo", new File("img/geduco.png").getAbsolutePath());
		//educacionContinuaService.generarReporteSNIESEducacionContinua(new Date(), 0);
		return "email/plantilla";
	}
	
	
	@RequestMapping(value = "/dashboard")
	public String listar(Model model) {
		
		return "reportes-snies/dashboard";
	}
	
	
	
	
	
	
}
