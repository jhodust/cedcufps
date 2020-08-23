package com.ufps.cedcufps.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IPersonaCustomDao;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Graduado;

@Repository
public class PersonaCustomDaoImpl implements IPersonaCustomDao {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Override
	public int saveEstudiante(Estudiante e) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("insert into estudiantes (id_persona,id_programa,codigo) values (?,?,?)")
				.setParameter(1, e.getId())
				.setParameter(2, e.getPrograma().getId())
				.setParameter(3, e.getCodigo())
				.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public int saveDocente(Docente d) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("insert into docentes (id_persona,id_departamento,codigo) values (?,?,?)")
				.setParameter(1, d.getId())
				.setParameter(2, d.getDepartamento().getId())
				.setParameter(3, d.getCodigo())
				.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public int saveAdministrativo(Administrativo a) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("insert into administrativos (id_persona,dependencia,cargo) values (?,?,?)")
				.setParameter(1, a.getId())
				.setParameter(2, a.getDependencia())
				.setParameter(3, a.getCargo())
				.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public int saveGraduado(Graduado g) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("insert into graduados (id_persona,id_programa,anio) values (?,?,?)")
				.setParameter(1, g.getId())
				.setParameter(2, g.getPrograma().getId())
				.setParameter(3, g.getAnio())
		 		.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public int saveExterno(Externo e) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("insert into externos (id_persona,profesion,empresa) values (?,?,?)")
				.setParameter(1, e.getId())
				.setParameter(2, e.getProfesion())
				.setParameter(3, e.getEmpresa())
				.executeUpdate();
		
	}

}
