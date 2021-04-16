package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ufps.cedcufps.dto.AsistenciaDto;
import com.ufps.cedcufps.modelos.Asistencia;

public interface IAsistenciaCustomDao {

	public List<AsistenciaDto> findAsistenciasByJornadas(List<Long> jornadas);
	
	public AsistenciaDto findAsistenciasByJornadaAndParticipante(Long idJornada, Long idParticipante);
	
	public List<AsistenciaDto> findAsistenciasByIdParticipante(Long idParticipante);
	
	public List<AsistenciaDto> findAllAsistencias();
}
