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

import com.ufps.cedcufps.SpringSecurityConfig;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.EducacionContinuaTipoBeneficiario;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.SessionWebGoogle;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.services.IProgramaService;
import com.ufps.cedcufps.utils.ReportesExcel;
import com.ufps.cedcufps.utils.paginator.PageRender;

@Controller
public class InicioController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IProgramaService programaService;
	
	
	@RequestMapping(value = "/")
	public String listar(Model model) {
		Pageable pageRequest=PageRequest.of(0, 3);
		Page<EducacionContinua> edc=educacionContinuaService.educacionContinuaNoTerminadas(pageRequest);
		PageRender<EducacionContinua> pageRender= new PageRender<EducacionContinua>("/reload", edc);
		model.addAttribute("educacionesRecientes",educacionContinuaService.educacionContinuaRecientes());
		model.addAttribute("educacionesContinuas",edc);
		System.out.println("TOTAL PAGINAS");
		System.out.println(pageRender.getTotalPaginas());
		System.out.println("total edc: " + edc.getNumberOfElements());
		model.addAttribute("page",pageRender);
		model.addAttribute("programas",programaService.findAll());
		model.addAttribute("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinuaExisting());
		model.addAttribute("tipo_beneficiarios",educacionContinuaService.findAllTipoBeneficiario());
		SessionWebGoogle session=SpringSecurityConfig.getInfoSession();
		if(session!=null) {
			model.addAttribute("photoUser", session.getPhoto());
			model.addAttribute("nameUser", session.getName());
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/reload")
	public String reloadPanelEventos(@RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="idTipoEdC", defaultValue = "0") String idTipoEdC,
			@RequestParam(name="idPrograma", defaultValue = "0") String idPrograma,
			@RequestParam(name="idPublico", defaultValue = "0") String idPublico,
			@RequestParam(name="baseUri", defaultValue = "/reload") String baseUri,Model model) {
		Pageable pageRequest=PageRequest.of(page, 3);
		Page<EducacionContinua> edc=educacionContinuaService.educacionContinuaFiltroPanel(Long.parseLong(idTipoEdC),
				Long.parseLong(idPrograma), Long.parseLong(idPublico), pageRequest);
		baseUri.concat("?idTipoEdC=").concat(idTipoEdC).
		concat("?idPrograma=").concat(idPrograma).
		concat("?idPublico=").concat(idPublico);
		PageRender<EducacionContinua> pageRender= new PageRender<EducacionContinua>(baseUri, edc);
		model.addAttribute("educacionesContinuas",edc);
		model.addAttribute("page",pageRender);
		
		return "index :: listPanel";
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
	
	
	

	
	
	
	
	
	
	
	
	
}
