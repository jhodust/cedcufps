package com.ufps.cedcufps.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.log.SysoLogger;
import com.ufps.cedcufps.dao.IClasificacionCineDao;
import com.ufps.cedcufps.dao.IDiplomaDao;
import com.ufps.cedcufps.dao.IDocenteDao;
import com.ufps.cedcufps.dao.IEducacionContinuaCustomDao;
import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dao.IJornadaDao;
import com.ufps.cedcufps.dao.IParticipanteDao;
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.dao.ITipoBeneficiarioDao;
import com.ufps.cedcufps.dao.ITipoEducacionContinuaDao;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.InfoEducacionContinuaDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.TipoBeneficiarioDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.mapper.IEducacionContinuaMapper;
import com.ufps.cedcufps.mapper.IJornadaMapper;
import com.ufps.cedcufps.modelos.ClasificacionCine;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.TipoBeneficiario;
import com.ufps.cedcufps.modelos.TipoEducacionContinua;
import com.ufps.cedcufps.utils.Archivo;
import com.ufps.cedcufps.utils.Encrypt;
import com.ufps.cedcufps.utils.ReportesExcel;
import com.ufps.cedcufps.utils.StatusEducacionContinua;

@Service
public class EducacionContinuaService implements IEducacionContinuaService{

	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IParticipanteDao  participanteDao;
	
	@Autowired
	private IEducacionContinuaDao educacionContinuaDao;
	
	@Autowired
	private ITipoEducacionContinuaDao tipoEducacionContinuaDao;
	
	@Autowired
	private IJornadaDao jornadaDao;
	
	@Autowired
	private IClasificacionCineDao clasificacionCineDao;
	
	@Autowired
	private ITipoBeneficiarioDao tipoBeneficiarioDao;
	
	@Autowired
	private IDiplomaDao diplomaDao;
	
	@Autowired
	private IEducacionContinuaMapper educacionContinuaMapper;
	
	@Autowired
	private IJornadaMapper jornadaMapper;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IEducacionContinuaCustomDao educacionContinuaCustomDao;
	
	@Autowired
	private IDocenteDao docenteDao;
	
	@Autowired
	private IProgramaDao programaDao;
	
	@Override
	public List<EducacionContinua> findAll() {
		// TODO Auto-generated method stub
		return (List<EducacionContinua>) educacionContinuaDao.findAll();
	}

	@Override
	public void save(EducacionContinua ec) {
		// TODO Auto-generated method stub
		educacionContinuaDao.save(ec);
	}

	@Override
	public Optional<EducacionContinua> findOne(Long id) {
		// TODO Auto-generated method stub
		return educacionContinuaDao.findById(id);
	}

	@Override
	public List<TipoEducacionContinua> findAllTiposEducacionContinua(Long id) {
		// TODO Auto-generated method stub
		return  tipoEducacionContinuaDao.findAllTipoEducacionContinua(id);
	}

	
	
	@Override
	public List<EducacionContinua> educacionContinuaRecientes() {
		// TODO Auto-generated method stub
		for(EducacionContinua e: educacionContinuaDao.educacionesContinuasRecientes()) {
			System.out.println(e.getNombre());
		}
		return educacionContinuaDao.educacionesContinuasRecientes();
	}
	
	@Override
	public Page<EducacionContinua> educacionContinuaNoTerminadas(Pageable pageable) {
		// TODO Auto-generated method stub
		return educacionContinuaDao.educacionesContinuasPanel(pageable);
	}

	@Override
	public List<EducacionContinua> educacionContinuasByTipoAndPrograma(Long idTipo, Long idPrograma) {
		// TODO Auto-generated method stub
		return educacionContinuaDao.educacionContinuasByTipoAndPrograma(idTipo, idPrograma);
	}


