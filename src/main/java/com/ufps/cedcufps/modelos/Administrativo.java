package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="administrativos")
@PrimaryKeyJoinColumn(name="id_persona")
public class Administrativo extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dependencia;
	
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