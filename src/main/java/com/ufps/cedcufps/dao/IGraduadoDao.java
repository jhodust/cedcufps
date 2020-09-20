package com.ufps.cedcufps.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Graduado;

public interface IGraduadoDao extends CrudRepository<Graduado, Long> {

	@Query(value = "select * from graduados where id_persona=?1", nativeQuery = true)
	public Graduado findOnlyGraduado(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO graduados (anio, id_programa, id_persona) VALUES(?1,?2,?3) ON DUPLICATE KEY  UPDATE anio=VALUES(anio), id_programa=VALUES(id_programa)", nativeQuery = true)
	public void updateOnlyGraduado(String anio, Long idPrograma, Long idPersona);

}
