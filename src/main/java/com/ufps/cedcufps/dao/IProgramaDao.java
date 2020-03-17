package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ufps.cedcufps.modelos.Programa;

public interface IProgramaDao extends CrudRepository<Programa, Long>{

	@Query("select p from Programa p where p.facultad.facultad = ?1")
	public List<Programa> findByFacultad(String facultad);
}
