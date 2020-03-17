package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import com.ufps.cedcufps.modelos.Programa;

public interface IProgramaService {

	public List<Programa> findAll();
	
	public void save(Programa p);
	
	public Optional<Programa> findOne(Long id);
	
	public List<Programa> findByFacultad(String facultad);
}
