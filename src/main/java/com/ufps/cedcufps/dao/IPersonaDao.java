package com.ufps.cedcufps.dao;

import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Persona;

public interface IPersonaDao extends CrudRepository<Persona, Long> {

<<<<<<< HEAD
=======
	public Persona findByUsername(String username);
	
	
	
>>>>>>> preparacion
}
