package com.ufps.cedcufps.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.TipoParticipante;

public interface ITipoParticipanteDao extends JpaRepository<TipoParticipante, Long>{

	@Query("select tp from TipoParticipante tp where tp.tipoParticipante = ?1")
	  public TipoParticipante findByTipoParticipante(String tipoParticipante);
}
