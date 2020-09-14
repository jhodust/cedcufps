package com.ufps.cedcufps.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ufps.cedcufps.modelos.Estudiante;

public interface IEstudianteDao extends CrudRepository<Estudiante, Long>{

	@Query(value = "select * from estudiantes where id_persona=?1", nativeQuery = true)
	public Estudiante findOnlyEstudiante(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO estudiantes (codigo, id_programa, id_persona) VALUES(?1,?2,?3) ON DUPLICATE KEY  UPDATE codigo=VALUES(codigo), id_programa=VALUES(id_programa)", nativeQuery = true)
	public void updateOnlyEstudiante(String codigo, Long idPrograma, Long idPersona);

}
