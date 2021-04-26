package com.ufps.cedcufps.services;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ufps.cedcufps.GoogleProperties;
import com.ufps.cedcufps.HostProperties;

@Service
public class EmailService implements IEmailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    private HostProperties hostProperties;
    
    public EmailService(HostProperties hostProperties) {
		this.hostProperties=hostProperties;
	}

    @Async
    @Override
    public void sendEmailInscripcion(String remitente, String asunto,  String file, String contenido, String nombreParticipante, boolean adjuntarImagen) {
    	
    	LOGGER.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    	LOGGER.debug("preparando email INSCRIPCIONNNNNNNNNNN");
    	LOGGER.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    	LOGGER.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		try {
		
		Locale locale= new Locale("es");
		
		
		// Preparando las variables para la plantilla
	    final Context ctx = new Context(locale);
	    ctx.setVariable("participante", nombreParticipante);
	    ctx.setVariable("contenido", contenido);
	    ctx.setVariable("logo", new File("img/geduco.png").getAbsolutePath());
	   
	    // generando plantilla con las variables
	    final String htmlContent = this.templateEngine.process("email/plantillaEducacionContinua.html", ctx);
	    
	    
	    // Preparando email
	    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	    final MimeMessageHelper message =
	        new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
	    message.setSubject(asunto);
	    message.setTo(remitente);
	    
	    message.setText(htmlContent, true); // true = isHtml

	    
	    //adjuntando imagen
	    if(adjuntarImagen) {
	    	if(file != null) {
	    		File f= new File(file);
		 	    message.addAttachment("tarjeta inscripción", f);
	    	}
	    	
	    }
	   
	    
	    // enviando  mail
	    this.mailSender.send(mimeMessage);
	    LOGGER.debug("enviando email");
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @Async
    @Override
    public void sendEmailRegistro(String remitente, String asunto,  String contenido, String nombreUsuario) {
    	
    	LOGGER.debug("preparando email");
		
		try {
		
		Locale locale= new Locale("es");
		
		
		// Preparando las variables para la plantilla
	    final Context ctx = new Context(locale);
	    ctx.setVariable("usuario", nombreUsuario);
	    ctx.setVariable("contenido", contenido);
	    
	    // generando plantilla con las variables
	    final String htmlContent = this.templateEngine.process("email/plantillaRegistro.html", ctx);
	    
	    
	    // Preparando email
	    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	    final MimeMessageHelper message =
	        new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
	    message.setSubject(asunto);
	    message.setTo(remitente);
	    
	    message.setText(htmlContent, true); // true = isHtml

	    
	   
	    
	    // enviando  mail
	    this.mailSender.send(mimeMessage);
	    LOGGER.debug("enviando email");
		}catch(Exception e) {
			e.printStackTrace();
		}
    }

    @Async
	@Override
	public void sendEmailActualización(String remitente, String asunto, String contenido, String token, String persona) {
		// TODO Auto-generated method stub
		LOGGER.debug("preparando email actualizacion");
		try {
		
		Locale locale= new Locale("es");
		
		
		// Preparando las variables para la plantilla
	    final Context ctx = new Context(locale);
	    ctx.setVariable("contenido", contenido);
	    ctx.setVariable("persona", persona);
	    
	    ctx.setVariable("url", this.hostProperties.getHost().concat("/update-email/").concat(token));
	   
	  
	    // generando plantilla con las variables
	    final String htmlContent = this.templateEngine.process("email/plantillaActualizacionDatos.html", ctx);
	    
	    
	    // Preparando email
	    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	    final MimeMessageHelper message =
	        new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
	    message.setSubject(asunto);
	    message.setTo(remitente);
	    
	    message.setText(htmlContent, true); // true = isHtml

	    
	   
	   
	    
	    // enviando  mail
	    this.mailSender.send(mimeMessage);
	    LOGGER.debug("enviando email");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}