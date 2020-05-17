package com.ufps.cedcufps.modelos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	private int xNombre;
	
	private int yNombre;
	
	private int xCargo;
	
	private int yCargo;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="id_diploma")
	private Diploma diploma;

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

	public Diploma getDiploma() {
		return diploma;
	}

	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}

	public int getxNombre() {
		return xNombre;
	}

	public void setxNombre(int xNombre) {
		this.xNombre = xNombre;
	}

	public int getyNombre() {
		return yNombre;
	}

	public void setyNombre(int yNombre) {
		this.yNombre = yNombre;
	}

	public int getxCargo() {
		return xCargo;
	}

	public void setxCargo(int xCargo) {
		this.xCargo = xCargo;
	}

	public int getyCargo() {
		return yCargo;
	}

	public void setyCargo(int yCargo) {
		this.yCargo = yCargo;
	}
	
	
	

}
