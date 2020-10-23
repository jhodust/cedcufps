package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.TextoDiploma;

public interface ITextoDiplomaDao extends JpaRepository<TextoDiploma, Long> {

	@Query("select t from TextoDiploma t where t.diploma.id = ?1")
	 public List<TextoDiploma> findByDiploma(Long idDiploma);
	
	@Query("select t from TextoDiploma t where t.diploma = null")
	 public List<TextoDiploma> findAllDefault();
}
