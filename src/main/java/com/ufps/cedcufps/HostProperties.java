package com.ufps.cedcufps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring")
public class HostProperties {

	@Value("${spring.url}") 
	private String url;

	public String getHost() {
		return url;
	}

	public void setHost(String host) {
		this.url = host;
	}
	
	
}
