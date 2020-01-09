package com.ufps.cedcufps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ufps.cedcufps.auth.handler.LoginSuccessHandler;
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
		http.authorizeRequests().antMatchers("/template/**","/images/**").permitAll()
		.antMatchers("/persona/**").hasAnyRole("ADMIN")
		.antMatchers("/programa/**").hasAnyRole("ADMIN")
		.antMatchers("/tipo-documento/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
			.formLogin().successHandler(successHandler).loginPage("/login")
			.permitAll()
		.and()
		.logout().permitAll();
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
