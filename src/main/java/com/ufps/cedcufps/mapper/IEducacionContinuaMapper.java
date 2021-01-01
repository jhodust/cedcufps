package com.ufps.cedcufps.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.InfoEducacionContinuaDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Ponente;

public interface IEducacionContinuaMapper {

	
	public List<EducacionContinuaAppDto> convertEducacionContinuaToApp(List<EducacionContinua> edc);
	public InfoEducacionContinuaDto convertEducacionContinuaToEducacionContinuaWeb(EducacionContinua e, boolean hasPermission);
	public ParticipanteDto convertParticipanteToParticipanteDto(Participante p);
	public List<ParticipanteDto> convertParticipantesToParticipanteDto(List<Participante> p);
	public PonenteDto convertPonenteToPonenteDto(Ponente p);
	public List<PonenteDto> convertListPonentesToListPonentesDto(List<Ponente> p);
	public EducacionContinuaWebDto convertEducacionContinuaToEduContinuaWebDto(EducacionContinua e);
	public List<EducacionContinuaWebDto> convertListEducacionContinuaToListEduContinuaWebDto(List<EducacionContinua> educacionesContinuas);
	public EducacionContinuaWebDto convertInfoToEduContinuaDto(String id, String nombre, Date fechaInicio,
			Date fechaFin, String duracion, String cantMaxParticipantes, Date fechaLimInscripcion, String costoInscripcion, 
			String lugar, String costoEducacionContinua, String porcentajeAsistencia, String infoAdicional, String idTipoEduContinua, String tipoEduContinua,
			String idProgramaResponsable, String idDocenteResponsable, String idClasificacionCine, String consecutivo,
			String[] idTipoBeneficiarios);
	public String convertFieldsFullName(Persona p);
	
}
