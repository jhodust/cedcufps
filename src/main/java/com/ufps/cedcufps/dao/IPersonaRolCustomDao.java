package com.ufps.cedcufps.dao;

import com.ufps.cedcufps.modelos.PersonaRol;

public interface IPersonaRolCustomDao {

	public void save(PersonaRol p);
	
	public void deleteRolesDirPrograma(Long idPersona);
	
}
