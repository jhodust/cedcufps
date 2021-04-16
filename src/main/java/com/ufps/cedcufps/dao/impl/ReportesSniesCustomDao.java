package com.ufps.cedcufps.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IReportesSniesCustomDao;
import com.ufps.cedcufps.dto.InformeCursosDto;
import com.ufps.cedcufps.dto.InformeDetalleEducacionContinuaDto;
import com.ufps.cedcufps.dto.InformeEducacionContinuaDto;
import com.ufps.cedcufps.dto.InformeParticipanteResponsableDto;
import com.ufps.cedcufps.dto.InformePonenteDto;
import com.ufps.cedcufps.dto.InformeSniesDto;
import com.ufps.cedcufps.exception.CustomException;

@Repository
public class ReportesSniesCustomDao implements IReportesSniesCustomDao{

	@PersistenceContext
	private EntityManager em;
	

	@Override
	public List<InformeSniesDto>  findAllInformesSNIES(Long idPrograma) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT id, DATE_FORMAT(fecha_inicio, '%d/%m/%Y'), DATE_FORMAT(fecha_fin, '%d/%m/%Y'), informe_cursos, informe_educacion_continua, informe_participante, descripcion ") 
			 .append(" from informes_snies e ");
		
		if(idPrograma==null) {
			query.append(" where id_programa IS NULL");
		}else {
			query.append(" where id_programa = ?1");
		}
		
		query.append(" order by fecha_inicio");
			 
			
		Query q=em.createNativeQuery(query.toString());
		if(idPrograma!=null) {
			q.setParameter(1,idPrograma);
		}
		
		List<Object[]> result= q.getResultList();
		List<InformeSniesDto> list= new ArrayList<>();
		for(Object[] o: result ) {
			InformeSniesDto dto= new InformeSniesDto();
			dto.setId(Long.parseLong(String.valueOf(o[0])));
			dto.setFechaInicio(String.valueOf(o[1]));
			dto.setFechaFin(String.valueOf(o[2]));
			dto.setInformeCursos(String.valueOf(o[3]));
			dto.setInformeEduContinua(String.valueOf(o[4]));
			dto.setInformeParticipantes(String.valueOf(o[5]));
			dto.setDescripcion(String.valueOf(o[6]));
			list.add(dto);
			
		}
			
