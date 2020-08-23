package com.ufps.cedcufps.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IPersonaService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

@RestController
@RequestMapping("/app")
public class AppRestController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping(value="/validateLogin/{token}", produces = "application/json")
    public ResponseEntity<?> validarLogin(@PathVariable String token) {
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),  new JacksonFactory())
			    // Specify the CLIENT_ID of the app that accesses the backend:
			    .setAudience(Collections.singletonList("460942427313-o7mqvi3ksnuvslpahmkdgsm7u24qnhkt.apps.googleusercontent.com"))
			    // Or, if multiple clients access the backend:
			    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
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
			  System.out.println("User ID: " + userId);

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
			  
			  System.out.println("email");
			  System.out.println(email);
			  Persona p= personaService.findByEmail(email);
		        if(p!=null) {
		        	if(buscarPermisosAdminsitradorCursosYEventos(p)) {
		        		return  new ResponseEntity<>(personaService.convertPersonaLogueadaApp(p),HttpStatus.OK);
		        	}else {
		        		return  new ResponseEntity<>("No cuenta con permisos para administrar cursos y/o eventos de educación continua",HttpStatus.FORBIDDEN);
		        	}
		        	
		        }
			} else {
			  System.out.println("Invalid ID token.");
			  return  new ResponseEntity<>("Invalid ID token",HttpStatus.UNAUTHORIZED);
			}
		
		return  new ResponseEntity<>("Usuario no existe",HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	public boolean buscarPermisosAdminsitradorCursosYEventos(Persona p) {
		for(Rol r:p.getRoles()) {
			if(r.getAuthority().equalsIgnoreCase("ROLE_ADMIN_CEEC") || r.getAuthority().equalsIgnoreCase("ROLE_SUPERADMIN")) {
				return true;
			}
			
		}
		return false;
	}
	
	
	@GetMapping(value="/misCursosYEduContinua/{idPersona}", produces = "application/json")
    public ResponseEntity<?> searchCursosYEventos(@PathVariable Long idPersona) {
        
        return  new ResponseEntity<>(educacionContinuaService.findAllEducacionesApp(),HttpStatus.OK);
    }
	
	@GetMapping(value="/jornadasEducacionContinua/{idEducacionContinua}", produces = "application/json")
    public ResponseEntity<?> searchJornadasCursosYEventos(@PathVariable Long idEducacionContinua) {
        
        return  new ResponseEntity<>(educacionContinuaService.findAllJornadasByEduContinuaApp(idEducacionContinua),HttpStatus.OK);
    }
	
	@GetMapping(value="/asistencia/{idEducacionContinua}/{qr}", produces = "application/json")
    public ResponseEntity<?> searchJornadasCursosYEventos(@PathVariable Long idEducacionContinua, @PathVariable String qr) {
        
        return  new ResponseEntity<>("Se tomó asistencia exitosamente",HttpStatus.OK);
    }
	
	
	
}
