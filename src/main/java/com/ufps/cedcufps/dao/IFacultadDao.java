package com.ufps.cedcufps.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.modelos.Facultad;

public interface IFacultadDao extends PagingAndSortingRepository<Facultad, Long>{

	public Facultad findByFacultad(String facultad);
	
	@Query("select count(f) from Facultad f where f.id != ?1 and f.facultad = ?2")
	public int cantidadFacultadesExistentes(Long idF, String facultad);
}
