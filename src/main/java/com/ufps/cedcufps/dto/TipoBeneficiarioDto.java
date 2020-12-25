package com.ufps.cedcufps.dto;

public class TipoBeneficiarioDto {
	
	private Long id;
	private String tipoBeneficiario;
	private String homologacion;
	private Long idTipoPersona;
	private String tipoPersona;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoBeneficiario() {
		return tipoBeneficiario;
	}
	public void setTipoBeneficiario(String tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}
	public String getHomologacion() {
		return homologacion;
	}
	public void setHomologacion(String homologacion) {
		this.homologacion = homologacion;
	}
	public Long getIdTipoPersona() {
		return idTipoPersona;
	}
	public void setIdTipoPersona(Long idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	
}
