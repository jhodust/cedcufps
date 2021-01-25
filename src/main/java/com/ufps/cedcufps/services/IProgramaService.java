package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Programa;

public interface IProgramaService {

	public List<Programa> findAll();
	
	public Page<Programa> findAll(Pageable pageable);
	
	public void save(Programa p);
	
	public Programa findOne(Long id);
	
	public List<Programa> findByFacultad(String facultad);
	
	public Page<Programa> findByFacultad(String facultad,Pageable pageable);
	
	public List<ProgramaDto> findProgramaByDirector(Long idDir,Long idPrograma);
	
	public Programa findProgramaByDirector(Long idDir);
	
	public ProgramaDto searchProgramaById(Long id);
	
	public List<ProgramaDto> programasParaEduContinuaBase(Long idPersona, boolean isSuperAdmin, boolean hasPermission);
	
	public List<ProgramaDto> findAllProgramasOfPermission(Long idPersona, boolean isSuperAdmin, boolean hasPermission);
}
