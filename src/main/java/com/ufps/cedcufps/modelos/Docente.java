package com.ufps.cedcufps.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

@Entity
@Table(name="docentes")
@PrimaryKeyJoinColumn(name="id_persona")
public class Docente extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "El campo código es requerido")
	//@Pattern(regexp = "^$|^([0-9]{5,5})*$", message = "El código debe contener 5 dígitos")
	private String codigo;
	
	
	@NotNull(message = "Seleccione el Departamento Académico en el que se encuentra adscrito")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_departamento", foreignKey=@ForeignKey(name = "FK_docente_departamento"))
	private Departamento departamento;
	
	@JsonIgnore //ignora esta referencia cuando se hace mediante ajax(json) 
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_docente")
	private List<EducacionContinua> educacionesContinuas;

	@JsonIgnore //ignora esta referencia cuando se hace mediante ajax(json) 
	@OneToOne(mappedBy = "directorPrograma")
    private Programa programaACargoDirector;
	
	@Column(columnDefinition = "boolean default true")
	private boolean estado;
	
	public Docente() {
		educacionesContinuas=new ArrayList<EducacionContinua>();
	}
	
	
	
	
	
}
