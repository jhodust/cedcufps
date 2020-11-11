package com.ufps.cedcufps.dto;

public class TipoBeneficiarioDto {
	
	private Long id;
	private String tipoBeneficiario;
	private String homologacion;
	
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
	
	
}
