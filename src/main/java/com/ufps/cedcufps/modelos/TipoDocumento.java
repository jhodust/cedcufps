package com.ufps.cedcufps.modelos;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
=======

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
>>>>>>> preparacion
import javax.persistence.Table;

@Entity
@Table(name="tipos_documento")
public class TipoDocumento implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipo_documento")
	private String tipoDocumento;

<<<<<<< HEAD
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_documento")
	private List<Persona> personas;
	
	
	
	public TipoDocumento() {
		this.personas= new ArrayList<Persona>();
	}
=======
>>>>>>> preparacion

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

<<<<<<< HEAD
	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	public void addPersona(Persona p) {
		this.personas.add(p);
	}
	
	
=======
>>>>>>> preparacion

}
