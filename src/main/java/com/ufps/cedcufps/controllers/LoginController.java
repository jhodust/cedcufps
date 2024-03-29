package com.ufps.cedcufps.controllers;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	
	@GetMapping("/loginSuccess")
	public String loginSuccess(Model model, OAuth2AuthenticationToken authentication) {
	    OAuth2AuthorizedClient client = authorizedClientService
	      .loadAuthorizedClient(
	        authentication.getAuthorizedClientRegistrationId(), 
	          authentication.getName());
	    
	    return "redirect:/";
	}
	
	@GetMapping("/loginFailureee")
	public String loginFailure(Model model, OAuth2AuthenticationToken authentication) {
	    
	    return "redirect:/";
	}
	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	    	
	        new SecurityContextLogoutHandler().logout(request, null, null);
	        //new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    
	    SecurityContextHolder.clearContext();
	    auth.setAuthenticated(false);
        try {
			request.logout();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        request.getSession().invalidate();
        SecurityContextHolder.getContext().setAuthentication(null);
	    return "redirect:/";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	
}
