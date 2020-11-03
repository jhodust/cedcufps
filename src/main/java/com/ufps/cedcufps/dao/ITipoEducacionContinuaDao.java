package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.TipoEducacionContinua;

public interface ITipoEducacionContinuaDao extends JpaRepository<TipoEducacionContinua, Long> {

	@Query(value = "select t from TipoEducacionContinua t where t.id = ?1")
	public TipoEducacionContinua findTipoEducacionContinuaById(Long id);
	
	@Query(value = "select t from TipoEducacionContinua t where UPPER(t.tipoEduContinua) = ?1")
	public TipoEducacionContinua findTipoEducacionContinuaByTipoEduContinua(String tipoEduContinua);
	
	@Query(value = "select distinct t from TipoEducacionContinua t where t.estadoOficial='1' or t.id = ?1")
	public List<TipoEducacionContinua> findAllTipoEducacionContinua(Long id);
}
