package com.ufps.cedcufps.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IPersonaCustomDao;
import com.ufps.cedcufps.dao.IProgramaCustomDao;
import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.mapper.IProgramaMapper;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.utils.TipoPersonaUtil;

@Repository
public class ProgramaCustomDaoImpl implements IProgramaCustomDao{
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IPersonaCustomDao personaCustomDao;
	
	@Autowired
	private IProgramaMapper programaMapper;
	
	@Override
	public ProgramaDto findProgramaDtoByDirector(Long idDir) {
		StringBuilder query = new StringBuilder();
		query.append(" select id, codigo, programa, id_director, id_facultad")
			 .append(" from programas pro")
		     .append(" where id_director = ?1");
		
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idDir);
		
		List<Object[]> result=q.getResultList();
		if(result.size()==1) {
			return programaMapper.convertObjectToProgramaDto(result.get(0));
		}
		return null;
	}
	
	
	@Override
	public List<ProgramaDto> findAllProgramas() {
		StringBuilder query = new StringBuilder();
		query.append(" select id, codigo, programa, id_director, id_facultad")
			 .append(" from programas pro");
		
		Query q=em.createNativeQuery(query.toString());
		
		List<Object[]> result=q.getResultList();
		return programaMapper.convertListObjectToListProgramaDto(result);
	}
	

	@Override
	public List<ProgramaDto> findProgramasOfPermissionEdCPersona(Long idPersona) {
		StringBuilder query = new StringBuilder();
		query.append(" select id, codigo, programa, id_director, id_facultad")
			 .append(" from programas p where p.id in (select rpp.id_programa from ")
			 .append(" roles_personas_programas_ec rpp where id_persona = ?1)");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idPersona);
		
		List<Object[]> result=q.getResultList();
		return programaMapper.convertListObjectToListProgramaDto(result);
	}
	
	@Override
	public List<ProgramaDto> findProgramasEducacionContinuaBase(Long idPersona) {
		StringBuilder query = new StringBuilder();
		query.append(" select id, codigo, programa, id_director, id_facultad")
			 .append(" from programas p where p.id in (select rpp.id_programa from ")
			 .append(" roles_personas_programas_ec rpp where id_persona = ?1)")
			 .append(" or p.id in (select distinct e.id_programa from")
			 .append(" educacion_continua e where e.id_docente = ?1)");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idPersona);
		
		List<Object[]> result=q.getResultList();
		return programaMapper.convertListObjectToListProgramaDto(result);
		
	}
	
	
	
	@Override
	public ProgramaDto findOthersProgramasByDirector(Long idPro, Long idDir) {
		StringBuilder query = new StringBuilder();
		query.append(" select id, codigo, programa, id_director, id_facultad")
			 .append(" from programas p where p.id != ?1 and p.id_director = ?2");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idPro).setParameter(2, idDir);
		
		List<Object[]> result=q.getResultList();
		if(result.size()==1) {
			return programaMapper.convertObjectToProgramaDto(result.get(0));
		}
		
		return null;
	}
	
	
	
	@Override
	public ProgramaDto findByDirector(Long idDir) {
		StringBuilder query = new StringBuilder();
		query.append(" select id, codigo, programa, id_director, id_facultad")
			 .append(" from programas p where p.id_director = ?1");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idDir);
		
		List<Object[]> result=q.getResultList();
		List<ProgramaDto> list=new ArrayList<ProgramaDto>();
		if(result.size()==1) {
			return programaMapper.convertObjectToProgramaDto(result.get(0));
		}
		
		return null;
	}

	
	
	@Override
	public Programa findProgramaById(Long id) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_programa, p.codigo, p.programa, p.id_director, p.id_facultad as id_facultad, f.facultad")
			 .append(" from programas p join facultades f on p.id_facultad= f.id")
			 .append(" where p.id = ?1");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, id);
		
		List<Object[]> result=q.getResultList();
		List<ProgramaDto> list=new ArrayList<ProgramaDto>();
		if(result.size()==1) {
			Programa programa=new Programa();
			programa.setId(Long.parseLong(String.valueOf(result.get(0)[0])));
			programa.setCodigo(String.valueOf(result.get(0)[1]));
			programa.setPrograma(String.valueOf(result.get(0)[2]));
			programa.setDirectorPrograma(personaCustomDao.findDocenteDirPrograma(programa.getId(), Long.parseLong(String.valueOf(result.get(0)[3]))));
			Facultad f= new Facultad();
			f.setId(Long.parseLong(String.valueOf(result.get(0)[4])));
			f.setFacultad(String.valueOf(result.get(0)[5]));
			programa.setFacultad(f);
			return programa;
		}
		
		return null;
	}
	
	
	
	
	@Override
	public List<ProgramaDto> findProgramasExceptSome(List<Long> idProgramas) {
		StringBuilder query = new StringBuilder();
		query.append(" select id, codigo, programa, id_director, id_facultad")
			 .append(" from programas p where p.id not in (?1)");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idProgramas);
		
		List<Object[]> result=q.getResultList();
		return programaMapper.convertListObjectToListProgramaDto(result);
	}
	
	@Override
	public List<ProgramaDto> findProgramasPermisosEduContinuaForDirProgramaExceptOwn(Long idDirector, Long idPrograma) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_programa, p.codigo, p.programa, p.id_director, p.id_facultad")
			 .append(" from roles_personas_programas_ec rppe join programas p on rppe.id_programa=p.id")
			 .append(" where rppe.id_persona = ?1 and rppe.id_programa != ?2");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idDirector).setParameter(2, idPrograma);
		
		List<Object[]> result=q.getResultList();
		return programaMapper.convertListObjectToListProgramaDto(result);
	}
	
	@Override
	public List<ProgramaDto> findProgramasPermisosEduContinuaForDocEstAdminvo(Long idPersona) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_programa, p.codigo, p.programa, p.id_director, p.id_facultad")
			 .append(" from roles_personas_programas_ec rppe join programas p on rppe.id_programa=p.id ")
			 .append(" where rppe.id_persona = ?1");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idPersona);
		
		List<Object[]> result=q.getResultList();
		return programaMapper.convertListObjectToListProgramaDto(result);
	}
	
	@Override
	public List<ProgramaDto> findProgramasPermisosEstudiantesForDirProgramaExceptOwn(Long idDirector, Long idPrograma) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_programa, p.codigo, p.programa, p.id_director, p.id_facultad")
			 .append(" from rol_persona_programa_per rppp join programas p on rppp.id_programa=p.id ")
			 .append(" join tipos_persona tp on tp.id=rppp.id_tipo_persona")
			 .append(" where rppp.id_persona = ?1 and rppp.id_programa != ?2 and tp.id = ?3 ");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idDirector).setParameter(2, idPrograma).setParameter(3, TipoPersonaUtil.ESTUDIANTE);
		
		List<Object[]> result=q.getResultList();
		return programaMapper.convertListObjectToListProgramaDto(result);
		
	}
	
	
	@Override
	public List<ProgramaDto> findProgramasPermisosGraduadosForDirProgramaExceptOwn(Long idDirector, Long idPrograma) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_programa, p.codigo, p.programa, p.id_director, p.id_facultad")
			 .append(" from rol_persona_programa_per rppp join programas p on rppp.id_programa=p.id ")
			 .append(" join tipos_persona tp on tp.id=rppp.id_tipo_persona")
			 .append(" where rppp.id_persona = ?1 and rppp.id_programa != ?2 and tp.id = ?3 ");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idDirector).setParameter(2, idPrograma).setParameter(3, TipoPersonaUtil.GRADUADO);
		
		List<Object[]> result=q.getResultList();
		return programaMapper.convertListObjectToListProgramaDto(result);
	}
	
	
	@Override
	public List<ProgramaDto> findProgramasPermisosEstudiantesForDocEstAdminvo(Long idPersona) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_programa, p.codigo, p.programa, p.id_director, p.id_facultad")
			 .append(" from rol_persona_programa_per rppp join programas p on rppp.id_programa=p.id ")
			 .append(" join tipos_persona tp on tp.id=rppp.id_tipo_persona")
			 .append(" where rppp.id_persona = ?1 and tp.tipo_persona=?2 ");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idPersona).setParameter(2, TipoPersonaUtil.ESTUDIANTE);
		
		List<Object[]> result=q.getResultList();
		return programaMapper.convertListObjectToListProgramaDto(result);
	}
	
	@Override
	public List<ProgramaDto> findProgramasPermisosGraduadosForDocEstAdminvo(Long idPersona) {
		StringBuilder query = new StringBuilder();
		query.append(" select p.id as id_programa, p.codigo, p.programa, p.id_director, p.id_facultad")
			 .append(" from rol_persona_programa_per rppp join programas p on rppp.id_programa=p.id ")
			 .append(" join tipos_persona tp on tp.id=rppp.id_tipo_persona")
			 .append(" where rppp.id_persona = ?1 and tp.tipo_persona=?2 ");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idPersona).setParameter(2, TipoPersonaUtil.GRADUADO);
		
		List<Object[]> result=q.getResultList();
		return programaMapper.convertListObjectToListProgramaDto(result);
	}
	
	



	
	
	
}
