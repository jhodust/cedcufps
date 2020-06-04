package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="estudiantes", uniqueConstraints={
		   @UniqueConstraint(columnNames={"codigo", "id_programa"})})
@PrimaryKeyJoinColumn(name="id_persona")
public class Estudiante extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "El campo código es requerido")
	@Size(max = 8, message = "El campo código debe tene máximo 7 dígitos")
	private String codigo;

	@NotNull(message = "Seleccione el Programa Académico en el que se encuentra matriculado")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_programa")
	private Programa programa;
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	
	
}
