package com.ufps.cedcufps;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.services.IPersonaService;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	protected Log logger = LogFactory.getLog(this.getClass());
	 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
   // https://www.baeldung.com/spring_redirect_after_login
    
    @Autowired
    private IPersonaService personaService;
    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("entra a sucess");
		//handle(request, response, authentication);
        clearAuthenticationAttributes(request);
        
        HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("url_prior_login");
            if (redirectUrl != null) {
                // we do not forget to clean this attribute from session
                session.removeAttribute("url_prior_login");
                // then we redirect
                redirectStrategy.sendRedirect(request, response, redirectUrl);
            } else {
            	handle(request, response, authentication);
            }
        } else {
        	handle(request, response, authentication);
        }
	}
	
	protected void handle(
	        HttpServletRequest request,
	        HttpServletResponse response, 
	        Authentication authentication
	) throws IOException {
		OAuth2AuthenticationToken auth=(OAuth2AuthenticationToken) authentication;
		String targetUrl =null;
	    System.out.println("authentication");
	    System.out.println(auth.getPrincipal().getAttribute("email").toString());
	    Persona p=personaService.findPersonaLogueada();
	    if(p==null) {
	    	 targetUrl = "/registrarse";
	    	 HttpSession session = request.getSession();
	 	     System.out.println("session");
	 	     System.out.println(session.getAttribute("email"));
	 	     
	    	 new SecurityContextLogoutHandler().logout(request, response, auth);
	    }else {
	    	targetUrl = "/nn";
	    }
	    if (response.isCommitted()) {
	        logger.debug(
	                "Response has already been committed. Unable to redirect to "
	                        + targetUrl);
	        return;
	    }
	    

	    
	    redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
	    
		HttpSession session = request.getSession(false);
		
	    if (session == null) {
	        return;
	    }
	    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
