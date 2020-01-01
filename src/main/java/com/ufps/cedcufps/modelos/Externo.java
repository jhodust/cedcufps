package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="externos")
@PrimaryKeyJoinColumn(name="id_persona")
public class Externo extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String profesion;


	public String getProfesion() {
		return profesion;
	}


	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	

	
	
}