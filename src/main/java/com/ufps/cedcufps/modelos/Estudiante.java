package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="estudiantes", uniqueConstraints={
		   @UniqueConstraint(columnNames={"consecutivo", "codigo_programa"})})
@PrimaryKeyJoinColumn(name="id_persona")
public class Estudiante extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String consecutivo;

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
}
