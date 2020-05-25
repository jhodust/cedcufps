package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="externos")
@PrimaryKeyJoinColumn(name="id_persona")
public class Externo extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max=30,message = "El campo profesión debe tener máximo 30 caracteres")
	private String profesion;


	public String getProfesion() {
		return profesion;
	}


	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	

	
	
}