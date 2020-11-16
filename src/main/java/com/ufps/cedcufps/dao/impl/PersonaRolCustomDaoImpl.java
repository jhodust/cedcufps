package com.ufps.cedcufps.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IPersonaRolCustomDao;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.PersonaRolDto;
import com.ufps.cedcufps.dto.PersonaRolEducacionContinuaDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.modelos.PersonaRol;

@Repository
public class PersonaRolCustomDaoImpl implements IPersonaRolCustomDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	@Override
	public void save(String authority, Long idPersona) {
		// TODO Auto-generated method stub
		em.createNativeQuery("insert into personas_x_roles (id_rol,id_persona) values ((select id from roles where authority = ?1), ?2)")
		.setParameter(1, authority)
		.setParameter(2, idPersona)
		.executeUpdate();

	}
	
	@Transactional
	@Override
	public void save(Long idRol, Long idPersona) {
		// TODO Auto-generated method stub
		em.createNativeQuery("insert into personas_x_roles (id_rol,id_persona) values ( ?1, ?2)")
		.setParameter(1, idRol)
		.setParameter(2, idPersona)
		.executeUpdate();

	}
	
	
	@Transactional
	@Override
	public void savePermisoParaEducacionContinua(String authority, Long idPersona, Long idPrograma) {
		// TODO Auto-generated method stub
		//borrar todos los permisos menos el de tomar asistencia y el de user (default)
		em.createNativeQuery("insert into roles_personas_programas_ec (id_rol,id_persona,id_programa) values ((select id from roles where authority = ?1), ?2, ?3)")
		.setParameter(1, authority)
		.setParameter(2, idPersona)
		.setParameter(3, idPrograma)
		.executeUpdate();
	}
	
	@Transactional
	@Override
	public void savePermisoParaTipoPersonas(String authority, Long idPersona, String tipoPersona) {
		// TODO Auto-generated method stub
		//borrar todos los permisos menos el de tomar asistencia y el de user (default)
		em.createNativeQuery("insert into rol_persona_tip_pers (id_rol,id_persona,id_tipo_persona) values ((select id from roles where authority = ?1), ?2, (select id from tipos_persona where tipo_persona = ?3))")
		.setParameter(1, authority)
		.setParameter(2, idPersona)
		.setParameter(3, tipoPersona)
		.executeUpdate();
	}
	
	@Transactional
	@Override
	public void savePermisoParaPersonaPrograma(String authority, Long idPersona, String tipoPersona, Long idPrograma) {
		// TODO Auto-generated method stub
		//borrar todos los permisos menos el de tomar asistencia y el de user (default)
		em.createNativeQuery("insert into rol_persona_programa_per (id_rol,id_persona,id_tipo_persona,id_programa) values ((select id from roles where authority = ?1), ?2, (select id from tipos_persona where tipo_persona = ?3), ?4)")
		.setParameter(1, authority)
		.setParameter(2, idPersona)
		.setParameter(3, tipoPersona)
		.setParameter(4, idPrograma)
		.executeUpdate();
	}
	
	@Transactional
	@Override
	public void savePermisoParaPersonaDepartamento(String authority, Long idPersona, String tipoPersona, Long idDepartamento) {
		// TODO Auto-generated method stub
		//borrar todos los permisos menos el de tomar asistencia y el de user (default)
		em.createNativeQuery("insert into rol_persona_depto_per (id_rol,id_persona,id_tipo_persona,id_depto) values ((select id from roles where authority = ?1), ?2, (select id from tipos_persona where tipo_persona = ?3), ?4)")
		.setParameter(1, authority)
		.setParameter(2, idPersona)
		.setParameter(3, tipoPersona)
		.setParameter(4, idDepartamento)
		.executeUpdate();
	}
	
	@Transactional
	@Modifying
	@Override
	public void deleteRolesDirPrograma(Long idPersona) {
		// TODO Auto-generated method stub
		//borrar todos los permisos menos el de tomar asistencia y el de user (default)
		em.createNativeQuery("delete from personas_x_roles where id_persona=?1 and id_rol NOT IN (select id from roles where authority='ROLE_ATTENDANCE' or authority='ROLE_USER')")
		.setParameter(1, idPersona)
		.executeUpdate();
	}

	@Transactional
	@Modifying
	@Override
	public boolean updateRolesPersona(Long idPersona,boolean hasPermisosEduC, boolean hasPermisosPer, boolean hasPermisosAtt,
			List<Long> idsProEduContinua, List<Long> idsProEst, List<Long> idsDeptoDoc, List<Long> idsProGrad,
			List<Long> idsEduAtt, boolean hasPermisosAdminvo, boolean hasPermisosExter, boolean isDirPrograma, boolean isDocente, Long idProgramaDirector) {
		// TODO Auto-generated method stub
		/************************************************ PERMISO PARA EDUCACION CONTINUA***********************************/
		/*******************************************************************************************************************/
		/*******************************************************************************************************************/
		
		if(isDirPrograma) {
			em.createNativeQuery("DELETE FROM roles_personas_programas_ec where id_rol= ? and id_persona= ? and id_programa != ?")
			.setParameter(1, 2L)
			.setParameter(2, idPersona)
			.setParameter(3, idProgramaDirector)
			.executeUpdate();
		}else {
			em.createNativeQuery("DELETE FROM personas_x_roles where id_rol= ? and id_persona= ?")
			.setParameter(1, 2L)
			.setParameter(2, idPersona)
			.executeUpdate();
		}
		
		if(hasPermisosEduC) {
			em.createNativeQuery("INSERT IGNORE INTO personas_x_roles (id_rol, id_persona) VALUES (?,?)")
		      .setParameter(1, 2L)
		      .setParameter(2, idPersona)
		      .executeUpdate();
			
			if(idsProEduContinua != null) {
				if(idsProEduContinua.size()>0) {
					StringBuilder query = new StringBuilder();
					 query.append("INSERT IGNORE INTO roles_personas_programas_ec (id_rol, id_persona, id_programa) VALUES");
					 int n=1;
					 for(Long i: idsProEduContinua) {
						 query.append("("+2L+","+idPersona+","+i+")");
						 if(n<idsProEduContinua.size()) {
							 query.append(",");
						 }
						 n++;
					 }
					Query q= em.createNativeQuery(query.toString());
					q.executeUpdate();
				}
			}
			
		
		}else {
			if(isDirPrograma) {
				throw new CustomException("No es posible quitarle permisos al director de programa de gestionar educaciones continuas");
			}
		}
		
		/***********************************************PERMISOS PARA PERSONAS***********************************************/
		/********************************************************************************************************************/
		/********************************************************************************************************************/
		
		if(isDirPrograma) {
			em.createNativeQuery("DELETE FROM rol_persona_tip_pers where id_rol= ? and id_persona= ? and id_tipo_persona = ?")
			.setParameter(1, 3L)
			.setParameter(2, idPersona)
			.setParameter(3, 2L)
			.executeUpdate();
			
			em.createNativeQuery("DELETE FROM rol_persona_programa_per where id_rol= ? and id_persona= ? and id_tipo_persona IN (?,?) and id_programa != ?")
			.setParameter(1, 3L)
			.setParameter(2, idPersona)
			.setParameter(3, 1L)
			.setParameter(4, 4L)
			.setParameter(5, idProgramaDirector)
			.executeUpdate();
			
		}else {
			em.createNativeQuery("DELETE FROM personas_x_roles where id_rol= ? and id_persona= ?")
			.setParameter(1, 3L)
			.setParameter(2, idPersona)
			.executeUpdate();
		}
		
		if(hasPermisosPer) {
			
			em.createNativeQuery("INSERT IGNORE INTO personas_x_roles (id_rol, id_persona) VALUES (?,?)")
		      .setParameter(1, 3L)
		      .setParameter(2, idPersona)
		      .executeUpdate();
			
			StringBuilder query = new StringBuilder();
			 query.append("INSERT IGNORE INTO rol_persona_tip_pers (id_rol, id_persona, id_tipo_persona) VALUES");
			 boolean insertAnterior=false;
			 if(idsProEst != null) {
				 if(idsProEst.size()>0 || isDirPrograma) {
						insertAnterior=true;
						query.append(" ("+3L+","+idPersona+","+1L+")");
					}
			 }
			
			 if(idsDeptoDoc != null) {
				 if(idsDeptoDoc.size()>0) {
						if(insertAnterior) {
							query.append(" ,");
						}
						insertAnterior=true;
						query.append(" ("+3L+","+idPersona+","+2L+")");
					}
			 }
			
			 
			if(hasPermisosAdminvo) {
				if(insertAnterior) {
					query.append(" ,");
				}
				insertAnterior=true;
				query.append(" ("+3L+","+idPersona+","+3L+")");
			}
			if(idsProGrad != null) {
				if(idsProGrad.size()>0 || isDirPrograma) {
					if(insertAnterior) {
						query.append(" ,");
					}
					insertAnterior=true;
					query.append(" ("+3L+","+idPersona+","+4L+")");
				}
			}
			
			
			if(hasPermisosExter) {
				if(insertAnterior) {
					query.append(" ,");
				}
				
				insertAnterior=true;
				query.append(" ("+3L+","+idPersona+","+5L+")");
			}
			
			Query q= em.createNativeQuery(query.toString());
			q.executeUpdate();
			
			if(idsProEst!=null) {
				if(idsProEst.size()>0) {
					query = new StringBuilder();
					 query.append("INSERT IGNORE INTO rol_persona_programa_per (id_rol, id_persona, id_tipo_persona, id_programa) VALUES");
					 int n=1;
					 for(Long i: idsProEst) {
						 query.append("("+3L+","+idPersona+","+1L+","+i+")");
						 if(n<idsProEst.size()) {
							 query.append(",");
						 }
						 n++;
					 }
					q= em.createNativeQuery(query.toString());
					q.executeUpdate();
				}
			}
			
			
			if(idsDeptoDoc!=null) {
				if(idsDeptoDoc.size()>0) {
					query = new StringBuilder();
					 query.append("INSERT IGNORE INTO rol_persona_depto_per (id_rol, id_persona, id_tipo_persona, id_depto) VALUES");
					 int n=1;
					 for(Long i: idsDeptoDoc) {
						 query.append("("+3L+","+idPersona+","+2L+","+i+")");
						 if(n<idsDeptoDoc.size()) {
							 query.append(",");
						 }
						 n++;
					 }
					q= em.createNativeQuery(query.toString());
					q.executeUpdate();
				}
			}
			
			
			if(idsProGrad!=null) {
				if(idsProGrad.size()>0) {
					query = new StringBuilder();
					 query.append("INSERT IGNORE INTO rol_persona_programa_per (id_rol, id_persona, id_tipo_persona, id_programa) VALUES");
					 int n=1;
					 for(Long i: idsProGrad) {
						 query.append("("+3L+","+idPersona+","+4L+","+i+")");
						 if(n<idsProGrad.size()) {
							 query.append(",");
						 }
						 n++;
					 }
					q= em.createNativeQuery(query.toString());
					q.executeUpdate();
				}
			
			}
				
		}else {
			if(isDirPrograma) {
				throw new CustomException("No es posible quitarle permisos al director de programa de gestionar personas");
			}
			
		}
		
		
		/************************************************PERMISO PARA ASISTENCIA**********************************************/
		/*********************************************************************************************************************/
		/********************************************************************************************************************/
		em.createNativeQuery("DELETE FROM personas_x_roles where id_rol= ? and id_persona= ?")
		.setParameter(1, 4L)
		.setParameter(2, idPersona)
		.executeUpdate();
		
		
		if(hasPermisosAtt) {
			if(idsEduAtt!=null) {
				if(idsEduAtt.size()>0) {
					em.createNativeQuery("INSERT IGNORE INTO personas_x_roles (id_rol, id_persona) VALUES (?,?)")
				      .setParameter(1, 4L)
				      .setParameter(2, idPersona)
				      .executeUpdate();
					
					StringBuilder query = new StringBuilder();
					 query.append("INSERT IGNORE INTO rol_persona_asistencia (id_rol, id_persona, id_edu_continua) VALUES");
					 int n=1;
					 for(Long i: idsEduAtt) {
						 query.append("("+4L+","+idPersona+","+i+")");
						 if(n<idsEduAtt.size()) {
							 query.append(",");
						 }
						 n++;
					 }
					Query q= em.createNativeQuery(query.toString());
					q.executeUpdate();
				}else {
					throw new CustomException("No se seleccionaron las educaciones continuas a las cual se les otorgará permisos de asistencia");
				}
			}
			
		
		}
		return true;
	}
}
