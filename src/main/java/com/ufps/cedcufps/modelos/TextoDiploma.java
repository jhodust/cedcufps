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
@Table(name="textos_diploma")
@PrimaryKeyJoinColumn(name="id_elemento")
public class TextoDiploma extends ElementoDiploma{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String categoria;
	private String texto;
	//private String fuente;
	//private String size;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="id_diploma")
	private Diploma diploma;
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	/*public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}*/
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Diploma getDiploma() {
		return diploma;
	}
	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}
	
	
}
