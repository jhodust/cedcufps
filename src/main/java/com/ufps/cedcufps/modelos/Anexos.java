package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="anexos")
public class Anexos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 200, message="El nombre del archivo no debe tener máximo 200 caracteres")
	private String nombre;
	
	@NotNull
	private String file;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private EducacionContinua educacionContinua;
	
	@Column(name = "fecha_registro", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegisto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public EducacionContinua getEducacionContinua() {
		return educacionContinua;
	}

	public void setEducacionContinua(EducacionContinua educacionContinua) {
		this.educacionContinua = educacionContinua;
	}

	public Date getFechaRegisto() {
		return fechaRegisto;
	}

	public void setFechaRegisto(Date fechaRegisto) {
		this.fechaRegisto = fechaRegisto;
	}
	
	

}
