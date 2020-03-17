package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

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

@Entity
@Table(name="programas")
public class Programa implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String codigo;
	
	@Column(name = "programa")
	private String nombrePrograma;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programa")
	private List<Estudiante> estudiantes;

	@ManyToOne(fetch = FetchType.EAGER)//eager para que sirva el ajax
	@JoinColumn(name="id_facultad")
	private Facultad facultad;
	
	public Programa() {
		estudiantes= new ArrayList<Estudiante>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	

	public void addEstudiante(Estudiante e) {
		this.estudiantes.add(e);
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	
	
	

}
