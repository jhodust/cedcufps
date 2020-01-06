package com.ufps.cedcufps.modelos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="ponentes")
@PrimaryKeyJoinColumn(name="id_participante")
public class Ponente extends Participante{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tema;

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	

}
