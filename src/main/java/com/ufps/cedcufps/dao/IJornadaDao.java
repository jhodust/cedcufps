package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Jornada;

public interface IJornadaDao extends CrudRepository<Jornada, Long> {

	@Query("select j from Jornada j where j.educacionContinua.id = ?1")
	  public List<Jornada> findByIdEducacionContinua(Long idEducacionContinua);
}
