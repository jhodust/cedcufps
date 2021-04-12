package com.ufps.cedcufps.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IProgramaCustomDao;
import com.ufps.cedcufps.dto.ProgramaDto;

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
	
	
}
