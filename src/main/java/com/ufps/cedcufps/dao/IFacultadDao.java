package com.ufps.cedcufps.dao;

import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Facultad;

public interface IFacultadDao extends CrudRepository<Facultad, Long>{

	public Facultad findByFacultad(String facultad);
}
