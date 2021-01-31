package com.ufps.cedcufps.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IPersonaService;


@Controller
@SessionAttributes("docente")
public class DocenteController {

	/*@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/usuarios/docente/registro")
	public String agregar(Map<String, Object> model) {
		Docente d= new Docente();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("docente",d);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("departamentos",personaService.findAllDepartamentos());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/formRegistroDocente";
	}*/
	
	/*@RequestMapping(value = "/usuarios/docente/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Docente d= (Docente)personaService.findOne(id).get();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("docente",d);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("departamentos",personaService.findAllDepartamentos());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/formRegistroDocente";
	}*/
	
	/*@RequestMapping(value = "/usuarios/docente/registro", method = RequestMethod.POST)
	public String save(@Valid Docente d, BindingResult result,Map<String, Object> model, SessionStatus status,RedirectAttributes redirectAttributes) {
		//d.setTipoPersona(personaService.findByTipoPersona("Docente"));
		System.out.println("*******************************************************************");
		System.out.println(d.getFechaExpedicionDocumento());
		System.out.println(d.getFechaNacimiento());
		if(result.hasErrors()) {
			System.out.println("Entra al if");
			model.put("tipos_documento",personaService.findAllTiposDocumento());
			model.put("tipos_persona",personaService.findAllTiposPersona());
			model.put("departamentos",personaService.findAllDepartamentos());
			model.put("generos",personaService.findAllGeneros());
			model.put("estados_civiles",personaService.findAllEstadosCiviles());
			model.put("errorMessage","No se pudo procesar la solicitud...");
			return "persona/formRegistroDocente";
		}
		if(d.getId()==null) {
			Rol r= new Rol();
			r.setAuthority("ROLE_DOCENTE");
			//d.getRoles().add(r);
			r= new Rol();
			r.setAuthority("ROLE_USER");
			//d.getRoles().add(r);
			System.out.println("******************************entra solo cuando id es null****************************");
		}
		if(d.getPassword()!=null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			d.setPassword(passwordEncoder.encode(d.getPassword()));
		}
		d.setEnabled(true);
		personaService.save(d);
		status.setComplete();
		redirectAttributes.addFlashAttribute("successMessage", "Se ha guardado la información correctamente...");
		return "redirect:/usuarios";
	}*/
}
