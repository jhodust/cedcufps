package com.ufps.cedcufps.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IProgramaCustomDao;
import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.modelos.Programa;

@Repository
public class ProgramaCustomDaoImpl implements IProgramaCustomDao{
	
	
	@PersistenceContext
	private EntityManager em;
	
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
			ProgramaDto dto=new ProgramaDto();
			dto.setId(Long.parseLong(String.valueOf(result.get(0)[0])));
			dto.setCodigo(String.valueOf(result.get(0)[1]));
			dto.setPrograma(String.valueOf(result.get(0)[2]));
			dto.setIdDirector(Long.parseLong(String.valueOf(result.get(0)[3])));
			dto.setIdFacultad(Long.parseLong(String.valueOf(result.get(0)[4])));
			return dto;
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
		List<ProgramaDto> list=new ArrayList<ProgramaDto>();
		for(Object[] object : result) {
			ProgramaDto dto=new ProgramaDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setCodigo(String.valueOf(object[1]));
			dto.setPrograma(String.valueOf(object[2]));
			dto.setIdDirector(Long.parseLong(String.valueOf(object[3])));
			dto.setIdFacultad(Long.parseLong(String.valueOf(object[4])));
			list.add(dto);
		}
		
		return list;
	}
	

	@Override
	public List<ProgramaDto> findProgramasOfPermissionEdCPersona(Long idPersona) {
		StringBuilder query = new StringBuilder();
		query.append(" select id, codigo, programa, id_director, id_facultad")
			 .append(" from programas p where p.id in (select rpp.id_programa from ")
			 .append(" roles_personas_programas_ec rpp where id_persona = ?1)");
			 
		
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idPersona);
		
		List<Object[]> result=q.getResultList();
		List<ProgramaDto> list=new ArrayList<ProgramaDto>();
		for(Object[] object : result) {
			ProgramaDto dto=new ProgramaDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setCodigo(String.valueOf(object[1]));
			dto.setPrograma(String.valueOf(object[2]));
			dto.setIdDirector(Long.parseLong(String.valueOf(object[3])));
			dto.setIdFacultad(Long.parseLong(String.valueOf(object[4])));
			list.add(dto);
		}
		
		return list;
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
		List<ProgramaDto> list=new ArrayList<ProgramaDto>();
		for(Object[] object : result) {
			ProgramaDto dto=new ProgramaDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setCodigo(String.valueOf(object[1]));
			dto.setPrograma(String.valueOf(object[2]));
			dto.setIdDirector(Long.parseLong(String.valueOf(object[3])));
			dto.setIdFacultad(Long.parseLong(String.valueOf(object[4])));
			list.add(dto);
		}
		
		return list;
	}
	
	
}
