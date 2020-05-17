package com.ufps.cedcufps.modelos;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "imagenes_diploma")
@PrimaryKeyJoinColumn(name = "id_elemento")
public class ImagenDiploma extends ElementoDiploma {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ruta;

	private int ancho;

	private int alto;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="id_diploma")
	private Diploma diploma;
	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public Diploma getDiploma() {
		return diploma;
	}

	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}

	
}
