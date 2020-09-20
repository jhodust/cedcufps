package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="rol_persona_tip_pers")
public class RolPersonaTipoPersona implements Serializable{
		
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
	@JoinColumn(name="id_tipo_persona")
	private TipoPersona tipoPersona;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rolPersonaTipPer", cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RolPersonaPerPrograma> rolPersonaPerPrograma;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rolPersonaTipPer", cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RolPersonaPerDepto> rolPersonaPerDepto;

	
	public RolPersonaTipoPersona() {
		this.rolPersonaPerPrograma=new ArrayList<RolPersonaPerPrograma>();
		this.rolPersonaPerDepto=new ArrayList<RolPersonaPerDepto>();
	}

	public PersonaRol getRolPersona() {
		return rolPersona;
	}

	public void setRolPersona(PersonaRol rolPersona) {
		this.rolPersona = rolPersona;
	}

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public List<RolPersonaPerPrograma> getRolPersonaPerPrograma() {
		return rolPersonaPerPrograma;
	}

	public void setRolPersonaPerPrograma(List<RolPersonaPerPrograma> rolPersonaPerPrograma) {
		this.rolPersonaPerPrograma = rolPersonaPerPrograma;
	}

	public List<RolPersonaPerDepto> getRolPersonaPerDepto() {
		return rolPersonaPerDepto;
	}

	public void setRolPersonaPerDepto(List<RolPersonaPerDepto> rolPersonaPerDepto) {
		this.rolPersonaPerDepto = rolPersonaPerDepto;
	}

	public void addPersonaPerPrograma(RolPersonaPerPrograma p) {
		this.rolPersonaPerPrograma.add(p);
	}
	
	public void addPersonaPerDepto(RolPersonaPerDepto d) {
		this.rolPersonaPerDepto.add(d);
	}
	
	
	

}