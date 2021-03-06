package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
public class PersonaController {

	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/usuarios")
	public String listar(Model model) {
		model.addAttribute("titulo","PROGRAMAS");
		model.addAttribute("personas",personaService.findAllPersonas());
		model.addAttribute("estudiantes",personaService.findAllEstudiantes());
		model.addAttribute("docentes",personaService.findAllDocentes());
		model.addAttribute("administrativos",personaService.findAllAdministrativos());
		model.addAttribute("externos",personaService.findAllExternos());
		
		
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
	
	@RequestMapping(value = "/usuarios/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Persona p= personaService.findOne(id).get();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		if(p.getTipoPersona().getTipoPersona().equalsIgnoreCase("Estudiante")) {
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
		}
	}
	
	@RequestMapping(value = "/persona/{id}/permisos")
	public String permisos(@PathVariable(value = "id") Long idPersona, Map<String, Object> model) {
		model.put("persona",personaService.findOne(idPersona).get());
		return "persona/permisos";
	}
}
