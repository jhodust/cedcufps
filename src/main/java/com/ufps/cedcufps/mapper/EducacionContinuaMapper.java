package com.ufps.cedcufps.mapper;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IAsistenciaCustomDao;
import com.ufps.cedcufps.dao.IAsistenciaDao;
import com.ufps.cedcufps.dao.IDiplomaCustomDao;
import com.ufps.cedcufps.dao.IDiplomaDao;
import com.ufps.cedcufps.dao.IJornadaDao;
import com.ufps.cedcufps.dao.IParticipanteCustomDao;
import com.ufps.cedcufps.dto.AnexosDto;
import com.ufps.cedcufps.dto.AsistenciaDto;
import com.ufps.cedcufps.dto.CertificacionDto;
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
import com.ufps.cedcufps.modelos.Anexos;
import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.EducacionContinuaTipoBeneficiario;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.utils.StatusEducacionContinua;
import com.zaxxer.hikari.util.SuspendResumeLock;

@Repository
public class EducacionContinuaMapper implements IEducacionContinuaMapper {

	@Autowired
	private IJornadaMapper jornadaMapper;
	
	@Autowired
	private IUsuarioMapper usuarioMapper;
	
	@Autowired
	private IParticipanteCustomDao participanteCustomDao;
	
	@Autowired
	private IDiplomaDao diplomaDao;
	
	@Autowired
	private IJornadaDao jornadaDao;
	
	@Autowired
	private IAsistenciaCustomDao asistenciaCustomDao;
	
	/*@Override
	public List<EducacionContinuaAppDto> convertEducacionContinuaToApp(List<EducacionContinuaAppDto> edC, Map<Long,List<JornadaAppDto>> jornadasMap) {
		// TODO Auto-generated method stub
		List<EducacionContinuaAppDto> ecadto=new ArrayList<>();
		
		for(EducacionContinuaAppDto e: edC) {
			e.setJornadas(jornadasMap.get(e.getId()));
		}
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
*/
	
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
			eduContinuaDto.setDiploma(this.convertDiplomaToDiplomaDto(diplomaDao.findDiplomaByIdEduContinua(e.getId()),e.getId()));
			
			Map<Long, Integer> asistenciasGeneralesMap=new HashMap<Long, Integer>();
			List<ParticipanteDto> participantes=this.participanteCustomDao.findAllParticipantesEducacionContinuaById(e.getId());
			List<Jornada> jornadas=jornadaDao.findByIdEducacionContinua(e.getId());
			for(Jornada j: jornadas) {
				asistenciasGeneralesMap.put(j.getId(), participantes.size());
			}
			
