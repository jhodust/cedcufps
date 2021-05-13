package com.ufps.cedcufps.services;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dto.CertificacionDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.TipoParticipante;
import com.ufps.cedcufps.utils.Archivo;

public interface IParticipanteService {

	
	public List<TipoParticipante> findAllTiposParticipante();
	
	public List<Participante> findAllParticipante();
	
	public ParticipanteDto saveAsistente(Long idEduContinua, Long idTipoPersona);
	
	public Optional<Participante> findOne(Long id);
	
	public TipoParticipante findByTipoParticipante(String tipoParticipante);
	
	public ParticipanteDto findByIdEducacionContinuaAndIdPersona(Long idEducacionContinua, Long idPersona);
	
	public void deleteParticipante(Long idParticipante);
	
	public void cancelarInscripcion(Long idEduContinua);
	
	
	public List<PonenteDto> findAllPonentesOfOneEducacionContinuaById(Long idEducacionContinua);
	
	public ParticipanteDto findParticipante(Long id);
	
	public void saveTarjetaInscripcion(MultipartFile file,Long idParticipante);
	
	public PonenteDto findPonente(Long id);
	
	public void deleteParticipanteById(Long id);
	
	public List<ParticipanteDto> findAllParticipacionesActivasByParticipante();
	
	
	public TipoParticipante findTipoParticipanteById(Long id);
	
	public ParticipanteDto savePonente(Ponente p);
	
	public void certificarParticipante(MultipartFile file,Long idEduContinua, String token, String documentoParticipante);
	
	public void updateCertificado(MultipartFile file, String filename, String token, Long idEduContinua, String documentoParticipante);

	public ByteArrayInputStream generarPdfDiplomas(String token);
	
	public void cancelarCertificacionParticipante(String token);
	
	public List<CertificacionDto> findCertificaciones();
	
	public CertificacionDto findCertificacionByToken(String token);
	
	public List<PonenteDto> findPonentesByEduContinua(Long idEducacionContinua);
	
	public void notificarPreInscripcionAllParticipantes(Long idEduContinua);
	
	public void notificarPreInscripcionParticipante(Long idEduContinua, String tokenParticipante);
	
	public void aprobarPreInscripcionAllParticipantes(Long idEduContinua);
	
	public void aprobarPreInscripcionParticipante(Long idEduContinua, String tokenParticipante);
	
	public void cancelarPreInscripcionParticipante(Long idEduContinua, String tokenParticipante);
	
	
}
