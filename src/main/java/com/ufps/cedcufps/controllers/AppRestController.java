package com.ufps.cedcufps.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IJornadaService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.utils.Encrypt;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

@RestController
@RequestMapping("/app")
public class AppRestController {

	private static final String idDebug="877594372056-aegs95db4kk73knee04esc80phfn3lbp.apps.googleusercontent.com";
	private static final String idPro="362769569855-et1d0fucmaneh2urgfggho49migpbss6.apps.googleusercontent.com";
									   
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping(value="/validateLoginOutlook", produces = "application/json")
    public ResponseEntity<?> validarLoginOutlook(@RequestParam(name = "email", required = true) String email) {
		System.out.println("***********************************");
		System.out.println("***********************************");
		System.out.println("entra a login outlook");
		System.out.println("email: " + email);
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/validateLogin/{token}", produces = "application/json")
    public ResponseEntity<?> validarLogin(@PathVariable String token) {
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),  new JacksonFactory())
			    // Specify the CLIENT_ID of the app that accesses the backend:
			   .setAudience(Collections.singletonList(idPro))
			    // Or, if multiple clients access the backend:
			    //.setAudience(Arrays.asList(idDebug, idPro))
			    .build();

			// (Receive idTokenString by HTTPS POST)

			GoogleIdToken idToken=null;
			try {
				idToken = verifier.verify(token);
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (idToken != null) {
			  Payload payload = idToken.getPayload();

			  // Print user identifier
			  String userId = payload.getSubject();
			  
			  // Get profile information from payload
			  String email = payload.getEmail();
			  /*boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			  String name = (String) payload.get("name");
			  String pictureUrl = (String) payload.get("picture");
			  String locale = (String) payload.get("locale");
			  String familyName = (String) payload.get("family_name");
			  String givenName = (String) payload.get("given_name");
				*/
			  // Use or store profile information
			  // ...
			  
			  Persona p= personaService.findByEmail(email);
		        if(p!=null) {
		        	if(buscarPermisosAdminsitradorCursosYEventos(p)) {
		        		return  new ResponseEntity<>(personaService.convertPersonaLogueadaApp(p),HttpStatus.OK);
		        	}else {
		        		return  new ResponseEntity<>("No cuenta con permisos para administrar cursos y/o eventos de educación continua",HttpStatus.FORBIDDEN);
		        	}
		        	
		        }
			} else {
			  return  new ResponseEntity<>("Invalid ID token",HttpStatus.UNAUTHORIZED);
			}
		
		return  new ResponseEntity<>("Usuario no existe",HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	public boolean buscarPermisosAdminsitradorCursosYEventos(Persona p) {
		if(personaService.hasPermissionForAttendance(p) || personaService.isSuperAdmin(p) || personaService.hasPermissionForEduContinua(p.getId())) {

			return true;
		}
		
		return false;
	}
	
	
	@GetMapping(value="/misCursosYEduContinua/{idPersona}", produces = "application/json")
    public ResponseEntity<List<EducacionContinuaAppDto>> searchCursosYEventos(@PathVariable Long idPersona) {
        
        return  new ResponseEntity<>(educacionContinuaService.findAllEducacionesApp(idPersona),HttpStatus.OK);
    }
	
	@GetMapping(value="/jornadasEducacionContinua/{idEducacionContinua}", produces = "application/json")
    public ResponseEntity<List<JornadaAppDto>> searchJornadasCursosYEventos(@PathVariable Long idEducacionContinua) {
        
        return  new ResponseEntity<>(educacionContinuaService.findAllJornadasByEduContinuaApp(idEducacionContinua),HttpStatus.OK);
    }
	
	@GetMapping(value="/asistencia/{idEducacionContinua}/{idJornada}/{qr}", produces = "application/json")
    public ResponseEntity<?> tomarAsistenciaApp(@PathVariable Long idEducacionContinua, @PathVariable Long idJornada, @PathVariable String qr) {

		Map<Integer, ParticipanteDto> map=educacionContinuaService.tomarAsistencia(idEducacionContinua, idJornada, qr);
		int codigo=map.keySet().iterator().next();
		System.out.println("codigoooooooooooooooooooooooooooooo");
		if(codigo==200) {
			return  new ResponseEntity<>(map.get(codigo),HttpStatus.OK);
		}
		
		if(codigo==412) {
			return  new ResponseEntity<>("El participante no se encuentra inscrito",HttpStatus.PRECONDITION_FAILED);
		}
		
		if(codigo==400) {
			 return  new ResponseEntity<>("No se encontró la jornada seleccionada en la base de datos",HttpStatus.BAD_REQUEST);
		}
		if(codigo==500) {
			 return  new ResponseEntity<>("Código QR inválido",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return  new ResponseEntity<>("El participante ya había registrado su asistencia",HttpStatus.CONFLICT);

    }
	
	
	
}
