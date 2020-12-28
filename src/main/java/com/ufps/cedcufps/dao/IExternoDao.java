package com.ufps.cedcufps.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;

public interface IExternoDao extends JpaRepository<Externo, Long>{


	@Query(value = "select * from externos where id_persona=?1", nativeQuery = true)
	public Externo findOnlyExterno(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO externos (profesion, empresa, id_persona) VALUES(?1,?2,?3) ON DUPLICATE KEY UPDATE profesion=VALUES(profesion), empresa=VALUES(empresa), estado= true", nativeQuery = true)
	public void updateOnlyExterno(String profesion, String empresa, Long idPersona);
}
