package com.ufps.cedcufps.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Persona;

public interface IPersonaDao extends CrudRepository<Persona, Long> {

	public Persona findByUsername(String username);
	
	public Optional<Persona> findByEmail(String email);
	
	@Query("select p from Persona p where p.id IN (?1)")
	public List<Persona> findManyPeople(List<Long> idsPersonas);
	

}
