package com.ufps.cedcufps.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.services.IPersonaService;

public class EntityAuditorAware implements AuditorAware<String> {

	@Autowired
	private IPersonaService personaService;
	
    public Optional<String> getCurrentAuditor() {
        return Optional.of(personaService.findEmailPersonaLogueada());
    }
}
