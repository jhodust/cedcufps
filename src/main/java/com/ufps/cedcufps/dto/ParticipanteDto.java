package com.ufps.cedcufps.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipanteDto {

	private Long id;
	private Long idPersona;
	private String nombrePersona;
	private Long idTipoParticipante;
	private String tipoParticipante;
	private Long idTipoDocumento;
	private String numeroDocumento;
	private String tipoDocumento;
	private String tipoEduContinua;
	private Long idTipoEduContinua;
	private Long idEducacionContinua;
	private String educacionContinua;
	private Date fechaInicioEduContinua;
	private Date fechaFinEduContinua;
	private String lugarEducacionContinua;
	private Date fechaInscripcion;
	private String codigoQR;
	private String imagenQr;
	private String tarjetaInscripcion;
	Map<Long, Boolean> jornadasAsistencias;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	
	
	public ParticipanteDto() {
		this.jornadasAsistencias=new HashMap<>();
	}
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
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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
	public String getTipoEduContinua() {
		return tipoEduContinua;
	}
	public void setTipoEduContinua(String tipoEduContinua) {
		this.tipoEduContinua = tipoEduContinua;
	}
	public String getEducacionContinua() {
		return educacionContinua;
	}
	public void setEducacionContinua(String educacionContinua) {
		this.educacionContinua = educacionContinua;
	}
	
	public Date getFechaInicioEduContinua() {
		return fechaInicioEduContinua;
	}
	public void setFechaInicioEduContinua(Date fechaInicioEduContinua) {
		this.fechaInicioEduContinua = fechaInicioEduContinua;
	}
	public Date getFechaFinEduContinua() {
		return fechaFinEduContinua;
	}
	public void setFechaFinEduContinua(Date fechaFinEduContinua) {
		this.fechaFinEduContinua = fechaFinEduContinua;
	}
	public String getLugarEducacionContinua() {
		return lugarEducacionContinua;
	}
	public void setLugarEducacionContinua(String lugarEducacionContinua) {
		this.lugarEducacionContinua = lugarEducacionContinua;
	}
	public String getImagenQr() {
		return imagenQr;
	}
	public void setImagenQr(String imagenQr) {
		this.imagenQr = imagenQr;
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
	
	public void addAsistencia(Long idJornada, boolean status) {
		this.jornadasAsistencias.put(idJornada, status);
	}
	
	public Map<Long, Boolean> getJornadasAsistencias() {
		return jornadasAsistencias;
	}
	public void setJornadasAsistencias(Map<Long, Boolean> jornadasAsistencias) {
		this.jornadasAsistencias = jornadasAsistencias;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getCodigoQR() {
		return codigoQR;
	}
	public void setCodigoQR(String codigoQR) {
		this.codigoQR = codigoQR;
	}
	public String getTarjetaInscripcion() {
		return tarjetaInscripcion;
	}
	public void setTarjetaInscripcion(String tarjetaInscripcion) {
		this.tarjetaInscripcion = tarjetaInscripcion;
	}
	public Long getIdEducacionContinua() {
		return idEducacionContinua;
	}
	public void setIdEducacionContinua(Long idEducacionContinua) {
		this.idEducacionContinua = idEducacionContinua;
	}
	public Long getIdTipoEduContinua() {
		return idTipoEduContinua;
	}
	public void setIdTipoEduContinua(Long idTipoEduContinua) {
		this.idTipoEduContinua = idTipoEduContinua;
	}
	
	
	
}
