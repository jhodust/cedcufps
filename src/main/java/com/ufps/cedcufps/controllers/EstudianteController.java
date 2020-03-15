package com.ufps.cedcufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@SessionAttributes("estudiante")
public class EstudianteController {

	@Autowired
	private IPersonaService personaService;
	
	
	
	@RequestMapping(value = "/usuarios/estudiante/registro", method = RequestMethod.POST)
	public String save(Estudiante e, SessionStatus status) {
		e.setTipoPersona(personaService.findByTipoPersona("Estudiante"));
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
		String[] datos=e.getMunicipioNacimiento().split("-");
		e.setMunicipioNacimiento(datos[0]);
		e.setIdMunicipioNacimiento(datos[1]);
		
		datos=e.getPaisNacimiento().split("-");
		e.setPaisNacimiento(datos[0]);
		e.setIdPaisNacimiento(datos[1]);
		e.setEnabled(true);
		personaService.save(e);
		status.setComplete();
		return "redirect:/usuarios";
	}
}
