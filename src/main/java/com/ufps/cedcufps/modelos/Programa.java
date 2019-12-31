package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="programas")
public class Programa implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String codigo;
	
	@Column(name = "programa")
	private String nombrePrograma;
	
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	
	
	
	

}
