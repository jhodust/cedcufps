package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor

@Entity
@Table(name="departamentos", uniqueConstraints={
		   @UniqueConstraint(columnNames={"departamento"},name = "UK_departamento")})
public class Departamento implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "El campo departamento es requerido")
	@Size(max = 100, message = "El campo departamento no puede exceder los 100 caracteres")
	private String departamento;
	


	@JsonIgnore //ignora esta referencia cuando se hace mediante ajax(json) 
	//@OneToMany(fetch = FetchType.LAZY)
	//@JoinColumn(name = "id_departamento")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departamento")
	private List<Docente> docentes;
	
	
	
	
	@NotNull(message =  "Seleccione la facultad")
	@ManyToOne(fetch = FetchType.EAGER)//eager para que sirva el ajax
	@JoinColumn(name="id_facultad",nullable = false, foreignKey=@ForeignKey(name = "FK_facultad_departamento"))
	private Facultad facultad;

	
	public Departamento(Long id, String departamento, Facultad facultad) {
		this.id=id;
		this.departamento=departamento;
		this.facultad=facultad;
		docentes= new ArrayList<Docente>();
	}	
	
	public Departamento() {
		docentes= new ArrayList<Docente>();
	}	

}

