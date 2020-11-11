package com.ufps.cedcufps.dto;

public class InformeCursosDto {

	private String codigoCurso;
	private String nombreCurso;
	private String clasificacionCine;
	private  final String esExtension="S";
	private  final String activo="S";
	
	public String getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public String getNombreCurso() {
		return nombreCurso;
	}
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	public String getClasificacionCine() {
		return clasificacionCine;
	}
	public void setClasificacionCine(String clasificacionCine) {
		this.clasificacionCine = clasificacionCine;
	}
	public  String getEsextension() {
		return esExtension;
	}
	public  String getActivo() {
		return activo;
	}
	
	
	
}
