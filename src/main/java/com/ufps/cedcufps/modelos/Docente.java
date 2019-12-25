package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="docentes")
@PrimaryKeyJoinColumn(name="id_persona")
public class Docente extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
