package com.ufps.cedcufps.dto;

public class DepartamentoDto {
	
	private Long id;
	private String departamento;
	private Long idFacultad;
	private String nombreFacultad;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public Long getIdFacultad() {
		return idFacultad;
	}
	public void setIdFacultad(Long idFacultad) {
		this.idFacultad = idFacultad;
	}
	public String getNombreFacultad() {
		return nombreFacultad;
	}
	public void setNombreFacultad(String nombreFacultad) {
		this.nombreFacultad = nombreFacultad;
	}
	
	
}
