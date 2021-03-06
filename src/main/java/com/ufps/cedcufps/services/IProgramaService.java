package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Programa;

public interface IProgramaService {

	public List<Programa> findAll();
	
	public Page<Programa> findAll(Pageable pageable);
	
	public void save(Programa p);
	
	public Optional<Programa> findOne(Long id);
	
	public List<Programa> findByFacultad(String facultad);
	
	public Page<Programa> findByFacultad(String facultad,Pageable pageable);
}
