package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Programa;

public interface IProgramaDao extends PagingAndSortingRepository<Programa, Long>{

	@Query("select p from Programa p where p.facultad.facultad = ?1")
	public List<Programa> findByFacultad(String facultad);
	
	@Query("select p from Programa p where p.facultad.facultad = ?1")
	public Page<Programa> findByFacultad(String facultad,Pageable pageable);
}
