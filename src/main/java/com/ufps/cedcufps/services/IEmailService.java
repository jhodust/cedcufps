package com.ufps.cedcufps.services;

import java.util.Date;

public interface IEmailService {

	public void sendEmailInscripcion(String remitente, String asunto,  String file, String contenido, String nombreParticipante, boolean adjuntarImagen);
}