package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.TipoParticipante;

public interface IParticipanteService {

	
	public List<TipoParticipante> findAllTiposParticipante();
	
	public List<Participante> findAllParticipante();
	
	public void save(Participante p);
	
	public Optional<Participante> findOne(Long id);
	
	public TipoParticipante findByTipoParticipante(String tipoParticipante);
	
	public Participante findByIdEducacionContinuaAndIdPersona(Long idEducacionContinua, Long idPersona);
}
