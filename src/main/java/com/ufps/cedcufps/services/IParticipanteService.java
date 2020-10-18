package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.TipoParticipante;

public interface IParticipanteService {

	
	public List<TipoParticipante> findAllTiposParticipante();
	
	public List<Participante> findAllParticipante();
	
	public void save(Participante p);
	
	public Optional<Participante> findOne(Long id);
	
	public TipoParticipante findByTipoParticipante(String tipoParticipante);
	
	public ParticipanteDto findByIdEducacionContinuaAndIdPersona(Long idEducacionContinua, Long idPersona);
	
	public void deleteParticipante(Long idParticipante);
	
	public void cancelarInscripcion(Long idEduContinua);
	
	public List<Participante> findAllPonentesOfOneEducacionContinua(String educacionContinua);
	
	public List<Participante> findAllPonentesOfOneEducacionContinuaById(Long idEducacionContinua);
	
	public ParticipanteDto findParticipante(Long id);
	
	public void saveTarjetaInscripcion(MultipartFile file,Long idParticipante);
	
	public PonenteDto findPonente(Long id);
	
	public void deleteParticipanteById(Long id);
	
	public List<Participante> findAllParticipacionesActivasByParticipante(String numDocumento);
	
	public List<ParticipanteDto> findAllParticipantesByEducacionContinua(String idEduContinua);
	
	public TipoParticipante findTipoParticipanteById(Long id);
	
	public void savePonente(Ponente p);
}