			List<PonenteDto> ponentes = participanteCustomDao.findAllPonentesOfOneEducacionContinuaById(e.getId());
			for(ParticipanteDto p: participantes) {
				
				
				List<AsistenciaDto> asistencias=asistenciaCustomDao.findAsistenciasByIdParticipante(p.getId());
				for(AsistenciaDto a:asistencias) {
					
					
					p.addAsistencia(a.getIdJornada(), true);
					asistenciasGeneralesMap.put(a.getIdJornada(), asistenciasGeneralesMap.get(a.getIdJornada()) - 1);
				}
				p.setEnableToCertificate(p.getJornadasAsistencias().size() >= Math.ceil(
						(double)(e.getJornadas().size()) * 
						(double)(Integer.parseInt((e.getPorcentajeAsistencia() != null) ? e.getPorcentajeAsistencia() : "100")) / 
						(double)(100) ) || p.getDiplomaParticipacion() != null);
				
			}
			
			
			eduContinuaDto.setParticipantes(participantes);
			eduContinuaDto.setJornadas(jornadaMapper.convertJornadasToJornadaAppDto(jornadas));
			eduContinuaDto.setCantidadInscritos(participantes.size()-ponentes.size());
			eduContinuaDto.setPonentes(ponentes);
			eduContinuaDto.setAnexos(this.convertListAnexoToListAnexoDto(e.getAnexos()));
			eduContinuaDto.setAsistenciasGenerales(this.validateAsistenciasGenerales(asistenciasGeneralesMap));
			dto.setEducacionContinua(eduContinuaDto);
			eduContinuaDto.setAbleToActions(!e.getEstado().equalsIgnoreCase(StatusEducacionContinua.STATUS_TERMINADO));
			eduContinuaDto.setStatusAllPreinscripciones(e.isAllParticipantesAprobadaPreinscipcion());
		}else {
			dto.setEducacionContinua(null);
		}
		return dto;
		
	}
	
	public Map<Long, Boolean> validateAsistenciasGenerales(Map<Long,Integer> asistenciasGenerales){
		Map<Long, Boolean> map=new HashMap<Long, Boolean>();
		Iterator<Map.Entry<Long, Integer>> itr = asistenciasGenerales.entrySet().iterator(); 
		while(itr.hasNext()) 
        { 
			Map.Entry<Long, Integer> entry = itr.next(); 
			map.put(entry.getKey(),
					entry.getValue() == 0); 
        } 
		return map;
	}

	@Override
	public ParticipanteDto convertParticipanteToParticipanteDto(Participante p) {
		// TODO Auto-generated method stub
		ParticipanteDto pdto= new ParticipanteDto();
		
		pdto.setId(p.getId());
		pdto.setIdPersona(p.getPersona().getId());
		pdto.setNombrePersona(usuarioMapper.convertFieldsFullName(p.getPersona()));
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
		pdto.setAprobado(p.isAprobado());
		pdto.setDiplomaParticipacion(p.getDiplomaParticipacion());
		pdto.setFechaGeneracionDiploma(p.getFechaGeneracionDiploma());
		pdto.setToken(p.getToken());
		pdto.setEmail(p.getPersona().getEmail());
		pdto.setStatusInscripcion(p.isStatusPreinscripcion());
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
	
	private DiplomaDto convertDiplomaToDiplomaDto(Diploma d, Long idEduContinua) {
		if(d!=null) {
			DiplomaDto dto= new DiplomaDto();
			dto.setId(d.getId());
			dto.setEstructuraDiploma(d.getEstructuraDiploma());
			dto.setIdEduContinua(idEduContinua);
			dto.setFechaActualizacionDiploma(d.getUpdatedAt());
			return dto;
		}
		return null;
		
	}
	
	/*private TextosDiplomaDto convertTextoToTextoDto(TextoDiploma textoDiploma) {
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
	}*/


	@Override
	public EducacionContinuaWebDto convertInfoToEduContinuaDto(String id, String nombre, Date fechaInicio,
			Date fechaFin, String duracion, String cantMaxParticipantes, Date fechaLimInscripcion, String costoInscripcion, 
			String lugar, String costoEducacionContinua, String porcentajeAsistencia, String infoAdicional, String idTipoEduContinua, String tipoEduContinua,
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
		dto.setIdTipoEduContinua(Long.parseLong(idTipoEduContinua));
		dto.setTipoEduContinua(tipoEduContinua);
		dto.setIdProgramaResp(Long.parseLong(idProgramaResponsable));
		dto.setIdDocenteResp(Long.parseLong(idDocenteResponsable));
		dto.setIdClasificacion(Long.parseLong(idClasificacionCine));
		dto.setCantMaxParticipantes(cantMaxParticipantes);
		dto.setPorcentajeAsistencia(porcentajeAsistencia);
		dto.setCostoEducacionContinua(costoEducacionContinua);
		dto.setInfoAdicional(infoAdicional);
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
		EducacionContinuaWebDto eduContinuaDto= new EducacionContinuaWebDto();
		eduContinuaDto.setId(e.getId());
		eduContinuaDto.setIdAcceso(e.getIdAcceso());
		eduContinuaDto.setNombre(e.getNombre());
		eduContinuaDto.setFechaInicio(e.getFechaInicio());
		eduContinuaDto.setFechaFin(e.getFechaFin());
		eduContinuaDto.setFechaLimInscripcion(e.getFechaLimInscripcion());
		eduContinuaDto.setCostoInscripcion(e.getCostoInscripcion());
		eduContinuaDto.setCantMaxParticipantes(e.getCantMaxParticipantes());
		eduContinuaDto.setLugar(e.getLugar());
		eduContinuaDto.setDuracion(e.getDuracion());
		eduContinuaDto.setImagen(e.getImagen());
		eduContinuaDto.setInfoAdicional(e.getInfoAdicional());
		eduContinuaDto.setEstado(e.getEstado());
		eduContinuaDto.setEnableAsistencia(!e.getEstado().equalsIgnoreCase(StatusEducacionContinua.STATUS_ACTIVO));
		eduContinuaDto.setIdTipoEduContinua(e.getTipoEduContinua().getId());
		eduContinuaDto.setTipoEduContinua(e.getTipoEduContinua().getTipoEduContinua());
		eduContinuaDto.setIdDocenteResp(e.getDocenteResponsable().getId());
		eduContinuaDto.setNombreDocenteResp(usuarioMapper.convertFieldsFullName(e.getDocenteResponsable()));
		eduContinuaDto.setCodigoDocenteResp(e.getDocenteResponsable().getCodigo());
		eduContinuaDto.setIdProgramaResp(e.getProgramaResponsable().getId());
		eduContinuaDto.setProgramaResp(e.getProgramaResponsable().getPrograma());
		eduContinuaDto.setIdFacultad(e.getProgramaResponsable().getFacultad().getId());
		eduContinuaDto.setFacultad(e.getProgramaResponsable().getFacultad().getFacultad());
		eduContinuaDto.setIdClasificacion((e.getClasificacionCine() != null ) ? e.getClasificacionCine().getId() : null);
		eduContinuaDto.setClasificacion((e.getClasificacionCine() != null ) ? e.getClasificacionCine().getClasificacionCine() : null);
		eduContinuaDto.setConsecutivo(e.getConsecutivo());
		eduContinuaDto.setPorcentajeAsistencia(e.getPorcentajeAsistencia());
		eduContinuaDto.setCostoEducacionContinua(e.getCostoEducacionContinua());
		eduContinuaDto.setEstadoOficialTipoEducacionContinua(e.getTipoEduContinua().isEstadoOficial());
		List<TipoBeneficiarioDto> list = new ArrayList<TipoBeneficiarioDto>();
		
		for(EducacionContinuaTipoBeneficiario ectb:e.getTipoBeneficiarios()) {
			
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


	@Override
	public CertificacionDto convertToMisCertificaciones(Long idParticipante, Long idPersona, String nombrePersona, String tipoParticipante,
			 String numeroDocumento, String tipoDocumento, String tipoEduContinua,
			Long idEducacionContinua, String educacionContinua, Date fechaInicioEduContinua, Date fechaFinEduContinua, 
			String diplomaParticipacion, boolean aprobado, Date fechaGeneracionDiploma, String token, Long idDiploma,
			Map<String,Object> estructuraDiploma, Date fechaActualizacionDiploma) {
		// TODO Auto-generated method stub
		
		ParticipanteDto participanteDto=new ParticipanteDto();
		participanteDto.setId(idParticipante);
		participanteDto.setIdPersona(idPersona);
		participanteDto.setNombrePersona(nombrePersona);
		participanteDto.setTipoParticipante(tipoParticipante);
		participanteDto.setNumeroDocumento(numeroDocumento);
		participanteDto.setTipoDocumento(tipoDocumento);
		participanteDto.setTipoEduContinua(tipoEduContinua);
		participanteDto.setIdEducacionContinua(idEducacionContinua);
		participanteDto.setEducacionContinua(educacionContinua);
		participanteDto.setFechaInicioEduContinua(fechaInicioEduContinua);
		participanteDto.setFechaFinEduContinua(fechaFinEduContinua);
		participanteDto.setDiplomaParticipacion(diplomaParticipacion);
		participanteDto.setAprobado(aprobado);
		participanteDto.setFechaGeneracionDiploma(fechaGeneracionDiploma);
		participanteDto.setToken(token);
		
		DiplomaDto diplomaDto=new DiplomaDto();
		diplomaDto.setId(idDiploma);
		diplomaDto.setEstructuraDiploma((Map<String,Object>)estructuraDiploma);
		diplomaDto.setFechaActualizacionDiploma(fechaActualizacionDiploma);
		
		
		CertificacionDto certDto=new CertificacionDto();
		certDto.setDiplomaDto(diplomaDto);
		certDto.setParticipanteDto(participanteDto);
		if(diplomaDto.getFechaActualizacionDiploma() != null && participanteDto.getFechaGeneracionDiploma() != null) {
			certDto.setUpdateDiploma(participanteDto.getFechaGeneracionDiploma().before(diplomaDto.getFechaActualizacionDiploma()));
		}else {
			certDto.setUpdateDiploma(diplomaDto.getFechaActualizacionDiploma() != null);
		}
		
		return certDto;
	}

	@Override
	public CertificacionDto convertToMisCertificaciones(ParticipanteDto participanteDto, EducacionContinuaWebDto e, Diploma diploma) {
		// TODO Auto-generated method stub
		
		
		
		DiplomaDto diplomaDto=new DiplomaDto();
		diplomaDto.setId(participanteDto.getIdDiploma());
		diplomaDto.setEstructuraDiploma(diploma.getEstructuraDiploma());
		diplomaDto.setFechaActualizacionDiploma(participanteDto.getFechaActualizacionDiploma());
		
		
		CertificacionDto certDto=new CertificacionDto();
		certDto.setDiplomaDto(diplomaDto);
		certDto.setParticipanteDto(participanteDto);
		if(diplomaDto.getFechaActualizacionDiploma() != null && participanteDto.getFechaGeneracionDiploma() != null) {
			certDto.setUpdateDiploma(participanteDto.getFechaGeneracionDiploma().before(diplomaDto.getFechaActualizacionDiploma()));
		}else {
			certDto.setUpdateDiploma(diplomaDto.getFechaActualizacionDiploma() != null);
		}
		certDto.setEduContinuaDto(e);
		
		return certDto;
	}
	
	 public static Map<String, Object> parameters(Object obj) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try { map.put(field.getName(), field.get(obj)); } catch (Exception e) { }
        }
        return map;
    }
	@Override
	public List<PonenteDto> convertListParticipantesToListPonentesDto(List<Participante> participantes) {
		// TODO Auto-generated method stub
		List<PonenteDto> ponentes = new ArrayList<PonenteDto>();
		for(Participante p: participantes) {
			ponentes.add(this.convertPonenteToPonenteDto((Ponente)p));
		}
		return ponentes;
	}



	@Override
	public AnexosDto convertAnexoToAnexoDto(Anexos a) {
		// TODO Auto-generated method stub
		AnexosDto dto=new AnexosDto();
		dto.setId(a.getId());
		dto.setNombre(a.getNombre());
		dto.setFile(a.getFile());
		dto.setFechaSubida(a.getFechaRegisto());
		dto.setImage(a.getFile().contains(".jpeg") || a.getFile().contains(".jpg") || a.getFile().contains(".png"));
		return dto;
	}



	@Override
	public List<AnexosDto> convertListAnexoToListAnexoDto(List<Anexos> anexos) {
		// TODO Auto-generated method stub
		List<AnexosDto> list= new ArrayList<AnexosDto>();
		for(Anexos a: anexos) {
			list.add(this.convertAnexoToAnexoDto(a));
		}
		return list;
	}




	

}
