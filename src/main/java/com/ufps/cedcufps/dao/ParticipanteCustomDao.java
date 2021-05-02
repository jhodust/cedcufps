package com.ufps.cedcufps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.mapper.IUsuarioMapper;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.TipoParticipante;
import com.ufps.cedcufps.utils.StatusEducacionContinua;
import com.ufps.cedcufps.utils.TipoParticipanteUtil;
import com.ufps.cedcufps.utils.TipoPersonaUtil;

@Repository
public class ParticipanteCustomDao implements IParticipanteCustomDao {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IUsuarioMapper usuarioMapper;

	@Transactional
	@Override
	public void saveParticipante(ParticipanteDto p) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder().append("insert into participantes (codigoqr, fecha_registro, imagen_codigo_qr, educacion_continua_id, id_persona, id_tipo_participante, id_tipo_persona, token)")
				.append(" VALUES (?,?,?,?,?,?,?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, p.getCodigoQR());
				ps.setObject(2, p.getFechaInscripcion());
				ps.setString(3, p.getImagenQr());
				ps.setLong(4, p.getIdEducacionContinua());
				ps.setLong(5, p.getIdPersona());
				ps.setLong(6, p.getIdTipoParticipante());
				ps.setObject(7, p.getIdTipoPersona());
				ps.setObject(8, p.getToken());
				
				return ps;
			}

		}, keyHolder);
		
		p.setId(Long.parseLong(String.valueOf(keyHolder.getKey())));

	}
	
	
	
	
	@Override
	public List<ParticipanteDto> findAllParticipacionesActivasByParticipante(String numDocumento) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_participante, tp.id as id_tipo_participante, tp.tipo_participante,")
		.append(" td.id as id_tipo_documento, td.tipo_documento, e.id as id_edu_continua, e.nombre, p.codigoqr, p.fecha_generacion_diploma, ")
			 .append(" p.fecha_registro, p.imagen_codigo_qr, p.tarjeta_inscripcion, p.aprobado, p.token, tec.id as id_tipo_educacion_continua,")
			 .append(" tec.tipo_educacion_continua, per.primer_nombre, per.segundo_nombre, per.primer_apellido, per.segundo_apellido,")
			 .append(" per.email, e.fecha_inicio, per.numero_documento")
			 .append(" from participantes p join personas per on p.id_persona=per.id ")
			 .append(" join educacion_continua e on p.educacion_continua_id=e.id")
			 .append(" join tipos_participante tp on p.id_tipo_participante=tp.id")
			 .append(" join tipos_documento td on per.id_tipo_documento=td.id")
			 .append(" join tipos_educacion_continua tec on e.id_tipo_educacion_continua=tec.id")
			 .append(" where per.numero_documento = ?1 and e.estado != ?2");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, numDocumento).setParameter(2, StatusEducacionContinua.STATUS_TERMINADO);
		
		List<Object[]> result=q.getResultList();
		List<ParticipanteDto> list=new ArrayList<ParticipanteDto>();
		for(Object[] object : result) {
			ParticipanteDto dto=new ParticipanteDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setIdTipoParticipante(Long.parseLong(String.valueOf(object[1])));
			dto.setTipoParticipante(String.valueOf(object[2]));
			dto.setIdTipoDocumento(Long.parseLong(String.valueOf(object[3])));
			dto.setTipoDocumento(String.valueOf(object[4]));
			dto.setIdEducacionContinua(Long.parseLong(String.valueOf(object[5])));
			dto.setEducacionContinua(String.valueOf(object[6]));
			dto.setCodigoQR(String.valueOf(object[7]));
			dto.setFechaGeneracionDiploma((Date)object[8]);
			dto.setFechaInscripcion((Date)object[9]);
			dto.setImagenQr(String.valueOf(object[10]));
			dto.setTarjetaInscripcion(String.valueOf(object[11]));
			dto.setAprobado((Boolean)object[12]);
			dto.setToken(String.valueOf(object[13]));
			dto.setIdTipoEduContinua(Long.parseLong(String.valueOf(object[14])));
			dto.setTipoEduContinua(String.valueOf(object[15]));
			dto.setPrimerNombre(String.valueOf(object[16]));
			dto.setSegundoNombre(String.valueOf(object[17]));
			dto.setPrimerApellido(String.valueOf(object[18]));
			dto.setSegundoApellido(String.valueOf(object[19]));
			dto.setEmail(String.valueOf(object[20]));
			dto.setFechaInicioEduContinua((Date)object[21]);
			dto.setNumeroDocumento(String.valueOf(object[22]));
			dto.setNombrePersona(usuarioMapper.convertFieldsFullName(dto.getPrimerNombre(), 
					dto.getSegundoNombre(), dto.getPrimerApellido(), dto.getSegundoApellido()));
			list.add(dto);
		}
		return list;
	}
	
	
	
	@Override
	public List<PonenteDto> findAllPonentesOfOneEducacionContinuaById(Long idEduContinua) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_participante, tp.id as id_tipo_participante, tp.tipo_participante,")
		.append(" td.id as id_tipo_documento, td.tipo_documento, e.id as id_edu_continua, e.nombre, p.codigoqr, p.fecha_generacion_diploma, ")
			 .append(" p.fecha_registro, p.imagen_codigo_qr, p.tarjeta_inscripcion, p.aprobado, p.token, tec.id as id_tipo_educacion_continua,")
			 .append(" tec.tipo_educacion_continua, per.primer_nombre, per.segundo_nombre, per.primer_apellido, per.segundo_apellido,")
			 .append(" per.email, e.fecha_inicio, pon.tema,per.numero_documento")
			 .append(" from participantes p join personas per on p.id_persona=per.id ")
			 .append(" join educacion_continua e on p.educacion_continua_id=e.id")
			 .append(" join tipos_participante tp on p.id_tipo_participante=tp.id")
			 .append(" join tipos_documento td on per.id_tipo_documento=td.id")
			 .append(" join tipos_educacion_continua tec on e.id_tipo_educacion_continua=tec.id")
			 .append(" join ponentes pon on p.id=pon.id_participante")
			 .append(" where e.id= ?1 and tp.id = ?2");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idEduContinua).setParameter(2, TipoParticipanteUtil.PONENTE);
		
		List<Object[]> result=q.getResultList();
		List<PonenteDto> list=new ArrayList<PonenteDto>();
		for(Object[] object : result) {
			ParticipanteDto dto=new ParticipanteDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setIdTipoParticipante(Long.parseLong(String.valueOf(object[1])));
			dto.setTipoParticipante(String.valueOf(object[2]));
			dto.setIdTipoDocumento(Long.parseLong(String.valueOf(object[3])));
			dto.setTipoDocumento(String.valueOf(object[4]));
			dto.setIdEducacionContinua(Long.parseLong(String.valueOf(object[5])));
			dto.setEducacionContinua(String.valueOf(object[6]));
			dto.setCodigoQR(String.valueOf(object[7]));
			dto.setFechaGeneracionDiploma((Date)object[8]);
			dto.setFechaInscripcion((Date)object[9]);
			dto.setImagenQr(String.valueOf(object[10]));
			dto.setTarjetaInscripcion(String.valueOf(object[11]));
			dto.setAprobado((Boolean)object[12]);
			dto.setToken(String.valueOf(object[13]));
			dto.setIdTipoEduContinua(Long.parseLong(String.valueOf(object[14])));
			dto.setTipoEduContinua(String.valueOf(object[15]));
			dto.setPrimerNombre(String.valueOf(object[16]));
			dto.setSegundoNombre(String.valueOf(object[17]));
			dto.setPrimerApellido(String.valueOf(object[18]));
			dto.setSegundoApellido(String.valueOf(object[19]));
			dto.setEmail(String.valueOf(object[20]));
			dto.setFechaInicioEduContinua((Date)object[21]);
			
			dto.setNombrePersona(usuarioMapper.convertFieldsFullName(dto.getPrimerNombre(), 
					dto.getSegundoNombre(), dto.getPrimerApellido(), dto.getSegundoApellido()));
			PonenteDto ponenteDto=new PonenteDto();
			ponenteDto.setParticipante(dto);
			ponenteDto.setTema(String.valueOf(object[22]));
			dto.setNumeroDocumento(String.valueOf(object[23]));
			list.add(ponenteDto);
		}
		return list;
	}
	
	
	@Override
	public List<ParticipanteDto> findTipoParticipantesByIdEducacionContinua(Long idEduContinua, Long idTipoEduContinua) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_participante, tp.id as id_tipo_participante, tp.tipo_participante,")
		.append(" td.id as id_tipo_documento, td.tipo_documento, e.id as id_edu_continua, e.nombre, p.codigoqr, p.fecha_generacion_diploma, ")
			 .append(" p.fecha_registro, p.imagen_codigo_qr, p.tarjeta_inscripcion, p.aprobado, p.token, tec.id as id_tipo_educacion_continua,")
			 .append(" tec.tipo_educacion_continua, per.primer_nombre, per.segundo_nombre, per.primer_apellido, per.segundo_apellido,")
			 .append(" per.email, e.fecha_inicio, per.numero_documento")
			 .append(" from participantes p join personas per on p.id_persona=per.id ")
			 .append(" join educacion_continua e on p.educacion_continua_id=e.id")
			 .append(" join tipos_participante tp on p.id_tipo_participante=tp.id")
			 .append(" join tipos_documento td on per.id_tipo_documento=td.id")
			 .append(" join tipos_educacion_continua tec on e.id_tipo_educacion_continua=tec.id")
			 .append(" where e.id= ?1 and tp.id = ?2");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idEduContinua).setParameter(2, idTipoEduContinua);
		
		List<Object[]> result=q.getResultList();
		List<ParticipanteDto> list=new ArrayList<ParticipanteDto>();
		for(Object[] object : result) {
			ParticipanteDto dto=new ParticipanteDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setIdTipoParticipante(Long.parseLong(String.valueOf(object[1])));
			dto.setTipoParticipante(String.valueOf(object[2]));
			dto.setIdTipoDocumento(Long.parseLong(String.valueOf(object[3])));
			dto.setTipoDocumento(String.valueOf(object[4]));
			dto.setIdEducacionContinua(Long.parseLong(String.valueOf(object[5])));
			dto.setEducacionContinua(String.valueOf(object[6]));
			dto.setCodigoQR(String.valueOf(object[7]));
			dto.setFechaGeneracionDiploma((Date)object[8]);
			dto.setFechaInscripcion((Date)object[9]);
			dto.setImagenQr(String.valueOf(object[10]));
			dto.setTarjetaInscripcion(String.valueOf(object[11]));
			dto.setAprobado((Boolean)object[12]);
			dto.setToken(String.valueOf(object[13]));
			dto.setIdTipoEduContinua(Long.parseLong(String.valueOf(object[14])));
			dto.setTipoEduContinua(String.valueOf(object[15]));
			dto.setPrimerNombre(String.valueOf(object[16]));
			dto.setSegundoNombre(String.valueOf(object[17]));
			dto.setPrimerApellido(String.valueOf(object[18]));
			dto.setSegundoApellido(String.valueOf(object[19]));
			dto.setEmail(String.valueOf(object[20]));
			dto.setFechaInicioEduContinua((Date)object[21]);
			dto.setNumeroDocumento(String.valueOf(object[22]));
			dto.setNombrePersona(usuarioMapper.convertFieldsFullName(dto.getPrimerNombre(), 
					dto.getSegundoNombre(), dto.getPrimerApellido(), dto.getSegundoApellido()));
			list.add(dto);
		}
		return list;
	}
	
	
	
	@Override
	public List<ParticipanteDto> findAllParticipantesEducacionContinua(Long idEduContinua) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_participante, tp.id as id_tipo_participante, tp.tipo_participante,")
		.append(" td.id as id_tipo_documento, td.tipo_documento, e.id as id_edu_continua, e.nombre, p.codigoqr, p.fecha_generacion_diploma, ")
			 .append(" p.fecha_registro, p.imagen_codigo_qr, p.tarjeta_inscripcion, p.aprobado, p.token, tec.id as id_tipo_educacion_continua,")
			 .append(" tec.tipo_educacion_continua, per.primer_nombre, per.segundo_nombre, per.primer_apellido, per.segundo_apellido,")
			 .append(" per.email, e.fecha_inicio, per.numero_documento")
			 .append(" from participantes p join personas per on p.id_persona=per.id ")
			 .append(" join educacion_continua e on p.educacion_continua_id=e.id")
			 .append(" join tipos_participante tp on p.id_tipo_participante=tp.id")
			 .append(" join tipos_documento td on per.id_tipo_documento=td.id")
			 .append(" join tipos_educacion_continua tec on e.id_tipo_educacion_continua=tec.id")
			 .append(" where e.id= ?1 ORDER BY per.primer_apellido");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idEduContinua);
		
		List<Object[]> result=q.getResultList();
		List<ParticipanteDto> list=new ArrayList<ParticipanteDto>();
		for(Object[] object : result) {
			ParticipanteDto dto=new ParticipanteDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setIdTipoParticipante(Long.parseLong(String.valueOf(object[1])));
			dto.setTipoParticipante(String.valueOf(object[2]));
			dto.setIdTipoDocumento(Long.parseLong(String.valueOf(object[3])));
			dto.setTipoDocumento(String.valueOf(object[4]));
			dto.setIdEducacionContinua(Long.parseLong(String.valueOf(object[5])));
			dto.setEducacionContinua(String.valueOf(object[6]));
			dto.setCodigoQR(String.valueOf(object[7]));
			dto.setFechaGeneracionDiploma((Date)object[8]);
			dto.setFechaInscripcion((Date)object[9]);
			dto.setImagenQr(String.valueOf(object[10]));
			dto.setTarjetaInscripcion(String.valueOf(object[11]));
			dto.setAprobado((Boolean)object[12]);
			dto.setToken(String.valueOf(object[13]));
			dto.setIdTipoEduContinua(Long.parseLong(String.valueOf(object[14])));
			dto.setTipoEduContinua(String.valueOf(object[15]));
			dto.setPrimerNombre(String.valueOf(object[16]));
			dto.setSegundoNombre(String.valueOf(object[17]));
			dto.setPrimerApellido(String.valueOf(object[18]));
			dto.setSegundoApellido(String.valueOf(object[19]));
			dto.setEmail(String.valueOf(object[20]));
			dto.setFechaInicioEduContinua((Date)object[21]);
			dto.setNumeroDocumento(String.valueOf(object[22]));
			dto.setNombrePersona(usuarioMapper.convertFieldsFullName(dto.getPrimerNombre(), 
					dto.getSegundoNombre(), dto.getPrimerApellido(), dto.getSegundoApellido()));
			list.add(dto);
		}
		return list;
	}
	
	
	
	
	
	
	
	@Override
	public ParticipanteDto findParticipanteByIdEducacionContinuaAndIdPersona(Long idEducacionContinua, Long idPersona ) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_participante, tp.id as id_tipo_participante, tp.tipo_participante,")
		.append(" td.id as id_tipo_documento, td.tipo_documento, e.id as id_edu_continua, e.nombre, p.codigoqr, p.fecha_generacion_diploma, ")
			 .append(" p.fecha_registro, p.imagen_codigo_qr, p.tarjeta_inscripcion, p.aprobado, p.token, tec.id as id_tipo_educacion_continua,")
			 .append(" tec.tipo_educacion_continua, per.primer_nombre, per.segundo_nombre, per.primer_apellido, per.segundo_apellido,")
			 .append(" per.email, e.fecha_inicio, per.numero_documento")
			 .append(" from participantes p join personas per on p.id_persona=per.id ")
			 .append(" join educacion_continua e on p.educacion_continua_id=e.id")
			 .append(" join tipos_participante tp on p.id_tipo_participante=tp.id")
			 .append(" join tipos_documento td on per.id_tipo_documento=td.id")
			 .append(" join tipos_educacion_continua tec on e.id_tipo_educacion_continua=tec.id")
			 .append(" where e.id= ?1 and per.id = ?2");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idEducacionContinua).setParameter(2, idPersona);
		
		List<Object[]> result=q.getResultList();
		List<ParticipanteDto> list=new ArrayList<ParticipanteDto>();
		if(result.size()==1) {
			Object[] object=result.get(0);
			ParticipanteDto dto=new ParticipanteDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setIdTipoParticipante(Long.parseLong(String.valueOf(object[1])));
			dto.setTipoParticipante(String.valueOf(object[2]));
			dto.setIdTipoDocumento(Long.parseLong(String.valueOf(object[3])));
			dto.setTipoDocumento(String.valueOf(object[4]));
			dto.setIdEducacionContinua(Long.parseLong(String.valueOf(object[5])));
			dto.setEducacionContinua(String.valueOf(object[6]));
			dto.setCodigoQR(String.valueOf(object[7]));
			dto.setFechaGeneracionDiploma((Date)object[8]);
			dto.setFechaInscripcion((Date)object[9]);
			dto.setImagenQr(String.valueOf(object[10]));
			dto.setTarjetaInscripcion(String.valueOf(object[11]));
			dto.setAprobado((Boolean)object[12]);
			dto.setToken(String.valueOf(object[13]));
			dto.setIdTipoEduContinua(Long.parseLong(String.valueOf(object[14])));
			dto.setTipoEduContinua(String.valueOf(object[15]));
			dto.setPrimerNombre(String.valueOf(object[16]));
			dto.setSegundoNombre(String.valueOf(object[17]));
			dto.setPrimerApellido(String.valueOf(object[18]));
			dto.setSegundoApellido(String.valueOf(object[19]));
			dto.setEmail(String.valueOf(object[20]));
			dto.setFechaInicioEduContinua((Date)object[21]);
			dto.setNumeroDocumento(String.valueOf(object[22]));
			dto.setNombrePersona(usuarioMapper.convertFieldsFullName(dto.getPrimerNombre(), 
					dto.getSegundoNombre(), dto.getPrimerApellido(), dto.getSegundoApellido()));
			return dto;
		}
		return null;
	}
	
	
	@Override
	public ParticipanteDto findParticipanteById(Long idParticipante ) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_participante, tp.id as id_tipo_participante, tp.tipo_participante,")
			 .append(" td.id as id_tipo_documento, td.tipo_documento, e.id as id_edu_continua, e.nombre, p.codigoqr, p.fecha_generacion_diploma, ")
			 .append(" p.fecha_registro, p.imagen_codigo_qr, p.tarjeta_inscripcion, p.aprobado, p.token, tec.id as id_tipo_educacion_continua,")
			 .append(" tec.tipo_educacion_continua, per.primer_nombre, per.segundo_nombre, per.primer_apellido, per.segundo_apellido,")
			 .append(" per.email, e.fecha_inicio, per.numero_documento")
			 .append(" from participantes p join personas per on p.id_persona=per.id ")
			 .append(" join educacion_continua e on p.educacion_continua_id=e.id")
			 .append(" join tipos_participante tp on p.id_tipo_participante=tp.id")
			 .append(" join tipos_documento td on per.id_tipo_documento=td.id")
			 .append(" join tipos_educacion_continua tec on e.id_tipo_educacion_continua=tec.id")
			 .append(" where p.id=?1");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idParticipante);
		
		List<Object[]> result=q.getResultList();
		List<ParticipanteDto> list=new ArrayList<ParticipanteDto>();
		if(result.size()==1) {
			Object[] object=result.get(0);
			ParticipanteDto dto=new ParticipanteDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setIdTipoParticipante(Long.parseLong(String.valueOf(object[1])));
			dto.setTipoParticipante(String.valueOf(object[2]));
			dto.setIdTipoDocumento(Long.parseLong(String.valueOf(object[3])));
			dto.setTipoDocumento(String.valueOf(object[4]));
			dto.setIdEducacionContinua(Long.parseLong(String.valueOf(object[5])));
			dto.setEducacionContinua(String.valueOf(object[6]));
			dto.setCodigoQR(String.valueOf(object[7]));
			dto.setFechaGeneracionDiploma((Date)object[8]);
			dto.setFechaInscripcion((Date)object[9]);
			dto.setImagenQr(String.valueOf(object[10]));
			dto.setTarjetaInscripcion(String.valueOf(object[11]));
			dto.setAprobado((Boolean)object[12]);
			dto.setToken(String.valueOf(object[13]));
			dto.setIdTipoEduContinua(Long.parseLong(String.valueOf(object[14])));
			dto.setTipoEduContinua(String.valueOf(object[15]));
			dto.setPrimerNombre(String.valueOf(object[16]));
			dto.setSegundoNombre(String.valueOf(object[17]));
			dto.setPrimerApellido(String.valueOf(object[18]));
			dto.setSegundoApellido(String.valueOf(object[19]));
			dto.setEmail(String.valueOf(object[20]));
			dto.setFechaInicioEduContinua((Date)object[21]);
			dto.setNumeroDocumento(String.valueOf(object[22]));
			dto.setNombrePersona(usuarioMapper.convertFieldsFullName(dto.getPrimerNombre(), 
					dto.getSegundoNombre(), dto.getPrimerApellido(), dto.getSegundoApellido()));
			return dto;
		}
		return null;
	}
	
	
	@Override
	public ParticipanteDto findByToken(String token ) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_participante, tp.id as id_tipo_participante, tp.tipo_participante,")
		.append(" td.id as id_tipo_documento, td.tipo_documento, e.id as id_edu_continua, e.nombre, p.codigoqr, p.fecha_generacion_diploma, ")
			 .append(" p.fecha_registro, p.imagen_codigo_qr, p.tarjeta_inscripcion, p.aprobado, p.token, tec.id as id_tipo_educacion_continua,")
			 .append(" tec.tipo_educacion_continua, per.primer_nombre, per.segundo_nombre, per.primer_apellido, per.segundo_apellido,")
			 .append(" per.email, e.fecha_inicio, e.fecha_fin, d.id as id_diploma, d.structure_diploma, p.diploma_participacion,")
			 .append(" d.updated_at, per.numero_documento")
			 .append(" from participantes p join personas per on p.id_persona=per.id ")
			 .append(" join educacion_continua e on p.educacion_continua_id=e.id")
			 .append(" join tipos_participante tp on p.id_tipo_participante=tp.id")
			 .append(" join tipos_documento td on per.id_tipo_documento=td.id")
			 .append(" join tipos_educacion_continua tec on e.id_tipo_educacion_continua=tec.id")
			 .append(" left join diplomas d on e.id_diploma=d.id")
			 .append(" where p.token=?1");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, token);
		
		List<Object[]> result=q.getResultList();
		List<ParticipanteDto> list=new ArrayList<ParticipanteDto>();
		if(result.size()==1) {
			Object[] object=result.get(0);
			ParticipanteDto dto=new ParticipanteDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setIdTipoParticipante(Long.parseLong(String.valueOf(object[1])));
			dto.setTipoParticipante(String.valueOf(object[2]));
			dto.setIdTipoDocumento(Long.parseLong(String.valueOf(object[3])));
			dto.setTipoDocumento(String.valueOf(object[4]));
			dto.setIdEducacionContinua(Long.parseLong(String.valueOf(object[5])));
			dto.setEducacionContinua(String.valueOf(object[6]));
			dto.setCodigoQR(String.valueOf(object[7]));
			dto.setFechaGeneracionDiploma((Date)object[8]);
			dto.setFechaInscripcion((Date)object[9]);
			dto.setImagenQr(String.valueOf(object[10]));
			dto.setTarjetaInscripcion(String.valueOf(object[11]));
			dto.setAprobado((Boolean)object[12]);
			dto.setToken(String.valueOf(object[13]));
			dto.setIdTipoEduContinua(Long.parseLong(String.valueOf(object[14])));
			dto.setTipoEduContinua(String.valueOf(object[15]));
			dto.setPrimerNombre(String.valueOf(object[16]));
			dto.setSegundoNombre(String.valueOf(object[17]));
			dto.setPrimerApellido(String.valueOf(object[18]));
			dto.setSegundoApellido(String.valueOf(object[19]));
			dto.setEmail(String.valueOf(object[20]));
			dto.setFechaInicioEduContinua((Date)object[21]);
			dto.setFechaFinEduContinua((Date)object[22]);
			dto.setIdDiploma(Long.parseLong(String.valueOf(object[23])));
			dto.setEstructuraDiploma(String.valueOf(object[24]));
			dto.setDiplomaParticipacion(String.valueOf(object[25]));
			dto.setFechaActualizacionDiploma((Date) object[26]);
			dto.setNumeroDocumento(String.valueOf(object[27]));
			dto.setNombrePersona(usuarioMapper.convertFieldsFullName(dto.getPrimerNombre(), 
					dto.getSegundoNombre(), dto.getPrimerApellido(), dto.getSegundoApellido()));
			return dto;
		}
		return null;
	}
	
	
	@Override
	public ParticipanteDto validarParticipanteYaInscritoApp(Long idEduContinua, String documento) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_participante, tp.id as id_tipo_participante, tp.tipo_participante,")
		.append(" td.id as id_tipo_documento, td.tipo_documento, e.id as id_edu_continua, e.nombre, p.codigoqr, p.fecha_generacion_diploma, ")
			 .append(" p.fecha_registro, p.imagen_codigo_qr, p.tarjeta_inscripcion, p.aprobado, p.token, tec.id as id_tipo_educacion_continua,")
			 .append(" tec.tipo_educacion_continua, per.primer_nombre, per.segundo_nombre, per.primer_apellido, per.segundo_apellido,")
			 .append(" per.email, e.fecha_inicio, per.numero_documento")
			 .append(" from participantes p join personas per on p.id_persona=per.id ")
			 .append(" join educacion_continua e on p.educacion_continua_id=e.id")
			 .append(" join tipos_participante tp on p.id_tipo_participante=tp.id")
			 .append(" join tipos_documento td on per.id_tipo_documento=td.id")
			 .append(" join tipos_educacion_continua tec on e.id_tipo_educacion_continua=tec.id")
			 .append(" where e.id= ?1 and per.numero_documento = ?2");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idEduContinua).setParameter(2, documento);
		
		List<Object[]> result=q.getResultList();
		List<ParticipanteDto> list=new ArrayList<ParticipanteDto>();
		if(result.size()==1) {
			Object[] object=result.get(0);
			ParticipanteDto dto=new ParticipanteDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setIdTipoParticipante(Long.parseLong(String.valueOf(object[1])));
			dto.setTipoParticipante(String.valueOf(object[2]));
			dto.setIdTipoDocumento(Long.parseLong(String.valueOf(object[3])));
			dto.setTipoDocumento(String.valueOf(object[4]));
			dto.setIdEducacionContinua(Long.parseLong(String.valueOf(object[5])));
			dto.setEducacionContinua(String.valueOf(object[6]));
			dto.setCodigoQR(String.valueOf(object[7]));
			dto.setFechaGeneracionDiploma((Date)object[8]);
			dto.setFechaInscripcion((Date)object[9]);
			dto.setImagenQr(String.valueOf(object[10]));
			dto.setTarjetaInscripcion(String.valueOf(object[11]));
			dto.setAprobado((Boolean)object[12]);
			dto.setToken(String.valueOf(object[13]));
			dto.setIdTipoEduContinua(Long.parseLong(String.valueOf(object[14])));
			dto.setTipoEduContinua(String.valueOf(object[15]));
			dto.setPrimerNombre(String.valueOf(object[16]));
			dto.setSegundoNombre(String.valueOf(object[17]));
			dto.setPrimerApellido(String.valueOf(object[18]));
			dto.setSegundoApellido(String.valueOf(object[19]));
			dto.setEmail(String.valueOf(object[20]));
			dto.setFechaInicioEduContinua((Date)object[21]);
			dto.setNumeroDocumento(String.valueOf(object[22]));
			dto.setNombrePersona(usuarioMapper.convertFieldsFullName(dto.getPrimerNombre(), 
					dto.getSegundoNombre(), dto.getPrimerApellido(), dto.getSegundoApellido()));
			return dto;
		}
		return null;
	}
	
	
	@Override
	public ParticipanteDto validarQr(String qr) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_participante, tp.id as id_tipo_participante, tp.tipo_participante,")
		.append(" td.id as id_tipo_documento, td.tipo_documento, e.id as id_edu_continua, e.nombre, p.codigoqr, p.fecha_generacion_diploma, ")
			 .append(" p.fecha_registro, p.imagen_codigo_qr, p.tarjeta_inscripcion, p.aprobado, p.token, tec.id as id_tipo_educacion_continua,")
			 .append(" tec.tipo_educacion_continua, per.primer_nombre, per.segundo_nombre, per.primer_apellido, per.segundo_apellido,")
			 .append(" per.email, e.fecha_inicio, per.numero_documento")
			 .append(" from participantes p join personas per on p.id_persona=per.id ")
			 .append(" join educacion_continua e on p.educacion_continua_id=e.id")
			 .append(" join tipos_participante tp on p.id_tipo_participante=tp.id")
			 .append(" join tipos_documento td on per.id_tipo_documento=td.id")
			 .append(" join tipos_educacion_continua tec on e.id_tipo_educacion_continua=tec.id")
			 .append(" where p.codigoqr= ?1 ");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, qr);
		
		List<Object[]> result=q.getResultList();
		List<ParticipanteDto> list=new ArrayList<ParticipanteDto>();
		if(result.size()==1) {
			Object[] object=result.get(0);
			ParticipanteDto dto=new ParticipanteDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setIdTipoParticipante(Long.parseLong(String.valueOf(object[1])));
			dto.setTipoParticipante(String.valueOf(object[2]));
			dto.setIdTipoDocumento(Long.parseLong(String.valueOf(object[3])));
			dto.setTipoDocumento(String.valueOf(object[4]));
			dto.setIdEducacionContinua(Long.parseLong(String.valueOf(object[5])));
			dto.setEducacionContinua(String.valueOf(object[6]));
			dto.setCodigoQR(String.valueOf(object[7]));
			dto.setFechaGeneracionDiploma((Date)object[8]);
			dto.setFechaInscripcion((Date)object[9]);
			dto.setImagenQr(String.valueOf(object[10]));
			dto.setTarjetaInscripcion(String.valueOf(object[11]));
			dto.setAprobado((Boolean)object[12]);
			dto.setToken(String.valueOf(object[13]));
			dto.setIdTipoEduContinua(Long.parseLong(String.valueOf(object[14])));
			dto.setTipoEduContinua(String.valueOf(object[15]));
			dto.setPrimerNombre(String.valueOf(object[16]));
			dto.setSegundoNombre(String.valueOf(object[17]));
			dto.setPrimerApellido(String.valueOf(object[18]));
			dto.setSegundoApellido(String.valueOf(object[19]));
			dto.setEmail(String.valueOf(object[20]));
			dto.setFechaInicioEduContinua((Date)object[21]);
			dto.setNumeroDocumento(String.valueOf(object[22]));
			dto.setNombrePersona(usuarioMapper.convertFieldsFullName(dto.getPrimerNombre(), 
					dto.getSegundoNombre(), dto.getPrimerApellido(), dto.getSegundoApellido()));
			return dto;
		}
		return null;
	}




	
	
}
