package com.ufps.cedcufps.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IInformeSnies;
import com.ufps.cedcufps.dao.IReportesSniesCustomDao;
import com.ufps.cedcufps.dto.InformeCursosDto;
import com.ufps.cedcufps.dto.InformeDetalleEducacionContinuaDto;
import com.ufps.cedcufps.dto.InformeEducacionContinuaDto;
import com.ufps.cedcufps.dto.InformeParticipanteResponsableDto;
import com.ufps.cedcufps.dto.InformeSniesDto;
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
	private IPersonaService personaService;
	
	@Autowired
	private IProgramaService programaService;

	@Override
	public List<InformeSniesDto> findAll() {
		// TODO Auto-generated method stub
		Persona p=personaService.findPersonaLogueada();
		Long idPrograma=null;
		if(!personaService.isSuperAdmin(p) && personaService.isDirPrograma(p)) {
			idPrograma=(programaService.findProgramaByDirector(p.getId())).getId();
		}
		
		return reporteSniesCustomDao.findAllInformesSNIES(idPrograma);
	}


	public String generarReporteSNIESFormatoCurso(Date fechaInicio, Date fechaFin, Long idPrograma,String nombreMarcaTiempo) {
		// TODO Auto-generated method stub
		System.out.println("******************************PREPARANDO INFORME EXCEL 2******************");
		//ReportesExcel.reporteEducacionContinua("/formatos_reportes_excel/formato_educacion_continua.xlsx",educacionesContinuas,año);
		List<InformeCursosDto> result = reporteSniesCustomDao.informeExcelCursos(fechaInicio, fechaFin, idPrograma);
		return ReportesExcel.reporteCursos(result,nombreMarcaTiempo);
		
	}
	
	public String generarReporteSNIESFormatoEducacionContinua(Date fechaInicio, Date fechaFin, Long idPrograma,String nombreMarcaTiempo) {
		// TODO Auto-generated method stub
		System.out.println("******************************PREPARANDO INFORME EXCEL 1******************");
		//ReportesExcel.reporteCursos("/formatos_reportes_excel/formato_cursos.xlsx",educacionesContinuas,año);
		//ReportesExcel.reporteEducacionContinuaHoja1("/formatos_reportes_excel/nuevo.xlsx",educacionesContinuas);
		List<InformeEducacionContinuaDto> resultEduContinua = reporteSniesCustomDao.informeExcelEduContinuaHoja1(fechaInicio, fechaFin, idPrograma);
		List<InformeDetalleEducacionContinuaDto> resultParticipantesEduContinua = reporteSniesCustomDao.informeExcelEduContinuaHoja2(fechaInicio, fechaFin,idPrograma);
		return ReportesExcel.reporteEducacionContinua(resultEduContinua, resultParticipantesEduContinua, nombreMarcaTiempo);
	}
	
	public String generarReporteSNIESFormatoParticipantesResponsable(Date fechaInicio, Date fechaFin, Long idPrograma, String nombreMarcaTiempo) {
		// TODO Auto-generated method stub
		System.out.println("******************************PREPARANDO INFORME EXCEL 3******************");
		//ReportesExcel.reporteCursos("/formatos_reportes_excel/formato_cursos.xlsx",educacionesContinuas,año);
		//ReportesExcel.reporteEducacionContinuaHoja1("/formatos_reportes_excel/nuevo.xlsx",educacionesContinuas);
		List<InformeParticipanteResponsableDto> result = reporteSniesCustomDao.informeExcelParticipantesResponsables(fechaInicio, fechaFin,idPrograma);
		return ReportesExcel.reporteDocentesParticipantesResponsables(result, nombreMarcaTiempo);
	}

	@Override
	public void generarReporteSNIES(String fechaInicio, String fechaFin) {
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
		
		System.out.println("fechasRecibidas");
		System.out.println(fechaI);
		System.out.println(fechaF);
		Long marcaTiempo=System.currentTimeMillis();
		Persona p=personaService.findPersonaLogueada();
		Long idPrograma=null;
		if(!personaService.isSuperAdmin(p) && personaService.isDirPrograma(p)) {
			idPrograma=(programaService.findProgramaByDirector(p.getId())).getId();
		}
		String rutaInformeCurso=this.generarReporteSNIESFormatoCurso(fechaI, fechaF,idPrograma,String.valueOf(marcaTiempo));
		String rutaInformeEduContinua=this.generarReporteSNIESFormatoEducacionContinua(fechaI, fechaF,idPrograma,String.valueOf(marcaTiempo));
		String rutaInformeParticipantes=this.generarReporteSNIESFormatoParticipantesResponsable(fechaI, fechaF,idPrograma, String.valueOf(marcaTiempo));
	
		informeSniesDao.saveInformeSnies(fechaI, fechaF, rutaInformeCurso, rutaInformeEduContinua, rutaInformeParticipantes,idPrograma);
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
