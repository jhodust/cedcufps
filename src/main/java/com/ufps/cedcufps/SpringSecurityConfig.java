package com.ufps.cedcufps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.core.GrantedAuthority;

import com.ufps.cedcufps.auth.handler.LoginSuccessHandler;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.PersonaService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PersonaService personaService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/","/files/**","/pdfreport","/home","/plantilla/**", "/logos/**","/data/**","/js/**","/css/**","/programa/save", "/uploads/**").permitAll()
		.antMatchers("/persona/**").hasAnyRole("SUPERADMIN", "ESTUDIANTE")
		.antMatchers("/programa/**").hasAnyRole("SUPERADMIN")
		.antMatchers("/tipo-documento/**").hasAnyRole("SUPERADMIN")
		.antMatchers("/educacion-continua/**").hasAnyRole("SUPERADMIN","ADMIN","DOCENTE","DIRPROGRAMA")
		.antMatchers("/participaciones-educacion-continua","/preinscripcion/**", "/realizar-inscripcion/**","/cancelar-inscripcion/**").hasRole("USER")
		.antMatchers("/educacion-continua-a-cargo").hasRole("DOCENTE")
		.anyRequest().authenticated()
		.and()
		.oauth2Login()
			.userInfoEndpoint()
				.oidcUserService(oidcUserService())
				.oidcUserService(this.oidcUserService());
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
	
	
	
	private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
		final OidcUserService delegate = new OidcUserService();

		return (userRequest) -> {
			// Delegate to the default implementation for loading a user
			OidcUser oidcUser = delegate.loadUser(userRequest);

			OAuth2AccessToken accessToken = userRequest.getAccessToken();
			System.out.println("email");
			System.out.println(oidcUser.getEmail());
			Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
			Persona p=personaService.findByEmail(oidcUser.getEmail());
			for(Rol r:p.getRoles()) {
				mappedAuthorities.add(new SimpleGrantedAuthority(r.getAuthority()));
			}

			// TODO
			// 1) Fetch the authority information from the protected resource using accessToken
			// 2) Map the authority information to one or more GrantedAuthority's and add it to mappedAuthorities

			// 3) Create a copy of oidcUser but use the mappedAuthorities instead
			oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());

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
					for(Rol r:p.getRoles()) {
						mappedAuthorities.add(new SimpleGrantedAuthority(r.getAuthority()));
					}
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


	
	
	
}
