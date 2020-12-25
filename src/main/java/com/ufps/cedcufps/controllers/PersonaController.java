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
public class PersonaController {

	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IProgramaService programaService;
	
	@Autowired
	private IDepartamentoService departamentoService;
	
	@RequestMapping(value = "/usuarios")
	public String listar(Model model) {
		model.addAttribute("titulo","PROGRAMAS");
		model.addAttribute("personas",personaService.findAllPersonasPosibles());
		model.addAttribute("otorganPermisos",personaService.isSuperAdmin() || personaService.isDirPrograma() );
		model.addAttribute("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.addAttribute("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "persona/index";
	}
	
	/*@RequestMapping(value = "/usuarios/registro")
	public String agregar(Map<String, Object> model) {
		Estudiante e= new Estudiante();
		Docente d= new Docente();
		Administrativo a= new Administrativo();
		Externo ex= new Externo();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("estudiante",e);
		model.put("docente",d);
		model.put("administrativo",a);
		model.put("externo",ex);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/form";
	}*/
	
	@RequestMapping(value = "/usuarios/registro")
	public String crear(Map<String, Object> model) {
		model.put("persona",new UsuarioDto());
		model.put("titulo","FORMULARIO PERSONA");
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("departamentos",personaService.findAllDepartamentos());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
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
	
	@RequestMapping(value = "/usuarios/editar")
	public String editar(@RequestParam(name = "id") String idAcceso, Map<String, Object> model) {
		model.put("titulo","FORMULARIO PERSONA");
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		model.put("departamentos",personaService.findAllDepartamentos());
		model.put("persona", personaService.editarUsuario(idAcceso));
		model.put("otorganPermisos",personaService.isSuperAdmin() || personaService.isDirPrograma() );
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
	
	@RequestMapping(value = "/persona/permisos")
	public String permisos( @RequestParam(name = "id") String idAcceso, Map<String, Object> model,RedirectAttributes redirectAttributes) {
		PerfilRolUsuarioDto dto=personaService.findPermisos(idAcceso);
		if(dto==null) {
			redirectAttributes.addFlashAttribute("errorMessage", "No es posible que gestione los permisos de otros usuarios");
			return "redirect:/usuarios";
		}
		model.put("persona",dto);
		System.out.println("is director: " + dto.isDirPrograma());
		System.out.println("****************************************************************************************");
		System.out.println("va a entrar a programas para edu continua");
		System.out.println(dto.getProgramasForEduContinua().size());
		for(ProgramaDto p: dto.getProgramasForEduContinua()) {
			System.out.println(p.getId());
		}
		
		System.out.println("va a entrar a programas para estudiantes");
		System.out.println(dto.getProgramasForEstudiantes().size());
		for(ProgramaDto p: dto.getProgramasForEstudiantes()) {
			System.out.println(p.getId());
		}
		
		System.out.println("va a entrar a deptos para docentes");
		System.out.println(dto.getDeptosForDocentes().size());
		for(DepartamentoDto p: dto.getDeptosForDocentes()) {
			System.out.println(p.getId());
		}
		
		System.out.println("va a entrar a programas para graduados");
		System.out.println(dto.getProgramasForGraduados().size());
		for(ProgramaDto p: dto.getProgramasForGraduados()) {
			System.out.println(p.getId());
		}
		
		System.out.println("va a entrar a educ  para asistencia");
		System.out.println(dto.getEduContinuasForAttendance().size());
		for(EducacionContinuaAppDto p: dto.getEduContinuasForAttendance()) {
			System.out.println(p.getId());
		}
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "persona/permisos";
	}
}
