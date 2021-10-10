package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.JornadaDto;
import com.ufps.cedcufps.modelos.Jornada;

public interface IJornadaService {

	public List<JornadaAppDto> findAllByIdEducacionContinua(Long idEducacionContinua);
	
	public void save(JornadaDto jornadaDto);
	
	public JornadaAppDto findOne(Long id);
	
	public void deleteJornada(Long idJornada);
}
