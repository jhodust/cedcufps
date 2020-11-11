package com.ufps.cedcufps.dto;

public class PersonaRolEducacionContinuaDto {
	
	private Long idRol;
	private Long idPersona;
	private Long idPrograma;
	private String programa;
	private String authority;
	
	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Long getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Long idPrograma) {
		this.idPrograma = idPrograma;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
