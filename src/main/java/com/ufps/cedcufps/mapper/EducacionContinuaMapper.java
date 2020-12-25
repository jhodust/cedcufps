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
import com.ufps.cedcufps.dto.TipoBeneficiarioDto;
import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.EducacionContinuaTipoBeneficiario;
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
				dto.setFechaLimInscripcion(e.getFechaLimInscripcion());
				dto.setTipoEduContinua(e.getTipoEduContinua().getTipoEduContinua());
				dto.setCantidadParticipantes(10);
				dto.setProgramaResponsable(e.getProgramaResponsable().getPrograma());

				dto.setDocenteResponsable(this.convertFieldsFullName(e.getDocenteResponsable()));

				dto.setJornadas(jornadaMapper.convertJornadasToJornadaAppDto(e.getJornadas()));
				dto.setIdAcceso(e.getIdAcceso());
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
			EducacionContinuaWebDto eduContinuaDto= this.convertEducacionContinuaToEduContinuaWebDto(e);
			//eduContinuaDto.setIdTipoBeneficiario(e.getTipoBeneficiario().getId());
			//eduContinuaDto.setTipoBeneficiario(e.getTipoBeneficiario().getTipoBeneficiario());
			if(e.getDiploma()!=null) {
				eduContinuaDto.setDiploma(this.convertDiplomaToDiplomaDto(e.getDiploma()));
			}else {
				eduContinuaDto.setDiploma(null);
			}
			
			
			List<ParticipanteDto> participantes = new ArrayList<ParticipanteDto>();
			List<PonenteDto> ponentes = new ArrayList<PonenteDto>();
			System.out.println("******************************************");
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
	
	@Override
	public String convertFieldsFullName(Persona p) {
		String nombreResponsable=null;
		if(p.getPrimerNombre()!="") {
			nombreResponsable=p.getPrimerNombre();
		}
		if(p.getSegundoNombre() != null && p.getSegundoNombre()!="") {
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


	@Override
	public EducacionContinuaWebDto convertInfoToEduContinuaDto(String id, String nombre, Date fechaInicio,
			Date fechaFin, String duracion, String cantMaxParticipantes, Date fechaLimInscripcion, String costoInscripcion, String lugar,
			String costoEducacionContinua, String requisitos, String objetivo, String porcentajeAsistencia,
			String resumen, String contenidoGeneral, String idTipoEduContinua, String tipoEduContinua,
			String idProgramaResponsable, String idDocenteResponsable, String idClasificacionCine, String consecutivo,
			String[] idTipoBeneficiarios) {
		// TODO Auto-generated method stub
		EducacionContinuaWebDto dto= new EducacionContinuaWebDto();
		dto.setId(Long.parseLong(id));
		dto.setNombre(nombre);
		dto.setFechaInicio(fechaInicio);
		dto.setFechaFin(fechaFin);
		dto.setFechaLimInscripcion(fechaLimInscripcion);
		dto.setDuracion(duracion);
		dto.setCostoInscripcion(costoInscripcion);
		dto.setLugar(lugar);
		dto.setResumen(resumen);
		dto.setContenidoGral(contenidoGeneral);
		dto.setIdTipoEduContinua(Long.parseLong(idTipoEduContinua));
		dto.setTipoEduContinua(tipoEduContinua);
		dto.setIdProgramaResp(Long.parseLong(idProgramaResponsable));
		dto.setIdDocenteResp(Long.parseLong(idDocenteResponsable));
		dto.setIdClasificacion(Long.parseLong(idClasificacionCine));
		dto.setCantMaxParticipantes(cantMaxParticipantes);
		dto.setPorcentajeAsistencia(porcentajeAsistencia);
		dto.setCostoEducacionContinua(costoEducacionContinua);
		dto.setObjetivo(objetivo);
		dto.setRequisitos(requisitos);
		dto.setConsecutivo(consecutivo);
		List<TipoBeneficiarioDto> list = new ArrayList<TipoBeneficiarioDto>();
		for(String a:idTipoBeneficiarios) {
			TipoBeneficiarioDto d = new TipoBeneficiarioDto();
			d.setId(Long.parseLong(a));
			list.add(d);
		}
		dto.setTipoBeneficiarios(list);
		return dto;
	}


	@Override
	public EducacionContinuaWebDto convertEducacionContinuaToEduContinuaWebDto(EducacionContinua e) {
		// TODO Auto-generated method stub
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("mapperrrrrrrrrrrrrrrrrrrrrrrrrrr");
		EducacionContinuaWebDto eduContinuaDto= new EducacionContinuaWebDto();
		eduContinuaDto.setId(e.getId());
		eduContinuaDto.setNombre(e.getNombre());
		eduContinuaDto.setFechaInicio(e.getFechaInicio());
		eduContinuaDto.setFechaFin(e.getFechaFin());
		eduContinuaDto.setFechaLimInscripcion(e.getFechaLimInscripcion());
		eduContinuaDto.setCostoInscripcion(e.getCostoInscripcion());
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
		eduContinuaDto.setCodigoDocenteResp(e.getDocenteResponsable().getCodigo());
		eduContinuaDto.setIdProgramaResp(e.getProgramaResponsable().getId());
		eduContinuaDto.setProgramaResp(e.getProgramaResponsable().getPrograma());
		eduContinuaDto.setIdFacultad(e.getProgramaResponsable().getFacultad().getId());
		eduContinuaDto.setFacultad(e.getProgramaResponsable().getFacultad().getFacultad());
		eduContinuaDto.setIdClasificacion(e.getClasificacionCine().getId());
		eduContinuaDto.setClasificacion(e.getClasificacionCine().getClasificacionCine());
		eduContinuaDto.setConsecutivo(e.getConsecutivo());
		eduContinuaDto.setPorcentajeAsistencia(e.getPorcentajeAsistencia());
		eduContinuaDto.setCostoEducacionContinua(e.getCostoEducacionContinua());
		eduContinuaDto.setEstadoOficialTipoEducacionContinua(e.getTipoEduContinua().isEstadoOficial());
		List<TipoBeneficiarioDto> list = new ArrayList<TipoBeneficiarioDto>();
		System.out.println("educacion continuaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(e.getId());
		System.out.println(e.getNombre());
		System.out.println("cantidad beneficiarios");
		System.out.println(e.getTipoBeneficiarios().size());
		for(EducacionContinuaTipoBeneficiario ectb:e.getTipoBeneficiarios()) {
			System.out.println("--------------------");
			System.out.println(ectb.getTipoBeneficiario().getTipoBeneficiario());
			TipoBeneficiarioDto d = new TipoBeneficiarioDto();
			d.setId(ectb.getTipoBeneficiario().getId());
			d.setTipoBeneficiario(ectb.getTipoBeneficiario().getTipoBeneficiario());
			d.setHomologacion(ectb.getTipoBeneficiario().getHomologacion());
			list.add(d);
		}
		eduContinuaDto.setTipoBeneficiarios(list);
		return eduContinuaDto;
	}
	
	@Override
	public List<EducacionContinuaWebDto> convertListEducacionContinuaToListEduContinuaWebDto(List<EducacionContinua> educacionesContinuas) {
		// TODO Auto-generated method stub
		List<EducacionContinuaWebDto> list = new ArrayList<EducacionContinuaWebDto>();
		for(EducacionContinua e: educacionesContinuas) {
			list.add(convertEducacionContinuaToEduContinuaWebDto(e));
		}
		return list;
	}


	

}
