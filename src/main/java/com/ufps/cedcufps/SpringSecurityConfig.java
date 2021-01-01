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
		http.authorizeRequests().antMatchers("/","/email","/registrarse","/search/programa/**","/files/**","/pdfreport","/home","/plantilla/**", "/logos/**","/data/**","/js/**","/css/**","/programa/save", "/uploads/**","/app/**","/upload_image/**").permitAll()
		.antMatchers("/persona/**").hasAnyRole("SUPERADMIN", "MANPEOPLE")
		.antMatchers("/programa/**").hasAnyRole("SUPERADMIN")
		.antMatchers("/tipo-documento/**").hasAnyRole("SUPERADMIN")
		.antMatchers("/educacion-continua/**").hasAnyRole("SUPERADMIN","MANAECCU")
		.antMatchers("/participaciones-educacion-continua","/preinscripcion/**", "/realizar-inscripcion/**","/cancelar-inscripcion/**").hasRole("USER")
		.antMatchers("/educacion-continua-a-cargo").hasRole("MANAECCU")
		.anyRequest().authenticated()
		//.and().cors().configurationSource(corsConfigurationSource())
		.and()
		.oauth2Login()
			.failureHandler(customAuthenticationFailureHandler())
			.successHandler(customSuccessHandler())
			/*.authorizationEndpoint()
				.baseUri(this.authorizationRequestBaseUri())
				.authorizationRequestRepository(this.cookieAuthorizationRequestRepository())
				.and()
			.tokenEndpoint()
				.accessTokenResponseClient(this.accessTokenResponseClient())
				.and()*/
			.userInfoEndpoint()
				.oidcUserService(this.oidcUserService())
		//.authorizedClientService(authorizedClientService())
			//.clientRegistrationRepository(this.clientRegistrationRepository())
			//.authorizationEndpoint()
				//.baseUri("/login/oauth2/authorization")
				//.authorizationRequestRepository(this.cookieAuthorizationRequestRepository())
				//.and()
				
		.and()
		.and().logout().logoutSuccessUrl("/logoutt").logoutSuccessHandler(this.oidcLogoutSuccessHandler()).clearAuthentication(true).deleteCookies("oauth2_auth_request","JSESSIONID").permitAll();
		
		
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
		System.out.println("nueva peticion");
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
	
	
	private String authorizationRequestBaseUri() {
		// TODO Auto-generated method stub
		System.out.println("url");
		System.out.println(OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI+"/226613155821-ri427lmire8qq5icol9s20srnuvh2vqg.apps.googleusercontent.com");
		return OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI+"/226613155821-ri427lmire8qq5icol9s20srnuvh2vqg.apps.googleusercontent.com";
	}

	 
	    public ClientRegistrationRepository clientRegistrationRepository() {
	        List<ClientRegistration> registrations = clients.stream()
	          .map(c -> getRegistration(c))
	          .filter(registration -> registration != null)
	          .collect(Collectors.toList());
	        
	        return new InMemoryClientRegistrationRepository(registrations);
	    }
	 
	 private static String CLIENT_PROPERTY_KEY 
	  = "spring.security.oauth2.client.registration.";
	 
	
	private ClientRegistration getRegistration(String client) {
	    String clientId = env.getProperty(
	      CLIENT_PROPERTY_KEY + client + ".client-id");
	 
	    if (clientId == null) {
	        return null;
	    }
	 
	    String clientSecret = env.getProperty(
	      CLIENT_PROPERTY_KEY + client + ".client-secret");
	 
	    if (client.equals("google")) {
	        return CommonOAuth2Provider.GOOGLE.getBuilder(client)
	          .clientId(clientId).clientSecret(clientSecret).build();
	    }
	    return null;
	}
	 
	OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() { 
		OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri(URI.create("http://localhost:8080/"));
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
				for(PersonaRol r:p.getRoles()) {
					System.out.println("imprimiendo informacion");
					System.out.println(r.getPersona().getPrimerNombre());
					System.out.println(r.getRol().getAuthority());
					mappedAuthorities.add(new SimpleGrantedAuthority(r.getRol().getAuthority()));
				}
				oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
			}else {
				System.out.println("no es usuario registrado");
				
				//OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
		        //successHandler.setPostLogoutRedirectUri(URI.create("http://localhost:8080/"));
		        //return null;
			}
			
			

			// TODO
			// 1) Fetch the authority information from the protected resource using accessToken
			// 2) Map the authority information to one or more GrantedAuthority's and add it to mappedAuthorities

			// 3) Create a copy of oidcUser but use the mappedAuthorities instead
			

			return oidcUser;
		};
	}
	
	
	private GrantedAuthoritiesMapper userAuthoritiesMapper() {
		System.out.println("entra a mapper");
		int i=0;
		return (authorities) -> {
			
			System.out.println("entra a return");
			Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

			authorities.forEach(authority -> {
				
				if(authority.getAuthority()=="ROLE_USER") {
					System.out.println("authority");
					System.out.println(authority);
					OAuth2UserAuthority oauth2UserAuthority = (OAuth2UserAuthority)authority;
					
					Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();
					System.out.println("email");
					System.out.println(userAttributes.get("email"));
					Persona p=personaService.findByEmail(String.valueOf(userAttributes.get("email")));
					List<GrantedAuthority> authoritiess= new ArrayList<GrantedAuthority>();
					/*for(Rol r:p.getRoles()) {
						mappedAuthorities.add(new SimpleGrantedAuthority(r.getAuthority()));
					}*/
					mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPERADMIN"));
				}
				
				
				if (OidcUserAuthority.class.isInstance(authority)) {
					OidcUserAuthority oidcUserAuthority = (OidcUserAuthority)authority;

					OidcIdToken idToken = oidcUserAuthority.getIdToken();
					OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();

					// Map the claims found in idToken and/or userInfo
					// to one or more GrantedAuthority's and add it to mappedAuthorities

				} else if (OAuth2UserAuthority.class.isInstance(authority)) {
					/*OAuth2UserAuthority oauth2UserAuthority = (OAuth2UserAuthority)authority;
					
					Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();
					System.out.println("email");
					System.out.println(userAttributes.get("email"));
					*/
					// Map the attributes found in userAttributes
					// to one or more GrantedAuthority's and add it to mappedAuthorities

				}
			});

			return mappedAuthorities;
		};
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(personaService)
		.passwordEncoder(passwordEncoder);
	}


	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
}
