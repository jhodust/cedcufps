package com.ufps.cedcufps.dto;

import java.util.List;

import com.ufps.cedcufps.modelos.Participante;

public class RequisitosInscripcionDto {

	private boolean hasCupos;
	private int cuposDisponibles;
	private int totalInscritos;
	private boolean hasCoincidenciasBeneficiario;
	private List<Object[]> listTipoPersonaValidInscripcion;
	private boolean estaInscrito;
	private ParticipanteDto participante;
	private EducacionContinuaWebDto educacionContinua;
	public boolean isHasCupos() {
		return hasCupos;
	}
	public void setHasCupos(boolean hasCupos) {
		this.hasCupos = hasCupos;
	}
	public int getCuposDisponibles() {
		return cuposDisponibles;
	}
	public void setCuposDisponibles(int cuposDisponibles) {
		this.cuposDisponibles = cuposDisponibles;
	}
	public boolean isHasCoincidenciasBeneficiario() {
		return hasCoincidenciasBeneficiario;
	}
	public void setHasCoincidenciasBeneficiario(boolean hasCoincidenciasBeneficiario) {
		this.hasCoincidenciasBeneficiario = hasCoincidenciasBeneficiario;
	}
	public List<Object[]> getListTipoPersonaValidInscripcion() {
		return listTipoPersonaValidInscripcion;
	}
	public void setListTipoPersonaValidInscripcion(List<Object[]> listTipoPersonaValidInscripcion) {
		this.listTipoPersonaValidInscripcion = listTipoPersonaValidInscripcion;
	}
	public boolean isEstaInscrito() {
		return estaInscrito;
	}
	public void setEstaInscrito(boolean estaInscrito) {
		this.estaInscrito = estaInscrito;
	}
	public ParticipanteDto getParticipante() {
		return participante;
	}
	public void setParticipante(ParticipanteDto participante) {
		this.participante = participante;
	}
	public EducacionContinuaWebDto getEducacionContinua() {
		return educacionContinua;
	}
	public void setEducacionContinua(EducacionContinuaWebDto educacionContinua) {
		this.educacionContinua = educacionContinua;
	}
	public int getTotalInscritos() {
		return totalInscritos;
	}
	public void setTotalInscritos(int totalInscritos) {
		this.totalInscritos = totalInscritos;
	}
	
	
	
}
