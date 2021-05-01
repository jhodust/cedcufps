package com.ufps.cedcufps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class GoogleProperties {
	
	@Value("${spring.security.oauth2.client.registration.google.client-id}") 
	private String googleClientId;
	
	@Value("${spring.security.oauth2.client.registration.google.client-secret}") 
	private String googleClientSecret;
	
	@Value("${server.servlet.url}") 
	private String url;
	
	@Value("${server.servlet.context-path}") 
	private String contextPath;
	
	@Value("${spring.security.oauth2.appdebug}") 
	private String appDebug;
	
	@Value("${spring.security.oauth2.appproduccion}") 
	private String appProduccion;
	
	public String getGoogleClientId() {
		return googleClientId;
	}
	public void setGoogleClientId(String googleClientId) {
		this.googleClientId = googleClientId;
	}
	public String getGoogleClientSecret() {
		return googleClientSecret;
	}
	public void setGoogleClientSecret(String googleClientSecret) {
		this.googleClientSecret = googleClientSecret;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAppDebug() {
		return appDebug;
	}
	public void setAppDebug(String appDebug) {
		this.appDebug = appDebug;
	}
	public String getAppProduccion() {
		return appProduccion;
	}
	public void setAppProduccion(String appProduccion) {
		this.appProduccion = appProduccion;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	
}
