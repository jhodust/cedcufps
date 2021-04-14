package com.ufps.cedcufps.dao;

import com.ufps.cedcufps.dto.DepartamentoDto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ufps.cedcufps.modelos.Departamento;

public interface IDepartamentoCustomDao {

	public List<DepartamentoDto> findDeptosPermisosDocentesForDocEstAdminvo(Long idDirector);
	
	public List<DepartamentoDto> findAll() ;
}
