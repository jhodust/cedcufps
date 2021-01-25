package com.ufps.cedcufps.dto;

public class PersonaDtoLogueada {

	private Long idPersona;
	private boolean superAdmin;
	private boolean dirPrograma;
	private boolean docente;
	private boolean hasPermisosEdC;
	private boolean hasPermisosOnlyMyEdC;
	public boolean isHasPermisosOnlyMyEdC() {
		return hasPermisosOnlyMyEdC;
	}
	public void setHasPermisosOnlyMyEdC(boolean hasPermisosOnlyMyEdC) {
		this.hasPermisosOnlyMyEdC = hasPermisosOnlyMyEdC;
	}
	public boolean isSuperAdmin() {
		return superAdmin;
	}
	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}
	public boolean isDirPrograma() {
		return dirPrograma;
	}
	public void setDirPrograma(boolean dirPrograma) {
		this.dirPrograma = dirPrograma;
	}
	public boolean isDocente() {
		return docente;
	}
	public void setDocente(boolean docente) {
		this.docente = docente;
	}
	public boolean isHasPermisosEdC() {
		return hasPermisosEdC;
	}
	public void setHasPermisosEdC(boolean hasPermisosEdC) {
		this.hasPermisosEdC = hasPermisosEdC;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	
	
}
