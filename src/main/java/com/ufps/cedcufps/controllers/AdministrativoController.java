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
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@SessionAttributes("administrativo")
public class AdministrativoController {

	@Autowired
	private IPersonaService personaService;
	
	/*@RequestMapping(value = "/usuarios/administrativo/registro")
	public String agregar(Map<String, Object> model) {
		Administrativo a= new Administrativo();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("administrativo",a);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/formRegistroAdministrativo";
	}*/
	
	/*@RequestMapping(value = "/usuarios/administrativo/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Administrativo a= (Administrativo)personaService.findOne(id).get();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("administrativo",a);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/formRegistroAdministrativo";
	}*/
	
	/*@RequestMapping(value = "/usuarios/administrativo/registro", method = RequestMethod.POST)
	public String save(@Valid Administrativo a, BindingResult result,Map<String, Object> model, SessionStatus status,RedirectAttributes redirectAttributes) {
		//a.setTipoPersona(personaService.findByTipoPersona("Administrativo"));
		System.out.println("*******************************************************************");
		System.out.println(a.getFechaExpedicionDocumento());
		System.out.println(a.getFechaNacimiento());
		if(result.hasErrors()) {
			System.out.println("Entra al if");
			model.put("tipos_documento",personaService.findAllTiposDocumento());
			model.put("tipos_persona",personaService.findAllTiposPersona());
			model.put("programas",personaService.findAllProgramas());
			model.put("generos",personaService.findAllGeneros());
			model.put("estados_civiles",personaService.findAllEstadosCiviles());
			model.put("errorMessage","No se pudo procesar la solicitud...");
			return "persona/formRegistroAdministrativo";
		}
		if(a.getId()==null) {
			Rol r= new Rol();
			r.setAuthority("ROLE_USER");
			//a.getRoles().add(r);
			System.out.println("******************************entra solo cuando id es null****************************");
		}
		if(a.getPassword()!=null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			a.setPassword(passwordEncoder.encode(a.getPassword()));
		}
		a.setEnabled(true);
		personaService.save(a);
		status.setComplete();
		redirectAttributes.addFlashAttribute("successMessage", "Se ha guardado la información correctamente...");
		return "redirect:/usuarios";

	}*/
}