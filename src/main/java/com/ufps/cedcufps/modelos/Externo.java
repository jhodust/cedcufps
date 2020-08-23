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

	@Size(max=50,message = "El campo profesión debe tener máximo 50 caracteres")
	private String profesion;
	
	@Size(max=50,message = "El campo empresa debe tener máximo 50 caracteres")
	private String empresa;


	public String getProfesion() {
		return profesion;
	}


	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}


	public String getEmpresa() {
		return empresa;
	}


	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	

	
	
}