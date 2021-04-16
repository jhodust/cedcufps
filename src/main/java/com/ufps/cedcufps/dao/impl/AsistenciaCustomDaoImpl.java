package com.ufps.cedcufps.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IAsistenciaCustomDao;
import com.ufps.cedcufps.dto.AsistenciaDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.utils.StatusEducacionContinua;

@Repository
public class AsistenciaCustomDaoImpl implements IAsistenciaCustomDao {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AsistenciaDto> findAllAsistencias(){
		StringBuilder query = new StringBuilder();
		query.append(" select id as id_asistencia, id_jornada as id_jornada, id_participante as id_participante, fecha_asistencia")
			 .append(" from asistencias ");
			 
		Query q=em.createNativeQuery(query.toString());
		
		List<Object[]> result=q.getResultList();
		List<AsistenciaDto> list=new ArrayList<AsistenciaDto>();
		for(Object[] object : result) {
			AsistenciaDto dto=new AsistenciaDto();
			dto.setIdAsistencia(Long.parseLong(String.valueOf(object[0])));
			dto.setIdJornada(Long.parseLong(String.valueOf(object[1])));
			dto.setIdParticipante(Long.parseLong(String.valueOf(object[2])));
			dto.setFechaAsistencia((object[3] != null ) ? (Date)object[3]:null);
			list.add(dto);
		}
		return list;
	}
	
	
	@Override
	public List<AsistenciaDto> findAsistenciasByJornadas(List<Long> jornadas){
		StringBuilder query = new StringBuilder();
		query.append(" select id as id_asistencia, id_jornada as id_jornada, id_participante as id_participante, fecha_asistencia")
			 .append(" from asistencias ")
			 .append(" where id_jornada in ( ?1)");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, jornadas);
		
		List<Object[]> result=q.getResultList();
		List<AsistenciaDto> list=new ArrayList<AsistenciaDto>();
		for(Object[] object : result) {
			AsistenciaDto dto=new AsistenciaDto();
			dto.setIdAsistencia(Long.parseLong(String.valueOf(object[0])));
			dto.setIdJornada(Long.parseLong(String.valueOf(object[1])));
			dto.setIdParticipante(Long.parseLong(String.valueOf(object[2])));
			dto.setFechaAsistencia((object[3] != null ) ? (Date)object[3]:null);
			list.add(dto);
		}
		return list;
	}
	
	@Override
	public AsistenciaDto findAsistenciasByJornadaAndParticipante(Long idJornada, Long idParticipante) {
		StringBuilder query = new StringBuilder();
		query.append(" select id as id_asistencia, id_jornada as id_jornada, id_participante as id_participante, fecha_asistencia")
			 .append(" from asistencias ")
			 .append(" where id_jornada = ?1 and id_participante = ?2");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idJornada).setParameter(2, idParticipante);
		
		List<Object[]> result=q.getResultList();
		if(result.size()==1) {
			Object[] object=result.get(0);
			AsistenciaDto dto=new AsistenciaDto();
			dto.setIdAsistencia(Long.parseLong(String.valueOf(object[0])));
			dto.setIdJornada(Long.parseLong(String.valueOf(object[1])));
			dto.setIdParticipante(Long.parseLong(String.valueOf(object[2])));
			dto.setFechaAsistencia((object[3] != null ) ? (Date)object[3]:null);
			return dto;
		}
		return null;
	}
	
	@Override
	public List<AsistenciaDto> findAsistenciasByIdParticipante(Long idParticipante){
		StringBuilder query = new StringBuilder();
		query.append(" select id as id_asistencia, id_jornada as id_jornada, id_participante as id_participante, fecha_asistencia")
			 .append(" from asistencias ")
			 .append(" where id_participante = ?1");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idParticipante);
		
		List<Object[]> result=q.getResultList();
		List<AsistenciaDto> list=new ArrayList<AsistenciaDto>();
		for(Object[] object : result) {
			AsistenciaDto dto=new AsistenciaDto();
			dto.setIdAsistencia(Long.parseLong(String.valueOf(object[0])));
			dto.setIdJornada(Long.parseLong(String.valueOf(object[1])));
			dto.setIdParticipante(Long.parseLong(String.valueOf(object[2])));
			dto.setFechaAsistencia((object[3] != null ) ? (Date)object[3]:null);
			list.add(dto);
		}
		return list;
	}
}
