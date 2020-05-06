package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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

}
