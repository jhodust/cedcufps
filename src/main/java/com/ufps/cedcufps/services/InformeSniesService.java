package com.ufps.cedcufps.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IInformeSnies;
import com.ufps.cedcufps.dao.IProgramaCustomDao;
import com.ufps.cedcufps.dao.IReportesSniesCustomDao;
import com.ufps.cedcufps.dto.InformeCursosDto;
import com.ufps.cedcufps.dto.InformeDetalleEducacionContinuaDto;
import com.ufps.cedcufps.dto.InformeEducacionContinuaDto;
import com.ufps.cedcufps.dto.InformeParticipanteResponsableDto;
import com.ufps.cedcufps.dto.InformeSniesDto;
import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.InformeSnies;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.utils.ReportesExcel;

@Service
public class InformeSniesService implements IInformeSniesService {

	@Autowired
	private IInformeSnies informeSniesDao;
	
	@Autowired
	private IReportesSniesCustomDao reporteSniesCustomDao;
	
	@Autowired
	private IProgramaCustomDao programaCustomDao;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IProgramaService programaService;
	
	@Autowired
	private IFileStorageService fileStorageService;

	@Override
	public List<InformeSniesDto> findAll() {
		// TODO Auto-generated method stub
		Persona p=personaService.findPersonaLogueada();
		Long idPrograma=null;
		ProgramaDto dto=this.programaCustomDao.findProgramaDtoByDirector(p.getId());
		if(!personaService.isSuperAdmin(p) && dto!=null) {
			idPrograma=dto.getId();
		}
		
		return reporteSniesCustomDao.findAllInformesSNIES(idPrograma);
	}


	public String generarReporteSNIESFormatoCurso(Date fechaInicio, Date fechaFin, Long idPrograma,String nombreMarcaTiempo) {
		// TODO Auto-generated method stub
		//ReportesExcel.reporteEducacionContinua("/formatos_reportes_excel/formato_educacion_continua.xlsx",educacionesContinuas,año);
		List<InformeCursosDto> result = reporteSniesCustomDao.informeExcelCursos(fechaInicio, fechaFin, idPrograma);
		if(result.size()==0) {
			throw new CustomException("No se encontraron educaciones continuas en los criterios seleccionados");
		}
		return ReportesExcel.reporteCursos(result,nombreMarcaTiempo,
				fileStorageService.dirPlantillaCursos(), fileStorageService.dirReportesSnies());
		
	}
	
	public String generarReporteSNIESFormatoEducacionContinua(Date fechaInicio, Date fechaFin, Long idPrograma,String nombreMarcaTiempo) {
		// TODO Auto-generated method stub
		//ReportesExcel.reporteCursos("/formatos_reportes_excel/formato_cursos.xlsx",educacionesContinuas,año);
		//ReportesExcel.reporteEducacionContinuaHoja1("/formatos_reportes_excel/nuevo.xlsx",educacionesContinuas);
		List<InformeEducacionContinuaDto> resultEduContinua = reporteSniesCustomDao.informeExcelEduContinuaHoja1(fechaInicio, fechaFin, idPrograma);
		List<InformeDetalleEducacionContinuaDto> resultParticipantesEduContinua = reporteSniesCustomDao.informeExcelEduContinuaHoja2(fechaInicio, fechaFin,idPrograma);
		return ReportesExcel.reporteEducacionContinua(resultEduContinua, resultParticipantesEduContinua, nombreMarcaTiempo,
				fileStorageService.dirPlantillaEducacionContinua(), fileStorageService.dirReportesSnies());
	}
	
	public String generarReporteSNIESFormatoParticipantesResponsable(Date fechaInicio, Date fechaFin, Long idPrograma, String nombreMarcaTiempo) {
		// TODO Auto-generated method stub
		//ReportesExcel.reporteCursos("/formatos_reportes_excel/formato_cursos.xlsx",educacionesContinuas,año);
		//ReportesExcel.reporteEducacionContinuaHoja1("/formatos_reportes_excel/nuevo.xlsx",educacionesContinuas);
		List<InformeParticipanteResponsableDto> result = reporteSniesCustomDao.informeExcelParticipantesResponsables(fechaInicio, fechaFin,idPrograma);
		return ReportesExcel.reporteDocentesParticipantesResponsables(result, nombreMarcaTiempo, 
				fileStorageService.dirPlantillaParticipantesResponsables(), fileStorageService.dirReportesSnies());
	}

	@Transactional(rollbackOn = CustomException.class)
	@Override
	public void generarReporteSNIES(String fechaInicio, String fechaFin, String descripcion) {
		// TODO Auto-generated method stub
		Date fechaI = null;
		Date fechaF = null;
		try {
			String format="dd/MM/yyyy";
			fechaI=new SimpleDateFormat(format).parse(fechaInicio);
			fechaF=new SimpleDateFormat(format).parse(fechaFin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw new CustomException("Se ha generado un error con las fechas seleccionadas");
		}
		
		Long marcaTiempo=System.currentTimeMillis();
		Persona p=personaService.findPersonaLogueada();
		Long idPrograma=null;
		if(!personaService.isSuperAdmin(p) && personaService.isDirPrograma(p)) {
			idPrograma=(programaService.findProgramaDtoByDirector(p.getId())).getId();
		}
		String rutaInformeCurso=this.generarReporteSNIESFormatoCurso(fechaI, fechaF,idPrograma,String.valueOf(marcaTiempo));
		String rutaInformeEduContinua=this.generarReporteSNIESFormatoEducacionContinua(fechaI, fechaF,idPrograma,String.valueOf(marcaTiempo));
		String rutaInformeParticipantes=this.generarReporteSNIESFormatoParticipantesResponsable(fechaI, fechaF,idPrograma, String.valueOf(marcaTiempo));
	
		informeSniesDao.saveInformeSnies(fechaI, fechaF, rutaInformeCurso, rutaInformeEduContinua, rutaInformeParticipantes,idPrograma,descripcion);
	}


	@Override
	public List<Object[]> generarStatisticsConteoGeneralEduContinua(String fechaInicio, String fechaFin,
			String idPrograma) {
		
			return this.reporteSniesCustomDao.dashboardConteoGeneral(fechaInicio, fechaFin, idPrograma);
	
	}


	@Override
	public List<Object[]> generarStatisticsConteoGeneralPersonas(String fechaInicio, String fechaFin, String idPrograma) {
		// TODO Auto-generated method stub
		
			return this.reporteSniesCustomDao.dashboardConteoGeneralTipoPersonas(fechaInicio, fechaFin, idPrograma);
		
	}


	@Override
	public List<Object[]> generarStatisticsConteoGeneralGenero(String fechaInicio, String fechaFin, String idPrograma) {
		// TODO Auto-generated method stub
			
			return this.reporteSniesCustomDao.dashboardConteoGeneralGenero(fechaInicio, fechaFin, idPrograma);
		
	}
	
}
