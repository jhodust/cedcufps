package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.FirmaDiploma;

public interface IFirmaDiplomaDao extends CrudRepository<FirmaDiploma, Long>  {

	@Query("select f from FirmaDiploma f where f.diploma.id = ?1")
	 public List<FirmaDiploma> findByDiploma(Long idDiploma);
}
