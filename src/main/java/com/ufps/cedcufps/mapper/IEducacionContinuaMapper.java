package com.ufps.cedcufps.mapper;

import java.util.List;

import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.InfoEducacionContinuaDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;

public interface IEducacionContinuaMapper {

	
	public List<EducacionContinuaAppDto> convertEducacionContinuaToApp(List<EducacionContinua> edc);
	public InfoEducacionContinuaDto convertEducacionContinuaToEducacionContinuaWeb(EducacionContinua e, boolean hasPermission);
	public ParticipanteDto convertParticipanteToParticipanteDto(Participante p);
}
