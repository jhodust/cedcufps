package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="roles_personas_programas_ec")
public class RolPersonaEduCPrograma implements Serializable{
	
	
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="id_persona", referencedColumnName="id_persona", foreignKey=@ForeignKey(name = "FK_persona_rolperproec")),
    			  @JoinColumn(name="id_rol", referencedColumnName="id_rol", foreignKey=@ForeignKey(name = "FK_rol_rolperproec"))})
	@OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId
    private PersonaRol rolPersona;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_programa", foreignKey=@ForeignKey(name = "FK_programa_rolperproec"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Programa programa;
	
	
	public PersonaRol getRolPersona() {
		return rolPersona;
	}

	public void setRolPersona(PersonaRol rolPersona) {
		this.rolPersona = rolPersona;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	

		
		
	
	
}

