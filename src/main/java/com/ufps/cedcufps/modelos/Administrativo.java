package com.ufps.cedcufps.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="administrativos")
@PrimaryKeyJoinColumn(name="id_persona", foreignKey=@ForeignKey(name = "FK_adminvo_persona"))
public class Administrativo extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Seleccione la dependencia a la que se encuentra adscrito(a)")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_dependencia", foreignKey=@ForeignKey(name = "FK_administrativo_dependencia"))
	private Dependencia dependencia;
	
	@Size(max=50,message = "El campo cargo debe tener máximo 50 caracteres")
	private String cargo;

	@Column(columnDefinition = "boolean default true")
	private boolean estado;
	
	
	
}