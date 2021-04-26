package com.ufps.cedcufps;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.sun.tools.doclint.Env;
import com.ufps.cedcufps.auth.handler.LoginSuccessHandler;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.PersonaRol;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.modelos.SessionWebGoogle;
import com.ufps.cedcufps.services.PersonaService;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private Environment env;
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	

	private GoogleProperties googleProperties;
	
	public SpringSecurityConfig(GoogleProperties googleProp) {
		this.googleProperties=googleProp;
	}
	
	public static SessionWebGoogle getInfoSession() {
		// TODO Auto-generated constructor stub
		Authentication authentication =
			    SecurityContextHolder
			        .getContext()
			        .getAuthentication();
		try {
			
			OAuth2AuthenticationToken oauthToken =
				    (OAuth2AuthenticationToken) authentication;
			SessionWebGoogle session=new SessionWebGoogle();
			session.setName(oauthToken.getPrincipal().getAttribute("name").toString());
			session.setEmail(oauthToken.getPrincipal().getAttribute("email").toString());
			session.setPhoto(oauthToken.getPrincipal().getAttribute("picture").toString());
				return session;
		}catch(Exception e) {
			return null;
		}
			
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/","/change-email", "/update-email/**", "/bienvenida","/reload**","/registrarse","/files/**","/js/**","/css/**","/plantilla/**", "/logos/**","/app/**","/data/***").permitAll()
		.antMatchers("/preinscripcion/**", "/participaciones-educacion-continua**", "/certificaciones-educacion-continua**", "/perfil/**").hasRole("USER")
		.antMatchers("/facultades/**","/departamentos-academicos/**","/programas-academicos/**").hasRole("SUPERADMIN")
		.antMatchers("/educacion-continua/**").hasAnyRole("SUPERADMIN","MANAECCU")
		.antMatchers("/reportes-SNIES/**").hasAnyRole("SUPERADMIN","SNIES")
		.antMatchers("/usuarios/**").hasAnyRole("SUPERADMIN", "MANPEOPLE")
		.anyRequest().authenticated()
		.and()
		.logout().logoutSuccessUrl("/logout").logoutSuccessHandler(this.oidcLogoutSuccessHandler()).clearAuthentication(true).deleteCookies("oauth2_auth_request","JSESSIONID")
		.and().cors().configurationSource(corsConfigurationSource())
		.and()
		.oauth2Login(oauthLogin -> oauthLogin
	            
			.authorizedClientService(this.authorizedClientService())

			.failureHandler(customAuthenticationFailureHandler())
			.successHandler(customSuccessHandler())
			.authorizationEndpoint()
			.authorizationRequestRepository(authorizationRequestRepository()).and()/*
			.authorizationEndpoint()
			 	.baseUri("/login/oauth2/authorization")
			 	.and()
			/*.authorizationEndpoint()
			 .baseUri("/oauth2/code/google")
			 .and()*/
			/*.authorizationEndpoint()
				//.baseUri(this.authorizationRequestBaseUri())
				.authorizationRequestRepository(this.cookieAuthorizationRequestRepository())
				.and()
			/*.tokenEndpoint()
				.accessTokenResponseClient(this.accessTokenResponseClient())
				.and()*/
			.userInfoEndpoint()
				.oidcUserService(this.oidcUserService()));
		//.authorizedClientService(authorizedClientService())
			//.clientRegistrationRepository(this.clientRegistrationRepository())
			//.authorizationEndpoint()
				//.baseUri("/login/oauth2/authorization")
				//.authorizationRequestRepository(this.cookieAuthorizationRequestRepository())
				//.and()
				
		
				//.userAuthoritiesMapper(this.userAuthoritiesMapper());
		/*.and()
			.formLogin().successHandler(successHandler).loginPage("/login")
			.permitAll()
		.and()
		.logout().permitAll();
		
		/*
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.oauth2Login();*/
	}
	
	private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
		return new DefaultAuthorizationCodeTokenResponseClient();
	}

	private static List<String> clients = Arrays.asList("google");
	
	@Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
	
	@Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
	 
	
	public OAuth2AuthorizedClientService authorizedClientService() {
	 
	    return new InMemoryOAuth2AuthorizedClientService(
	      clientRegistrationRepository());
	}
	
	
	public ClientRegistrationRepository clientRegistrationRepository() {
	 return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
	 }

	
	private ClientRegistration googleClientRegistration() {
		
		return CommonOAuth2Provider.GOOGLE.getBuilder("google")
		 .clientId(this.googleProperties.getGoogleClientId())
		 .clientSecret(this.googleProperties.getGoogleClientSecret())
		 .build();
		 }

	
	private String authorizationRequestBaseUri() {
		// TODO Auto-generated method stub
		return OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI+"/"+this.googleProperties.getGoogleClientId();
	}

	 
	   
	
	
	 
	OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() { 
		OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository());
        successHandler.setPostLogoutRedirectUri(URI.create(this.googleProperties.getUrl()));
        return successHandler;
		
    }
	private AuthorizationRequestRepository<OAuth2AuthorizationRequest> cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}
	private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
		final OidcUserService delegate = new OidcUserService();

		return (userRequest) -> {
			// Delegate to the default implementation for loading a user
			OidcUser oidcUser = delegate.loadUser(userRequest);

			Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
			Persona p=personaService.findByEmail(oidcUser.getEmail());
			if(p!=null) {
				for(PersonaRol r:p.getPersonaXRoles()) {
					mappedAuthorities.add(new SimpleGrantedAuthority(r.getRol().getAuthority()));
				}
				oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
			}
			
			

			// TODO
			// 1) Fetch the authority information from the protected resource using accessToken
			// 2) Map the authority information to one or more GrantedAuthority's and add it to mappedAuthorities

			// 3) Create a copy of oidcUser but use the mappedAuthorities instead
			

			return oidcUser;
		};
	}
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	
	/*@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(personaService)
		.passwordEncoder(passwordEncoder);
	}*/


	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", this.googleProperties.getUrl()));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Bean
	public AuthorizationRequestRepository<OAuth2AuthorizationRequest> 
	  authorizationRequestRepository() {
	 
	    return new HttpSessionOAuth2AuthorizationRequestRepository();
	}
	
	

}
