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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.google.common.primitives.Longs;
import com.ufps.cedcufps.dao.IEducacionContinuaCustomDao;
import com.ufps.cedcufps.dao.IJornadaDao;
import com.ufps.cedcufps.dto.CertificacionDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.TipoBeneficiarioDto;
import com.ufps.cedcufps.mapper.IEducacionContinuaMapper;
import com.ufps.cedcufps.mapper.IJornadaMapper;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.utils.StatusEducacionContinua;

@Repository
public class EducacionContinuaCustomDaoImpl implements IEducacionContinuaCustomDao{

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IEducacionContinuaMapper educacionContinuaMapper;
	
	@Autowired
	private IJornadaMapper jornadaMapper;
	
	@Autowired
	private IJornadaDao jornadaDao;

	/*@Override
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
	}*/
	
	
	@Override
	public boolean  docenteHasPermission(String idAcceso, Long idPersona) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		query.append("select count(e.id) from educacion_continua e");
		query.append(" where (e.id_programa IN (select rppp.id_programa from roles_personas_programas_ec rppp");
		query.append(" where rppp.id_persona=?1 and rppp.id_rol=(select ro.id from roles ro where ro.authority='ROLE_MANAECCU'))" );
		query.append(" or e.id_docente=?1) and e.id_acceso=?2 " );
			
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


	@Override
	public List<CertificacionDto> findCertificaciones(String numDocumento) {
		// TODO Auto-generated method stub
		StringBuilder certificacionesQuery = new StringBuilder();
		certificacionesQuery.append("select p.id as id_participante, p.id_persona as id_persona,")
		.append(" CONCAT(COALESCE(per.primer_nombre,''),' ', COALESCE(per.segundo_nombre,''),' ', COALESCE(per.primer_apellido,''),' ' ,COALESCE(per.segundo_apellido,'')),")
		.append(" tpar.tipo_participante, per.numero_documento, td.tipo_documento, tec.tipo_educacion_continua, e.id as id_educacion_continua, e.nombre,")
		.append(" e.fecha_inicio, e.fecha_fin, p.diploma_participacion, p.aprobado, p.fecha_generacion_diploma, p.token,")
		.append(" d.id as id_diploma, d.structure_diploma, d.updated_at")
		.append(" from participantes p")
		.append(" join educacion_continua e on p.educacion_continua_id=e.id ")
		.append(" left join diplomas d on e.id_diploma=d.id ")
		.append(" join personas per on p.id_persona=per.id ")
		.append(" join tipos_documento td on per.id_tipo_documento=td.id")
		.append(" join tipos_educacion_continua tec on e.id_tipo_educacion_continua=tec.id")
		.append(" join tipos_participante tpar on p.id_tipo_participante=tpar.id ")
		.append(" where per.numero_documento = ?1");
		System.out.println(numDocumento);
		Query query=em.createNativeQuery(certificacionesQuery.toString())
				 .setParameter(1, numDocumento);
		
		List<Object[]> result=query.getResultList();
		
		List<CertificacionDto> dto=new ArrayList<CertificacionDto>();
		System.out.println("ENTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ACCCCCCCCCCCCCCCCCCCCCCCCCAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		for(Object[] data: result) {
			dto.add(educacionContinuaMapper.convertToMisCertificaciones(
					Long.parseLong(String.valueOf(data[0])),
					Long.parseLong(String.valueOf(data[1])), 
					String.valueOf(data[2]),
					String.valueOf(data[3]),
					String.valueOf(data[4]),
					String.valueOf(data[5]),
					String.valueOf(data[6]),
					Long.parseLong(String.valueOf(data[7])),
					String.valueOf(data[8]),
					(Date) data[9],
					(Date) data[10],
					String.valueOf(data[11]),
					Boolean.parseBoolean(String.valueOf(data[12])),
					(Date) data[13],
					String.valueOf(data[14]),
					data[15] == null ? null : Long.parseLong(String.valueOf(data[15])),
					null,
					(Date) data[17]));
		}
		return dto;
	}


