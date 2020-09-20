package com.ufps.cedcufps.dto;

import java.util.Date;


public class UsuarioDto {
	
	private Long id;
	private Long tipoDocumento;
	private String numeroDocumento;
	private Date fechaExpedicionDocumento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Long genero;
	private Long estadoCivil;
	private Date fechaNacimiento;
	private String idPaisNacimiento;
	private String idDepartamentoNacimiento;
	private String idMunicipioNacimiento;
	private String email;
	private String direccion;
	private String telefono;
	private String codigo;
	private Long programa;
	private String profesion;
	private String empresa;
	private boolean estudiante;
	private boolean docente;
	private boolean administrativo;
	private boolean graduado;
	private boolean externo;
	private Long programaGraduado;
	private String anioGraduado;
	private Long deptoAdscrito;
	private boolean estadoDocente;
	private String codigoDocente;
	private String dependencia;
	private String cargo;
	
	
	public Long getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(Long tipoDocumento) {
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
	public Long getGenero() {
		return genero;
	}
	public void setGenero(Long genero) {
		this.genero = genero;
	}
	public Long getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(Long estadoCivil) {
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
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getPrograma() {
		return programa;
	}
	public void setPrograma(Long programa) {
		this.programa = programa;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	
	public Long getProgramaGraduado() {
		return programaGraduado;
	}
	public void setProgramaGraduado(Long programaGraduado) {
		this.programaGraduado = programaGraduado;
	}
	public String getAnioGraduado() {
		return anioGraduado;
	}
	public void setAnioGraduado(String anioGraduado) {
		this.anioGraduado = anioGraduado;
	}
	public Long getDeptoAdscrito() {
		return deptoAdscrito;
	}
	public void setDeptoAdscrito(Long deptoAdscrito) {
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isEstadoDocente() {
		return estadoDocente;
	}
	public void setEstadoDocente(boolean estadoDocente) {
		this.estadoDocente = estadoDocente;
	}
	
	
	
}
