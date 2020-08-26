package com.ufps.cedcufps.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IPersonaRolCustomDao;
import com.ufps.cedcufps.modelos.PersonaRol;

@Repository
public class PersonaRolDaoImpl implements IPersonaRolCustomDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void save(PersonaRol p) {
		// TODO Auto-generated method stub
		em.persist(p);
	}

	@Transactional
	@Modifying
	@Override
	public void deleteRolesDirPrograma(Long idPersona) {
		// TODO Auto-generated method stub
		
		em.createNativeQuery("delete from personas_x_roles where id_persona=?1 and id_rol NOT IN (select id from roles where authority='ROLE_ATTENDANCE' or authority='ROLE_USER')")
		.setParameter(1, idPersona)
		.executeUpdate();
	}
}