		return list;
	}
	
	@Override
	public List<InformeCursosDto>  informeExcelCursos(Date fechaInicio, Date fechaFin, Long idPrograma) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT CONCAT(p.codigo,'-',tp.tipo_educacion_continua,e.consecutivo), e.nombre, CONCAT(cc.id, ' ',cc.clasificacion_cine) ") 
			 .append(" from educacion_continua e ")
			 .append(" join clasificacion_cine cc on cc.id=e.id_clasificacion_cine")
			 .append(" join programas p on e.id_programa=p.id")
			 .append(" join tipos_educacion_continua tp on tp.id=e.id_tipo_educacion_continua")
			 .append(" where (e.fecha_inicio >= ?1 and e.fecha_inicio <= ?2 ) and e.is_deleted=false");
		
		if(idPrograma!=null) {
			query.append(" and e.id_programa = ?3");
		}
		
		query.append(" order by p.codigo, tp.tipo_educacion_continua, e.consecutivo");
			 
		
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, fechaInicio)
		 .setParameter(2, fechaFin);
		
		if(idPrograma!=null) {
			q.setParameter(3,idPrograma);
		}
		
		List<Object[]> result= q.getResultList();
		List<InformeCursosDto> list= new ArrayList<>();
		for(Object[] o: result ) {
			InformeCursosDto dto= new InformeCursosDto();
			dto.setCodigoCurso(String.valueOf(o[0]));
			dto.setNombreCurso(String.valueOf(o[1]));
			dto.setClasificacionCine(String.valueOf(o[2]));
			list.add(dto);
			
		}
			
		return list;
	}
	
	
	@Override
	public List<InformeEducacionContinuaDto>  informeExcelEduContinuaHoja1(Date fechaInicio, Date fechaFin, Long idPrograma) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		
		query.append(" SELECT DATE_FORMAT(e.fecha_inicio, '%Y'),")
			 .append(" case when DATE_FORMAT(e.fecha_inicio, '%m') BETWEEN 1 and 6 THEN '1'")
			 .append(" else '2'")
			 .append(" end as semestre,")
			 .append(" concat(p.codigo,'-',tp.tipo_educacion_continua,e.consecutivo), e.duracion, tp.id, ")
			 .append(" e.costo_educacion_continua, UPPER(CONCAT(td.tipo_documento,'-',td.descripcion)), per.numero_documento, UPPER(CONCAT(tb.id,'-',tb.tipo_beneficiario)), s1.conteo")
			 .append(" from educacion_continua e ")
			 .append(" join programas p on e.id_programa=p.id")
			 .append(" join tipos_educacion_continua tp on tp.id=e.id_tipo_educacion_continua")
			 .append(" join personas per on e.id_docente=per.id")
			 .append(" join tipos_documento td on td.id=per.id_tipo_documento")
			 .append(" join educacion_continua_tipo_beneficiario ectb on ectb.id_educacion_continua=e.id")
			 .append(" join tipos_beneficiarios tb on tb.id=ectb.id_tipo_beneficiario")
			 .append(" left join (")
			 .append(" select count(par.id) as conteo, ec.id as idEduContinua, tb.id_tipo_persona as idTipoPersona from  educacion_continua ec")
			 .append(" join educacion_continua_tipo_beneficiario ectb on ectb.id_educacion_continua=ec.id")
			 .append(" join tipos_beneficiarios tb on tb.id=ectb.id_tipo_beneficiario")
			 .append(" left join participantes par on par.id_tipo_persona=tb.id_tipo_persona and ec.id=par.educacion_continua_id")
			 .append(" group by ec.id, tb.id_tipo_persona ) s1 on s1.idEduContinua=e.id and s1.idTipoPersona=tb.id_tipo_persona")
			 .append(" where (e.fecha_inicio >= ?1 and e.fecha_inicio <= ?2 ) and e.is_deleted=false");
		
		if(idPrograma!=null) {
			query.append(" and e.id_programa = ?3");
		}
		
		query.append(" order by e.id, e.fecha_inicio");
			 
		
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, fechaInicio)
		 .setParameter(2, fechaFin);
		
		if(idPrograma!=null) {
			q.setParameter(3,idPrograma);
		}
		List<Object[]> result= q.getResultList();
		List<InformeEducacionContinuaDto> list= new ArrayList<>();
		for(Object[] o: result ) {
			InformeEducacionContinuaDto dto= new InformeEducacionContinuaDto();
			dto.setAnio(String.valueOf(o[0]));
			dto.setSemestre(String.valueOf(o[1]));
			dto.setCodigoCurso(String.valueOf(o[2]));
			dto.setNumHoras(String.valueOf(o[3]));
			dto.setIdTipoCurso(String.valueOf(o[4]));
			dto.setValorCurso(String.valueOf(o[5]));
			dto.setIdTipoDocumentoResponsable(String.valueOf(o[6]));
			dto.setNumDocumentoResponsable(String.valueOf(o[7]));
			dto.setIdTipoBeneficiario(String.valueOf(o[8]));
			dto.setCantidadBeneficiarios(String.valueOf(o[9]));
			list.add(dto);
			
		}
			
		return list;
	}
	
	
	@Override
	public List<InformeDetalleEducacionContinuaDto>  informeExcelEduContinuaHoja2(Date fechaInicio, Date fechaFin, Long idPrograma) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		
		query.append(" select e.id, CONCAT(proEdu.codigo,'-',tp.tipo_educacion_continua,e.consecutivo), ")
			 .append(" CONCAT(tb.id,'-',tb.tipo_beneficiario),e.nombre, e.id_tipo_educacion_continua, ")
			 .append(" DATE_FORMAT(e.fecha_inicio, '%d/%m/%Y'),")
			 .append(" UPPER(CONCAT(td.tipo_documento,'-',td.descripcion)), per.numero_documento,")
			 .append(" CONCAT(COALESCE(per.primer_nombre,''),' ',COALESCE(per.segundo_nombre,''),' ',COALESCE(per.primer_apellido,''),")
			 .append("		 ' ',COALESCE(per.segundo_apellido,'')), COALESCE(pro.programa,''), e.costo_educacion_continua,e.duracion")
			 .append(" from educacion_continua e")
			 .append(" join participantes p on p.educacion_continua_id=e.id")
			 .append(" join personas per on p.id_persona=per.id")
			 .append(" join tipos_documento td on td.id=per.id_tipo_documento")
			 .append(" join programas proEdu on proEdu.id=e.id_programa")
			 .append(" left join estudiantes est on per.id=est.id_persona")
			 .append(" join programas pro on pro.id=est.id_programa")
			 .append(" join tipos_educacion_continua tp on tp.id=e.id_tipo_educacion_continua")
			 .append(" join tipos_beneficiarios tb on tb.id_tipo_persona=p.id_tipo_persona")
			 .append(" where (e.fecha_inicio >= ?1 and e.fecha_inicio <= ?2 ) and p.id_tipo_participante='1' and e.is_deleted=false");
			 
		if(idPrograma!=null) {
			query.append(" and e.id_programa = ?3");
		}
			query.append(" order by e.id, e.fecha_inicio");
		
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, fechaInicio)
		 .setParameter(2, fechaFin);
		
		if(idPrograma!=null) {
			q.setParameter(3,idPrograma);
		}
		List<Object[]> result= q.getResultList();
		List<InformeDetalleEducacionContinuaDto> listParticipantes= new ArrayList<>();
		for(Object[] o: result ) {
			InformeDetalleEducacionContinuaDto dto= new InformeDetalleEducacionContinuaDto();
			dto.setIdCurso(String.valueOf(o[0]));
			dto.setCodigoCurso(String.valueOf(o[1]));
			dto.setIdTipoBeneficiario(String.valueOf(o[2]));
			dto.setNombreCurso(String.valueOf(o[3]));
			dto.setTipoCurso(String.valueOf(o[4]));
			dto.setFechaInicio(String.valueOf(o[5]));
			dto.setTipoDocumentoParticipante(String.valueOf(o[6]));
			dto.setNumDocumentoParticipante(String.valueOf(o[7]));
			dto.setNombreParticipante(String.valueOf(o[8]));
			dto.setProgramaEstudiante(String.valueOf(o[9]));
			dto.setValorCurso(String.valueOf(o[10]));
			dto.setNumHorasCurso(String.valueOf(o[11]));
			listParticipantes.add(dto);
			
		}
		
		
		
		query = new StringBuilder();
		
		query.append(" select e.id,")
			 .append(" UPPER(CONCAT(td.tipo_documento,'-',td.descripcion)), per.numero_documento,")
			 .append(" CONCAT(COALESCE(per.primer_nombre,''),' ',COALESCE(per.segundo_nombre,''),' ',COALESCE(per.primer_apellido,''),")
			 .append("		 ' ',COALESCE(per.segundo_apellido,''))")
			 .append(" from educacion_continua e")
			 .append(" join participantes p on p.educacion_continua_id=e.id")
			 .append(" join personas per on p.id_persona=per.id")
			 .append(" join tipos_documento td on td.id=per.id_tipo_documento")
			 .append(" where (e.fecha_inicio >= ?1 and e.fecha_inicio <= ?2 ) and p.id_tipo_participante='2' ");
		if(idPrograma!=null) {
			query.append(" and e.id_programa = ?3");
		}
		query.append(" order by e.id, e.fecha_inicio");
		
		
		q=em.createNativeQuery(query.toString());
		q.setParameter(1, fechaInicio)
		 .setParameter(2, fechaFin);
			
		if(idPrograma!=null) {
			q.setParameter(3,idPrograma);
		}
		
		result= q.getResultList();
		List<InformePonenteDto> listPonentes= new ArrayList<>();
		for(Object[] o: result ) {
			InformePonenteDto dto= new InformePonenteDto();
			dto.setIdCurso(String.valueOf(o[0]));
			dto.setTipoDocumentoPonente(String.valueOf(o[1]));
			dto.setNumDocumentoPonente(String.valueOf(o[2]));
			dto.setNombrePonente(String.valueOf(o[3]));
			
			listPonentes.add(dto);
			
		}
		
		
		return joinParticipantesWithPonentes(listParticipantes,listPonentes);
	}
	
	
	public List<InformeDetalleEducacionContinuaDto> joinParticipantesWithPonentes(List<InformeDetalleEducacionContinuaDto> listParticipantes, List<InformePonenteDto> listPonentes ){
		HashMap<String, List<InformePonenteDto>> map = new HashMap<>();
		for(InformePonenteDto dto:listPonentes) {
			List<InformePonenteDto> list = map.get(dto.getIdCurso());
			if(list==null) {
				list=new ArrayList<InformePonenteDto>();
			}
			
			list.add(dto);
			map.put(dto.getIdCurso(), list);
		}
		
		for(InformeDetalleEducacionContinuaDto dto: listParticipantes) {
			dto.setPonentes(map.get(dto.getIdCurso()));
		}
		
		return listParticipantes;
	}

	
	
	@Override
	public List<InformeParticipanteResponsableDto>  informeExcelParticipantesResponsables(Date fechaInicio, Date fechaFin, Long idPrograma) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		
		query.append(" select UPPER(CONCAT(td.tipo_documento,'-',td.descripcion)), p.numero_documento,")
			 .append(" DATE_FORMAT(p.fecha_expedicion_documento, '%d/%m/%Y'), COALESCE(p.primer_nombre,''),COALESCE(p.segundo_nombre,''),")
			 .append(" COALESCE(p.primer_apellido,''),COALESCE(p.segundo_apellido,''),")
			 .append(" CONCAT(g.descripcion,'-',g.genero), ec.estado_civil, DATE_FORMAT(p.fecha_nacimiento, '%d/%m/%Y'),")
			 .append(" p.id_pais_nacimiento, COALESCE(p.id_municipio_nacimiento,''),p.telefono,p.email as emailPersonal,p.email as emailInstitucional")
			 .append(" FROM educacion_continua e")
			 .append(" join personas p on e.id_docente=p.id")
			 .append(" join tipos_documento td on td.id=p.id_tipo_documento")
			 .append(" join generos g on g.id=p.id_genero")
			 .append(" join estados_civiles ec on ec.id=p.id_estado_civil");
		if(idPrograma!=null) {
			query.append(" and e.id_programa = ?3");
		}
		query.append(" where e.fecha_inicio between ?1 and ?2 and e.is_deleted=false");
			 
		
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, fechaInicio)
		 .setParameter(2, fechaFin);
		
		if(idPrograma!=null) {
			q.setParameter(3,idPrograma);
		}
		List<Object[]> result= q.getResultList();
		List<InformeParticipanteResponsableDto> list= new ArrayList<>();
		for(Object[] o: result ) {
			InformeParticipanteResponsableDto dto= new InformeParticipanteResponsableDto();
			dto.setIdTipoDocumento(String.valueOf(o[0]));
			dto.setNumDocumento(String.valueOf(o[1]));
			dto.setFechaExpedicion(String.valueOf(o[2]));
			dto.setPrimerNombre(String.valueOf(o[3]));
			dto.setSegundoNombre(String.valueOf(o[4]));
			dto.setPrimerApellido(String.valueOf(o[5]));
			dto.setSegundoApellido(String.valueOf(o[6]));
			dto.setIdSexoBiologico(String.valueOf(o[7]));
			dto.setIdEstadoCivil(String.valueOf(o[8]));
			dto.setFechaNacimiento(String.valueOf(o[9]));
			dto.setIdPais(String.valueOf(o[10]));
			dto.setIdMunicipio(String.valueOf(o[11]));
			dto.setTelefonoContacto(String.valueOf(o[12]));
			dto.setEmailPersonal(String.valueOf(o[13]));
			dto.setEmailInstitucional(String.valueOf(o[14]));
			
			
			list.add(dto);
			
		}
			
		return list;
	}
	
	
	
	@Override
	public List<Object[]>  dashboardConteoGeneral(String fechaInicio, String fechaFin, String idPrograma) {
		// TODO Auto-generated method stub
		
		
		Date fechaI = null;
		Date fechaF = null;
		try {
			String format="dd/MM/yyyy";
			fechaI=new SimpleDateFormat(format).parse(fechaInicio);
			fechaF=new SimpleDateFormat(format).parse(fechaFin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new CustomException("Fecha inválida");
			//throw new CustomException("Se ha generado un error con las fechas seleccionadas");
		}
		
		
		
		StringBuilder query = new StringBuilder();
		
		query.append(" (select tec.tipo_educacion_continua, count(e.id)")
			 .append(" FROM educacion_continua e")
			 .append(" join tipos_educacion_continua tec on tec.id=e.id_tipo_educacion_continua")
			 .append(" where (e.fecha_inicio between ?1 and ?2 ) and tec.estado_oficial and e.is_deleted=false");
		if(idPrograma!=null) {
			query.append(" and e.id_programa = ?3");
		}
		query.append(" GROUP BY tec.tipo_educacion_continua)")
			 .append(" UNION")
			 .append(" (select 'Otro', (select COALESCE(count(e.id),0) ")
			 .append(" FROM educacion_continua e")
			 .append(" join tipos_educacion_continua tec on tec.id=e.id_tipo_educacion_continua")
			 .append(" where (e.fecha_inicio >= ?1 and e.fecha_inicio <= ?2 ) and  not tec.estado_oficial and e.is_deleted=false");
		if(idPrograma!=null) {
			query.append(" and e.id_programa = ?3");
		}
		query.append("))");
		
		
		
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, fechaI)
		 .setParameter(2, fechaF);
		
		if(idPrograma!=null) {
			q.setParameter(3,idPrograma);
		}
		List<Object[]> result= q.getResultList();
		
			
		return this.loadDataConteoGeneral(result);
	}

	public List<Object[]> initDataDashboardConteoGeneral(){
		List<Object[]> result=new ArrayList<Object[]>();
		result.add(new Object[] {"Educación Continua","Total"});
		result.add(new Object[] {"Curso",0});
		result.add(new Object[] {"Taller",0});
		result.add(new Object[] {"Seminario",0});
		result.add(new Object[] {"Congreso",0});
		result.add(new Object[] {"Simposio",0});
		result.add(new Object[] {"Diplomado",0});
		result.add(new Object[] {"Otro",0});
		return result;
	}
	
	public List<Object[]> loadDataConteoGeneral(List<Object[]> resultQuery){
		List<Object[]> data= this.initDataDashboardConteoGeneral();
		
		Map<String, Integer> mapEdC= new HashMap<String,Integer>();
		 mapEdC.put("Curso", 1);
		 mapEdC.put("Taller", 2);
		 mapEdC.put("Seminario", 3);
		 mapEdC.put("Congreso", 4);
		 mapEdC.put("Simposio", 5);
		 mapEdC.put("Diplomado", 6);
		 mapEdC.put("Otro", 7);
		 
		for(Object[] o: resultQuery) {
			int row = mapEdC.get(String.valueOf(o[0]));
			data.get(row)[1]=Integer.parseInt(String.valueOf(o[1]));
		}
		
		return data;
	}
	
	@Override
	public List<Object[]> dashboardConteoGeneralTipoPersonas(String fechaInicio, String fechaFin, String idPrograma) {
		// TODO Auto-generated method stub

		Date fechaI = null;
		Date fechaF = null;
		try {
			String format="dd/MM/yyyy";
			fechaI=new SimpleDateFormat(format).parse(fechaInicio);
			fechaF=new SimpleDateFormat(format).parse(fechaFin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new CustomException("Fecha inválida");
			//throw new CustomException("Se ha generado un error con las fechas seleccionadas");
		}
		
		StringBuilder query = new StringBuilder();
		
		query.append(" SELECT tec.tipo_educacion_continua, tp.tipo_persona, COUNT(par.id) ")
			 .append(" FROM participantes par")
			 .append(" join tipos_persona tp on tp.id=par.id_tipo_persona")
			 .append(" join educacion_continua e on e.id=par.educacion_continua_id")
			 .append(" join tipos_educacion_continua tec on tec.id=e.id_tipo_educacion_continua")
			 .append(" where (e.fecha_inicio >= ?1 and e.fecha_inicio <= ?2 ) and tec.estado_oficial and e.is_deleted=false");
		if(idPrograma!=null) {
			query.append(" and e.id_programa = ?3");
		}
		query.append(" GROUP BY tec.tipo_educacion_continua, tp.tipo_persona")
			 .append(" UNION")
			 .append(" SELECT 'Otro', tp.tipo_persona, COUNT(par.id)  ")
			 .append(" FROM participantes par")
			 .append(" join tipos_persona tp on tp.id=par.id_tipo_persona")
			 .append(" join educacion_continua e on e.id=par.educacion_continua_id")
			 .append(" join tipos_educacion_continua tec on tec.id=e.id_tipo_educacion_continua")
			 .append(" where (e.fecha_inicio >= ?1 and e.fecha_inicio <= ?2 ) and  not tec.estado_oficial and e.is_deleted=false");
		if(idPrograma!=null) {
			query.append(" and e.id_programa = ?3");
		}
		query.append(" GROUP BY  tp.tipo_persona ");
		
		
		
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, fechaI)
		 .setParameter(2, fechaF);
		
		if(idPrograma!=null) {
			q.setParameter(3,idPrograma);
		}
		List<Object[]> resultQuery= q.getResultList();
		
		 
			
		return this.loadDataConteoGeneralPersonas(resultQuery);
	}
	
	public List<Object[]> initDataDashboardConteoPersonasEdC(){
		List<Object[]> result=new ArrayList<Object[]>();
		result.add(new Object[] {"Educación Continua","Estudiante", "Docente", "Administrativo", "Graduado", "Externo"});
		result.add(new Object[] {"Curso",0,0,0,0,0});
		result.add(new Object[] {"Taller",0,0,0,0,0});
		result.add(new Object[] {"Seminario",0,0,0,0,0});
		result.add(new Object[] {"Congreso",0,0,0,0,0});
		result.add(new Object[] {"Simposio",0,0,0,0,0});
		result.add(new Object[] {"Diplomado",0,0,0,0,0});
		result.add(new Object[] {"Otro",0,0,0,0,0});
		return result;
	}

	public List<Object[]> loadDataConteoGeneralPersonas(List<Object[]> resultQuery){
		
		Map<String, Integer> mapPersonas= new HashMap<String,Integer>();
		 mapPersonas.put("Estudiante", 1);
		 mapPersonas.put("Docente", 2);
		 mapPersonas.put("Administrativo", 3);
		 mapPersonas.put("Graduado", 4);
		 mapPersonas.put("Externo", 5);
		 
		 Map<String, Integer> mapEdC= new HashMap<String,Integer>();
		 mapEdC.put("Curso", 1);
		 mapEdC.put("Taller", 2);
		 mapEdC.put("Seminario", 3);
		 mapEdC.put("Congreso", 4);
		 mapEdC.put("Simposio", 5);
		 mapEdC.put("Diplomado", 6);
		 mapEdC.put("Otro", 7);
		 
		
		List<Object[]>data=this.initDataDashboardConteoPersonasEdC();
		for(Object[] o: resultQuery) {
			int row = mapEdC.get(String.valueOf(o[0]));
			int column = mapPersonas.get(String.valueOf(o[1]));
			data.get(row)[column]=Integer.parseInt(String.valueOf(o[2]));
		}
		
		return data;
	}
	
	@Override
	public List<Object[]> dashboardConteoGeneralGenero(String fechaInicio, String fechaFin, String idPrograma) {
		// TODO Auto-generated method stub

		Date fechaI = null;
		Date fechaF = null;
		try {
			String format="dd/MM/yyyy";
			fechaI=new SimpleDateFormat(format).parse(fechaInicio);
			fechaF=new SimpleDateFormat(format).parse(fechaFin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new CustomException("Fecha inválida");
			//throw new CustomException("Se ha generado un error con las fechas seleccionadas");
		}
		
		StringBuilder query = new StringBuilder();
		
		query.append(" SELECT g.genero, COUNT(par.id) ")
			 .append(" FROM participantes par")
			 .append(" join personas per on per.id=par.id_persona")
			 .append(" join generos g on g.id=per.id_genero")
			 .append(" join educacion_continua e on e.id=par.educacion_continua_id")
			 .append(" where (e.fecha_inicio >= ?1 and e.fecha_inicio <= ?2 ) and e.is_deleted=false ");
		if(idPrograma!=null) {
			query.append(" and e.id_programa = ?3");
		}
		query.append(" GROUP BY g.genero");
		
		
		
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, fechaI)
		 .setParameter(2, fechaF);
		if(idPrograma!=null) {
			q.setParameter(3,idPrograma);
		}
		List<Object[]> result= q.getResultList();
		result.add(0,new Object [] {"Género","Total"} );
			
		return result;
	}
	
	
}
