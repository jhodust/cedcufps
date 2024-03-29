package com.ufps.cedcufps.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;

public interface IAsistenciaDao extends JpaRepository<Asistencia, Long> {

	
	
	@Query(value= "SELECT count(a.id_jornada) as 'total', a.id_jornada as 'jornada' FROM asistencias a JOIN jornadas j on (j.id=a.id_jornada) WHERE (j.educacion_continua_id=(select e.id from educacion_continua e where e.nombre = ?1 and e.fecha_inicio = ?2 )) GROUP BY (a.id_jornada)  ORDER BY a.id_jornada ASC", nativeQuery = true)
	public List<?> countByJornada(String eduContinua, Date fechaInicio);
	
	
	
	@Transactional
	@Modifying
	@Query("delete from Asistencia a where a.jornada.id=?1 and a.participante.id=?2")
	public void deleteAsistencia(Long idJornada, Long idParticipante);
	
	@Transactional
	@Modifying
	@Query(value="delete a from asistencias a "
			     + "join jornadas j on a.id_jornada=j.id "
			     + "join participantes p on a.id_participante=p.id "
			     + "where a.id_jornada=?1 and p.aprobado = 0", nativeQuery=true)
	public void deleteAsistenciasByJornada(Long idJornada);
	
	@Transactional
	@Modifying
	@Query(value="insert into asistencias (fecha_asistencia, id_jornada, id_participante) values (?1,?2,?3)", nativeQuery=true)
	public void matchAsistencia(Date fechaAsistencia,Long idJornada, Long idParticipante);
	
	@Query(value="SELECT count(*) FROM asistencias WHERE id_jornada=?1",nativeQuery=true)
	public int countAsistenciasOfJornada(Long idJornada);
}
