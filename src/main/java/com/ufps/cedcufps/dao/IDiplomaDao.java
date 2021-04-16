package com.ufps.cedcufps.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Diploma;

public interface IDiplomaDao extends JpaRepository<Diploma, Long>{

	@Query(value="select d from Diploma d where d.id = ?1")
	public Diploma findDiplomaById(Long id);
	
	@Query(value="select d.* from educacion_continua ec join diplomas d on ec.id_diploma=d.id where ec.id = ?1", nativeQuery=true)
	public Diploma findDiplomaByIdEduContinua(Long id);

}
