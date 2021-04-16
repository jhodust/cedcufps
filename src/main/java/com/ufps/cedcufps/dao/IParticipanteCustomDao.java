package com.ufps.cedcufps.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.modelos.Participante;

public interface IParticipanteCustomDao  {

	public void saveParticipante(ParticipanteDto p);
	
	public List<ParticipanteDto> findAllParticipacionesActivasByParticipante(String numDocumento) ;
	
	

	
	public List<PonenteDto> findAllPonentesOfOneEducacionContinuaById(Long idEducacionContinua);
	
	public List<ParticipanteDto> findTipoParticipantesByIdEducacionContinua(Long idEducacionContinua, Long idTipoParticipante );
	
	public ParticipanteDto findParticipanteByIdEducacionContinuaAndIdPersona(Long idEducacionContinua, Long idPersona );

	public ParticipanteDto findParticipanteById(Long idParticipante );
	
	public ParticipanteDto findByToken(String token);
	
	List<ParticipanteDto> findAllParticipantesEducacionContinua(Long idEduContinua);
	
	
	
	public ParticipanteDto validarParticipanteYaInscritoApp(Long idEduContinua, String documento);
	
	public ParticipanteDto validarQr(String qr);
}
