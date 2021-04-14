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
	
	public List<ProgramaDto> findProgramasExceptSome(List<Long> idProgramas);
	
	public List<ProgramaDto> findProgramasPermisosEduContinuaForDirProgramaExceptOwn(Long idDirector, Long idPrograma);
	
	public List<ProgramaDto> findProgramasPermisosEduContinuaForDocEstAdminvo(Long idPersona);
	
	public List<ProgramaDto> findProgramasPermisosEstudiantesForDirProgramaExceptOwn(Long idDirector, Long idPrograma);
	
	public List<ProgramaDto> findProgramasPermisosEstudiantesForDocEstAdminvo(Long idPeronsa);
	
	public List<ProgramaDto> findProgramasPermisosGraduadosForDirProgramaExceptOwn(Long idDirector, Long idPrograma);
	
	public List<ProgramaDto> findProgramasPermisosGraduadosForDocEstAdminvo(Long idDirector);

	public ProgramaDto findOthersProgramasByDirector(Long idPro, Long idDir);
	
	public ProgramaDto findByDirector(Long idDir);

	public Programa findProgramaById(Long id);
	
}
