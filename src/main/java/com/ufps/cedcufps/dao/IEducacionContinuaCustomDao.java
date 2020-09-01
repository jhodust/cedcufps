package com.ufps.cedcufps.dao;

import java.util.List;

import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Persona;

public interface IEducacionContinuaCustomDao {

	public List<Long> listAllPossibleEducacionContinua(Long idPersona);
	public boolean  docenteHasPermission(String nombreEduContinua, Long idPersona);
	
	public int registrarAsistencia(Long idJornada,Long idParticipante);
}