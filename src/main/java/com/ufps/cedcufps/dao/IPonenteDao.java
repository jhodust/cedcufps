package com.ufps.cedcufps.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Ponente;

public interface IPonenteDao extends JpaRepository<Ponente, Long> {

}
