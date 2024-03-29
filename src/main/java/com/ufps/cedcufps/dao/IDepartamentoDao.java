package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Programa;


public interface IDepartamentoDao extends PagingAndSortingRepository<Departamento, Long>{

	@Query("select d from Departamento d where d.facultad.facultad = ?1")
	public List<Departamento> findByFacultad(String facultad);
	
	
	@Query("select d from Departamento d where d.facultad.facultad = ?1")
	public Page<Departamento> findByFacultad(String facultad,Pageable pageable);
	
	@Query("select count(d) from Departamento d where d.id != ?1 and d.departamento = ?2")
	public int cantidadDeptosExistentes(Long idDepto, String depto);
	
	
	
}
