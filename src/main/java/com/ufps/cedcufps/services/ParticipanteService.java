package com.ufps.cedcufps.services;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dao.IEducacionContinuaCustomDao;
import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dao.IParticipanteCustomDao;
import com.ufps.cedcufps.dao.IParticipanteDao;
import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dao.ITipoParticipanteDao;
import com.ufps.cedcufps.dto.CertificacionDto;
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
		EducacionContinua e= educacionContinuaDao.findEducacionContinuaById(idEduContinua);
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
		System.out.println("texto original: " + texto);
		texto=Encrypt.encriptar(texto);
		try {
			;
			System.out.println("encriptado: " + texto);
			System.out.println("desencriptado: " + Encrypt.desencriptar(texto));
			dto.setImagenQr(CodigoQR.generateQR(fileStorageService.dirEducacionContinua().resolve(String.valueOf(e.getId())).resolve(fileStorageService.dirQrParticipantes()),nombreArchivo, texto));
			dto.setCodigoQR(texto);
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		System.out.println("##########################################################");
		System.out.println(idTipoPersona);
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
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(dto.getId());
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
		return educacionContinuaMapper.convertParticipanteToParticipanteDto(participanteDao.findParticipanteByIdEducacionContinuaAndIdPersona(idEducacionContinua, idPersona));
	}

	@Override
	@Transactional(rollbackFor = CustomException.class)
	public void deleteParticipante(Long idParticipante) {
		// TODO Auto-generated method stub
		Participante pa= participanteDao.findParticipanteById(idParticipante); 
		if(pa==null) {
			throw new CustomException("El participante a eliminar no fue encontrado en la base de datos");
		}
		
		this.deleteInfoParticipante(idParticipante, pa.getCodigoQR(), pa.getTarjetaInscripcion());
		
		PersonaDto perDto=usuarioMapper.convertPersonaToPersonaDto(pa.getPersona());
		this.prepararEmailCancelarInscripcion(pa.getEducacionContinua(), perDto);
		
	}
	
	private void deleteInfoParticipante(Long idParticipante, String codigoQr, String tarjetaInscripcion) {
		try {
			participanteDao.deleteParticipante(idParticipante);
			Archivo.deleteImage(codigoQr);
			Archivo.deleteImage(tarjetaInscripcion);
		}catch(DataIntegrityViolationException e) {
			throw new CustomException("El participante ya tiene asistencias registradas y no es posible cancelar su inscripción");
		}
		
	}
	
	@Override
	@Transactional(rollbackFor = CustomException.class)
	public void cancelarInscripcion(Long idEduContinua) {
		// TODO Auto-generated method stub
		Participante pa = participanteDao.findParticipanteByIdEducacionContinuaAndIdPersona(idEduContinua, personaService.findPersonaLogueada().getId());
		if(pa==null) {
			throw new CustomException("La inscripción a eliminar no fue encontrada en la base de datos");
		}
		
		this.deleteInfoParticipante(pa.getId(), pa.getImagenCodigoQR(), pa.getTarjetaInscripcion());
		PersonaDto perDto=usuarioMapper.convertPersonaToPersonaDto(pa.getPersona());
		this.prepararEmailCancelarInscripcion(pa.getEducacionContinua(), perDto);
	}

	@Override
	public List<Participante> findAllPonentesOfOneEducacionContinua(String educacionContinua) {
		// TODO Auto-generated method stub
		return participanteDao.findAllPonentesOfOneEducacionContinua(educacionContinua);
	}
	
	@Override
	public List<Participante> findAllPonentesOfOneEducacionContinuaById(Long idEducacionContinua) {
		// TODO Auto-generated method stub
		return participanteDao.findAllPonentesOfOneEducacionContinuaById(idEducacionContinua);
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
		return educacionContinuaMapper.convertParticipantesToParticipanteDto(participanteDao.findAllParticipacionesActivasByParticipante(personaService.findPersonaLogueada().getNumeroDocumento()));
		
	}

	@Override
	public List<ParticipanteDto> findAllParticipantesByEducacionContinua(String eduContinua) {
		// TODO Auto-generated method stub
		return educacionContinuaMapper.convertParticipantesToParticipanteDto(participanteDao.findAllParticipantesByEducacionContinua(eduContinua));
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
			Participante participanteValidado = participanteDao.validarParticipanteYaInscrito(ponente.getEducacionContinua().getId(), ponente.getPersona().getId());
			if( participanteValidado != null) {
				throw new CustomException("La persona ya se encuentra registrada como " + participanteValidado.getTipoParticipante().getTipoParticipante(), HttpStatus.BAD_REQUEST);
			}
		}
		Persona p=personaDao.findPersonaById(ponente.getPersona().getId());
		if(p==null) {
			throw new CustomException("La persona no fue encontrada en la base de datos ", HttpStatus.BAD_REQUEST);
		}
		/*Persona p=personaDao.findById(ponente.getPersona().getId()).get();
		EducacionContinua ec=educacionContinuaDao.findById(ponente.getEducacionContinua().getId()).orElseThrow(() -> new CustomException("No fue posible encontrar la educación continua asociada en la base de datos"));
		TipoParticipante tp=tipoParticipanteDao.findById(ponente.getTipoParticipante().getId()).orElseThrow(() -> new CustomException("No fue posible encontrar el tipo de participante asociado"));
		/*preparando qr de inscripcion*/
		/*System.out.println("ponete");
		System.out.println(ponente.getId()==null);
		System.out.println(ponente.getId().equals(0L));*/
		if(ponente.getId()==null || ponente.getId().equals(0L)) {
			System.out.println("entra a crear");
			ParticipanteDto dto=this.saveParticipante(ponente.getEducacionContinua().getId(), p, "Ponente",0L);
			this.participanteDao.insertPonente(dto.getId(), ponente.getTema());
			return dto;
			/*System.out.println("entra al if");
			System.out.println(p!=null);
			System.out.println(ec!=null);
			if(p!=null && ec!=null) {
				System.out.println("entra al otro if");
				ponente.setPersona(p);
				ponente.setEducacionContinua(ec);
				ponente.setTipoParticipante(tp);
				
				String texto=ec.getProgramaResponsable().getCodigo()+"_"+ec.getTipoEduContinua().getId()+"_"+ec.getId()+"_"+ponente.getTipoParticipante().getId()+"_"+p.getNumeroDocumento();
				String nombreArchivo=p.getNumeroDocumento()+".png";
				ponente.setCodigoQR(Encrypt.encriptar(texto));
				System.out.println("guarda qr");
				try {
					System.out.println("encriptado: " + ponente.getCodigoQR());
					System.out.println("desencriptado: " + Encrypt.desencriptar(ponente.getCodigoQR()));
					ponente.setImagenCodigoQR(CodigoQR.generateQR(ec.getId()+"/qr-participantes/"+nombreArchivo, ponente.getCodigoQR()));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new CustomException("No fue posible generar el código QR del ponente, registro fallido", HttpStatus.BAD_REQUEST);
				}
			}*/
		}else {
			/*Ponente po=(Ponente)participanteDao.findById(ponente.getId()).orElseThrow(() -> new CustomException("No se encontró el ponente asociado en la base de datos"));
			po.setTema(ponente.getTema());
			ponente=po;*/
			System.out.println("entra a actualizar");
			participanteDao.updatePonente(ponente.getTema(),ponente.getId());
			return null;
		}
			
			/*participanteDao.save(ponente);*/
	}

	@Override
	public void saveTarjetaInscripcion(MultipartFile file, Long idParticipante) {
		// TODO Auto-generated method stub
		Participante p= participanteDao.findParticipanteById(idParticipante);
		String tarjetaInscripcion=Archivo.saveImageAboutEducacionContinua(file,"inscripcion_"+p.getPersona().getNumeroDocumento(),fileStorageService.dirEducacionContinua().resolve(String.valueOf(p.getEducacionContinua().getId())).resolve(fileStorageService.dirTarjetasInscripcion()));
		participanteDao.updateTarjetaInscripcion(tarjetaInscripcion, idParticipante);
		System.out.println("#############################################################################");
		System.out.println("tarjeta inscripcion");
		
		PersonaDto perDto=usuarioMapper.convertPersonaToPersonaDto(p.getPersona());
		
		
		prepararEmailInscripcion(p.getEducacionContinua(), perDto, tarjetaInscripcion);
		
		
	}

	public void prepararEmailInscripcion(EducacionContinua e, PersonaDto persona, String tarjetaInscripcion) {
		String strDateFormat = "dd/MM/yyyy hh:mm a"; // El formato de fecha está especificado  
	     SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
	     
		String contenido=String.format("La inscripción al %s %s se ha realizado exitosamente. "
				+ "Recuerde que la educación continua inica %s A continuación se adjunta su respectiva tarjeta de inscripción.",
				e.getTipoEduContinua().getTipoEduContinua(),e.getNombre(), objSDF.format(e.getFechaInicio())); 
		
		notificarViaEmail(persona.getEmail(), "Inscripción Realizada " + e.getNombre(), tarjetaInscripcion, contenido, persona.getNombre(),true);
	}
	
	public void prepararEmailCancelarInscripcion(EducacionContinua e, PersonaDto persona) {
		
	     
		String contenido=String.format("La inscripción al %s %s se ha cancelado exitosamente. ",
				e.getTipoEduContinua().getTipoEduContinua(),e.getNombre()); 
		
		notificarViaEmail(persona.getEmail(), "Inscripción Cancelada " + e.getNombre(), null, contenido, persona.getNombre(),false);
	}
	
	public void notificarViaEmail(String email, String asunto, String tarjetaInscripcion, String contenido, String nombreParticipante, boolean adjuntarImagen) {
		emailService.sendEmailInscripcion(email, asunto, tarjetaInscripcion, contenido, nombreParticipante,adjuntarImagen);
		
	}

	@Override
	public void certificarParticipante(MultipartFile file, Long idEduContinua, String token, String documentoParticipante) {
		// TODO Auto-generated method stub
		System.out.println(token);
		System.out.println(idEduContinua);
		String diplomaImagen=Archivo.saveImageAboutEducacionContinua(file,documentoParticipante,fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEduContinua)).resolve(fileStorageService.dirDiplomasParticipantes()));
		participanteDao.createCertificacionParticipante(true, diplomaImagen, new Date(), token);
		//prepararEmailInscripcion(p.getEducacionContinua(), perDto, tarjetaInscripcion);
		
	}
	
	
	@Override
	public ByteArrayInputStream generarPdfDiplomas(String token) {
		// TODO Auto-generated method stub
		Participante p= participanteDao.findByToken(token);
		return ManejoPdf.generarPDFDiplomas(p.getDiplomaParticipacion());
	}

	@Override
	public void cancelarCertificacionParticipante(String token) {
		// TODO Auto-generated method stub
		Participante p= participanteDao.findByToken(token);
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
		
		Participante p = participanteDao.findByToken(token);
		Persona per=p.getPersona();
		EducacionContinua e= p.getEducacionContinua();
		Diploma d= e.getDiploma();
		return educacionContinuaMapper.convertToMisCertificaciones(p.getId(), per.getId(),
				educacionContinuaMapper.convertFieldsFullName(per), p.getTipoParticipante().getTipoParticipante(),
				per.getNumeroDocumento(), per.getTipoDocumento().getTipoDocumento(), e.getTipoEduContinua().getTipoEduContinua(), 
				e.getId(), e.getNombre(), e.getFechaInicio(), e.getFechaFin(), p.getDiplomaParticipacion(), p.isAprobado(),
				p.getFechaGeneracionDiploma(),p.getToken(), d.getId(), d.getEstructuraDiploma(), d.getUpdatedAt());
		
	}

	@Override
	public void updateCertificado(MultipartFile file, String filename, String token, Long idEduContinua, String documentoParticipante) {
		// TODO Auto-generated method stub
		
		try {
			Archivo.deleteImage(filename);
			Archivo.saveImageAboutEducacionContinua(file,documentoParticipante, fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEduContinua)).resolve(fileStorageService.dirDiplomasParticipantes()));
			participanteDao.updateCertificacionParticipante(new Date(), token);
		}catch(Exception e) {
			System.out.println("No se encontró la imagen de la certificación del participante con token: " + token);
		}
	}

	@Override
	public List<PonenteDto> findPonentesByEduContinua(Long idEducacionContinua) {
		// TODO Auto-generated method stub
		return educacionContinuaMapper.convertListParticipantesToListPonentesDto(participanteDao.findAllPonentesOfOneEducacionContinuaById(idEducacionContinua));
	}
	

}
