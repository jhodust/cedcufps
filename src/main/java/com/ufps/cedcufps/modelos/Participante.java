package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "participantes", uniqueConstraints={
		   @UniqueConstraint(columnNames={"id_persona", "id_educacion_continua"})})
@Inheritance(strategy = InheritanceType.JOINED)
public class Participante implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_educacion_continua")
	private EducacionContinua educacionContinua;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_persona")
	private Persona persona;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_participante")
	private TipoParticipante tipoParticipante;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public EducacionContinua getEducacionContinua() {
		return educacionContinua;
	}

	public void setEducacionContinua(EducacionContinua educacionContinua) {
		this.educacionContinua = educacionContinua;
	}

		
}
