package com.ufps.cedcufps.services;

import java.util.ArrayList;
import java.util.List;

import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Jornada;

public interface IAsistenciaService {

	public List<Asistencia> findAll();
	
	public List<Asistencia> findAsistenciasByJornadas(List<JornadaAppDto> jornadas);
	
	public List<?> countAsistenciasByJornadas(String eduContinua,String fechaInicio);
	
	public Asistencia findAsistenciaByJornadaAndParticipante(Long idJornada, Long idParticipante);
	
	public void saveAsistencias(Long idJornada);
	
	public boolean marcarAsistencia(Long idJornada, Long idParticipante, int cantParticipantes);
	
	
	
	public void deleteAll(List<Asistencia> asistencias);
	
	public void deleteAsistencia(Long idJornada, Long idParticipante);
	
	public void deleteAsistenciasByJornada(Long idJornada);
}
