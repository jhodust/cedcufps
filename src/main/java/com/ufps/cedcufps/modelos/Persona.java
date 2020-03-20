package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {//*
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_documento")
	private TipoDocumento tipoDocumento;
	
	@Column(name = "numero_documento")
	private String numeroDocumento;
	
	@Column(name = "fecha_expedicion_documento")
	private Date fechaExpedicionDocumento;
	
	@Column(name = "primer_nombre")
	private String primerNombre;
	
	@Column(name = "segundo_nombre")
	private String segundoNombre;
	
	@Column(name = "primer_apellido")
	private String primerApellido;
	
	@Column(name = "segundo_apellido")
	private String segundoApellido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_genero")
	private Genero genero;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_estado_civil")
	private EstadoCivil estadoCivil;
	
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	@Column(name="id_pais_nacimiento")
	private String idPaisNacimiento;
	
	
	@Column(name="id_departamento_nacimiento")
	private String idDepartamentoNacimiento;
	

	
	@Column(name="id_municipio_nacimiento")
	private String idMunicipioNacimiento;
	
	
	private String email;
	private String direccion;
	private String telefono;
	private String password;

	private String username;
	
	private boolean enabled;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_persona")
	private TipoPersona tipoPersona;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_persona")
	private List<Rol> roles;
	
	
	
	public Persona() {
		this.roles = new ArrayList<Rol>();;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	public void addRol(Rol r) {
		this.roles.add(r);
	}
	public List<Rol> getRoles() {
		return roles;
	}
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getFechaExpedicionDocumento() {
		return fechaExpedicionDocumento;
	}

	public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
		this.fechaExpedicionDocumento = fechaExpedicionDocumento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getIdPaisNacimiento() {
		return idPaisNacimiento;
	}

	public void setIdPaisNacimiento(String idPaisNacimiento) {
		this.idPaisNacimiento = idPaisNacimiento;
	}

	
	public String getIdDepartamentoNacimiento() {
		return idDepartamentoNacimiento;
	}

	public void setIdDepartamentoNacimiento(String idDepartamentoNacimiento) {
		this.idDepartamentoNacimiento = idDepartamentoNacimiento;
	}
	
	
	public String getIdMunicipioNacimiento() {
		return idMunicipioNacimiento;
	}

	public void setIdMunicipioNacimiento(String idMunicipioNacimiento) {
		this.idMunicipioNacimiento = idMunicipioNacimiento;
	}

	
	
	
	
}
