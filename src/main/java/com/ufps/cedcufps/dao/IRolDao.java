package com.ufps.cedcufps.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Rol;

public interface IRolDao extends CrudRepository<Rol, Long>{

	@Transactional
	@Modifying
	@Query(value= "delete FROM roles where authority=?1 and id_persona=?2",nativeQuery = true)
	public void deleteRolByPersona(String rol, Long idPersona);
}
