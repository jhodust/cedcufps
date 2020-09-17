package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;

public interface IJornadaDao extends CrudRepository<Jornada, Long> {

	@Query("select j from Jornada j where j.educacionContinua.id = ?1 order by j.horaInicio")
	  public List<Jornada> findByIdEducacionContinua(Long idEducacionContinua);
	
	
	@Query("select j from Jornada j where j.educacionContinua.id = (select e.id from EducacionContinua e where e.nombre = ?1) order by j.horaInicio")
	  public List<Jornada> findByNombreEducacionContinua(String eduContinua);
	
	
}
