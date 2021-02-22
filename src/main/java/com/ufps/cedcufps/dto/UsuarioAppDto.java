package com.ufps.cedcufps.dto;

public class UsuarioAppDto {

	private Long id;
	private String documento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String email;
	private String telefono;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	
}
