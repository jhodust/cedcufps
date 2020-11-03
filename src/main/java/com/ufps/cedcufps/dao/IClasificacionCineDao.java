package com.ufps.cedcufps.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ufps.cedcufps.modelos.ClasificacionCine;
import com.ufps.cedcufps.modelos.TipoEducacionContinua;

public interface IClasificacionCineDao extends JpaRepository<ClasificacionCine, Long>{

	@Query(value = "select c from ClasificacionCine c where c.id = ?1")
	public ClasificacionCine findClasificacionById(Long id);
	
	@Query(value = "select c from ClasificacionCine c where c.clasificacionCine = ?1")
	public ClasificacionCine findClasificacionByClasificacion(String clasificacion);
}
