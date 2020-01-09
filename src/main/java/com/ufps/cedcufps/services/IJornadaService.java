package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import com.ufps.cedcufps.modelos.Jornada;

public interface IJornadaService {

	public List<Jornada> findAllByIdEducacionContinua(Long idEducacionContinua);
	
	public void save(Jornada j);
	
	public Optional<Jornada> findOne(Long id);
}
