package com.ufps.cedcufps.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.modelos.Jornada;

@Repository
public class JornadaMapper implements IJornadaMapper {

	@Override
	public List<JornadaAppDto> convertJornadasToJornadaAppDto(List<Jornada> jornadas) {
		// TODO Auto-generated method stub
		List<JornadaAppDto> jadto= new ArrayList<JornadaAppDto>();
		if(!jornadas.isEmpty()) {
			for(Jornada j: jornadas) {
				JornadaAppDto jornada= new JornadaAppDto();
				jornada.setId(j.getId());
				jornada.setHoraFin(j.getHoraFin());
				jornada.setHoraInicio(j.getHoraInicio());
				jadto.add(jornada);
			}
		}
		return jadto;
	}

}
