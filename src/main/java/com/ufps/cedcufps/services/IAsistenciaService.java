package com.ufps.cedcufps.services;

import java.util.ArrayList;
import java.util.List;

import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Jornada;

public interface IAsistenciaService {

	public List<Asistencia> findAll();
	
	public List<Asistencia> findAsistenciasByJornadas(List<Jornada> jornadas);
	
	public List<?> countAsistenciasByJornadas(Long idEduContinua);
	
	public Asistencia findAsistenciaByJornadaAndParticipante(Long idJornada, Long idParticipante);
	
	public void saveAsistencias(ArrayList<Asistencia> asistencias);
	
	public void save(Asistencia a);
	
	public void deleteAll(List<Asistencia> asistencias);
	
	public void deleteAsistencia(Long idJornada, Long idParticipante);
}
