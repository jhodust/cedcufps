package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
<<<<<<< HEAD
=======
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
>>>>>>> preparacion
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
<<<<<<< HEAD

=======
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="codigo_programa")
	private Programa programa;
	
>>>>>>> preparacion
	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
<<<<<<< HEAD
=======

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	
	
	
>>>>>>> preparacion
}