	@Override
	public Long[] listAllPossibleEducacionContinuaFiltro(String estado, Long idTipoEdC, Long idPrograma,
			Long idBeneficiarios) {
		// TODO Auto-generated method stub
		
        
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT distinct ec.id from educacion_continua ec")
		.append(" left join educacion_continua_tipo_beneficiario tb on tb.id_educacion_continua=ec.id")
		.append(" where ec.estado != ? ");
	
		System.out.println("idTipoEdc: " + idTipoEdC);
		System.out.println("idPrograma: " + idPrograma);
		System.out.println("idBeneficiarios: " + idBeneficiarios);
		if(idTipoEdC != 0L) {
			query.append(" and ec.id_tipo_educacion_continua = ? ");
		}
		if(idPrograma != 0L) {
			query.append(" and ec.id_programa = ? ");
		}
		if(idBeneficiarios != 0L) {
			query.append(" and tb.id_tipo_beneficiario = ? ");
		}
		query.append(" ORDER BY ec.fecha_inicio DESC");
		System.out.println("queryyyyyyyyyyy");
		System.out.println(query.toString());
		Query q=em.createNativeQuery(query.toString())
				.setParameter(1, estado);
		System.out.println("tipoedc: " + idTipoEdC );
		System.out.println(idTipoEdC != 0L);
		System.out.println("idPrograma: " + idPrograma );
		System.out.println(idPrograma != 0L);
		System.out.println("idBeneficiarios: " + idBeneficiarios );
		System.out.println(idBeneficiarios != 0L);
		if(idTipoEdC != 0L) {
			q.setParameter(2, idTipoEdC);
			System.out.println("entra a 1");
		}
		if(idPrograma != 0L && idTipoEdC != 0L ) {
			q.setParameter(3, idPrograma);
			System.out.println("entra a 2");
		}else if(idPrograma != 0L && idTipoEdC == 0L) {
			q.setParameter(2, idPrograma);
			System.out.println("entra a 3");
		}
		if(idBeneficiarios != 0L && idPrograma != 0L && idTipoEdC != 0L ) {
			q.setParameter(4, idBeneficiarios);
			System.out.println("entra a 4");
		}else if(idBeneficiarios != 0L && (idPrograma == 0L && idTipoEdC == 0L )) {
			q.setParameter(2, idBeneficiarios);
			System.out.println("entra a 5");
		} else if(idBeneficiarios != 0L && (idPrograma == 0L || idTipoEdC == 0L )) {
			q.setParameter(3, idBeneficiarios);
			System.out.println("entra a 6");
		}
		
		List<Object> result=q.getResultList();
		
		
		return this.convertListObjetctToLong(result);
		
				
	}
	
	
	public Long[] convertListObjetctToLong(List<Object>list) {
		Long[] array;
		System.out.println("list is empty?: " + list.size());
		
		if(list.isEmpty()) {
			array=new Long[1];
			array[0]=0L;
		}else {
			array= new Long[list.size()];
			int i=0;
			for(Object o: list) {
				array[i]=Long.parseLong(String.valueOf(o));
				i++;
			}
		}
		System.out.println("termina array con : " + array.length);
		return array;
	}


	@Override
	public List<EducacionContinuaAppDto> findEducacionesContinuasAGestionar(Long idPersona, boolean isSuperAdmin, boolean hasPermission) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append(" select e.id, e.nombre, e.fecha_inicio, e.fecha_fin, e.fecha_lim_inscripcion, pro.programa,");
		query.append(" tec.tipo_educacion_continua, e.id_acceso, p.primer_nombre, p.segundo_nombre, p.primer_apellido,");
		query.append(" p.segundo_apellido, (select count(par.id) from participantes par where par.educacion_continua_id=e.id) as cantidad_participantes");
		query.append(" from educacion_continua e");
		query.append(" join docentes d on d.id_persona=e.id_docente");
		query.append(" join personas p on d.id_persona=p.id");
		query.append(" join programas pro on pro.id=e.id_programa");
		query.append(" join tipos_educacion_continua tec on tec.id=e.id_tipo_educacion_continua");
		query.append(" where e.is_deleted = false");
		
