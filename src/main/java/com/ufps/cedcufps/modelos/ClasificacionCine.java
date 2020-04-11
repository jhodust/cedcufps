package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clasificacion_cine")
public class ClasificacionCine implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "clasificacion_cine")
	private String clasificacionCine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClasificacionCine() {
		return clasificacionCine;
	}

	public void setClasificacionCine(String clasificacionCine) {
		this.clasificacionCine = clasificacionCine;
	}
	
	
		
		
}
