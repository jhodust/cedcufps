package com.ufps.cedcufps.mapper;

import java.util.List;

import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.modelos.Programa;

public interface IProgramaMapper {

	public List<ProgramaDto> convertListProgramaToProgramaDto(List<Programa> programas);
	
	public ProgramaDto convertProgramaToProgramaDto(Programa programa);
	
	public Programa convertProgramaDtoToPrograma(ProgramaDto programaDto);
}
