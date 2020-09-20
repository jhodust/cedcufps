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

import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@SessionAttributes("externo")
public class ExternoController {

	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/usuarios/externo/registro")
	public String agregar(Map<String, Object> model) {
		Externo e= new Externo();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("externo",e);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/formRegistroExterno";
	}
	
	@RequestMapping(value = "/usuarios/externo/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Externo e= (Externo)personaService.findOne(id).get();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("externo",e);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/formRegistroExterno";
	}
	
	@RequestMapping(value = "/usuarios/externo/registro", method = RequestMethod.POST)
	public String save(@Valid Externo e, BindingResult result,Map<String, Object> model, SessionStatus status,RedirectAttributes redirectAttributes) {
		//e.setTipoPersona(personaService.findByTipoPersona("Externo"));
		System.out.println("*******************************************************************");
		System.out.println(e.getFechaExpedicionDocumento());
		System.out.println(e.getFechaNacimiento());
		if(result.hasErrors()) {
			System.out.println("Entra al if");
			model.put("tipos_documento",personaService.findAllTiposDocumento());
			model.put("tipos_persona",personaService.findAllTiposPersona());
			model.put("programas",personaService.findAllProgramas());
			model.put("generos",personaService.findAllGeneros());
			model.put("estados_civiles",personaService.findAllEstadosCiviles());
			model.put("errorMessage","No se pudo procesar la solicitud...");
			return "persona/formRegistroExterno";
		}
		if(e.getId()==null) {
			Rol r= new Rol();
			r.setAuthority("ROLE_USER");
			//e.getRoles().add(r);
			System.out.println("******************************entra solo cuando id es null****************************");
		}
		if(e.getPassword()!=null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			e.setPassword(passwordEncoder.encode(e.getPassword()));
		}
		e.setEnabled(true);
		personaService.save(e);
		status.setComplete();
		redirectAttributes.addFlashAttribute("successMessage", "Se ha guardado la información correctamente...");
		return "redirect:/usuarios";
	}
}