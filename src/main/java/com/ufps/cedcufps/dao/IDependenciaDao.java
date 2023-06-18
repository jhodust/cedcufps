package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ufps.cedcufps.modelos.Dependencia;

public interface IDependenciaDao extends PagingAndSortingRepository<Dependencia, Long> {
	
	public Dependencia findByDependencia(String dependencia);
}
