package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Genero;
import com.ufps.cedcufps.modelos.ImagenDiploma;
import com.ufps.cedcufps.modelos.TextoDiploma;

public interface IImagenDiplomaDao extends JpaRepository<ImagenDiploma, Long> {

	@Query("select i from ImagenDiploma i where i.diploma.id = ?1")
	 public List<ImagenDiploma> findByDiploma(Long idDiploma);
	
	@Query("select i from ImagenDiploma i where i.diploma.id = null")
	 public List<ImagenDiploma> findAllDefault();
}
