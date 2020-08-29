package com.ufps.cedcufps.dto;

public class InfoEducacionContinuaDto {

	private boolean hasPermission;
	private EducacionContinuaWebDto educacionContinua;
	public boolean isHasPermission() {
		return hasPermission;
	}
	public void setHasPermission(boolean hasPermission) {
		this.hasPermission = hasPermission;
	}
	public EducacionContinuaWebDto getEducacionContinua() {
		return educacionContinua;
	}
	public void setEducacionContinua(EducacionContinuaWebDto educacionContinua) {
		this.educacionContinua = educacionContinua;
	}
	
	
}
