package com.ufps.cedcufps.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ufps.cedcufps.modelos.ElementoDiploma;

public interface IElementoDiplomaDao extends CrudRepository<ElementoDiploma, Long> {

	@Modifying
	@Query("delete from ElementoDiploma e where e.diploma.id = ?1")
	public void limpiarElementosAntiguos(Long idDiploma);
	 
	  
}
