package com.ufps.cedcufps.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.InfoEducacionContinuaDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;

@Repository
public class EducacionContinuaMapper implements IEducacionContinuaMapper {

	
	@Override
	public List<EducacionContinuaAppDto> convertEducacionContinuaToApp(List<EducacionContinua> edc) {
		// TODO Auto-generated method stub
		List<EducacionContinuaAppDto> ecadto=new ArrayList<>();
		
		if(!edc.isEmpty()) {
			for(EducacionContinua e:edc) {
				EducacionContinuaAppDto dto= new EducacionContinuaAppDto();
				dto.setId(e.getId());
				dto.setNombre(e.getNombre());
				dto.setFechaInicio(e.getFechaInicio());
				dto.setFechaFin(e.getFechaFin());
				dto.setTipoEduContinua(e.getTipoEduContinua().getTipoEduContinua());
				dto.setCantidadParticipantes(10);
				dto.setProgramaResponsable(e.getProgramaResponsable().getPrograma());
				dto.setDocenteResponsable(this.convertFieldsFullName(e.getDocenteResponsable()));
				SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat formatHour = new SimpleDateFormat("HH:mm");

				List<JornadaAppDto>jadto= new ArrayList<JornadaAppDto>();
				for(Jornada j:e.getJornadas()) {
					JornadaAppDto jornada= new JornadaAppDto();
					jornada.setId(j.getId());
					jornada.setHoraFin(j.getHoraFin());
					jornada.setHoraInicio(j.getHoraInicio());
					jornada.setFechaJornadaString(formatDate.format(j.getHoraInicio()));
					jornada.setHoraInicioString(formatHour.format(j.getHoraInicio()));
					jornada.setHoraFinString(formatHour.format(j.getHoraFin()));
					jadto.add(jornada);
				}
				dto.setJornadas(jadto);
				ecadto.add(dto);
			}
		}
		
		return ecadto;
	}

	
	@Override
	public InfoEducacionContinuaDto convertEducacionContinuaToEducacionContinuaWeb(EducacionContinua e,
			boolean hasPermission) {
		// TODO Auto-generated method stub
		InfoEducacionContinuaDto dto= new InfoEducacionContinuaDto();
		dto.setHasPermission(hasPermission);
		if(e!=null) {
			EducacionContinuaWebDto eduContinuaDto= new EducacionContinuaWebDto();
			eduContinuaDto.setId(e.getId());
			eduContinuaDto.setNombre(e.getNombre());
			eduContinuaDto.setFechaInicio(e.getFechaInicio());
			eduContinuaDto.setFechaFin(e.getFechaFin());
			eduContinuaDto.setFechaLimInscripcion(e.getFechaLimInscripcion());
			eduContinuaDto.setCosto(e.getCosto());
			eduContinuaDto.setCantMaxParticipantes(e.getCantMaxParticipantes());
			eduContinuaDto.setLugar(e.getLugar());
			eduContinuaDto.setDuracion(e.getDuracion());
			eduContinuaDto.setImagen(e.getImagen());
			eduContinuaDto.setContenidoGral(e.getContenidoGeneral());
			eduContinuaDto.setObjetivo(e.getObjetivo());
			eduContinuaDto.setRequisitos(e.getRequisitos());
			eduContinuaDto.setResumen(e.getResumen());
			eduContinuaDto.setEstado(e.getEstado());
			eduContinuaDto.setIdTipoEduContinua(e.getTipoEduContinua().getId());
			eduContinuaDto.setTipoEduContinua(e.getTipoEduContinua().getTipoEduContinua());
			eduContinuaDto.setIdDocenteResp(e.getDocenteResponsable().getId());
			eduContinuaDto.setNombreDocenteResp(this.convertFieldsFullName(e.getDocenteResponsable()));
			eduContinuaDto.setIdClasificacion(e.getClasificacionCine().getId());
			eduContinuaDto.setClasificacion(e.getClasificacionCine().getClasificacionCine());
			eduContinuaDto.setIdTipoBeneficiario(e.getTipoBeneficiario().getId());
			eduContinuaDto.setTipoBeneficiario(e.getTipoBeneficiario().getTipoBeneficiario());
			if(e.getDiploma()!=null) {
				eduContinuaDto.setIdDiploma(e.getDiploma().getId());
			}else {
				eduContinuaDto.setIdDiploma(null);
			}
			
			
			List<ParticipanteDto> participantes = new ArrayList<ParticipanteDto>();
			for(Participante p: e.getParticipantes()) {
				participantes.add(this.convertParticipanteToParticipanteDto(p));
			}
			
			List<JornadaAppDto> jornadas = new ArrayList<JornadaAppDto>();
			for(Jornada j: e.getJornadas()) {
				JornadaAppDto jdto= new JornadaAppDto();
				jdto.setId(j.getId());
				jdto.setHoraInicio(j.getHoraInicio());
				jdto.setHoraFin(j.getHoraFin());
				jornadas.add(jdto);
			}
			eduContinuaDto.setParticipantes(participantes);
			eduContinuaDto.setJornadas(jornadas);
			eduContinuaDto.setCantidadInscritos(participantes.size());
			dto.setEducacionContinua(eduContinuaDto);
			
		}else {
			dto.setEducacionContinua(null);
		}
		return dto;
		
	}
	
	public String convertFieldsFullName(Persona p) {
		String nombreResponsable=null;
		if(p.getPrimerNombre()!="") {
			nombreResponsable=p.getPrimerNombre();
		}
		if(p.getSegundoNombre()!="") {
			nombreResponsable=nombreResponsable + " " +p.getSegundoNombre();
		}
		if(p.getPrimerApellido()!="") {
			nombreResponsable=nombreResponsable + " " +p.getPrimerApellido();
		}
		if(p.getSegundoApellido()!="") {
			nombreResponsable=nombreResponsable + " " +p.getSegundoApellido();
		}
		return nombreResponsable;
	}


	@Override
	public ParticipanteDto convertParticipanteToParticipanteDto(Participante p) {
		// TODO Auto-generated method stub
		ParticipanteDto pdto= new ParticipanteDto();
		pdto.setId(p.getId());
		pdto.setIdPersona(p.getPersona().getId());
		pdto.setNombrePersona(this.convertFieldsFullName(p.getPersona()));
		pdto.setIdTipoParticipante(p.getTipoParticipante().getId());
		pdto.setTipoParticipante(p.getTipoParticipante().getTipoParticipante());
		pdto.setIdTipoDocumento(p.getPersona().getTipoDocumento().getId());
		pdto.setTipoDocumento(p.getPersona().getTipoDocumento().getTipoDocumento());
		pdto.setNumeroDocumento(p.getPersona().getNumeroDocumento());
		return pdto;
	}


	@Override
	public List<ParticipanteDto> convertParticipantesToParticipanteDto(List<Participante> participantes) {
		// TODO Auto-generated method stub
		List<ParticipanteDto> dto= new ArrayList<>();
		for(Participante p:participantes) {
			dto.add(this.convertParticipanteToParticipanteDto(p));
		}
		return dto;
	}

}
