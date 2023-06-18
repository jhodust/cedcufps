package com.ufps.cedcufps.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Estudiante;


public interface IAdministrativoDao extends JpaRepository<Administrativo, Long>{


	@Query(value = "select * from administrativos where id_persona=?1", nativeQuery = true)
	public Administrativo findOnlyAdministrativo(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO administrativos (id_dependencia, cargo, id_persona) VALUES(?1,?2,?3) ON DUPLICATE KEY  UPDATE dependencia=VALUES(id_dependencia), cargo=VALUES(cargo), estado= true", nativeQuery = true)
	public void updateOnlyAdministrativo(Long idDependencia, String cargo, Long idPersona);
}