	@Async
	public void updateCodigoEducacionContinua(Long idEduContinua, Long idTipoEduContinua, Long idProgramaResponsable) {
		// TODO Auto-generated method stub
		String lastConsecutivo= educacionContinuaDao.findLastConsecutivo(idTipoEduContinua, idProgramaResponsable);
		String consecutivo=null;
		System.out.println("last consecutivo");
		System.out.println(lastConsecutivo);
		int cifrasMin=3;
		if(lastConsecutivo == null || lastConsecutivo.isBlank()) {
			consecutivo="001";
		}else {
			int tope=10^lastConsecutivo.length();
			int lastCon=Integer.parseInt(lastConsecutivo);
			int newCon=0;
			if(lastCon==(tope-1)) {
				newCon=lastCon+2;
				cifrasMin=lastConsecutivo.length()+1;
			}else {
				newCon=lastCon+1;
				cifrasMin=lastConsecutivo.length();
			}
			String pattern="%0"+cifrasMin+"d";
			consecutivo=String.format(pattern, newCon);
		}
		
		
		System.out.println("***********************************************************");
		System.out.println("actualizando consecutivos");
		System.out.println("***********************************************************");
		System.out.println(consecutivo);
		educacionContinuaDao.updateConsecutivo(consecutivo, idEduContinua);
	}

	@Override
	public String generarReporteSNIESFormatoEducacionContinua(String anio) {
		// TODO Auto-generated method stub
		List<EducacionContinua> educacionesContinuas=(List<EducacionContinua>)educacionContinuaDao.findAllEducacionContinuaByAnioReporte(Integer.parseInt(anio));
		System.out.println("******************************PREPARANDO INFORME EXCEL 1******************");
		return ReportesExcel.reporteEducacionContinua(educacionesContinuas,anio);
		//ReportesExcel.reporteCursos("/formatos_reportes_excel/formato_cursos.xlsx",educacionesContinuas,año);
		//ReportesExcel.reporteEducacionContinuaHoja1("/formatos_reportes_excel/nuevo.xlsx",educacionesContinuas);
		
	}
	
	@Override
	public String generarReporteSNIESFormatoCurso(String anio) {
		// TODO Auto-generated method stub
		List<EducacionContinua> educacionesContinuas=(List<EducacionContinua>)educacionContinuaDao.findAllEducacionContinuaByAnioReporte(Integer.parseInt(anio));
		System.out.println("******************************PREPARANDO INFORME EXCEL 2******************");
		//ReportesExcel.reporteEducacionContinua("/formatos_reportes_excel/formato_educacion_continua.xlsx",educacionesContinuas,año);
		return ReportesExcel.reporteCursos(educacionesContinuas,anio);
		//ReportesExcel.reporteEducacionContinuaHoja1("/formatos_reportes_excel/nuevo.xlsx",educacionesContinuas);
		
	}

	@Override
	public List<ClasificacionCine> findAllClasificacionCine() {
		// TODO Auto-generated method stub
		return (List<ClasificacionCine>) clasificacionCineDao.findAll();
	}

	@Override
	public List<TipoBeneficiario> findAllTipoBeneficiario() {
		// TODO Auto-generated method stub
		return (List<TipoBeneficiario>) tipoBeneficiarioDao.findAll();
	}

	@Override
	public List<EducacionContinua> findAllEducacionContinuaACargoDocente(String numDocumento) {
		// TODO Auto-generated method stub
		return educacionContinuaDao.findAllEducacionContinuaACargoDocente(numDocumento);
	}
	
	@Override
	public List<EducacionContinua> findAllEducacionContinuaACargoDirector(String numDocumento, Long idProgramaDirector) {
		// TODO Auto-generated method stub
		return educacionContinuaDao.findAllEducacionContinuaACargoDirector(numDocumento,idProgramaDirector);
	}

	@Override
	public EducacionContinua findOneByNombre(String educacionContinua) {
		// TODO Auto-generated method stub
		return educacionContinuaDao.findByNombre(educacionContinua);
	}
	
	@Override
	public EducacionContinuaWebDto editarEducacionContinuaByNombre(String educacionContinua) {
		// TODO Auto-generated method stub
		return educacionContinuaMapper.convertEducacionContinuaToEduContinuaWebDto( educacionContinuaDao.findByNombre(educacionContinua));
	}

