package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufps.cedcufps.modelos.Departamento;

public interface IDepartamentoService {

	public List<Departamento> findAll();
	
	public Page<Departamento> findAll(Pageable pageable);
	
	public void save(Departamento d);
	
	public Departamento findOne(Long id);
	
	public List<Departamento> findByFacultad(String facultad);
	
	public Page<Departamento> findByFacultad(String facultad,Pageable pageable);
}
