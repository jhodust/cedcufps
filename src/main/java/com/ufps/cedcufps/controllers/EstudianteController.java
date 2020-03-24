package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@SessionAttributes("estudiante")
public class EstudianteController {

	@Autowired
	private IPersonaService personaService;
	
	
	@RequestMapping(value = "/usuarios/estudiante/registro")
	public String agregar(Map<String, Object> model) {
		Estudiante e= new Estudiante();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("estudiante",e);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/formRegistroEstudiante";
	}
	
	@RequestMapping(value = "/usuarios/estudiante/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Estudiante e= (Estudiante)personaService.findOne(id).get();
		model.put("titulo","FORMULARIO PERSONA");
		model.put("estudiante",e);
		model.put("tipos_documento",personaService.findAllTiposDocumento());
		model.put("tipos_persona",personaService.findAllTiposPersona());
		model.put("programas",personaService.findAllProgramas());
		model.put("generos",personaService.findAllGeneros());
		model.put("estados_civiles",personaService.findAllEstadosCiviles());
		return "persona/formRegistroEstudiante";
	}
	
	
	@RequestMapping(value = "/usuarios/estudiante/registro", method = RequestMethod.POST)
	public String save(Estudiante e, SessionStatus status) {
		e.setTipoPersona(personaService.findByTipoPersona("Estudiante"));
		System.out.println("*******************************************************************");
		System.out.println(e.getFechaExpedicionDocumento());
		System.out.println(e.getFechaNacimiento());
		if(e.getId()==null) {
			Rol r= new Rol();
			r.setAuthority("ROLE_ESTUDIANTE");
			e.getRoles().add(r);
			System.out.println("******************************entra solo cuando id es null****************************");
		}
		if(e.getPassword()!=null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			e.setPassword(passwordEncoder.encode(e.getPassword()));
		}
		e.setEnabled(true);
		personaService.save(e);
		status.setComplete();
		
		return "redirect:/usuarios";
	}
}
