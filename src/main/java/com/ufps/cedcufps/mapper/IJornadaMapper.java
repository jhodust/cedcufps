package com.ufps.cedcufps.mapper;

import java.util.List;

import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.modelos.Jornada;

public interface IJornadaMapper {

	public List<JornadaAppDto> convertJornadasToJornadaAppDto(List<Jornada> jornadas);
	
	public JornadaAppDto convertJornadaToJornadaAppDto(Jornada jornada);
}
