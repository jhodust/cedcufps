package com.ufps.cedcufps.dto;

import java.util.List;

import com.ufps.cedcufps.modelos.Participante;

public class RequisitosInscripcionDto {

	private int cuposDisponibles;
	private int totalInscritos;
	private List<Object[]> listTipoPersonaValidInscripcion;
	private boolean estaInscrito;
	private ParticipanteDto participante;
	private EducacionContinuaWebDto educacionContinua;
	private boolean ableToInscription;
	private String mensajeNoInscripcion;
	
	
	public boolean isAbleToInscription() {
		return ableToInscription;
	}
	public void setAbleToInscription(boolean ableToInscription) {
		this.ableToInscription = ableToInscription;
	}
	public int getCuposDisponibles() {
		return cuposDisponibles;
	}
	public void setCuposDisponibles(int cuposDisponibles) {
		this.cuposDisponibles = cuposDisponibles;
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
	public String getMensajeNoInscripcion() {
		return mensajeNoInscripcion;
	}
	public void setMensajeNoInscripcion(String mensajeNoInscripcion) {
		this.mensajeNoInscripcion = mensajeNoInscripcion;
	}
	
	
	
}
