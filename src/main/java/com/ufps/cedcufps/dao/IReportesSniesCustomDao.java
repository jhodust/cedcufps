package com.ufps.cedcufps.dao;

import java.util.Date;
import java.util.List;

import com.ufps.cedcufps.dto.InformeCursosDto;
import com.ufps.cedcufps.dto.InformeDetalleEducacionContinuaDto;
import com.ufps.cedcufps.dto.InformeEducacionContinuaDto;
import com.ufps.cedcufps.dto.InformeParticipanteResponsableDto;
import com.ufps.cedcufps.dto.InformeSniesDto;

public interface IReportesSniesCustomDao {

	public List<InformeSniesDto>  findAllInformesSNIES(Long idPrograma);
	public List<InformeCursosDto>  informeExcelCursos(Date fechaInicio, Date fechaFin, Long idPrograma);
	public List<InformeEducacionContinuaDto>  informeExcelEduContinuaHoja1(Date fechaInicio, Date fechaFin, Long idPrograma);
	public List<InformeDetalleEducacionContinuaDto>  informeExcelEduContinuaHoja2(Date fechaInicio, Date fechaFin, Long idPrograma);
	public List<InformeParticipanteResponsableDto>  informeExcelParticipantesResponsables(Date fechaInicio, Date fechaFin, Long idPrograma);
	public List<Object[]>  dashboardConteoGeneral(String fechaInicio, String fechaFin, String idPrograma);
	public List<Object[]>  dashboardConteoGeneralTipoPersonas(String fechaInicio, String fechaFin, String idPrograma);
	public List<Object[]>  dashboardConteoGeneralGenero(String fechaInicio, String fechaFin, String idPrograma);

}
