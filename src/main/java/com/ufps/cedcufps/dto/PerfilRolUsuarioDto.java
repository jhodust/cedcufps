package com.ufps.cedcufps.dto;

import java.util.List;

public class PerfilRolUsuarioDto {

	private Long idPersona;
	private boolean isEstudiante;
	private boolean isDocente;
	private boolean isAdministrativo;
	private boolean isGraduado;
	private boolean isExterno;
	private boolean isSuperadmin;
	private boolean isDirPrograma;
	private Long idProgramaDirector;
	private String programaDirector;
	private boolean hasPermissionForEduContinua;
	private boolean hasPermissionForUsuarios;
	private boolean hasPermissionForAttendance;
	private List<ProgramaDto> selectProgramasForEduContinua;
	private List<ProgramaDto> selectProgramasForEstudiantes;
	private List<ProgramaDto> selectProgramasForGraduados;
	private List<DepartamentoDto> selectDeptosForDocentes;
	private List<EducacionContinuaAppDto> selectEduContinuasForAttendance;
	private List<ProgramaDto> programasForEduContinua;
	private List<ProgramaDto> programasForEstudiantes;
	private List<ProgramaDto> programasForGraduados;
	private List<DepartamentoDto> deptosForDocentes;
	private boolean hasPermissionForAdminvos;//checkbox
	private boolean hasPermissionForExternos;//checkbox
	private List<EducacionContinuaAppDto> eduContinuasForAttendance;
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public boolean isEstudiante() {
		return isEstudiante;
	}
	public void setEstudiante(boolean isEstudiante) {
		this.isEstudiante = isEstudiante;
	}
	public boolean isDocente() {
		return isDocente;
	}
	public void setDocente(boolean isDocente) {
		this.isDocente = isDocente;
	}
	public boolean isAdministrativo() {
		return isAdministrativo;
	}
	public void setAdministrativo(boolean isAdministrativo) {
		this.isAdministrativo = isAdministrativo;
	}
	public boolean isGraduado() {
		return isGraduado;
	}
	public void setGraduado(boolean isGraduado) {
		this.isGraduado = isGraduado;
	}
	public boolean isExterno() {
		return isExterno;
	}
	public void setExterno(boolean isExterno) {
		this.isExterno = isExterno;
	}
	public boolean isSuperadmin() {
		return isSuperadmin;
	}
	public void setSuperadmin(boolean isSuperadmin) {
		this.isSuperadmin = isSuperadmin;
	}
	public boolean isDirPrograma() {
		return isDirPrograma;
	}
	public void setDirPrograma(boolean isDirPrograma) {
		this.isDirPrograma = isDirPrograma;
	}
	public boolean isHasPermissionForEduContinua() {
		return hasPermissionForEduContinua;
	}
	public void setHasPermissionForEduContinua(boolean hasPermissionForEduContinua) {
		this.hasPermissionForEduContinua = hasPermissionForEduContinua;
	}
	public boolean isHasPermissionForUsuarios() {
		return hasPermissionForUsuarios;
	}
	public void setHasPermissionForUsuarios(boolean hasPermissionForUsuarios) {
		this.hasPermissionForUsuarios = hasPermissionForUsuarios;
	}
	public boolean isHasPermissionForAttendance() {
		return hasPermissionForAttendance;
	}
	public void setHasPermissionForAttendance(boolean hasPermissionForAttendance) {
		this.hasPermissionForAttendance = hasPermissionForAttendance;
	}
	public List<ProgramaDto> getProgramasForEduContinua() {
		return programasForEduContinua;
	}
	public void setProgramasForEduContinua(List<ProgramaDto> programasForEduContinua) {
		this.programasForEduContinua = programasForEduContinua;
	}
	public List<ProgramaDto> getProgramasForEstudiantes() {
		return programasForEstudiantes;
	}
	public void setProgramasForEstudiantes(List<ProgramaDto> programasForEstudiantes) {
		this.programasForEstudiantes = programasForEstudiantes;
	}
	public List<ProgramaDto> getProgramasForGraduados() {
		return programasForGraduados;
	}
	public void setProgramasForGraduados(List<ProgramaDto> programasForGraduados) {
		this.programasForGraduados = programasForGraduados;
	}
	public List<DepartamentoDto> getDeptosForDocentes() {
		return deptosForDocentes;
	}
	public void setDeptosForDocentes(List<DepartamentoDto> deptosForDocentes) {
		this.deptosForDocentes = deptosForDocentes;
	}
	public boolean isHasPermissionForAdminvos() {
		return hasPermissionForAdminvos;
	}
	public void setHasPermissionForAdminvos(boolean hasPermissionForAdminvos) {
		this.hasPermissionForAdminvos = hasPermissionForAdminvos;
	}
	public boolean isHasPermissionForExternos() {
		return hasPermissionForExternos;
	}
	public void setHasPermissionForExternos(boolean hasPermissionForExternos) {
		this.hasPermissionForExternos = hasPermissionForExternos;
	}
	public List<EducacionContinuaAppDto> getEduContinuasForAttendance() {
		return eduContinuasForAttendance;
	}
	public void setEduContinuasForAttendance(List<EducacionContinuaAppDto> eduContinuasForAttendance) {
		this.eduContinuasForAttendance = eduContinuasForAttendance;
	}
	
