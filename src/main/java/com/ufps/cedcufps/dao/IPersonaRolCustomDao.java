package com.ufps.cedcufps.dao;

import java.util.List;

import com.ufps.cedcufps.modelos.PersonaRol;

public interface IPersonaRolCustomDao {

	public void save(PersonaRol p);
	
	public void deleteRolesDirPrograma(Long idPersona);
	
	public boolean updateRolesPersona(Long idPersona, boolean hasPermisosEduC, boolean hasPermisosPer, boolean hasPermisosAtt,
			List<Long> idsProEduContinua, List<Long> idsProEst, List<Long> idsDeptoDoc, 
			List<Long> idsProGrad, List<Long> idsEduAtt, boolean hasPermisosAdminvo, boolean hasPermisosExter, boolean isDirPrograma, 
			boolean isDocente,Long idProgramaDirector);
	
}