		if(!isSuperAdmin && hasPermission) {
			query.append(" and e.id_programa IN (select rppp.id_programa from roles_personas_programas_ec rppp");
			query.append(" where rppp.id_persona=?1 and rppp.id_rol=(select ro.id from roles ro where ro.authority='ROLE_MANAECCU'))" );
			query.append(" or e.id_docente=?1" );
		}
		query.append(" order by e.fecha_inicio desc");
		
		
		
		Query q=em.createNativeQuery(query.toString());
		if(!isSuperAdmin && hasPermission) {
			q.setParameter(1, idPersona);
		}
			
		List<Object[]> result= q.getResultList();
		List<EducacionContinuaAppDto> list = new ArrayList<EducacionContinuaAppDto>();
		for(Object[] o: result) {
			EducacionContinuaAppDto dto=new EducacionContinuaAppDto();
			dto.setId(Long.parseLong(String.valueOf(o[0])));
			dto.setNombre(String.valueOf(o[1]));
			dto.setFechaInicio((Date) o[2]);
			dto.setFechaFin((Date) o[3]);
			dto.setFechaLimInscripcion((Date) o[4]);
			dto.setProgramaResponsable(String.valueOf(o[5]));
			dto.setTipoEduContinua(String.valueOf(o[6]));
			dto.setIdAcceso(String.valueOf(o[7]));
			String nombre=String.valueOf(o[8]);
			if(o[9] != null) {
				nombre = nombre + " " + String.valueOf(o[9]);
			}
			nombre = nombre + " " + String.valueOf(o[10]);
			if(o[11] != null) {
				nombre = nombre + " " + String.valueOf(o[11]);
			}
			
			dto.setDocenteResponsable(nombre);
			dto.setCantidadParticipantes(Integer.parseInt(String.valueOf(o[12])));
			dto.setJornadas(jornadaMapper.convertJornadasToJornadaAppDto(jornadaDao.findByIdEducacionContinua(dto.getId())));
			list.add(dto);
		}
		return list;
	}
	
	@Override
	public List<EducacionContinuaAppDto> findEducacionesContinuasForApp(Long idPersona, boolean isSuperAdmin) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		query.append(" select e.id, e.nombre, e.fecha_inicio, e.fecha_fin, e.fecha_lim_inscripcion, pro.programa,")
			 .append(" tec.tipo_educacion_continua, e.id_acceso, p.primer_nombre, p.segundo_nombre, p.primer_apellido,")
			 .append(" p.segundo_apellido, (select count(par.id) from participantes par where par.educacion_continua_id=e.id) as cantidad_participantes")
			 .append(" from educacion_continua e")
			 .append(" join docentes d on d.id_persona=e.id_docente")
			 .append(" join personas p on d.id_persona=p.id")
			 .append(" join programas pro on pro.id=e.id_programa")
			 .append(" join tipos_educacion_continua tec on tec.id=e.id_tipo_educacion_continua");
		
		
		
		
		if(!isSuperAdmin) {
			query.append(" join (select ec.id as idEdC from educacion_continua ec where ec.id_programa in")
			     .append(" (select rpec.id_programa from roles_personas_programas_ec rpec where rpec.id_persona =?1 )")
			     .append(" UNION")
			     .append(" select ec.id from educacion_continua ec where ec.id_docente=?1")
			     .append(" UNION")
			     .append(" select rpa.id_edu_continua from rol_persona_asistencia rpa where rpa.id_persona =?1) sq")
			     .append(" on sq.idEdC=e.id");
		}
		query.append(" where e.estado='"+StatusEducacionContinua.STATUS_EN_DESARROLLO+"' and e.is_deleted=false")
			 .append(" order by e.fecha_inicio asc");
		
		
		
		Query q=em.createNativeQuery(query.toString());
		if(!isSuperAdmin) {
			q.setParameter(1, idPersona);
		}
			
		List<Object[]> result= q.getResultList();
		List<EducacionContinuaAppDto> list = new ArrayList<EducacionContinuaAppDto>();
		for(Object[] o: result) {
			EducacionContinuaAppDto dto=new EducacionContinuaAppDto();
			dto.setId(Long.parseLong(String.valueOf(o[0])));
			dto.setNombre(String.valueOf(o[1]));
			dto.setFechaInicio((Date) o[2]);
			dto.setFechaFin((Date) o[3]);
			dto.setFechaLimInscripcion((Date) o[4]);
			dto.setProgramaResponsable(String.valueOf(o[5]));
			dto.setTipoEduContinua(String.valueOf(o[6]));
			dto.setIdAcceso(String.valueOf(o[7]));
			String nombre=String.valueOf(o[8]);
			if(o[9] != null) {
				nombre = nombre + " " + String.valueOf(o[9]);
			}
			nombre = nombre + " " + String.valueOf(o[10]);
			if(o[11] != null) {
				nombre = nombre + " " + String.valueOf(o[11]);
			}
			
			dto.setDocenteResponsable(nombre);
			dto.setCantidadParticipantes(Integer.parseInt(String.valueOf(o[12])));
			list.add(dto);
		}
		return list;
	}
	
	
	@Override
	public List<EducacionContinuaAppDto> findEduContinuasPermissionForAttendance(Long idPersona, boolean isAdmin, boolean isDirPrograma) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		query.append(" select e.id, e.nombre")
			 .append(" from educacion_continua e");
			 
		if(isDirPrograma) {//es director de programa
			query.append(" where e.id_programa = (select p.id from programas p where p.id_director = ?1 )");
		}
		   query.append(" order by e.fecha_inicio asc");
		
		
		
		Query q=em.createNativeQuery(query.toString());
		if(isDirPrograma) {
			q.setParameter(1, idPersona);
		}
			
		List<Object[]> result= q.getResultList();
		System.out.println("es director de programa en custom dato: " + isDirPrograma);
		System.out.println("result tamaño: " + result.size());
		List<EducacionContinuaAppDto> list = new ArrayList<EducacionContinuaAppDto>();
		for(Object[] o: result) {
			EducacionContinuaAppDto dto=new EducacionContinuaAppDto();
			dto.setId(Long.parseLong(String.valueOf(o[0])));
			dto.setNombre(String.valueOf(o[1]));
			list.add(dto);
		}
		return list;
	}
	
	
	@Override
	public List<EducacionContinuaAppDto> findEduContinuasPermissionForAttendance(Long idPersona) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append(" select e.id, e.nombre")
			 .append(" from educacion_continua e")
			 .append(" join rol_persona_asistencia rpa on rpa.id_edu_continua=e.id" )
			 .append(" where rpa.id_persona= ?1")
		     .append(" order by e.fecha_inicio asc");
		
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		
			
		List<Object[]> result= q.getResultList();
		List<EducacionContinuaAppDto> list = new ArrayList<EducacionContinuaAppDto>();
		for(Object[] o: result) {
			EducacionContinuaAppDto dto=new EducacionContinuaAppDto();
			dto.setId(Long.parseLong(String.valueOf(o[0])));
			dto.setNombre(String.valueOf(o[1]));
			list.add(dto);
		}
		return list;
	}
	
	
	@Override
	public List<EducacionContinuaAppDto> findEduContinuasPermissionForAttendanceExceptDirectorPrograma(Long idPersona, Long idPrograma) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append(" select e.id, e.nombre")
			 .append(" from educacion_continua e")
			 .append(" join rol_persona_asistencia rpa on rpa.id_edu_continua=e.id" )
			 .append(" where rpa.id_persona= ?1  and e.id_programa != ?2")
		     .append(" order by e.fecha_inicio asc");
		
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona)
		 .setParameter(2, idPrograma);
		
		
			
		List<Object[]> result= q.getResultList();
		List<EducacionContinuaAppDto> list = new ArrayList<EducacionContinuaAppDto>();
		for(Object[] o: result) {
			EducacionContinuaAppDto dto=new EducacionContinuaAppDto();
			dto.setId(Long.parseLong(String.valueOf(o[0])));
			dto.setNombre(String.valueOf(o[1]));
			list.add(dto);
		}
		return list;
	}

}
