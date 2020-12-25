package com.ufps.cedcufps.dao;

import java.util.Date;
import java.util.List;

import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Persona;

public interface IEducacionContinuaCustomDao {

	public List<Long> listAllPossibleEducacionContinua(Long idPersona);
	public boolean  docenteHasPermission(String idAcceso, Long idPersona);
	
	public int registrarAsistencia(Long idJornada,Long idParticipante);
	
	public void saveEducacionContinua(EducacionContinuaWebDto dto);
	
	public void updateEducacionContinua(EducacionContinuaWebDto dto);
	
	public Long insertNewTipoEduContinua(String tipoEduContinua, Boolean status);
	
	public EducacionContinuaWebDto findEduContinuaWebDtoByIdAcceso(String idAcceso);
}