	public void addProgramaForEstudiantes(ProgramaDto p) {
		this.programasForEstudiantes.add(p);
	}
	
	public void addProgramaForGraduados(ProgramaDto p) {
		this.programasForGraduados.add(p);
	}
	
	public void addDeptosForDocentes(DepartamentoDto d) {
		this.deptosForDocentes.add(d);
	}
	
	public void addProgramaForEduContinu(ProgramaDto p) {
		this.programasForEduContinua.add(p);
	}
	
	public void addEduContinuaForAtendance(EducacionContinuaAppDto e) {
		this.eduContinuasForAttendance.add(e);
	}
	
	public void addSelectProgramaForEstudiantes(ProgramaDto p) {
		this.programasForEstudiantes.add(p);
	}
	
	public void addSelectProgramaForGraduados(ProgramaDto p) {
		this.programasForGraduados.add(p);
	}
	
	public void addSelectDeptosForDocentes(DepartamentoDto d) {
		this.deptosForDocentes.add(d);
	}
	
	public void addSelectProgramaForEduContinu(ProgramaDto p) {
		this.programasForEduContinua.add(p);
	}
	
	public void addSelectEduContinuaForAtendance(EducacionContinuaAppDto e) {
		this.eduContinuasForAttendance.add(e);
	}
	
	
	public Long getIdProgramaDirector() {
		return idProgramaDirector;
	}
	public void setIdProgramaDirector(Long idProgramaDirector) {
		this.idProgramaDirector = idProgramaDirector;
	}
	public String getProgramaDirector() {
		return programaDirector;
	}
	public void setProgramaDirector(String programaDirector) {
		this.programaDirector = programaDirector;
	}
	public List<ProgramaDto> getSelectProgramasForEduContinua() {
		return selectProgramasForEduContinua;
	}
	public void setSelectProgramasForEduContinua(List<ProgramaDto> selectProgramasForEduContinua) {
		this.selectProgramasForEduContinua = selectProgramasForEduContinua;
	}
	public List<ProgramaDto> getSelectProgramasForEstudiantes() {
		return selectProgramasForEstudiantes;
	}
	public void setSelectProgramasForEstudiantes(List<ProgramaDto> selectProgramasForEstudiantes) {
		this.selectProgramasForEstudiantes = selectProgramasForEstudiantes;
	}
	public List<ProgramaDto> getSelectProgramasForGraduados() {
		return selectProgramasForGraduados;
	}
	public void setSelectProgramasForGraduados(List<ProgramaDto> selectProgramasForGraduados) {
		this.selectProgramasForGraduados = selectProgramasForGraduados;
	}
	public List<DepartamentoDto> getSelectDeptosForDocentes() {
		return selectDeptosForDocentes;
	}
	public void setSelectDeptosForDocentes(List<DepartamentoDto> selectDeptosForDocentes) {
		this.selectDeptosForDocentes = selectDeptosForDocentes;
	}
	public List<EducacionContinuaAppDto> getSelectEduContinuasForAttendance() {
		return selectEduContinuasForAttendance;
	}
	public void setSelectEduContinuasForAttendance(List<EducacionContinuaAppDto> selectEduContinuasForAttendance) {
		this.selectEduContinuasForAttendance = selectEduContinuasForAttendance;
	}
	
	
}
