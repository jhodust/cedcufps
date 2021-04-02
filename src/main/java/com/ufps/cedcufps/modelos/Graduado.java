package com.ufps.cedcufps.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="graduados")
@PrimaryKeyJoinColumn(name="id_persona")
public class Graduado extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "El campo código año de graduación es requerido")
	private String anio;

	@NotNull(message = "Seleccione el Programa Académico del que se graduó")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_programa", foreignKey=@ForeignKey(name = "FK_graduado_programa"))
	private Programa programa;
	

	@Column(columnDefinition = "boolean default true")
	private boolean estado;

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
}

