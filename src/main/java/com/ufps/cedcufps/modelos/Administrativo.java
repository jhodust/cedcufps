package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="administrativos")
@PrimaryKeyJoinColumn(name="id_persona")
public class Administrativo extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max=30,message = "El campo dependencia debe tener máximo 30 caracteres")
	private String dependencia;
	
	@Size(max=30,message = "El campo cargo debe tener máximo 30 caracteres")
	private String cargo;

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	
	
}