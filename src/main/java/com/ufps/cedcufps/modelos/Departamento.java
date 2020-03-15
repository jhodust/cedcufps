package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name="departamentos")
public class Departamento implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String departamento;
	


	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_departamento")
	private List<Docente> docentes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_facultad")
	private Facultad facultad;

	
	public Departamento() {
		docentes= new ArrayList<Docente>();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public List<Docente> getDocentes() {
		return docentes;
	}


	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}


	public Facultad getFacultad() {
		return facultad;
	}


	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	
	

}

