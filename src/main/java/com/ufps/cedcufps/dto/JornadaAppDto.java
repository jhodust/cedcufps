package com.ufps.cedcufps.dto;

import java.util.Date;

public class JornadaAppDto {

	private Long id;
	private Date horaInicio;
	private Date horaFin;
	private String fechaJornadaString;
	private String horaInicioString;
	private String horaFinString;
	
	
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
	
	public String getFechaJornadaString() {
		return fechaJornadaString;
	}
	public void setFechaJornadaString(String fechaJornadaString) {
		this.fechaJornadaString = fechaJornadaString;
	}
	public String getHoraInicioString() {
		return horaInicioString;
	}
	public void setHoraInicioString(String horaInicioString) {
		this.horaInicioString = horaInicioString;
	}
	public String getHoraFinString() {
		return horaFinString;
	}
	public void setHoraFinString(String horaFinString) {
		this.horaFinString = horaFinString;
	}
	
	
}
