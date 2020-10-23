package com.ufps.cedcufps.services;

public interface IEmailService {

	public void sendEmailInscripcion(String remitente, String asunto, String contenido, String file, String nombre);
}
