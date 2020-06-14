package com.ufps.cedcufps;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilesConfig implements WebMvcConfigurer{

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		String resourcePath=Paths.get("files").toAbsolutePath().toUri().toString();
		registry.addResourceHandler("/files/**")
		.addResourceLocations(resourcePath);
		
		log.info("ruta de archivos");
		log.info(resourcePath);
	}

	
}
