package com.ufps.cedcufps.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufps.cedcufps.dto.CertificacionDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Persona;

public interface IEducacionContinuaCustomDao {

	//public List<Long> listAllPossibleEducacionContinua(Long idPersona);
	public boolean  docenteHasPermission(String idAcceso, Long idPersona);
	
	public int registrarAsistencia(Long idJornada,Long idParticipante);
	
	public void saveEducacionContinua(EducacionContinuaWebDto dto, String createdBy);
	
	public void updateEducacionContinua(EducacionContinuaWebDto dto, String updatedBy);
	
	public Long insertNewTipoEduContinua(String tipoEduContinua, Boolean status);
	
	public EducacionContinuaWebDto findEduContinuaWebDtoByIdAcceso(String idAcceso);
	
	public List<CertificacionDto> findCertificaciones(String numDocumento);
	
	public Long[] listAllPossibleEducacionContinuaFiltro(String estado, Long idTipoEdC, Long idPrograma,
			Long idBeneficiarios);
	
	public List<EducacionContinuaAppDto> findEducacionesContinuasAGestionar(Long idPersona, boolean isSuperAdmin, boolean hasPermission);
	
	public List<EducacionContinuaAppDto> findEducacionesContinuasForApp(Long idPersona, boolean isSuperAdmin);
	
	public List<EducacionContinuaAppDto> findEduContinuasPermissionForAttendance(Long idPersona, boolean isAdmin, boolean isDirPrograma);
	
	public List<EducacionContinuaAppDto> findEduContinuasPermissionForAttendance(Long idPersona);
	
	public List<EducacionContinuaAppDto> findEduContinuasPermissionForAttendanceExceptDirectorPrograma(Long idPersona, Long idPrograma);
	
}
