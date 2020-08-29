package com.ufps.cedcufps.dto;

public class ParticipanteDto {

	private Long id;
	private Long idPersona;
	private String nombrePersona;
	private Long idTipoParticipante;
	private String tipoParticipante;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public Long getIdTipoParticipante() {
		return idTipoParticipante;
	}
	public void setIdTipoParticipante(Long idTipoParticipante) {
		this.idTipoParticipante = idTipoParticipante;
	}
	public String getTipoParticipante() {
		return tipoParticipante;
	}
	public void setTipoParticipante(String tipoParticipante) {
		this.tipoParticipante = tipoParticipante;
	}
	
	
}
