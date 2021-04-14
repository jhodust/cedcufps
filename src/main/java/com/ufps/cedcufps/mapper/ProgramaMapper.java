package com.ufps.cedcufps.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.modelos.Programa;

@Repository
public class ProgramaMapper implements IProgramaMapper {

	@Override
	public List<ProgramaDto> convertListProgramaToProgramaDto(List<Programa> programas) {
		// TODO Auto-generated method stub
		List<ProgramaDto> list= new ArrayList<>();
		for(Programa p: programas) {
			list.add(this.convertProgramaToProgramaDto(p));
		}
		return list;
	}

	@Override
	public ProgramaDto convertProgramaToProgramaDto(Programa programa) {
		// TODO Auto-generated method stub
		ProgramaDto dto= new ProgramaDto();
		if(programa != null ) {
			dto.setId(programa.getId());
			dto.setPrograma(programa.getPrograma());
			dto.setCodigo(programa.getCodigo());
			if(programa.getDirectorPrograma()!=null) {
				dto.setIdDirector(programa.getDirectorPrograma().getId());
			}
			
			dto.setIdFacultad(programa.getFacultad().getId());
		}
		
		return dto;
	}

	@Override
	public Programa convertProgramaDtoToPrograma(ProgramaDto programaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProgramaDto> convertListObjectToListProgramaDto(List<Object[]> result) {
		// TODO Auto-generated method stub
		List<ProgramaDto> list=new ArrayList<ProgramaDto>();
		for(Object[] object : result) {
			list.add(this.convertObjectToProgramaDto(object));
		}
		return list;
	}

	@Override
	public ProgramaDto convertObjectToProgramaDto(Object[] object) {
		// TODO Auto-generated method stub
		ProgramaDto dto=new ProgramaDto();
		dto.setId(Long.parseLong(String.valueOf(object[0])));
		dto.setCodigo(String.valueOf(object[1]));
		dto.setPrograma(String.valueOf(object[2]));
		dto.setIdDirector(Long.parseLong(String.valueOf(object[3])));
		dto.setIdFacultad(Long.parseLong(String.valueOf(object[4])));
		return dto;
	}

}
