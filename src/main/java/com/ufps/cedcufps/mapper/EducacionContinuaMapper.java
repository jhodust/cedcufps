package com.ufps.cedcufps.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.DiplomaDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.FirmasDiplomaDto;
import com.ufps.cedcufps.dto.ImagenesDiplomaDto;
import com.ufps.cedcufps.dto.InfoEducacionContinuaDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.dto.TextosDiplomaDto;
import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.FirmaDiploma;
import com.ufps.cedcufps.modelos.ImagenDiploma;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.TextoDiploma;
import com.zaxxer.hikari.util.SuspendResumeLock;

@Repository
public class EducacionContinuaMapper implements IEducacionContinuaMapper {

	@Autowired
	private IJornadaMapper jornadaMapper;
	
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

				dto.setJornadas(jornadaMapper.convertJornadasToJornadaAppDto(e.getJornadas()));
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
			eduContinuaDto.setIdProgramaResp(e.getProgramaResponsable().getId());
			eduContinuaDto.setProgramaResp(e.getProgramaResponsable().getPrograma());
			eduContinuaDto.setIdFacultad(e.getProgramaResponsable().getFacultad().getId());
			eduContinuaDto.setFacultad(e.getProgramaResponsable().getFacultad().getFacultad());
			eduContinuaDto.setIdClasificacion(e.getClasificacionCine().getId());
			eduContinuaDto.setClasificacion(e.getClasificacionCine().getClasificacionCine());
			eduContinuaDto.setIdTipoBeneficiario(e.getTipoBeneficiario().getId());
			eduContinuaDto.setTipoBeneficiario(e.getTipoBeneficiario().getTipoBeneficiario());
			if(e.getDiploma()!=null) {
				eduContinuaDto.setDiploma(this.convertDiplomaToDiplomaDto(e.getDiploma()));
			}else {
				eduContinuaDto.setDiploma(null);
			}
			
			
			List<ParticipanteDto> participantes = new ArrayList<ParticipanteDto>();
			List<PonenteDto> ponentes = new ArrayList<PonenteDto>();
			System.out.println("******************************************");
			System.out.println(e.getParticipantes().get(0).getTarjetaInscripcion());
			System.out.println(e.getParticipantes().get(2).getTarjetaInscripcion());
			System.out.println(e.getParticipantes().get(1).getTarjetaInscripcion());
			for(Participante p: e.getParticipantes()) {
				
				if(p.getTipoParticipante().getTipoParticipante().equalsIgnoreCase("ponente")) {
					System.out.println("entrando por ponente");
					ponentes.add(this.convertPonenteToPonenteDto((Ponente)p));
				}
				System.out.println("dentro del for");
				System.out.println(p.getTarjetaInscripcion());
				ParticipanteDto participanteDto=this.convertParticipanteToParticipanteDto(p);
				
				
				for(Asistencia a:p.getAsistencias()) {
					
					
					participanteDto.addAsistencia(a.getJornada().getId(), true);
				}
				System.out.println(participanteDto.getJornadasAsistencias().size());
				
				participantes.add(participanteDto);
			}
			
			
			eduContinuaDto.setParticipantes(participantes);
			eduContinuaDto.setJornadas(jornadaMapper.convertJornadasToJornadaAppDto(e.getJornadas()));
			eduContinuaDto.setCantidadInscritos(participantes.size());
			eduContinuaDto.setPonentes(ponentes);
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
		System.out.println("dentro de convert");
		System.out.println(pdto.getNombrePersona());
		System.out.println(p.getTarjetaInscripcion());
		pdto.setIdTipoParticipante(p.getTipoParticipante().getId());
		pdto.setTipoParticipante(p.getTipoParticipante().getTipoParticipante());
		pdto.setIdTipoDocumento(p.getPersona().getTipoDocumento().getId());
		pdto.setTipoDocumento(p.getPersona().getTipoDocumento().getTipoDocumento());
		pdto.setNumeroDocumento(p.getPersona().getNumeroDocumento());
		pdto.setEducacionContinua(p.getEducacionContinua().getNombre());
		pdto.setTipoEduContinua(p.getEducacionContinua().getTipoEduContinua().getTipoEduContinua());
		pdto.setFechaInicioEduContinua(p.getEducacionContinua().getFechaInicio());
		pdto.setFechaFinEduContinua(p.getEducacionContinua().getFechaFin());
		pdto.setLugarEducacionContinua(p.getEducacionContinua().getLugar());
		pdto.setImagenQr(p.getImagenCodigoQR());
		pdto.setPrimerNombre(p.getPersona().getPrimerNombre());
		pdto.setSegundoNombre(p.getPersona().getSegundoNombre());
		pdto.setPrimerApellido(p.getPersona().getPrimerApellido());
		pdto.setSegundoApellido(p.getPersona().getSegundoApellido());
		pdto.setTarjetaInscripcion(p.getTarjetaInscripcion());
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


