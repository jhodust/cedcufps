package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="rol_persona_asistencia")
public class RolPersonaAsistencia implements Serializable{
	
	
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name="id_persona", referencedColumnName="id_persona"),
    			  @JoinColumn(name="id_rol", referencedColumnName="id_rol")})
    @MapsId
    private PersonaRol rolPersona;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_edu_continua")
	private EducacionContinua eduContinua;

	public PersonaRol getRolPersona() {
		return rolPersona;
	}

	public void setRolPersona(PersonaRol rolPersona) {
		this.rolPersona = rolPersona;
	}

	public EducacionContinua getEduContinua() {
		return eduContinua;
	}

	public void setEduContinua(EducacionContinua eduContinua) {
		this.eduContinua = eduContinua;
	}
	
	
}