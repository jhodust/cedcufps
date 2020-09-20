package com.ufps.cedcufps.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IEducacionContinuaCustomDao;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Persona;

@Repository
public class EducacionContinuaCustomDaoImpl implements IEducacionContinuaCustomDao{

	@PersistenceContext
	private EntityManager em;
	
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

}
