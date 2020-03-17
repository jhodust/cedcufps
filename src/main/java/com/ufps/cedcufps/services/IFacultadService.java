package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;
import com.ufps.cedcufps.modelos.Facultad;

public interface IFacultadService {

	public List<Facultad> findAll();
	
	public void save(Facultad p);
	
	public Optional<Facultad> findOne(Long id);
	
	public Facultad findByFacultad(String facultad);
}
