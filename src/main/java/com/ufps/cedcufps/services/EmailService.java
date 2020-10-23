package com.ufps.cedcufps.services;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	
    @Autowired
    private JavaMailSender mailSender;

    @Async
    @Override
    public void sendEmailInscripcion(String remitente, String asunto, String contenido, String file, String nombre) {
    	
    	MimeMessage msg= mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(msg);
    	
    	try {
			helper.setTo(remitente);
			helper.setSubject(asunto);
			
			Multipart emailContent=new MimeMultipart();
			
			MimeBodyPart texto= new MimeBodyPart();
			texto.setText(contenido);
			
			File f= new File(file) ;
			LOGGER.info(f.getAbsolutePath());
			MimeBodyPart archivo= new MimeBodyPart();
			archivo.attachFile(f.getAbsolutePath());
			
			emailContent.addBodyPart(texto);
			emailContent.addBodyPart(archivo);
			
			msg.setContent(emailContent);
			mailSender.send(msg);
    	} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("Mail enviado!");
    	
        
    }
}