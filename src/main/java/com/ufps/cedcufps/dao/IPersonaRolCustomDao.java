package com.ufps.cedcufps.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.ufps.cedcufps.dto.PersonaRolDto;
import com.ufps.cedcufps.dto.PersonaRolEducacionContinuaDto;
import com.ufps.cedcufps.modelos.PersonaRol;

public interface IPersonaRolCustomDao {

	public void save(String authority, Long idPersona);
	public void savePermisoParaEducacionContinua(String authority, Long idPersona, Long idPrograma);
	public void savePermisoParaTipoPersonas(String authority, Long idPersona, String tipoPersona);
	public void savePermisoParaPersonaPrograma(String authority, Long idPersona, String tipoPersona, Long idPrograma);
	public void savePermisoParaPersonaDepartamento(String authority, Long idPersona, String tipoPersona, Long idDepartamento);
	
	public void deleteRolesDirPrograma(Long idPersona);
	
	public boolean updateRolesPersona(Long idPersona, boolean hasPermisosEduC, boolean hasPermisosPer, boolean hasPermisosAtt,
			List<Long> idsProEduContinua, List<Long> idsProEst, List<Long> idsDeptoDoc, 
			List<Long> idsProGrad, List<Long> idsEduAtt, boolean hasPermisosAdminvo, boolean hasPermisosExter, boolean isDirPrograma, 
			boolean isDocente,Long idProgramaDirector);
	
}
