package com.ufps.cedcufps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IDocenteCustomDao;
import com.ufps.cedcufps.dto.DocenteDto;

@Repository
public class DocenteCustomDaoImpl implements IDocenteCustomDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public DocenteDto findDocenteByIdPersona(Long idPersona) {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		sb.append("select id_persona, codigo, id_departamento from docentes where id_persona = ? and estado = 1 ");
		Query query= em.createNativeQuery(sb.toString())
		.setParameter(1, idPersona);
		
		 List<Object[]> result = query.getResultList();
		 
		 if(result.size() == 1) {
			 DocenteDto dto=new DocenteDto();
			 dto.setId(Long.parseLong(String.valueOf(result.get(0)[0])));
			 dto.setCodigo(String.valueOf(result.get(0)[2]));
			 return dto;
		 }
		return null;
	}

}
