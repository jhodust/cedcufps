package com.ufps.cedcufps.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="administrativos")
@PrimaryKeyJoinColumn(name="id_persona", foreignKey=@ForeignKey(name = "FK_adminvo_persona"))
public class Administrativo extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max=50,message = "El campo dependencia debe tener máximo 50 caracteres")
	private String dependencia;
	
	@Size(max=50,message = "El campo cargo debe tener máximo 50 caracteres")
	private String cargo;

	@Column(columnDefinition = "boolean default true")
	private boolean estado;
	
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
}