package com.ufps.cedcufps.dto;

public class PersonaDtoLogueada {

	private boolean superAdmin;
	private boolean dirPrograma;
	private boolean docente;
	private boolean hasPermisosEdC;
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
	
	
}
