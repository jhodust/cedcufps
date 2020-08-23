package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Docente;

public interface IDocenteDao extends CrudRepository<Docente, Long> {

	@Query("SELECT d FROM Docente d WHERE d.isDocente=1")
	public List<Docente> findDocentes();
}
