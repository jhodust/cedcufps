package com.ufps.cedcufps.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dao.IDiplomaDao;
import com.ufps.cedcufps.dao.IEducacionContinuaCustomDao;
import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dao.IParticipanteCustomDao;
import com.ufps.cedcufps.dao.IParticipanteDao;
import com.ufps.cedcufps.dao.IPersonaCustomDao;
import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dao.ITipoParticipanteDao;
import com.ufps.cedcufps.dto.CertificacionDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.mapper.IEducacionContinuaMapper;
import com.ufps.cedcufps.mapper.IUsuarioMapper;
import com.ufps.cedcufps.modelos.Asistente;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.TipoParticipante;
import com.ufps.cedcufps.utils.Archivo;
import com.ufps.cedcufps.utils.CodigoQR;
import com.ufps.cedcufps.utils.Encrypt;
import com.ufps.cedcufps.utils.ManejoPdf;

@Service
public class ParticipanteService implements IParticipanteService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParticipanteService.class);
	
	@Autowired
	private IPersonaDao personaDao;
	
	@Autowired
	private IEducacionContinuaDao educacionContinuaDao;
	
	@Autowired
	private IEducacionContinuaCustomDao educacionContinuaCustomDao;
	
	@Autowired
	private IParticipanteDao participanteDao;
	
	@Autowired
	private IParticipanteCustomDao participanteCustomDao;
	
	@Autowired
	private ITipoParticipanteDao tipoParticipanteDao;
	
	@Autowired
	private IEducacionContinuaMapper educacionContinuaMapper;
	
	@Autowired
	private IUsuarioMapper usuarioMapper;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private IFileStorageService fileStorageService;
	
	@Autowired
	private IPersonaCustomDao personaCustomDao;
	
	@Autowired
	private IDiplomaDao diplomaDao;
	
	@Override
	public List<TipoParticipante> findAllTiposParticipante() {
		// TODO Auto-generated method stub
		return (List<TipoParticipante>) tipoParticipanteDao.findAll();
	}

	@Override
	public List<Participante> findAllParticipante() {
		// TODO Auto-generated method stub
		return (List<Participante>) participanteDao.findAll();
	}

	@Override
	public ParticipanteDto saveAsistente(Long idEduContinua, Long idTipoPersona) {
		// TODO Auto-generated method stub
		ParticipanteDto dto=this.saveParticipante(idEduContinua, personaService.findPersonaLogueada(),"Asistente", idTipoPersona);
		this.participanteDao.insertAsistente(dto.getId());
		
		return dto;
	}
	
	public ParticipanteDto saveParticipante(Long idEduContinua, Persona p, String tipoParticipante, Long idTipoPersona) {
		ParticipanteDto dto=new ParticipanteDto();
		EducacionContinua e= educacionContinuaCustomDao.findEducacionContinuaById(idEduContinua);
		this.createDirEducacionContinua(idEduContinua);
		if(e == null) {
			throw new CustomException("No se encontró la educación continua en la base de datos");
		}else {
			dto.setIdEducacionContinua(idEduContinua);
			dto.setEducacionContinua(e.getNombre());
			dto.setFechaInicioEduContinua(e.getFechaInicio());
			dto.setFechaFinEduContinua(e.getFechaFin());
			dto.setTipoEduContinua(e.getTipoEduContinua().getTipoEduContinua());
			dto.setIdTipoEduContinua(e.getTipoEduContinua().getId());
			dto.setLugarEducacionContinua(e.getLugar());
		}
		
		TipoParticipante tp= tipoParticipanteDao.findByTipoParticipante(tipoParticipante);
		if(tp == null) {
			throw new CustomException("No se encontró el tipo participante 'Asistente' en la base de datos");
		}else {
			dto.setIdTipoParticipante(tp.getId());
			dto.setTipoParticipante(tp.getTipoParticipante());
		}
		PersonaDto perDto=usuarioMapper.convertPersonaToPersonaDto(p); 
		dto.setIdPersona(perDto.getId());
		dto.setPrimerNombre(p.getPrimerNombre());
		dto.setSegundoNombre(p.getSegundoNombre());
		dto.setPrimerApellido(p.getPrimerApellido());
		dto.setSegundoApellido(p.getSegundoApellido());
		    
		String texto=e.getProgramaResponsable().getCodigo()+"_"+e.getTipoEduContinua().getId()+"_"+e.getId()+"_"+tp.getId()+"_"+p.getNumeroDocumento();
		String nombreArchivo=p.getNumeroDocumento()+".png";
		texto=Encrypt.encriptar(texto);
		try {
			dto.setImagenQr(CodigoQR.generateQR(fileStorageService.dirEducacionContinua().resolve(String.valueOf(e.getId())).resolve(fileStorageService.dirQrParticipantes()),nombreArchivo, texto));
			dto.setCodigoQR(texto);
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		if(idTipoPersona == 0L) {
			if(tp.getTipoParticipante().equalsIgnoreCase("Ponente")) {
				dto.setIdTipoPersona(null);
			}else {
				dto.setIdTipoPersona(Long.parseLong(p.getIdsTipoPersona()));
			}
			
		}else {
			dto.setIdTipoPersona(idTipoPersona);
		}
		dto.setToken(String.valueOf(System.currentTimeMillis()));
		participanteCustomDao.saveParticipante(dto);
		participanteCustomDao.updateStatusPreInscripcionAllParticipantesEduContinua(idEduContinua);
		return dto;
	}

	@Override
	public TipoParticipante findByTipoParticipante(String tipoParticipante) {
		// TODO Auto-generated method stub
		return tipoParticipanteDao.findByTipoParticipante(tipoParticipante);
	}

	@Override
	public Optional<Participante> findOne(Long id) {
		// TODO Auto-generated method stub
		return participanteDao.findById(id);
	}

	@Override
	public ParticipanteDto findByIdEducacionContinuaAndIdPersona(Long idEducacionContinua, Long idPersona) {
		// TODO Auto-generated method stub
		return participanteCustomDao.findParticipanteByIdEducacionContinuaAndIdPersona(idEducacionContinua, idPersona);
	}

	@Override
	@Transactional(rollbackFor = CustomException.class)
	public void deleteParticipante(Long idParticipante) {
		// TODO Auto-generated method stub
		ParticipanteDto pa= participanteCustomDao.findParticipanteById(idParticipante); 
		if(pa==null) {
			throw new CustomException("El participante a eliminar no fue encontrado en la base de datos");
		}
		
		this.deleteInfoParticipante(idParticipante, pa.getCodigoQR(), pa.getTarjetaInscripcion());
		
		
	}
	
	private void deleteInfoParticipante(Long idParticipante, String codigoQr, String tarjetaInscripcion) {
		try {
			participanteDao.deleteParticipante(idParticipante);
			//Archivo.deleteImage(codigoQr);
			//Archivo.deleteImage(tarjetaInscripcion);
		}catch(DataIntegrityViolationException e) {
			throw new CustomException("El participante ya tiene asistencias registradas y no es posible cancelar su inscripción");
		}
		
	}
	
	@Override
	@Transactional(rollbackFor = CustomException.class)
	public void cancelarInscripcion(Long idEduContinua) {
		// TODO Auto-generated method stub
		ParticipanteDto pa = participanteCustomDao.findParticipanteByIdEducacionContinuaAndIdPersona(idEduContinua, personaService.findPersonaLogueada().getId());
		if(pa==null) {
			throw new CustomException("La inscripción a eliminar no fue encontrada en la base de datos");
		}
		
		this.deleteInfoParticipante(pa.getId(), pa.getImagenQr(), pa.getTarjetaInscripcion());
		this.prepararEmailCancelarInscripcion(pa);
	}

	
	
	@Override
	public List<PonenteDto> findAllPonentesOfOneEducacionContinuaById(Long idEducacionContinua) {
		// TODO Auto-generated method stub
		return participanteCustomDao.findAllPonentesOfOneEducacionContinuaById(idEducacionContinua);
	}

	@Override
	public ParticipanteDto findParticipante(Long id) {
		// TODO Auto-generated method stub
		return educacionContinuaMapper.convertParticipanteToParticipanteDto(participanteDao.findById(id).get());
	}
	
	@Override
	public PonenteDto findPonente(Long id) {
		// TODO Auto-generated method stub
		return educacionContinuaMapper.convertPonenteToPonenteDto((Ponente)participanteDao.findById(id).get());
	}

	@Override
	public void deleteParticipanteById(Long id) {
		// TODO Auto-generated method stub
		participanteDao.deleteById(id);
	}

	@Override
	public List<ParticipanteDto> findAllParticipacionesActivasByParticipante() {
		// TODO Auto-generated method stub
		return participanteCustomDao.findAllParticipacionesActivasByParticipante(personaService.findPersonaLogueada().getNumeroDocumento());
		
	}

	@Override
	public TipoParticipante findTipoParticipanteById(Long id) {
		// TODO Auto-generated method stub
		return tipoParticipanteDao.findById(id).get();
	}

	@Override
	@Transactional(rollbackFor = CustomException.class)
	public ParticipanteDto savePonente(Ponente ponente) {
		// TODO Auto-generated method stub
		if(ponente.getId() == null || ponente.getId().equals(0L) ) {
			ParticipanteDto participanteValidado = participanteCustomDao.findParticipanteByIdEducacionContinuaAndIdPersona(ponente.getEducacionContinua().getId(), ponente.getPersona().getId());
			if( participanteValidado != null) {
				throw new CustomException("La persona ya se encuentra registrada como " + participanteValidado.getTipoParticipante(), HttpStatus.BAD_REQUEST);
			}
		}
		Persona p=personaCustomDao.findPersonaById(ponente.getPersona().getId());
		if(p==null) {
			throw new CustomException("La persona no fue encontrada en la base de datos ", HttpStatus.BAD_REQUEST);
		}
		
		if(ponente.getId()==null || ponente.getId().equals(0L)) {
			ParticipanteDto dto=this.saveParticipante(ponente.getEducacionContinua().getId(), p, "Ponente",0L);
			this.participanteDao.insertPonente(dto.getId(), ponente.getTema());
			return dto;
			
		}else {
			participanteDao.updatePonente(ponente.getTema(),ponente.getId());
			return null;
		}
			
	}

	@Override
	public void saveTarjetaInscripcion(MultipartFile file, Long idParticipante) {
		// TODO Auto-generated method stub
		ParticipanteDto p= participanteCustomDao.findParticipanteById(idParticipante);
		String tarjetaInscripcion=Archivo.saveImageAboutEducacionContinua(file,"inscripcion_"+p.getNumeroDocumento(),
				fileStorageService.dirEducacionContinua().resolve(String.valueOf(p.getIdEducacionContinua())).resolve(fileStorageService.dirTarjetasInscripcion()));
		participanteDao.updateTarjetaInscripcion(tarjetaInscripcion, idParticipante);
		
		
		
	}

	public void prepararEmailInscripcion(ParticipanteDto participante) {
		String strDateFormat = "dd/MM/yyyy"; 
	     SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
	     
	     String strTimeFormat = "hh:mm a";   
	     SimpleDateFormat objSTF = new SimpleDateFormat(strTimeFormat);
	     
		String contenido=String.format("La inscripción al %s %s se ha realizado exitosamente. "
				+ "Recuerde que la actividad inicia el %s a las %s A continuación se adjunta su respectiva tarjeta de inscripción.",
				participante.getTipoEduContinua(), participante.getEducacionContinua(),
				objSDF.format(participante.getFechaInicioEduContinua()),objSTF.format(participante.getFechaInicioEduContinua())); 
		
		notificarViaEmail(participante.getEmail(), "Inscripción Realizada " + participante.getEducacionContinua(),
				participante.getTarjetaInscripcion(), contenido,participante.getNombrePersona(),true);
	}
	
	public void prepararEmailCancelarInscripcion(ParticipanteDto participante) {
		
	     
		String contenido=String.format("La inscripción al %s %s se ha cancelado exitosamente. ",
				participante.getTipoEduContinua(),participante.getEducacionContinua()); 
		
		notificarViaEmail(participante.getEmail(), "Inscripción Cancelada " + participante.getEducacionContinua(),
				null, contenido, participante.getNombrePersona(),false);
	}
	
	public void notificarViaEmail(String email, String asunto, String tarjetaInscripcion, String contenido, String nombreParticipante, boolean adjuntarImagen) {
		emailService.sendEmailInscripcion(email, asunto, tarjetaInscripcion, contenido, nombreParticipante,adjuntarImagen);
		
	}

	@Override
	public void certificarParticipante(MultipartFile file, Long idEduContinua, String token, String documentoParticipante) {
		// TODO Auto-generated method stub
		
		this.createDirEducacionContinua(idEduContinua);
		String diplomaImagen=Archivo.saveImageAboutEducacionContinua(file,documentoParticipante,fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEduContinua)).resolve(fileStorageService.dirDiplomasParticipantes()));
		participanteDao.createCertificacionParticipante(true, diplomaImagen, new Date(), token);
		//prepararEmailInscripcion(p.getEducacionContinua(), perDto, tarjetaInscripcion);
		
	}
	
	
	@Override
	public ByteArrayInputStream generarPdfDiplomas(String token) {
		// TODO Auto-generated method stub
		ParticipanteDto p= participanteCustomDao.findByToken(token);
		return ManejoPdf.generarPDFDiplomas(p.getDiplomaParticipacion());
	}

	@Override
	public void cancelarCertificacionParticipante(String token) {
		// TODO Auto-generated method stub
		ParticipanteDto p= participanteCustomDao.findByToken(token);
		try {
			Archivo.deleteImage(p.getDiplomaParticipacion());
		}catch(Exception e) {
			System.out.println("No se encontró la imagen de la certificación del participante con token: " + token);
		}
		
		participanteDao.createCertificacionParticipante(false, null, null, token);
	}

	@Override
	public List<CertificacionDto> findCertificaciones() {
		// TODO Auto-generated method stub
		return educacionContinuaCustomDao.findCertificaciones(personaService.findPersonaLogueada().getNumeroDocumento());
	}

	@Override
	public CertificacionDto findCertificacionByToken(String token) {
		// TODO Auto-generated method stub
		
		ParticipanteDto p= participanteCustomDao.findByToken(token);
		//Persona per=p.getPersona();
		//EducacionContinua e= p.getEducacionContinua();
		//Diploma d= e.getDiploma();
		EducacionContinuaWebDto e=educacionContinuaCustomDao.findInfoEducacionContinuaDiplomaById(p.getIdEducacionContinua());
		Diploma d=diplomaDao.findDiplomaById(p.getIdDiploma());
		return educacionContinuaMapper.convertToMisCertificaciones(p,e,d);
		
	}

	@Override
	public void updateCertificado(MultipartFile file, String filename, String token, Long idEduContinua, String documentoParticipante) {
		// TODO Auto-generated method stub
		
		try {
			this.createDirEducacionContinua(idEduContinua);
			Archivo.deleteImage(filename);
			String nombreArchivo=Archivo.saveImageAboutEducacionContinua(file,documentoParticipante, fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEduContinua)).resolve(fileStorageService.dirDiplomasParticipantes()));
			participanteDao.updateCertificacionParticipante(new Date(), nombreArchivo, token);
		}catch(Exception e) {
			System.out.println("No se encontró la imagen de la certificación del participante con token: " + token);
		}
	}

	@Override
	public List<PonenteDto> findPonentesByEduContinua(Long idEducacionContinua) {
		// TODO Auto-generated method stub
		return participanteCustomDao.findAllPonentesOfOneEducacionContinuaById(idEducacionContinua);
	}
	
	
	public void createDirEducacionContinua(Long idEducacionContinua) {
		try {
			File directory = new File(fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEducacionContinua)).resolve(fileStorageService.dirQrParticipantes()).toString());
			if(!directory.exists()) {
				Files.createDirectories(fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEducacionContinua)).resolve(fileStorageService.dirQrParticipantes()));
			}
			directory = new File(fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEducacionContinua)).resolve(fileStorageService.dirTarjetasInscripcion()).toString());
			if(!directory.exists()) {
				Files.createDirectories(fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEducacionContinua)).resolve(fileStorageService.dirTarjetasInscripcion()));
			}
			directory = new File(fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEducacionContinua)).resolve(fileStorageService.dirDiplomasParticipantes()).toString());
			if(!directory.exists()) {
				Files.createDirectories(fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEducacionContinua)).resolve(fileStorageService.dirDiplomasParticipantes()));
			}
			directory = new File(fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEducacionContinua)).resolve(fileStorageService.dirAnexos()).toString());
			if(!directory.exists()) {
				Files.createDirectories(fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEducacionContinua)).resolve(fileStorageService.dirAnexos()));
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Async
	@Override
	public void notificarPreInscripcionAllParticipantes(Long idEduContinua) {
		// TODO Auto-generated method stub
		List<ParticipanteDto> listParticipantes=participanteCustomDao.findAllParticipantesEducacionContinuaById(idEduContinua);
		for(ParticipanteDto dto:listParticipantes) {
			if(dto.isStatusInscripcion()) {
				this.notificarInscripcion(dto);
			}
			
		}
	}

	@Override
	public void notificarPreInscripcionParticipante(Long idEduContinua, String tokenParticipante) {
		// TODO Auto-generated method stub
		ParticipanteDto p= participanteCustomDao.findByToken(tokenParticipante);
		this.notificarInscripcion(p);
		
		
	}
	
	public void notificarInscripcion(ParticipanteDto participanteDto) {
		prepararEmailInscripcion(participanteDto);
	}

	@Override
	public void aprobarPreInscripcionAllParticipantes(Long idEduContinua) {
		// TODO Auto-generated method stub
		participanteCustomDao.updateStatusPreInscripcionAllParticipantes(idEduContinua);
		participanteCustomDao.updateStatusPreInscripcionAllParticipantesEduContinua(idEduContinua);
	}
	
	
	
	@Override
	public void aprobarPreInscripcionParticipante(Long idEduContinua, String tokenParticipante) {
		// TODO Auto-generated method stub
		participanteCustomDao.updateStatusPreInscripcionParticipante(idEduContinua, tokenParticipante);
		participanteCustomDao.updateStatusPreInscripcionAllParticipantesEduContinua(idEduContinua);
	}

	@Override
	public void cancelarPreInscripcionParticipante(Long idEduContinua, String tokenParticipante) {
		// TODO Auto-generated method stub
		ParticipanteDto pa= participanteCustomDao.findByToken(tokenParticipante); 
		if(pa==null) {
			throw new CustomException("El participante a eliminar no fue encontrado en la base de datos");
		}
		
		this.deleteInfoParticipante(pa.getId(), pa.getCodigoQR(), pa.getTarjetaInscripcion());
		
		this.prepararEmailCancelarInscripcion(pa);
	}

}
