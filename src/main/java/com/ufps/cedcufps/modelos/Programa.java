package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="programas", uniqueConstraints={
		   @UniqueConstraint(columnNames={"codigo"},name = "UK_code_programa")})
public class Programa implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty(message = "El campo código es requerido")
	@Pattern(regexp = "^([0-9]{3,3})*$", message = "El código debe contener 3 dígitos")
	//@Column(unique = true)
	private String codigo;
	
	@NotEmpty(message = "El campo programa es requerido")
	@Size(max = 80, message = "El campo programa no puede exceder los 80 caracteres")
	@Column(name = "programa")
	private String programa;

	@JsonIgnore //ignora esta referencia cuando se hace mediante ajax(json)  
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programa")
	private List<Estudiante> estudiantes;

	@NotNull(message =  "Seleccione la facultad")
	@ManyToOne(fetch = FetchType.EAGER)//eager para que sirva el ajax
	@JoinColumn(name="id_facultad", foreignKey=@ForeignKey(name = "FK_programa_facultad"))
	private Facultad facultad;
	
	@JsonIgnore //ignora esta referencia cuando se hace mediante ajax(json) 
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programa")
	private List<EducacionContinua> educacionesContinuas;
	
	//@NotNull(message =  "Seleccione director(a) del Programa")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_director", foreignKey=@ForeignKey(name = "FK_programa_director"))
	private Docente directorPrograma;
	
	public Programa() {
		estudiantes= new ArrayList<Estudiante>();
		educacionesContinuas= new ArrayList<EducacionContinua>();
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

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
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

	
	public void addEducacionContinua(EducacionContinua ec) {
		educacionesContinuas.add(ec);
	}

	public List<EducacionContinua> getEducacionesContinuas() {
		return educacionesContinuas;
	}

	public void setEducacionesContinuas(List<EducacionContinua> educacionesContinuas) {
		this.educacionesContinuas = educacionesContinuas;
	}

	public Docente getDirectorPrograma() {
		return directorPrograma;
	}

	public void setDirectorPrograma(Docente directorPrograma) {
		this.directorPrograma = directorPrograma;
	}

	
	

}
