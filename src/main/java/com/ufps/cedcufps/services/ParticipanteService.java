package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dao.IParticipanteDao;
import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dao.ITipoParticipanteDao;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.PonenteDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.mapper.IEducacionContinuaMapper;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Ponente;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.TipoParticipante;
import com.ufps.cedcufps.utils.Archivo;
import com.ufps.cedcufps.utils.CodigoQR;
import com.ufps.cedcufps.utils.Encrypt;

@Service
public class ParticipanteService implements IParticipanteService{
	
	@Autowired
	private IPersonaDao personaDao;
	
	@Autowired
	private IEducacionContinuaDao educacionContinuaDao;
	
	@Autowired
	private IParticipanteDao participanteDao;
	
	@Autowired
	private ITipoParticipanteDao tipoParticipanteDao;
	
	@Autowired
	private IEducacionContinuaMapper educacionContinuaMapper;
	
	@Autowired
	private IPersonaService personaService;
	
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
	public void save(Participante p) {
		// TODO Auto-generated method stub
		participanteDao.save(p);
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
		
		
	}
	
	private void deleteInfoParticipante(Long idParticipante, String codigoQr, String tarjetaInscripcion) {
		participanteDao.deleteParticipante(idParticipante);
		Archivo.deleteImage(codigoQr);
		Archivo.deleteImage(tarjetaInscripcion);
	}
	
	@Override
	@Transactional(rollbackFor = CustomException.class)
	public void cancelarInscripcion(Long idEduContinua) {
		// TODO Auto-generated method stub
		Participante pa = participanteDao.findParticipanteByIdEducacionContinuaAndIdPersona(idEduContinua, personaService.findPersonaLogueada().getId());
		if(pa==null) {
			throw new CustomException("La inscripción a eliminar no fue encontrada en la base de datos");
		}
		
		this.deleteInfoParticipante(pa.getId(), pa.getCodigoQR(), pa.getTarjetaInscripcion());
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
	public List<Participante> findAllParticipacionesActivasByParticipante(String numDocumento) {
		// TODO Auto-generated method stub
		return participanteDao.findAllParticipacionesActivasByParticipante(numDocumento);
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
	public void savePonente(Ponente ponente) {
		// TODO Auto-generated method stub
		if(ponente.getId() == null || ponente.getId().equals(0L) ) {
			Participante participanteValidado = participanteDao.validarParticipanteYaInscrito(ponente.getEducacionContinua().getId(), ponente.getPersona().getId());
			if( participanteValidado != null) {
				throw new CustomException("La persona ya se encuentra registrada como " + participanteValidado.getTipoParticipante().getTipoParticipante(), HttpStatus.BAD_REQUEST);
			}
		}
		
		Persona p=personaDao.findById(ponente.getPersona().getId()).get();
		EducacionContinua ec=educacionContinuaDao.findById(ponente.getEducacionContinua().getId()).orElseThrow(() -> new CustomException("No fue posible encontrar la educación continua asociada en la base de datos"));
		TipoParticipante tp=tipoParticipanteDao.findById(ponente.getTipoParticipante().getId()).orElseThrow(() -> new CustomException("No fue posible encontrar el tipo de participante asociado"));
		/*preparando qr de inscripcion*/
		System.out.println("ponete");
		System.out.println(ponente.getId()==null);
		System.out.println(ponente.getId().equals(0L));
		if(ponente.getId()==null || ponente.getId().equals(0L)) {
			System.out.println("entra al if");
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
			}
		}else {
			Ponente po=(Ponente)participanteDao.findById(ponente.getId()).orElseThrow(() -> new CustomException("No se encontró el ponente asociado en la base de datos"));
			po.setTema(ponente.getTema());
			ponente=po;
		}
			
			participanteDao.save(ponente);
	}

	@Override
	public void saveTarjetaInscripcion(MultipartFile file, Long idParticipante) {
		// TODO Auto-generated method stub
		Optional<Participante> p= participanteDao.findById(idParticipante);
		if(p!=null) {
			p.get().setTarjetaInscripcion(Archivo.saveImageAboutEducacionContinua(file,p.get().getEducacionContinua().getId()+"/tarjetas-inscripcion/inscripcion_"+p.get().getPersona().getNumeroDocumento()));
			participanteDao.save(p.get());
		}
	}

	

}
