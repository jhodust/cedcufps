package com.ufps.cedcufps.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ufps.cedcufps.modelos.CambioEmail;

public interface ICambioEmailDao extends JpaRepository<CambioEmail, Long> {

	
	@Query(value="select c from CambioEmail c where c.token = ?1")
	public CambioEmail findCambioEmailByToken(String token);
	
	@Query(value="select c from CambioEmail c where c.numeroDocumento = ?1")
	public CambioEmail findCambioEmailByDocumento(String documento);
	
	@Modifying
	@Transactional
	@Query(value="delete from cambio_email where token = ?1", nativeQuery = true)
	public void eliminarSolicitud(String token);
}
