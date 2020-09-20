package com.ufps.cedcufps.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;

public interface IAsistenciaDao extends CrudRepository<Asistencia, Long> {

	@Query("SELECT a FROM Asistencia a WHERE a.jornada.id IN (:jornadas)")
	public List<Asistencia> findAsistenciasByJornadas(List<Long> jornadas);
	
	@Query(value= "SELECT count(a.id_jornada) as 'total', a.id_jornada as 'jornada' FROM asistencias a JOIN jornadas j on (j.id=a.id_jornada) WHERE (j.educacion_continua_id=(select e.id from educacion_continua e where e.nombre = ?1)) GROUP BY (a.id_jornada)  ORDER BY a.id_jornada ASC", nativeQuery = true)
	public List<?> countByJornada(String eduContinua);
	
	@Query("SELECT a FROM Asistencia a WHERE a.jornada.id=?1 and a.participante.id=?2")
	public Asistencia findAsistenciasByJornadaAndParticipante(Long idJornada, Long idParticipante);
	
	@Transactional
	@Modifying
	@Query("delete from Asistencia a where a.jornada.id=?1 and a.participante.id=?2")
	public void deleteAsistencia(Long idJornada, Long idParticipante);
	
	@Transactional
	@Modifying
	@Query("delete from Asistencia a where a.jornada.id=?1")
	public void deleteAsistenciasByJornada(Long idJornada);
}