	@Override
	public Diploma generarDiploma(Long idEducacionContinua) {
		// TODO Auto-generated method stub
		EducacionContinua e=educacionContinuaDao.findById(idEducacionContinua).get();
		
		if(e!=null) {
			Diploma d= e.getDiploma();
			if(d==null) {
				d=new Diploma();
			}
			diplomaDao.save(d);
			e.setDiploma(d);
			educacionContinuaDao.save(e);
			return d;
		}else {
			return null;
		}
		
		
	}

	@Override
	public List<JornadaAppDto> findJornadasByEducacionContinua(String eduContinua) {
		// TODO Auto-generated method stub
		return jornadaMapper.convertJornadasToJornadaAppDto(jornadaDao.findByNombreEducacionContinua(eduContinua));
		
	}

	
	
	@Override
	public List<EducacionContinuaAppDto> findAllEducacionesApp() {
		// TODO Auto-generated method stub
		List<EducacionContinua> ec= this.findAll();
		return educacionContinuaMapper.convertEducacionContinuaToApp(ec);
		
	}
	
	@Override
	public List<JornadaAppDto> findAllJornadasByEduContinuaApp(Long idEduContinua) {
		// TODO Auto-generated method stub
		
		return jornadaMapper.convertJornadasToJornadaAppDto(this.findOne(idEduContinua).get().getJornadas());
		
	}

	@Override
	public List<EducacionContinuaAppDto> findPosiblesEduContinuaGestionar() {
		// TODO Auto-generated method stub
		Persona p=personaService.findPersonaLogueada();
		if(personaService.isSuperAdmin()) {
			return educacionContinuaMapper.convertEducacionContinuaToApp((List<EducacionContinua>) educacionContinuaDao.findAll());
		}else if(personaService.hasPermissionForEduContinua(p.getId())){
			return educacionContinuaMapper.convertEducacionContinuaToApp(educacionContinuaDao.findByManyIds(educacionContinuaCustomDao.listAllPossibleEducacionContinua(p.getId())));
		}
		return null;
	}

	@Override
	public InfoEducacionContinuaDto detallesEducacionContinua(String nombreEducacionContinua) {
		// TODO Auto-generated method stub
		if(personaService.isSuperAdmin() || educacionContinuaCustomDao.docenteHasPermission(nombreEducacionContinua, personaService.findPersonaLogueada().getId())) {
			EducacionContinua e=educacionContinuaDao.findByNombre(nombreEducacionContinua);
			if(e!=null) {
				return educacionContinuaMapper.convertEducacionContinuaToEducacionContinuaWeb(e, true);
			}else {
				throw new CustomException("No se encontró la educación continua en la base de datos");
			}
			
		}else {
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("entra a ca");
			return educacionContinuaMapper.convertEducacionContinuaToEducacionContinuaWeb(null, false);
		}
		
	}

