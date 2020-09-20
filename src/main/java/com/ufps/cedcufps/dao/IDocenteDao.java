package com.ufps.cedcufps.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;

public interface IDocenteDao extends CrudRepository<Docente, Long> {

	@Query("SELECT d FROM Docente d WHERE d.isDocente=1")
	public List<Docente> findDocentes();
	
	@Query(value = "select * from docentes where id_persona=?1", nativeQuery = true)
	public Docente findOnlyDocente(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO docentes (codigo, id_departamento, estado, id_persona) VALUES(?1,?2,?3,?4) ON DUPLICATE KEY  UPDATE codigo=VALUES(codigo), id_departamento=VALUES(id_departamento),estado=VALUES(estado)", nativeQuery = true)
	public void updateOnlyDocente(String codigo, Long idDepartamento, boolean estado, Long idPersona);
}
