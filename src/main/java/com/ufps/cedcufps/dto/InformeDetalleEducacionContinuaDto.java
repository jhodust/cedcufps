package com.ufps.cedcufps.dto;

import java.util.List;

public class InformeDetalleEducacionContinuaDto {

	private String idCurso;
	private String codigoCurso;
	private String idTipoBeneficiario;
	private String nombreCurso;
	private String tipoCurso;
	private String fechaInicio;
	private String tipoDocumentoParticipante;
	private String numDocumentoParticipante;
	private String nombreParticipante;
	private String programaEstudiante;
	private String valorCurso;
	private String numHorasCurso;
	private List<InformePonenteDto> ponentes;
	
	
	public String getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}
	public String getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public String getIdTipoBeneficiario() {
		return idTipoBeneficiario;
	}
	public void setIdTipoBeneficiario(String idTipoBeneficiario) {
		this.idTipoBeneficiario = idTipoBeneficiario;
	}
	public String getNombreCurso() {
		return nombreCurso;
	}
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	public String getTipoCurso() {
		return tipoCurso;
	}
	public void setTipoCurso(String tipoCurso) {
		this.tipoCurso = tipoCurso;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getTipoDocumentoParticipante() {
		return tipoDocumentoParticipante;
	}
	public void setTipoDocumentoParticipante(String tipoDocumentoParticipante) {
		this.tipoDocumentoParticipante = tipoDocumentoParticipante;
	}
	public String getNumDocumentoParticipante() {
		return numDocumentoParticipante;
	}
	public void setNumDocumentoParticipante(String numDocumentoParticipante) {
		this.numDocumentoParticipante = numDocumentoParticipante;
	}
	public String getNombreParticipante() {
		return nombreParticipante;
	}
	public void setNombreParticipante(String nombreParticipante) {
		this.nombreParticipante = nombreParticipante;
	}
	public String getProgramaEstudiante() {
		return programaEstudiante;
	}
	public void setProgramaEstudiante(String programaEstudiante) {
		this.programaEstudiante = programaEstudiante;
	}
	public String getValorCurso() {
		return valorCurso;
	}
	public void setValorCurso(String valorCurso) {
		this.valorCurso = valorCurso;
	}
	public String getNumHorasCurso() {
		return numHorasCurso;
	}
	public void setNumHorasCurso(String numHorasCurso) {
		this.numHorasCurso = numHorasCurso;
	}
	public List<InformePonenteDto> getPonentes() {
		return ponentes;
	}
	public void setPonentes(List<InformePonenteDto> ponentes) {
		this.ponentes = ponentes;
	}
	
	public void addPonente(InformePonenteDto ponente) {
		this.ponentes.add(ponente);
	}
	
}
