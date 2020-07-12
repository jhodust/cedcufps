package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufps.cedcufps.modelos.Facultad;

public interface IFacultadService {

	public List<Facultad> findAll();
	
	public Page<Facultad> findAll(Pageable pageable);
	
	public void save(Facultad p);
	
	public Facultad findOne(Long id);
	
	public Facultad findByFacultad(String facultad);
}
