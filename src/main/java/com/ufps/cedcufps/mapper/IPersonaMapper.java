package com.ufps.cedcufps.mapper;

import java.util.List;

import com.ufps.cedcufps.modelos.Persona;

public interface IPersonaMapper {
 
	public List<Persona> convertListObjectToListPersonas(List<Object[]> resultPersonas);
	
	public Persona convertObjectToPersona(Object[] persona);
}
