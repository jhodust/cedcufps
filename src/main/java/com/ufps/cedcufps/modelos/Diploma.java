package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.poi.hpsf.Array;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="diplomas")
public class Diploma implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="imagen_plantilla")
	private String imagenPlantilla;

	@JsonIgnore
	@OneToMany(mappedBy = "diploma",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ElementoDiploma> elementos;

	
	public Diploma() {
		elementos= new ArrayList<ElementoDiploma>();
	}
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getImagenPlantilla() {
		return imagenPlantilla;
	}


	public void setImagenPlantilla(String imagenPlantilla) {
		this.imagenPlantilla = imagenPlantilla;
	}

	public void addElemento(ElementoDiploma e) {
		this.elementos.add(e);
	}

	public List<ElementoDiploma> getElementos() {
		return elementos;
	}


	public void setElementos(List<ElementoDiploma> elementos) {
		this.elementos = elementos;
	}
	
	
}
