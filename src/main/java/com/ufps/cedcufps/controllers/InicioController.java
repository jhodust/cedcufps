package com.ufps.cedcufps.controllers;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import com.ufps.cedcufps.services.IStatusEducacionContinua;
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
	
	@Autowired
	private IStatusEducacionContinua statusEducacionContinuaService;
	
	
	@RequestMapping(value = "/")
	public String listar(Model model) {
		Pageable pageRequest=PageRequest.of(0, 3);
		Page<EducacionContinua> edc=educacionContinuaService.educacionContinuaNoTerminadas(pageRequest);
		PageRender<EducacionContinua> pageRender= new PageRender<EducacionContinua>("/reload", edc);
		model.addAttribute("educacionesRecientes",educacionContinuaService.educacionContinuaRecientes());
		model.addAttribute("educacionesContinuas",edc);
		model.addAttribute("page",pageRender);
		model.addAttribute("programas",programaService.findAll());
		model.addAttribute("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinuaExisting());
		model.addAttribute("tipo_beneficiarios",educacionContinuaService.findAllTipoBeneficiario());
		model.addAttribute("status",statusEducacionContinuaService.getAllStatus());
		model.addAttribute("educacionesFinalizadas", educacionContinuaService.ultimasEducacionesContinuasFinalizadas());
		SessionWebGoogle session=SpringSecurityConfig.getInfoSession();
		if(session!=null) {
			model.addAttribute("photoUser", session.getPhoto());
			model.addAttribute("nameUser", session.getName());
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/bienvenida")
	public String bienvenida(RedirectAttributes redirectAttributes, @RequestParam(name = "email") String email) {
		
		redirectAttributes.addFlashAttribute("successMessage", "Has iniciado sesión mediante " + email);
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/reload")
	public String reloadPanelEventos(@RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="idTipoEdC", defaultValue = "0") String idTipoEdC,
			@RequestParam(name="idPrograma", defaultValue = "0") String idPrograma,
			@RequestParam(name="idPublico", defaultValue = "0") String idPublico,
			@RequestParam(name="status", defaultValue = "0") String status,Model model) {
		System.out.println("STATUS: " + status);
		Pageable pageRequest=PageRequest.of(page, 3);
		Page<EducacionContinua> edc=educacionContinuaService.educacionContinuaFiltroPanel(Long.parseLong(idTipoEdC),
				Long.parseLong(idPrograma), Long.parseLong(idPublico), Long.parseLong(status), pageRequest);
		
		
		PageRender<EducacionContinua> pageRender= new PageRender<EducacionContinua>(this.convertBaseUri(Long.parseLong(idTipoEdC),
				Long.parseLong(idPrograma), Long.parseLong(idPublico), Long.parseLong(status)), edc);
		model.addAttribute("educacionesContinuas",edc);
		model.addAttribute("page",pageRender);
		
		return "index :: listPanel";
	}
	
	public String convertBaseUri(Long idTipoEdC, Long idPrograma, Long idPublico, Long status) {
		String baseUri="/reload";
		if(idTipoEdC!=0L){
			baseUri=baseUri.concat("?idTipoEdC="+idTipoEdC);
		}
		
		if(idPrograma!=0L && idTipoEdC==0L){
			baseUri=baseUri.concat("?idPrograma="+idPrograma);
		}else if(idPrograma!=0 && idTipoEdC!=0L){
			baseUri=baseUri.concat("&idPrograma="+idPrograma);
		}
		
		if(idPublico!=0L && idTipoEdC==0L && idPrograma==0L){
			baseUri=baseUri.concat("?idPublico="+idPublico);
		}else if(idPublico!=0L && (idTipoEdC==0L || idPrograma==0L)){
			baseUri=baseUri.concat("&idPublico="+idPublico);
		}else if(idPublico!=0L){
			baseUri=baseUri.concat("&idPublico="+idPublico);
		}
		
		if(status!=0L &&  idPublico==0L && idTipoEdC==0L && idPrograma==0L){
			baseUri=baseUri.concat("?status="+status);
		}else if(status!=0 && (idPublico!=0L || idTipoEdC==0L || idPrograma==0L)){
			baseUri=baseUri.concat("&status="+status);
		}else if(status!=0){
			baseUri=baseUri.concat("&status="+status);
		}
		
		
		//baseUri=baseUri.concat("&baseUri="+baseUri);
		return baseUri;
	}
	
	@GetMapping(value = "/registrarse")
	public String nuevoUsuario(Map<String, Object> model) {
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		//model.put("programas",personaService.findAllProgramas());
		//model.put("departamentos",personaService.findAllDepartamentos());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		model.put("persona",new UsuarioDto());
		model.put("recoveryEmail",true);
		model.put("propiedadesPerfiles",personaService.findPermisosRegistrarPersonas(0L,false));
		//educacionContinuaService.generarReporteSNIESEducacionContinua(new Date(), 0);
		return "registrarse";
	}
	
	

	
	
	
	
	
	
	
	
	
}
