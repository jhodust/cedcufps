package com.ufps.cedcufps.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dto.AnexosDto;
import com.ufps.cedcufps.dto.CertificacionDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.InfoEducacionContinuaDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.modelos.Anexos;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Ponente;

public interface IEducacionContinuaMapper {

	
	//public List<EducacionContinuaAppDto> convertEducacionContinuaToApp(List<EducacionContinuaAppDto> edC, Map<Long,List<JornadaAppDto>> jornadasMap);
	public InfoEducacionContinuaDto convertEducacionContinuaToEducacionContinuaWeb(EducacionContinua e, boolean hasPermission);
	public ParticipanteDto convertParticipanteToParticipanteDto(Participante p);
	public List<ParticipanteDto> convertParticipantesToParticipanteDto(List<Participante> p);
	public PonenteDto convertPonenteToPonenteDto(Ponente p);
	public List<PonenteDto> convertListParticipantesToListPonentesDto(List<Participante> participantes);
	public List<PonenteDto> convertListPonentesToListPonentesDto(List<Ponente> p);
	public EducacionContinuaWebDto convertEducacionContinuaToEduContinuaWebDto(EducacionContinua e);
	public AnexosDto convertAnexoToAnexoDto(Anexos a);
	public List<AnexosDto> convertListAnexoToListAnexoDto(List<Anexos> anexos);
	public List<EducacionContinuaWebDto> convertListEducacionContinuaToListEduContinuaWebDto(List<EducacionContinua> educacionesContinuas);
	public EducacionContinuaWebDto convertInfoToEduContinuaDto(String id, String nombre, Date fechaInicio,
			Date fechaFin, String duracion, String cantMaxParticipantes, Date fechaLimInscripcion, String costoInscripcion, 
			String lugar, String costoEducacionContinua, String porcentajeAsistencia, String infoAdicional, String idTipoEduContinua, String tipoEduContinua,
			String idProgramaResponsable, String idDocenteResponsable, String idClasificacionCine, String consecutivo,
			String[] idTipoBeneficiarios);
	
	
	public CertificacionDto convertToMisCertificaciones(Long idParticipante, Long idPersona, String nombrePersona, String tipoParticipante,
			 String numeroDocumento, String tipoDocumento, String tipoEduContinua,
			Long idEducacionContinua, String educacionContinua, Date fechaInicioEduContinua, Date fechaFinEduContinua, 
			String diplomaParticipacion, boolean aprobado, Date fechaGeneracionDiploma, String token, Long idDiploma,
			Map<String,Object> estructuraDiploma,Date fechaActualizacionDiploma);
	
	public CertificacionDto convertToMisCertificaciones(ParticipanteDto participanteDto);
	
	
	
}
