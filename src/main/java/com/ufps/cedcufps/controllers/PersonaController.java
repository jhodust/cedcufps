package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufps.cedcufps.SpringSecurityConfig;
import com.ufps.cedcufps.dto.DepartamentoDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.PerfilRolUsuarioDto;
import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.services.IDepartamentoService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.services.IProgramaService;

@Controller
@RequestMapping(value = "/usuarios")
public class PersonaController {

	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IProgramaService programaService;
	
	@Autowired
	private IDepartamentoService departamentoService;
	
	@RequestMapping
	public String listar(Model model) {
		model.addAttribute("titulo","PROGRAMAS");
		model.addAttribute("personas",personaService.findAllPersonasPosibles());
		model.addAttribute("otorganPermisos",personaService.isSuperAdmin() || personaService.isDirPrograma() );
		model.addAttribute("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.addAttribute("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "persona/index";
	}
	
	
	@RequestMapping(value = "/registro")
	public String crear(Map<String, Object> model) {
		model.put("persona",new UsuarioDto());
		model.put("titulo","FORMULARIO PERSONA");
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		//model.put("programas",personaService.findAllProgramas());
		//model.put("departamentos",personaService.findAllDepartamentos());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		model.put("propiedadesPerfiles",personaService.findPermisosRegistrarPersonas(0L,true));
		/*if(p.getTipoPersona().getTipoPersona().equalsIgnoreCase("Estudiante")) {
			model.put("estudiante",(Estudiante)personaService.findOne(id).get());
			return "redirect:/usuarios/estudiante/registro/"+p.getId();
		}else if(p.getTipoPersona().getTipoPersona().equalsIgnoreCase("Docente")) {
			model.put("docente",(Docente)personaService.findOne(id).get());
			return "redirect:/usuarios/docente/registro/"+p.getId();
		}else if(p.getTipoPersona().getTipoPersona().equalsIgnoreCase("Administrativo")) {
			model.put("administrativo",(Administrativo)personaService.findOne(id).get());
			return "redirect:/usuarios/administrativo/registro/"+p.getId();
		}else{
			model.put("externo",(Externo)personaService.findOne(id).get());
			return "redirect:/usuarios/externo/registro/"+p.getId();
		}*/
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		
		return "persona/form";
	}
	
	@RequestMapping(value = "/editar")
	public String editar(@RequestParam(name = "id") String idAcceso, Map<String, Object> model) {
		model.put("titulo","FORMULARIO PERSONA");
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		//model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		//model.put("departamentos",personaService.findAllDepartamentos());
		UsuarioDto p=personaService.editarUsuario(idAcceso);
		model.put("persona", p);
		model.put("otorganPermisos",personaService.isSuperAdmin() || personaService.isDirPrograma() );
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		model.put("propiedadesPerfiles",personaService.findPermisosRegistrarPersonas(p.getId(),true));
		return "persona/form";
	}
	
	@RequestMapping(value = "/permisos")
	public String permisos( @RequestParam(name = "id") String idAcceso, Map<String, Object> model,RedirectAttributes redirectAttributes) {
		PerfilRolUsuarioDto dto=personaService.findPermisos(idAcceso);
		if(dto==null) {
			redirectAttributes.addFlashAttribute("errorMessage", "No es posible que gestione los permisos de otros usuarios");
			return "redirect:/usuarios";
		}
		model.put("persona",dto);
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "persona/permisos";
	}
	
	
}
