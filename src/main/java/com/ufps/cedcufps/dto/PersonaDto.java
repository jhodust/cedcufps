package com.ufps.cedcufps.dto;

public class PersonaDto {

	private Long id;
	private String nombre;
	private String email;
	private String documento;
	private String tipoDocumento;
	private String perfiles;
	private String idAcceso;
	private boolean isEstudiante;
	private boolean isDocente;
	private boolean isAdministrativo;
	private boolean isGraduado;
	private boolean isExterno;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(String perfiles) {
		this.perfiles = perfiles;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getIdAcceso() {
		return idAcceso;
	}
	public void setIdAcceso(String idAcceso) {
		this.idAcceso = idAcceso;
	}
	public boolean isEstudiante() {
		return isEstudiante;
	}
	public void setEstudiante(boolean isEstudiante) {
		this.isEstudiante = isEstudiante;
	}
	public boolean isDocente() {
		return isDocente;
	}
	public void setDocente(boolean isDocente) {
		this.isDocente = isDocente;
	}
	public boolean isAdministrativo() {
		return isAdministrativo;
	}
	public void setAdministrativo(boolean isAdministrativo) {
		this.isAdministrativo = isAdministrativo;
	}
	public boolean isGraduado() {
		return isGraduado;
	}
	public void setGraduado(boolean isGraduado) {
		this.isGraduado = isGraduado;
	}
	public boolean isExterno() {
		return isExterno;
	}
	public void setExterno(boolean isExterno) {
		this.isExterno = isExterno;
	}
	
	
}
