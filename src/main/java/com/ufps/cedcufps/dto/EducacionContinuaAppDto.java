package com.ufps.cedcufps.dto;

import java.util.Date;

public class EducacionContinuaAppDto {

	private Long id;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private int cantidadParticipantes;
	private String tipoEduContinua;
	private String programaResponsable;
	private String docenteResponsable;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getCantidadParticipantes() {
		return cantidadParticipantes;
	}
	public void setCantidadParticipantes(int cantidadParticipantes) {
		this.cantidadParticipantes = cantidadParticipantes;
	}
	public String getTipoEduContinua() {
		return tipoEduContinua;
	}
	public void setTipoEduContinua(String tipoEduContinua) {
		this.tipoEduContinua = tipoEduContinua;
	}
	public String getProgramaResponsable() {
		return programaResponsable;
	}
	public void setProgramaResponsable(String programaResponsable) {
		this.programaResponsable = programaResponsable;
	}
	public String getDocenteResponsable() {
		return docenteResponsable;
	}
	public void setDocenteResponsable(String docenteResponsable) {
		this.docenteResponsable = docenteResponsable;
	}
	
	
}
