package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "personas_x_roles")
public class PersonaRol implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="id_persona")
	private Persona persona;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="id_rol")
	private Rol rol;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rolPersona", cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RolPersonaAsistencia> rolPersonaAsistencia;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rolPersona", cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RolPersonaEduCPrograma> rolPersonaEducacionesContinuasProgramas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rolPersona", cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<RolPersonaTipoPersona> rolPersonaTipoPersonas;
	
	

	public PersonaRol() {
		this.rolPersonaAsistencia= new ArrayList<RolPersonaAsistencia>();
		this.rolPersonaEducacionesContinuasProgramas= new ArrayList<RolPersonaEduCPrograma>();
		this.rolPersonaTipoPersonas= new ArrayList<RolPersonaTipoPersona>();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<RolPersonaAsistencia> getRolPersonaAsistencia() {
		return rolPersonaAsistencia;
	}

	public void setRolPersonaAsistencia(List<RolPersonaAsistencia> rolPersonaAsistencia) {
		this.rolPersonaAsistencia = rolPersonaAsistencia;
	}

	public List<RolPersonaEduCPrograma> getRolPersonaEducacionesContinuasProgramas() {
		return rolPersonaEducacionesContinuasProgramas;
	}

	public void setRolPersonaEducacionesContinuasProgramas(
			List<RolPersonaEduCPrograma> rolPersonaEducacionesContinuasProgramas) {
		this.rolPersonaEducacionesContinuasProgramas = rolPersonaEducacionesContinuasProgramas;
	}

	public List<RolPersonaTipoPersona> getRolPersonaTipoPersonas() {
		return rolPersonaTipoPersonas;
	}

	public void setRolPersonaTipoPersonas(List<RolPersonaTipoPersona> rolPersonaTipoPersonas) {
		this.rolPersonaTipoPersonas = rolPersonaTipoPersonas;
	}
	
	public void addProgramaForEduContinua(RolPersonaEduCPrograma p) {
		this.rolPersonaEducacionesContinuasProgramas.add(p);
	}
	
	public void addEduContinuaForAsistencia(RolPersonaAsistencia a) {
			this.rolPersonaAsistencia.add(a);
	}
	
	public void addTipoPersonaForGestionPersona(RolPersonaTipoPersona tp) {
		this.rolPersonaTipoPersonas.add(tp);
	}
}
