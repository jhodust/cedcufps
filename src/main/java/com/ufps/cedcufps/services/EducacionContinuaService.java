package com.ufps.cedcufps.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dao.IClasificacionCineDao;
import com.ufps.cedcufps.dao.IDiplomaDao;
import com.ufps.cedcufps.dao.IEducacionContinuaCustomDao;
import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dao.IJornadaDao;
import com.ufps.cedcufps.dao.ITipoBeneficiarioDao;
import com.ufps.cedcufps.dao.ITipoEducacionContinuaDao;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.InfoEducacionContinuaDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.mapper.IEducacionContinuaMapper;
import com.ufps.cedcufps.mapper.IJornadaMapper;
import com.ufps.cedcufps.modelos.ClasificacionCine;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.TipoBeneficiario;
import com.ufps.cedcufps.modelos.TipoEducacionContinua;
import com.ufps.cedcufps.utils.Archivo;
import com.ufps.cedcufps.utils.ReportesExcel;

@Service
public class EducacionContinuaService implements IEducacionContinuaService{

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
	public List<TipoEducacionContinua> findAllTiposEducacionContinua() {
		// TODO Auto-generated method stub
		return (List<TipoEducacionContinua>) tipoEducacionContinuaDao.findAll();
	}

	@Override
	public int cantidadCursos() {
		// TODO Auto-generated method stub
		return educacionContinuaDao.cantidadCursos();
	}

	@Override
	public int cantidadTalleres() {
		// TODO Auto-generated method stub
		return educacionContinuaDao.cantidadTalleres();
	}

	@Override
	public int cantidadDiplomados() {
		// TODO Auto-generated method stub
		return educacionContinuaDao.cantidadDiplomados();
	}

	@Override
	public int cantidadSeminarios() {
		// TODO Auto-generated method stub
		return educacionContinuaDao.cantidadSeminarios();
	}
	
	@Override
	public int cantidadCongresos() {
		// TODO Auto-generated method stub
		return educacionContinuaDao.cantidadCongresos();
	}
	
	@Override
	public int cantidadSimposios() {
		// TODO Auto-generated method stub
		return educacionContinuaDao.cantidadSimposios();
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
	@Override
	public void updateCodigoEducacionContinua(EducacionContinua ec) {
		// TODO Auto-generated method stub
		List<EducacionContinua> edcs=educacionContinuaDao.educacionContinuasByTipoAndPrograma(ec.getTipoEduContinua().getId(), ec.getProgramaResponsable().getId());
		int i=0;
		String consecutivoAnterior="001";
		for(EducacionContinua e: edcs) {
			if(i==0) {
				if(e.getConsecutivo() == null) {
					e.setConsecutivo("001");
				}
			}else {
				e.setConsecutivo(String.format("%03d", Integer.parseInt(consecutivoAnterior)+1));
			}
			
			consecutivoAnterior=e.getConsecutivo();
			edcs.set(i, e);
			i++;
		}
		
		System.out.println("***********************************************************");
		System.out.println("actualizando consecutivos");
		System.out.println("***********************************************************");
		
		educacionContinuaDao.saveAll(edcs);
	}

	@Override
	public String generarReporteSNIESFormatoEducacionContinua(String año) {
		// TODO Auto-generated method stub
		List<EducacionContinua> educacionesContinuas=(List<EducacionContinua>)educacionContinuaDao.findAllEducacionContinuaByAñoReporte(Integer.parseInt(año));
		System.out.println("******************************PREPARANDO INFORME EXCEL 1******************");
		return ReportesExcel.reporteEducacionContinua(educacionesContinuas,año);
		//ReportesExcel.reporteCursos("/formatos_reportes_excel/formato_cursos.xlsx",educacionesContinuas,año);
		//ReportesExcel.reporteEducacionContinuaHoja1("/formatos_reportes_excel/nuevo.xlsx",educacionesContinuas);
		
	}
	
	@Override
	public String generarReporteSNIESFormatoCurso(String año) {
		// TODO Auto-generated method stub
		List<EducacionContinua> educacionesContinuas=(List<EducacionContinua>)educacionContinuaDao.findAllEducacionContinuaByAñoReporte(Integer.parseInt(año));
		System.out.println("******************************PREPARANDO INFORME EXCEL 2******************");
		//ReportesExcel.reporteEducacionContinua("/formatos_reportes_excel/formato_educacion_continua.xlsx",educacionesContinuas,año);
		return ReportesExcel.reporteCursos(educacionesContinuas,año);
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
	public List<Jornada> findJornadasByEducacionContinua(Long idEduContinua) {
		// TODO Auto-generated method stub
		return jornadaDao.findByIdEducacionContinua(idEduContinua);
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
		if(personaService.isSuperAdmin()) {
			return educacionContinuaMapper.convertEducacionContinuaToApp((List<EducacionContinua>) educacionContinuaDao.findAll());
		}else if(personaService.hasPermissionForEduContinua()){
			return educacionContinuaMapper.convertEducacionContinuaToApp(educacionContinuaDao.findByManyIds(educacionContinuaCustomDao.listAllPossibleEducacionContinua(personaService.findPersonaLogueada().getId())));
		}
		return null;
	}

	@Override
	public InfoEducacionContinuaDto detallesEducacionContinua(String nombreEducacionContinua) {
		// TODO Auto-generated method stub
		if(educacionContinuaCustomDao.docenteHasPermission(nombreEducacionContinua, personaService.findPersonaLogueada().getId())) {
			EducacionContinua e=educacionContinuaDao.findByNombre(nombreEducacionContinua);
			if(e!=null) {
				return educacionContinuaMapper.convertEducacionContinuaToEducacionContinuaWeb(e, true);
			}else {
				throw new CustomException("No se encontró la educación continua en la base de datos");
			}
			
		}else {
			return educacionContinuaMapper.convertEducacionContinuaToEducacionContinuaWeb(null, false);
		}
		
	}

	

	

	

	

	

}
