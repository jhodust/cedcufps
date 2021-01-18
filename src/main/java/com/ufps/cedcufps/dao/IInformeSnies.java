package com.ufps.cedcufps.dao;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.InformeSnies;

public interface IInformeSnies extends JpaRepository<InformeSnies, Long> {

	@Transactional
	@Modifying
	@Query(value = "insert into informes_snies (fecha_inicio, fecha_fin, informe_cursos, informe_educacion_continua, informe_participante, id_programa, descripcion) values (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
	public void saveInformeSnies(Date fechaInicio, Date fechaFin, String informeCursos, String informeEduContinua, String informeParticipantes, Long idPrograma, String descripcion);
}
