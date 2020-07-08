package com.ufps.cedcufps.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.modelos.Facultad;

@Repository
public interface IFacultadDao extends PagingAndSortingRepository<Facultad, Long>{

	public Facultad findByFacultad(String facultad);
}
