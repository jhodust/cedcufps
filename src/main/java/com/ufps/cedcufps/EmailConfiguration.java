package com.ufps.cedcufps;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class EmailConfiguration {
		
		
	
		private EmailProperties emailProperties;
		
		public EmailConfiguration(EmailProperties emailProperties) {
			this.emailProperties=emailProperties;
		}
		
		@Bean
		public JavaMailSender getJavaMailSender() {
		    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		    mailSender.setHost(emailProperties.getHost());
		    mailSender.setPort(Integer.parseInt(emailProperties.getPort()));
		    mailSender.setUsername(emailProperties.getUsername());
		    mailSender.setPassword(emailProperties.getPassword());
		    
		    Properties props = mailSender.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.debug", "true");
		    
		    return mailSender;
		}
	
}
