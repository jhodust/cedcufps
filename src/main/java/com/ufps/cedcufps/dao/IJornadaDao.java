package com.ufps.cedcufps.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;

public interface IJornadaDao extends JpaRepository<Jornada, Long> {

	@Query("select j from Jornada j where j.id= ?1 ")
	public Jornada findOneById(Long id);
	
	@Query("select j from Jornada j where j.educacionContinua.id = ?1 order by j.horaInicio")
	  public List<Jornada> findByIdEducacionContinua(Long idEducacionContinua);
	
	
	@Query("select j from Jornada j where j.educacionContinua.id = (select e.id from EducacionContinua e where e.nombre = ?1) order by j.horaInicio")
	  public List<Jornada> findByNombreEducacionContinua(String eduContinua);
	
	@Transactional
	@Modifying
	@Query("delete from Jornada j  where j.id = ?1 ")
	public void deleteJornada(Long idJornada);
	
	
}
