package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ufps.cedcufps.modelos.Departamento;


public interface IDepartamentoDao extends CrudRepository<Departamento, Long>{

	@Query("select d from Departamento d where d.facultad.facultad = ?1")
	public List<Departamento> findByFacultad(String facultad);
	
	public Departamento findByDepartamento(String departamento);
}
