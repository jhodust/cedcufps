package com.ufps.cedcufps.dao;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.Participante;

public interface IParticipanteCustomDao  {

	public void saveParticipante(ParticipanteDto p);
}
