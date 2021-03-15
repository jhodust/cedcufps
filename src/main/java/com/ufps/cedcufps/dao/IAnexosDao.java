package com.ufps.cedcufps.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ufps.cedcufps.modelos.Anexos;


public interface IAnexosDao extends JpaRepository<Anexos, Long> {

	
	@Query(value="select anex.* from anexos anex join educacion_continua ec on anex.educacion_continua_id=ec.id where ec.id_acceso = ?1", 
			nativeQuery = true)
	public List<Anexos> findAnexosByEduContinuaIdAcceso(String idAcceso);
	
	@Query(value="select * from anexos where id = ?1", 
			nativeQuery = true)
	public Anexos findAnexoById(Long id);
	
	@Transactional
	@Modifying
	@Query(value="delete from anexos where id = ?1", 
			nativeQuery = true)
	public void deleteAnexo(Long id);


}
