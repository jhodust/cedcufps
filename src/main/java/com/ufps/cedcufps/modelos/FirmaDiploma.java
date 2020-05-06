package com.ufps.cedcufps.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "firmas_diploma")
@PrimaryKeyJoinColumn(name = "id_elemento")
public class FirmaDiploma extends ElementoDiploma {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private String cargo;
	
	@Column(name="imagen_firma_digital")
	private String imagenFirmaDigital;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getImagenFirmaDigital() {
		return imagenFirmaDigital;
	}

	public void setImagenFirmaDigital(String imagenFirmaDigital) {
		this.imagenFirmaDigital = imagenFirmaDigital;
	}
	
	
	

}
