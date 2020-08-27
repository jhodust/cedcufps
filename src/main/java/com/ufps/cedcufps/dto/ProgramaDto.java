package com.ufps.cedcufps.dto;

public class ProgramaDto {

	private Long id;
	private String programa;
	private String codigo;
	private Long idDirector;
	private Long idFacultad;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getIdDirector() {
		return idDirector;
	}
	public void setIdDirector(Long idDirector) {
		this.idDirector = idDirector;
	}
	public Long getIdFacultad() {
		return idFacultad;
	}
	public void setIdFacultad(Long idFacultad) {
		this.idFacultad = idFacultad;
	}
	
	
}
