package com.ufps.cedcufps.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;


public interface IParticipanteDao extends JpaRepository<Participante, Long>{

	
	
	@Transactional
	@Modifying
	@Query(value = "delete from participantes  where id = ?1 ", nativeQuery=true)
	public void deleteParticipante(Long idParticipante);
	
	
	
	@Transactional
	@Modifying
	@Query(value="insert into asistentes (id_participante) values (?1) ", nativeQuery = true)
	public void insertAsistente(Long idParticipante);
	
	@Transactional
	@Modifying
	@Query(value="insert into ponentes (id_participante, tema) values (?1, ?2) ", nativeQuery = true)
	public void insertPonente(Long idParticipante, String tema);
	
	@Transactional
	@Modifying
	@Query(value="update ponentes set tema = ?1 where id_participante = ?2 ", nativeQuery = true)
	public void updatePonente(String tema, Long idParticipante);
	
	@Transactional
	@Modifying
	@Query(value="update participantes set tarjeta_inscripcion = ?1 where id = ?2 ", nativeQuery = true)
	public void updateTarjetaInscripcion(String tarjetaInscripcion, Long idParticipante);
	
	@Query(value="select count(*) from participantes where educacion_continua_id = ?1 and id_tipo_participante = '1'", nativeQuery = true)
	public int countTotalParticipantes(Long idEduContinua);
	
	
	@Transactional
	@Modifying
	@Query(value="update participantes set aprobado = ?1, diploma_participacion = ?2, fecha_generacion_diploma = ?3 where token = ?4 ", nativeQuery = true)
	public void createCertificacionParticipante(boolean aprobado, String imagenDiploma, Date fechaDiploma, String token);
	
	
	
	@Transactional
	@Modifying
	@Query(value="update participantes set fecha_generacion_diploma = ?1 where token = ?2 ", nativeQuery = true)
	public void updateCertificacionParticipante(Date fechaDiploma, String token);
	
	
	
}
