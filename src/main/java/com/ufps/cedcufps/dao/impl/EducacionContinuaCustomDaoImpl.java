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
import com.ufps.cedcufps.dto.TipoBeneficiarioDto;
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
	public boolean  docenteHasPermission(String idAcceso, Long idPersona) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		query.append("select count(e.id) from educacion_continua e");
		query.append(" where (e.id_programa IN (select rppp.id_programa from roles_personas_programas_ec rppp");
		query.append(" where rppp.id_persona=?1 and rppp.id_rol=(select ro.id from roles ro where ro.authority='ROLE_MANAECCU'))" );
		query.append(" or e.id_docente=?1) and e.idAcceso=?2 " );
			
		System.out.println("*************************************** query******");
		System.out.println(query.toString());
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		q.setParameter(2, idAcceso);
		
	
		
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
				.append("fecha_fin,fecha_lim_inscripcion,cant_max_participantes,")
				.append("costo_inscripcion,duracion,id_tipo_educacion_continua,id_docente,id_programa,")
				.append("id_clasificacion_cine,lugar,estado,costo_educacion_continua, porcentaje_asistencia, ")
				.append("consecutivo, id_acceso, info_adicional)")
				.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, dto.getNombre());
				ps.setObject(2, dto.getFechaInicio());
				ps.setObject(3, dto.getFechaFin());
				ps.setObject(4, dto.getFechaLimInscripcion());
				ps.setString(5, dto.getCantMaxParticipantes());
				ps.setString(6, dto.getCostoInscripcion());
				ps.setString(7, dto.getDuracion());
				ps.setLong(8, dto.getIdTipoEduContinua());
				ps.setLong(9, dto.getIdDocenteResp());
				ps.setLong(10, dto.getIdProgramaResp());
				ps.setLong(11, dto.getIdClasificacion());
				ps.setString(12, dto.getLugar());
				ps.setString(13, dto.getEstado());
				ps.setString(14, dto.getCostoEducacionContinua());
				ps.setString(15, dto.getPorcentajeAsistencia());
				ps.setString(16, dto.getConsecutivo());
				ps.setString(17, String.valueOf(System.currentTimeMillis()));
				ps.setString(18, dto.getInfoAdicional());
				
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
			 .append(" cant_max_participantes = ?, costo_inscripcion = ?,")
			 .append(" duracion = ?, id_tipo_educacion_continua = ?, id_docente = ?, id_programa = ?,")
			 .append(" id_clasificacion_cine = ?, lugar = ?, costo_educacion_continua = ?, porcentaje_asistencia = ?, info_adicional = ?")
			 .append(" where id = ?");
		 em.createNativeQuery(query.toString())
				 .setParameter(1, dto.getNombre())
				 .setParameter(2, dto.getFechaInicio())
				 .setParameter(3, dto.getFechaFin())
				 .setParameter(4, dto.getFechaLimInscripcion())
				 .setParameter(5, dto.getCantMaxParticipantes())
				 .setParameter(6, dto.getCostoInscripcion())
				 .setParameter(7, dto.getDuracion())
				 .setParameter(8, dto.getIdTipoEduContinua())
				 .setParameter(9, dto.getIdDocenteResp())
				 .setParameter(10, dto.getIdProgramaResp())
				 .setParameter(11, dto.getIdClasificacion())
				 .setParameter(12, dto.getLugar())
				 .setParameter(13, dto.getCostoEducacionContinua())
				 .setParameter(14, dto.getPorcentajeAsistencia())
				 .setParameter(15, dto.getInfoAdicional())
				 .setParameter(16, dto.getId())
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


	@Override
	public EducacionContinuaWebDto findEduContinuaWebDtoByIdAcceso(String idAcceso) {
		// TODO Auto-generated method stub
		StringBuilder edcQuery = new StringBuilder();
		edcQuery.append("select e.id as id_edu_continua, e.nombre, e.fecha_inicio, e.fecha_fin, e.fecha_lim_inscripcion,")
		.append(" COALESCE(e.costo_inscripcion,'GRATUITO'), COALESCE(e.cant_max_participantes,'LIBRE'), e.lugar, ")
		.append(" e.duracion, e.imagen, e.info_adicional, e.estado,")
		.append(" e.consecutivo, e.costo_educacion_continua, e.porcentaje_asistencia,")
		.append(" tec.id as id_tipo_edu_continua, tec.tipo_educacion_continua, tec.estado_oficial, ")
		.append(" p.id as id_docente, CONCAT(COALESCE(p.primer_nombre,''),' ',COALESCE(p.segundo_nombre,''),' ',COALESCE(p.primer_apellido,''),' ',COALESCE(p.segundo_apellido,'')),")
		.append(" d.codigo, pro.id as id_programa, pro.programa, f.id as id_facultad, f.facultad, c.id as id_clasificacion_cine, c.clasificacion_cine")
		.append(" from educacion_continua e")
		.append(" join tipos_educacion_continua tec on tec.id=e.id_tipo_educacion_continua ")
		.append(" join docentes d on d.id_persona =e.id_docente ")
		.append(" join personas p on p.id=d.id_persona ")
		.append(" join programas pro on pro.id=e.id_programa")
		.append(" join facultades f on f.id=pro.id_facultad")
		.append(" join clasificacion_cine c on c.id=e.id_clasificacion_cine")
		.append(" where e.id_acceso = ?1");
		
		
		Query q=em.createNativeQuery(edcQuery.toString())
		 .setParameter(1, idAcceso);
		
		
		List<Object[]> result= q.getResultList();
		if(result.size()==1) {
			Object[] edc=result.get(0);
			EducacionContinuaWebDto eduContinuaDto= new EducacionContinuaWebDto();
			eduContinuaDto.setId(Long.parseLong(String.valueOf(edc[0])));
			eduContinuaDto.setNombre(String.valueOf(edc[1]));
			eduContinuaDto.setFechaInicio((Date) edc[2]);
			eduContinuaDto.setFechaFin((Date) edc[3]);
			eduContinuaDto.setFechaLimInscripcion((Date) edc[4]);
			eduContinuaDto.setCostoInscripcion(String.valueOf(edc[5]));
			eduContinuaDto.setCantMaxParticipantes(String.valueOf(edc[6]));
			eduContinuaDto.setLugar(String.valueOf(edc[7]));
			eduContinuaDto.setDuracion(String.valueOf(edc[8]));
			eduContinuaDto.setImagen(String.valueOf(edc[9]));
			eduContinuaDto.setInfoAdicional(String.valueOf(edc[10]));
			eduContinuaDto.setEstado(String.valueOf(edc[11]));
			eduContinuaDto.setConsecutivo(String.valueOf(edc[12]));
			eduContinuaDto.setCostoEducacionContinua(String.valueOf(edc[13]));
			eduContinuaDto.setPorcentajeAsistencia(String.valueOf(edc[14]));
			eduContinuaDto.setIdTipoEduContinua(Long.parseLong(String.valueOf(edc[15])));
			eduContinuaDto.setTipoEduContinua(String.valueOf(edc[16]));
			eduContinuaDto.setEstadoOficialTipoEducacionContinua(Boolean.parseBoolean(String.valueOf(edc[17])));
			eduContinuaDto.setIdDocenteResp(Long.parseLong(String.valueOf(edc[18])));
			eduContinuaDto.setNombreDocenteResp(String.valueOf(edc[19]));
			eduContinuaDto.setCodigoDocenteResp(String.valueOf(edc[20]));
			eduContinuaDto.setIdProgramaResp(Long.parseLong(String.valueOf(edc[21])));
			eduContinuaDto.setProgramaResp(String.valueOf(edc[22]));
			eduContinuaDto.setIdFacultad(Long.parseLong(String.valueOf(edc[23])));
			eduContinuaDto.setFacultad(String.valueOf(edc[24]));
			eduContinuaDto.setIdClasificacion(Long.parseLong(String.valueOf(edc[25])));
			eduContinuaDto.setClasificacion(String.valueOf(edc[26]));
			
			
			StringBuilder edcTipoBeneficiarios = new StringBuilder();
			edcTipoBeneficiarios.append("select tb.id as id_tipo_beneficiario, tb.tipo_beneficiario,")
			.append(" tb.homologacion, tp.id as id_tipo_persona, tp.tipo_persona")
			.append(" from educacion_continua e")
			.append(" join educacion_continua_tipo_beneficiario ectb on ectb.id_educacion_continua=e.id ")
			.append(" join tipos_beneficiarios tb on tb.id=ectb.id_tipo_beneficiario ")
			.append(" join tipos_persona tp on tp.id=tb.id_tipo_persona  ")
			.append(" where e.id = ?1");
			
			Query q2=em.createNativeQuery(edcTipoBeneficiarios.toString())
					 .setParameter(1, eduContinuaDto.getId());
			List<Object[]> resultBeneficiarios= q2.getResultList();
			List<TipoBeneficiarioDto> list= new ArrayList<TipoBeneficiarioDto>();
			System.out.println("result tipo beneficiariossssssssssssssss");
			System.out.println(resultBeneficiarios.size());
			for(Object[] o : resultBeneficiarios) {
				TipoBeneficiarioDto dto= new TipoBeneficiarioDto();
				dto.setId(Long.parseLong(String.valueOf(o[0])));
				dto.setTipoBeneficiario(String.valueOf(o[1]));
				dto.setHomologacion(String.valueOf(o[2]));
				dto.setIdTipoPersona(Long.parseLong(String.valueOf(o[3])));
				dto.setTipoPersona(String.valueOf(o[4]));
				list.add(dto);
			}
			eduContinuaDto.setTipoBeneficiarios(list);
			return eduContinuaDto;
		}
		return null;
	}
	

}
