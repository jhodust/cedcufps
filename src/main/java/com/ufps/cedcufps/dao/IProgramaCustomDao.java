package com.ufps.cedcufps.dao;

import com.ufps.cedcufps.dto.ProgramaDto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ufps.cedcufps.modelos.Programa;

public interface IProgramaCustomDao {

	public ProgramaDto findProgramaDtoByDirector(Long idDir);
	
	public List<ProgramaDto> findAllProgramas();
	
	public List<ProgramaDto> findProgramasOfPermissionEdCPersona(Long idPersona);
	
	public List<ProgramaDto> findProgramasEducacionContinuaBase(Long idPersona);
}