	@Override
	public PonenteDto convertPonenteToPonenteDto(Ponente p) {
		// TODO Auto-generated method stub
		PonenteDto dto=new PonenteDto();
		dto.setParticipante(this.convertParticipanteToParticipanteDto(p));
		System.out.println("nombre ponente");
		dto.setTema(p.getTema());
		return dto;
	}


	@Override
	public List<PonenteDto> convertListPonentesToListPonentesDto(List<Ponente> p) {
		// TODO Auto-generated method stub
		List<PonenteDto> dto= new ArrayList<>();
		for(Ponente po:p) {
			dto.add(this.convertPonenteToPonenteDto(po));
		}
		return dto;
	}
	
	private DiplomaDto convertDiplomaToDiplomaDto(Diploma d) {
		DiplomaDto dto= new DiplomaDto();
		dto.setId(d.getId());
		dto.setFirmas(this.convertListFirmaToListFirmaDto(d.getFirmas()));
		dto.setImagenes(this.convertListImagenesToListImagenesDto(d.getImagenes()));
		dto.setTextos(this.convertListTextosToListTextoDto(d.getTextos()));
		return dto;
		
	}
	
	private TextosDiplomaDto convertTextoToTextoDto(TextoDiploma textoDiploma) {
		TextosDiplomaDto dto= new TextosDiplomaDto();
		dto.setCategoria(textoDiploma.getCategoria());
		dto.setTexto(textoDiploma.getTexto());
		dto.setId(textoDiploma.getId());
		dto.setX(textoDiploma.getX());
		dto.setY(textoDiploma.getY());
		return dto;
	}
	
	private List<TextosDiplomaDto> convertListTextosToListTextoDto(List<TextoDiploma> listTextoDiploma) {
		List<TextosDiplomaDto> dto= new ArrayList<>();
		for(TextoDiploma t: listTextoDiploma) {
			dto.add(this.convertTextoToTextoDto(t));
		}
		return dto;
	}
	
	private ImagenesDiplomaDto convertImagenToImagenDto(ImagenDiploma imagenDiploma) {
		ImagenesDiplomaDto dto= new ImagenesDiplomaDto();
		dto.setId(imagenDiploma.getId());
		dto.setX(imagenDiploma.getX());
		dto.setY(imagenDiploma.getY());
		dto.setImagen("/"+imagenDiploma.getRuta());
		return dto;
	}
	
	private List<ImagenesDiplomaDto> convertListImagenesToListImagenesDto(List<ImagenDiploma> listImagenDiploma) {
		List<ImagenesDiplomaDto> dto= new ArrayList<>();
		for(ImagenDiploma i: listImagenDiploma) {
			dto.add(this.convertImagenToImagenDto(i));
		}
		return dto;
	}
	
	private FirmasDiplomaDto convertFirmaToFrimaDto(FirmaDiploma firmaDiploma) {
		FirmasDiplomaDto dto= new FirmasDiplomaDto();
		dto.setId(firmaDiploma.getId());
		dto.setNombre(firmaDiploma.getNombre());
		dto.setCargo(firmaDiploma.getCargo());
		dto.setImagen("/"+firmaDiploma.getImagenFirmaDigital());
		dto.setxCargo(firmaDiploma.getxCargo());
		dto.setyCargo(firmaDiploma.getyCargo());
		dto.setxNombre(firmaDiploma.getxNombre());
		dto.setyNombre(firmaDiploma.getyNombre());
		dto.setX(firmaDiploma.getX());
		dto.setY(firmaDiploma.getY());
		return dto;
	}
	
	private List<FirmasDiplomaDto> convertListFirmaToListFirmaDto(List<FirmaDiploma> listFirmaDiploma) {
		List<FirmasDiplomaDto> dto= new ArrayList<>();
		for(FirmaDiploma f: listFirmaDiploma) {
			dto.add(this.convertFirmaToFrimaDto(f));
		}
		return dto;
	}

}
