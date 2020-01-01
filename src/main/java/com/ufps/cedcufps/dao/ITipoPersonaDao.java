package com.ufps.cedcufps.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.TipoPersona;

public interface ITipoPersonaDao extends CrudRepository<TipoPersona, Long> {

	@Query("select tp from TipoPersona tp where tp.tipoPersona = ?1")
	  public TipoPersona findByTipoPersona(String tipoPersona);
	
	
}
