package com.ufps.cedcufps.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
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
	
	List<ParticipanteDto> findAllParticipantesEducacionContinuaById(Long idEduContinua);
	
	List<ParticipanteDto> findAllParticipantesEducacionContinuaByIdAcceso(String idAcceso);
	
	public List<ParticipanteDto> findAllParticipantesAprobadosEducacionContinuaByIdAcceso(String idAcceso);
	
	public List<ParticipanteDto> findAllParticipantesAprobadosEducacionContinuaById(Long idEduContinua);
	
	public List<ParticipanteDto> findAllParticipantesAprobadosSinNotificarEducacionContinuaById(Long idEduContinua);
	
	
	public void updateParticipantesNoNotificadosEmailByIdEducacionContinua(Long idEduContinua);
	
	public List<Object> findEmailParticipantesAprobadosEducacionContinuaByIdAcceso(String idAcceso);
	public List<Object> findEmailParticipantesEducacionContinuaByIdAcceso(String idAcceso);
	
	public ParticipanteDto validarParticipanteYaInscritoApp(Long idEduContinua, String documento);
	
	public ParticipanteDto validarQr(String qr);
	
	public void updateStatusPreInscripcionAllParticipantes(Long idEduContinua);
	
	public void updateStatusPreInscripcionParticipante(Long idEduContinua, String tokenParticipante);
	
	public void deletePreInscripcionParticipante(Long idEduContinua, String tokenParticipante);
	
	public void updateStatusPreInscripcionAllParticipantesEduContinua(Long idEduContinua);
	
	public void updateConstanciaPagoAsistente(Long idParticipante, String filePago);
}
