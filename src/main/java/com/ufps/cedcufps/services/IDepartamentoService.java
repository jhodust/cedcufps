package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import com.ufps.cedcufps.modelos.Departamento;

public interface IDepartamentoService {

	public List<Departamento> findAll();
	
	public void save(Departamento p);
	
	public Optional<Departamento> findOne(Long id);
}