	@Override
	public Map<Integer, ParticipanteDto> tomarAsistencia(Long idEducacionContinua, Long idJornada, String qr) {
		// TODO Auto-generated method stub
		int codigoError=0;
		Map<Integer, ParticipanteDto> map= new HashMap<Integer, ParticipanteDto>();
		ParticipanteDto dto=null;
		if(participanteDao.validarQr(qr)==null) {
			codigoError=500;//qr inválido
		}else {
			Optional<Jornada> j= jornadaDao.findById(idJornada);
			if(j.isPresent()) {
				String texto=Encrypt.desencriptar(qr);
				String [] data=texto.split("_");
				System.out.println("dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				System.out.println(data[2]);
				System.out.println(data[4]);
				if(Long.parseLong(data[2])==idEducacionContinua) {
					System.out.println("entraaaaaaaaaaaaaaaaaaaaaaaaaaa");
					System.out.println(idEducacionContinua);
					System.out.println(data[4]);
					Participante p=participanteDao.validarParticipanteYaInscritoApp(idEducacionContinua, data[4]);
					if(p!=null) {
						System.out.println("existe participante....................................");
						
						if(educacionContinuaCustomDao.registrarAsistencia(idJornada, p.getId())==1) {
							System.out.println("registraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
							dto=this.educacionContinuaMapper.convertParticipanteToParticipanteDto(p);
							codigoError=200;
						}else {
							System.out.println("noooooooooooo registraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
							codigoError=0;
						}
					}else {
						System.out.println("codigo error 412");
						codigoError=412;//no se encontró participante inscrito
						
					}
				}else {
					codigoError=500;//qr inválido
				}
			}else {
				codigoError=400;//no se encontro jornada
			}
		}
		
		map.put(codigoError, dto);
		
		return map;
		
	}

	@Override
	public void saveEducacionContinua(MultipartFile file, String id, String nombre, String fechaInicio, String fechaFin,
			String duracion, String cantMaxParticipantes, String fechaLimInscripcion, String costoInscripcion, String lugar,
			String costoEducacionContinua, String requisitos, String objetivo, String porcentajeAsistencia,
			String resumen, String contenidoGeneral, String idTipoEduContinua, String tipoEduContinua,
			String idProgramaResponsable, String idDocenteResponsable, String idClasificacionCine, String consecutivo,
			String idTipoBeneficiarios) {
		System.out.println("tipos beneficiarios");
		System.out.println(idTipoBeneficiarios);
		EducacionContinuaWebDto dto = this.convertEducacionContinuaToDto(id, nombre, fechaInicio, fechaFin, 
				duracion, cantMaxParticipantes, fechaLimInscripcion, costoInscripcion, lugar, costoEducacionContinua, 
				requisitos, objetivo, porcentajeAsistencia, resumen, contenidoGeneral, idTipoEduContinua, tipoEduContinua,
				idProgramaResponsable, idDocenteResponsable, idClasificacionCine, consecutivo, idTipoBeneficiarios.split(","));
		
		if(this.validateAsociacionesEduContinua(dto)) {
			if(dto.getId() == 0L) {
				dto.setEstado(StatusEducacionContinua.STATUS_ACTIVO);
				educacionContinuaCustomDao.saveEducacionContinua(dto);
				System.out.println("dto registrado edu continua");
				System.out.println(dto.getId());
				if(dto.getConsecutivo()==null) {
					this.updateCodigoEducacionContinua(dto.getId(),dto.getIdTipoEduContinua(),dto.getIdProgramaResp());
				}
				Archivo.generarDirectoriosPropiosEducacionContinua(dto.getId());
				
			}else {
				educacionContinuaCustomDao.updateEducacionContinua(dto);
			}
			
			EducacionContinua ec= educacionContinuaDao.findOneEducacionContinua(dto.getId());
			saveBeneficiarios(dto.getTipoBeneficiarios(), ec.getId());
			guardarImagenPortada(ec.getId(),ec.getImagen(),file);
		}else {
			throw new CustomException("Se ha presentado un error al guardar la educación continua");
		}
		
		
	}

	

	
	public  void guardarImagenPortada(Long idEducacionContinua, String imagenActual, MultipartFile imagen) {
		if(imagen != null) {
			if(imagenActual!=null && !imagenActual.isEmpty()) {
				Archivo.deleteImage(imagenActual);
			}
			String rutaImagen=Archivo.saveImageAboutEducacionContinua(imagen,idEducacionContinua+"/portada");
			educacionContinuaDao.updateImagenPortada(rutaImagen,idEducacionContinua);
			System.out.println("actualizando guardar imagen edu continua");
		}
	}
	
	
	public void saveBeneficiarios(List<TipoBeneficiarioDto> idsTipoBeneficiarios, Long idEducacionContinua ) {
		System.out.println("guardando tipo beneficiarios");
		educacionContinuaDao.deleteBeneficiarios(idEducacionContinua);
		
		for(TipoBeneficiarioDto dto: idsTipoBeneficiarios) {
			System.out.println("en el for");
			System.out.println(dto.getId());
			try {
				educacionContinuaDao.insertBeneficiarios(idEducacionContinua, dto.getId());
			}catch(Exception ex) {
				this.logger.debug("error al insertar beneificiarios");
			}
			
		}
	}
	
	public boolean validateAsociacionesEduContinua(EducacionContinuaWebDto dto) {
		
		if(tipoEducacionContinuaDao.findTipoEducacionContinuaById(dto.getIdTipoEduContinua()) == null ){
			return false;
		}
		if(clasificacionCineDao.findClasificacionById(dto.getIdClasificacion()) == null) {
			return false;
		}
		System.out.println("id a buscar docente: " + dto.getIdDocenteResp());
		
		if( docenteDao.findOnlyDocente(dto.getIdDocenteResp()) == null) {
			return false;
		}
		if(programaDao.findProgramaById(dto.getIdProgramaResp()) == null) {
			return false;
		}
		return true;
	}

	
	
	public EducacionContinuaWebDto convertEducacionContinuaToDto(String id, String nombre, String fechaInicio, String fechaFin,
			String duracion, String cantMaxParticipantes, String fechaLimInscripcion, String costoInscripcion, String lugar,
			String costoEducacionContinua, String requisitos, String objetivo, String porcentajeAsistencia,
			String resumen, String contenidoGeneral, String idTipoEduContinua, String tipoEduContinua,
			String idProgramaResponsable, String idDocenteResponsable, String idClasificacionCine, String consecutivo,
			String[] idTipoBeneficiarios) {
		Date fechaInicioFormat = null;
		Date fechaFinFormat = null;
		Date fechaLimiteInscripcionFormat = null;
		try {
			String format="dd/MM/yyyy HH:mm";
			fechaInicioFormat = new SimpleDateFormat(format).parse(fechaInicio);
			fechaFinFormat= new SimpleDateFormat(format).parse(fechaInicio);
			fechaLimiteInscripcionFormat=new SimpleDateFormat(format).parse(fechaInicio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(idTipoEduContinua.equals("-1")) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("entra a insertar nueva edu continua");
			TipoEducacionContinua tec = tipoEducacionContinuaDao.findTipoEducacionContinuaByTipoEduContinua(tipoEduContinua.toUpperCase());
			if(tec == null) {
				idTipoEduContinua = String.valueOf(educacionContinuaCustomDao.insertNewTipoEduContinua(tipoEduContinua, false));
			}else {
				idTipoEduContinua =  String.valueOf(tec.getId());
			}
			
			System.out.println("id obtenido");
			System.out.println(idTipoEduContinua);
		}
		return educacionContinuaMapper.convertInfoToEduContinuaDto(id, nombre, fechaInicioFormat, 
				fechaFinFormat, duracion, cantMaxParticipantes, fechaLimiteInscripcionFormat, costoInscripcion, lugar, costoEducacionContinua, requisitos, objetivo, 
				porcentajeAsistencia, resumen, contenidoGeneral, idTipoEduContinua, tipoEduContinua, idProgramaResponsable,
				idDocenteResponsable, idClasificacionCine, consecutivo, idTipoBeneficiarios);
		
		
	}
	
	
	@Override
	public List<EducacionContinuaWebDto> findEducacionesContinuasBase(List<Programa> programasBase){
		
		Persona p= personaService.findPersonaLogueada();
		if(programasBase == null) {
			return educacionContinuaMapper.convertListEducacionContinuaToListEduContinuaWebDto(educacionContinuaDao.findAllEducacionContinuaACargoDocente(p.getNumeroDocumento()));
		}else {
			return new ArrayList<>();
		}
	}
	
	@Override
	public EducacionContinuaWebDto findEducacionContinuaBase(Long id){
		return educacionContinuaMapper.convertEducacionContinuaToEduContinuaWebDto(educacionContinuaDao.findEducacionContinuaById(id));
	}
	
	@Override
	public EducacionContinuaWebDto createEducacionContinua() {
		Docente d = (Docente)personaService.findPersonaLogueada();
		EducacionContinuaWebDto dto = new EducacionContinuaWebDto();
		if(!personaService.isSuperAdmin(d) && !personaService.isDirPrograma(d)) {
			dto.setNombreDocenteResp(educacionContinuaMapper.convertFieldsFullName(d));
			dto.setCodigoDocenteResp(d.getCodigo());
			dto.setIdDocenteResp(d.getId());
		}
		return dto;
	}
	

}
