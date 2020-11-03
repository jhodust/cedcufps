package com.ufps.cedcufps.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IEducacionContinuaCustomDao;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Persona;

@Repository
public class EducacionContinuaCustomDaoImpl implements IEducacionContinuaCustomDao{

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Long>  listAllPossibleEducacionContinua(Long idPersona) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		query.append("select e.id from educacion_continua e");
		query.append(" where e.id_programa IN (select rppp.id_programa from roles_personas_programas_ec rppp");
		query.append(" where rppp.id_persona=?1 and rppp.id_rol=(select ro.id from roles ro where ro.authority='ROLE_MANAECCU'))" );
		query.append(" or e.id_docente=?1" );
			
		System.out.println("*************************************** query******");
		System.out.println(query.toString());
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		
		List<Long> list= new ArrayList<Long>();
		for(Object o: q.getResultList() )
			list.add(Long.parseLong(String.valueOf(o)));
		return list;
	}
	
	
	@Override
	public boolean  docenteHasPermission(String nombreEduContinua, Long idPersona) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		query.append("select count(e.id) from educacion_continua e");
		query.append(" where (e.id_programa IN (select rppp.id_programa from roles_personas_programas_ec rppp");
		query.append(" where rppp.id_persona=?1 and rppp.id_rol=(select ro.id from roles ro where ro.authority='ROLE_MANAECCU'))" );
		query.append(" or e.id_docente=?1) and e.nombre=?2 " );
			
		System.out.println("*************************************** query******");
		System.out.println(query.toString());
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		q.setParameter(2, nombreEduContinua);
		
	
		
		return Integer.parseInt(String.valueOf(q.getResultList().get(0)))>0;
	}


	@Transactional
	@Override
	public int registrarAsistencia(Long idJornada, Long idParticipante) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		return em.createNativeQuery("insert IGNORE into asistencias(id_jornada,id_participante, fecha_asistencia) values (?,?, ?)")
		.setParameter(1, idJornada)
		.setParameter(2, idParticipante)
		.setParameter(3, new Date())
		.executeUpdate();
	}
	
	
	@Transactional
	@Override
	public void saveEducacionContinua(EducacionContinuaWebDto dto) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder().append("insert into educacion_continua (nombre,fecha_inicio,")
				.append("fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,")
				.append("costo_inscripcion,duracion,id_tipo_educacion_continua,id_docente,id_programa,")
				.append("id_clasificacion_cine,lugar,estado,costo_educacion_continua, porcentaje_asistencia, ")
				.append("objetivo,requisitos, consecutivo)")
				.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, dto.getNombre());
				ps.setObject(2, dto.getFechaInicio());
				ps.setObject(3, dto.getFechaFin());
				ps.setObject(4, dto.getFechaLimInscripcion());
				ps.setString(5, dto.getContenidoGral());
				ps.setString(6, dto.getCantMaxParticipantes());
				ps.setString(7, dto.getResumen());
				ps.setString(8, dto.getCostoInscripcion());
				ps.setInt(9, dto.getDuracion());
				ps.setLong(10, dto.getIdTipoEduContinua());
				ps.setLong(11, dto.getIdDocenteResp());
				ps.setLong(12, dto.getIdProgramaResp());
				ps.setLong(13, dto.getIdClasificacion());
				ps.setString(14, dto.getLugar());
				ps.setString(15, dto.getEstado());
				ps.setString(16, dto.getCostoEducacionContinua());
				ps.setString(17, dto.getPorcentajeAsistencia());
				ps.setString(18, dto.getObjetivo());
				ps.setString(19, dto.getRequisitos());
				ps.setString(20, dto.getConsecutivo());
				
				return ps;
			}

		}, keyHolder);
		
		dto.setId(Long.parseLong(String.valueOf(keyHolder.getKey())));
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println(dto.getId() );
		System.out.println(  keyHolder.getKeys().get("id"));

	}
	
	
	@Transactional
	@Modifying
	@Override
	public void updateEducacionContinua(EducacionContinuaWebDto dto) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("update educacion_continua set nombre = ?, fecha_inicio = ?, fecha_fin = ?, fecha_lim_inscripcion = ?,")
			 .append(" contenido_general = ?, cant_max_participantes = ?, resumen = ?, costo_inscripcion = ?,")
			 .append(" duracion = ?, id_tipo_educacion_continua = ?, id_docente = ?, id_programa = ?,")
			 .append(" id_clasificacion_cine = ?, lugar = ?, costo_educacion_continua = ?, porcentaje_asistencia = ?, objetivo = ?")
			 .append(" requisitos = ? where id = ?");
		 em.createNativeQuery(query.toString())
				 .setParameter(1, dto.getNombre())
				 .setParameter(2, dto.getFechaInicio())
				 .setParameter(3, dto.getFechaFin())
				 .setParameter(4, dto.getFechaLimInscripcion())
				 .setParameter(5, dto.getContenidoGral())
				 .setParameter(6, dto.getCantMaxParticipantes())
				 .setParameter(7, dto.getResumen())
				 .setParameter(8, dto.getCostoInscripcion())
				 .setParameter(9, dto.getDuracion())
				 .setParameter(10, dto.getIdTipoEduContinua())
				 .setParameter(11, dto.getIdDocenteResp())
				 .setParameter(12, dto.getIdProgramaResp())
				 .setParameter(13, dto.getIdClasificacion())
				 .setParameter(14, dto.getLugar())
				 .setParameter(15, dto.getCostoEducacionContinua())
				 .setParameter(16, dto.getPorcentajeAsistencia())
				 .setParameter(17, dto.getObjetivo())
				 .setParameter(18, dto.getRequisitos())
				 .setParameter(19, dto.getId())
				 .executeUpdate();
	}
	
	
	@Transactional
	@Override
	public Long insertNewTipoEduContinua(String tipoEduContinua, Boolean status){
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder().append("insert into tipos_educacion_continua (tipo_educacion_continua, estado_oficial)")
				.append(" VALUES (?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, tipoEduContinua);
				ps.setObject(2, status);
				
				return ps;
			}

		}, keyHolder);
		
		return Long.parseLong(String.valueOf(keyHolder.getKey()));

	}
	

}
