package com.ufps.cedcufps.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IDepartamentoCustomDao;
import com.ufps.cedcufps.dto.DepartamentoDto;
import com.ufps.cedcufps.modelos.Departamento;
import javax.persistence.Query;

@Repository
public class DepartamentoCustomDaoImpl implements IDepartamentoCustomDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<DepartamentoDto> findDeptosPermisosDocentesForDocEstAdminvo(Long idDirector) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append(" select d.id, d.departamento, d.id_facultad, f.facultad")
			 .append(" from rol_persona_depto_per rpdp join departamentos d on rpdp.id_depto=d.id")
			 .append(" join facultades f on d.id_facultad=f.id")
			 .append(" where rpdp.id_persona = ?1");
			 
		Query q=em.createNativeQuery(query.toString()).setParameter(1, idDirector);
		
		List<Object[]> result=q.getResultList();
		List<DepartamentoDto> list= new ArrayList<DepartamentoDto>();
		for(Object[] object:result) {
			DepartamentoDto dto= new DepartamentoDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setDepartamento(String.valueOf(object[1]));
			dto.setIdFacultad(Long.parseLong(String.valueOf(object[2])));
			dto.setNombreFacultad(String.valueOf(object[3]));
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<DepartamentoDto> findAll() {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append(" select d.id, d.departamento, d.id_facultad, f.facultad")
			 .append(" from departamentos d join facultades f on d.id_facultad=f.id");
			 
		Query q=em.createNativeQuery(query.toString());
		
		List<Object[]> result=q.getResultList();
		List<DepartamentoDto> list= new ArrayList<DepartamentoDto>();
		for(Object[] object:result) {
			DepartamentoDto dto= new DepartamentoDto();
			dto.setId(Long.parseLong(String.valueOf(object[0])));
			dto.setDepartamento(String.valueOf(object[1]));
			dto.setIdFacultad(Long.parseLong(String.valueOf(object[2])));
			dto.setNombreFacultad(String.valueOf(object[3]));
			list.add(dto);
		}
		return list;
	}
}
