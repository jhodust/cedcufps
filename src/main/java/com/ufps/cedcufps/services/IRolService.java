package com.ufps.cedcufps.services;

import com.ufps.cedcufps.modelos.Rol;

public interface IRolService {

	public void save(Rol r);
	
	public void deleteRol(String rol, Long idPersona);
}
