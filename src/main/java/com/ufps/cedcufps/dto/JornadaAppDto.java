package com.ufps.cedcufps.dto;

import java.util.Date;

public class JornadaAppDto {

	private Long id;
	private Date horaInicio;
	private Date horaFin;
	private Long idEducacionContinua;
	private Date fechaInicioEduContinua;
	private Date fechaFinEduContinua;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	public Long getIdEducacionContinua() {
		return idEducacionContinua;
	}
	public void setIdEducacionContinua(Long idEducacionContinua) {
		this.idEducacionContinua = idEducacionContinua;
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
	
	
	
}
