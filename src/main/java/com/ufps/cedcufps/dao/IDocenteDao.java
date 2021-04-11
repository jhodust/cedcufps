package com.ufps.cedcufps.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Persona;

public interface IDocenteDao extends JpaRepository<Docente, Long> {

	@Query("SELECT d FROM Docente d WHERE d.isDocente=1")
	public List<Docente> findDocentes();
	
	@Query(value = "select count(*) from personas p where p.id = ?1 and p.is_docente=1 ", nativeQuery = true)
	public int findOnlyDocente(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO docentes (codigo, id_departamento, id_persona) VALUES(?1,?2,?3) ON DUPLICATE KEY  UPDATE codigo=VALUES(codigo), id_departamento=VALUES(id_departamento), estado= true", nativeQuery = true)
	public void updateOnlyDocente(String codigo, Long idDepartamento, Long idPersona);

	@Query(value ="select d " + 
			"from Docente d " + 
			"where d.codigo = ?1")	
	public List<Persona> findDocenteByCodigo(String codigo);
	
	
	@Query(value = "SELECT * from docentes where id_persona = ?1 and estado = 1", nativeQuery=true)
	public Docente findDocenteByIdPersona(Long idPersona);
	
}
