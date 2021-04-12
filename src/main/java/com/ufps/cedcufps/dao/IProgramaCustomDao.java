package com.ufps.cedcufps.dao;

import com.ufps.cedcufps.dto.ProgramaDto;

import org.springframework.data.jpa.repository.Query;

import com.ufps.cedcufps.modelos.Programa;

public interface IProgramaCustomDao {

	public ProgramaDto findProgramaDtoByDirector(Long idDir);
}
