package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="rol_persona_programa_per")
public class RolPersonaPerPrograma implements Serializable{
	
	
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name="id_persona", referencedColumnName="id_persona"),
    			  @JoinColumn(name="id_rol", referencedColumnName="id_rol"),
    			  @JoinColumn(name="id_tipo_persona", referencedColumnName="id_tipo_persona")})
    @MapsId
    private RolPersonaTipoPersona rolPersonaTipPer;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_programa", foreignKey=@ForeignKey(name = "FK_programa_rolperprogper"))
	private Programa programa;

	public RolPersonaTipoPersona getRolPersonaTipPer() {
		return rolPersonaTipPer;
	}

	public void setRolPersonaTipPer(RolPersonaTipoPersona rolPersonaTipPer) {
		this.rolPersonaTipPer = rolPersonaTipPer;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	
	
	
}