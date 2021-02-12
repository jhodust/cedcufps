package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.Participante;

public interface IParticipanteCustomDao  {

	public void saveParticipante(ParticipanteDto p);
	
}
