package com.ufps.cedcufps.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ufps.cedcufps.modelos.Facultad;

public interface IFacultadDao extends PagingAndSortingRepository<Facultad, Long>{

	public Facultad findByFacultad(String facultad);
}
