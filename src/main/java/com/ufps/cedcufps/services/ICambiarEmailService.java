package com.ufps.cedcufps.services;

import com.ufps.cedcufps.dto.UsuarioDto;

public interface ICambiarEmailService {
	
	public void guardarSolicitudCambioEmail(String documento, String email);
	
	public UsuarioDto realizarSolicitudGenerada(String token);

}
