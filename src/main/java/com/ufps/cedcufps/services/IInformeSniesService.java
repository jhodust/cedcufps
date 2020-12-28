package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import com.ufps.cedcufps.dto.InformeSniesDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.InformeSnies;
import com.ufps.cedcufps.utils.ReportesExcel;

public interface IInformeSniesService {

	public List<InformeSniesDto> findAll();
	
	
	public void generarReporteSNIES(String fechaInicio, String fechaFin);
	public List<Object[]> generarStatisticsConteoGeneralEduContinua(String fechaInicio, String fechaFin, String idPrograma);
	public List<Object[]> generarStatisticsConteoGeneralPersonas(String fechaInicio, String fechaFin, String idPrograma);
	public List<Object[]> generarStatisticsConteoGeneralGenero(String fechaInicio, String fechaFin, String idPrograma);
	
	
}
