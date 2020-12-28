package com.ufps.cedcufps.dto;

import java.util.Date;


public class UsuarioDto {
	
	private Long id;
	private Long idTipoDocumento;
	private String tipoDocumento;
	private String numeroDocumento;
	private Date fechaExpedicionDocumento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Long idGenero;
	private String genero;
	private Long idEstadoCivil;
	private String estadoCivil;
	private Date fechaNacimiento;
	private String idPaisNacimiento;
	private String paisNacimiento;
	private String idDepartamentoNacimiento;
	private String deptoNacimiento;
	private String idMunicipioNacimiento;
	private String mpioNacimiento;
	private String email;
	private String direccion;
	private String telefono;
	
	private String profesion;
	private String empresa;
	private boolean estudiante;
	private boolean docente;
	private boolean administrativo;
	private boolean graduado;
	private boolean externo;
	private String codigoProgramaEstudiante;
	private Long idProgramaEstudiante;
	private String programaEstudiante;
	private Long idProgramaGraduado;
	private String programaGraduado;
	private String anioGraduado;
	private Long idDeptoAdscrito;
	private String deptoAdscrito;
	private String codigoDocente;
	private String dependencia;
	private String cargo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Date getFechaExpedicionDocumento() {
		return fechaExpedicionDocumento;
	}
	public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
		this.fechaExpedicionDocumento = fechaExpedicionDocumento;
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
	public Long getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Long getIdEstadoCivil() {
		return idEstadoCivil;
	}
	public void setIdEstadoCivil(Long idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
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
	public String getPaisNacimiento() {
		return paisNacimiento;
	}
	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}
	public String getIdDepartamentoNacimiento() {
		return idDepartamentoNacimiento;
	}
	public void setIdDepartamentoNacimiento(String idDepartamentoNacimiento) {
		this.idDepartamentoNacimiento = idDepartamentoNacimiento;
	}
	public String getDeptoNacimiento() {
		return deptoNacimiento;
	}
	public void setDeptoNacimiento(String deptoNacimiento) {
		this.deptoNacimiento = deptoNacimiento;
	}
	public String getIdMunicipioNacimiento() {
		return idMunicipioNacimiento;
	}
	public void setIdMunicipioNacimiento(String idMunicipioNacimiento) {
		this.idMunicipioNacimiento = idMunicipioNacimiento;
	}
	public String getMpioNacimiento() {
		return mpioNacimiento;
	}
	public void setMpioNacimiento(String mpioNacimiento) {
		this.mpioNacimiento = mpioNacimiento;
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
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public boolean isEstudiante() {
		return estudiante;
	}
	public void setEstudiante(boolean estudiante) {
		this.estudiante = estudiante;
	}
	public boolean isDocente() {
		return docente;
	}
	public void setDocente(boolean docente) {
		this.docente = docente;
	}
	public boolean isAdministrativo() {
		return administrativo;
	}
	public void setAdministrativo(boolean administrativo) {
		this.administrativo = administrativo;
	}
	public boolean isGraduado() {
		return graduado;
	}
	public void setGraduado(boolean graduado) {
		this.graduado = graduado;
	}
	public boolean isExterno() {
		return externo;
	}
	public void setExterno(boolean externo) {
		this.externo = externo;
	}
	public String getCodigoProgramaEstudiante() {
		return codigoProgramaEstudiante;
	}
	public void setCodigoProgramaEstudiante(String codigoProgramaEstudiante) {
		this.codigoProgramaEstudiante = codigoProgramaEstudiante;
	}
	public Long getIdProgramaEstudiante() {
		return idProgramaEstudiante;
	}
	public void setIdProgramaEstudiante(Long idProgramaEstudiante) {
		this.idProgramaEstudiante = idProgramaEstudiante;
	}
	public String getProgramaEstudiante() {
		return programaEstudiante;
	}
	public void setProgramaEstudiante(String programaEstudiante) {
		this.programaEstudiante = programaEstudiante;
	}
	public Long getIdProgramaGraduado() {
		return idProgramaGraduado;
	}
	public void setIdProgramaGraduado(Long idProgramaGraduado) {
		this.idProgramaGraduado = idProgramaGraduado;
	}
	public String getProgramaGraduado() {
		return programaGraduado;
	}
	public void setProgramaGraduado(String programaGraduado) {
		this.programaGraduado = programaGraduado;
	}
	public String getAnioGraduado() {
		return anioGraduado;
	}
	public void setAnioGraduado(String anioGraduado) {
		this.anioGraduado = anioGraduado;
	}
	public Long getIdDeptoAdscrito() {
		return idDeptoAdscrito;
	}
	public void setIdDeptoAdscrito(Long idDeptoAdscrito) {
		this.idDeptoAdscrito = idDeptoAdscrito;
	}
	public String getDeptoAdscrito() {
		return deptoAdscrito;
	}
	public void setDeptoAdscrito(String deptoAdscrito) {
		this.deptoAdscrito = deptoAdscrito;
	}
	public String getCodigoDocente() {
		return codigoDocente;
	}
	public void setCodigoDocente(String codigoDocente) {
		this.codigoDocente = codigoDocente;
	}
	public String getDependencia() {
		return dependencia;
	}
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
	
	
	
}
