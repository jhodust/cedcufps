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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.poi.hpsf.Array;
import org.springframework.web.multipart.MultipartFile;

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

	
	/*@OneToMany(mappedBy = "diploma",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ElementoDiploma> elementos;
	*/
	@OneToMany(mappedBy = "diploma",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TextoDiploma> textos;
	
	@OneToMany(mappedBy = "diploma",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ImagenDiploma> imagenes;
	
	@OneToMany(mappedBy = "diploma",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<FirmaDiploma> firmas;
	
	
	public Diploma() {
		textos= new ArrayList<TextoDiploma>();
		imagenes= new ArrayList<ImagenDiploma>();
		firmas= new ArrayList<FirmaDiploma>();
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

	public void addTexto(TextoDiploma e) {
		this.textos.add(e);
	}

	public void addImagen(ImagenDiploma i) {
		this.imagenes.add(i);
	}
	
	public void addFirma(FirmaDiploma f) {
		this.firmas.add(f);
	}


	public void setTextos(List<TextoDiploma> textos) {
		this.textos = textos;
	}
	public List<ImagenDiploma> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<ImagenDiploma> imagenes) {
		this.imagenes = imagenes;
	}
	public List<FirmaDiploma> getFirmas() {
		return firmas;
	}
	public void setFirmas(List<FirmaDiploma> firmas) {
		this.firmas = firmas;
	}
	public List<TextoDiploma> getTextos() {
		return textos;
	}
	
	
	
}
