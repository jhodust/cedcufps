package com.ufps.cedcufps.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="docentes")
@PrimaryKeyJoinColumn(name="id_persona")
public class Docente extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_departamento")
	private Departamento departamento;
	
	@JsonIgnore //ignora esta referencia cuando se hace mediante ajax(json) 
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_docente")
	private List<EducacionContinua> educacionesContinuas;

	@JsonIgnore //ignora esta referencia cuando se hace mediante ajax(json) 
	@OneToOne(mappedBy = "directorPrograma")
    private Programa programaACargoDirector;
	
	public Docente() {
		educacionesContinuas=new ArrayList<EducacionContinua>();
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public void addEducacionContinua(EducacionContinua ec) {
		educacionesContinuas.add(ec);
	}

	public List<EducacionContinua> getEducacionesContinuas() {
		return educacionesContinuas;
	}

	public void setEducacionesContinuas(List<EducacionContinua> educacionesContinuas) {
		this.educacionesContinuas = educacionesContinuas;
	}

	public Programa getProgramaACargoDirector() {
		return programaACargoDirector;
	}

	public void setProgramaACargoDirector(Programa programaACargoDirector) {
		this.programaACargoDirector = programaACargoDirector;
	}

	
	
	
	
	
}
