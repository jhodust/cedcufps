package com.ufps.cedcufps.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.dto.DepartamentoDto;
import com.ufps.cedcufps.dto.DocenteDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.PersonaRolDto;
import com.ufps.cedcufps.dto.PersonaRolEducacionContinuaDto;
import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.modelos.PersonaRol;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.utils.RolUtil;
import com.ufps.cedcufps.utils.TipoPersonaUtil;

@Repository
public class PersonaRolCustomDaoImpl implements IPersonaRolCustomDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IProgramaDao programaDao;
	
	@Transactional
	@Override
	public void save(String authority, Long idPersona) {
		// TODO Auto-generated method stub
		em.createNativeQuery("insert IGNORE into personas_x_roles (id_rol,id_persona) values ((select id from roles where authority = ?1), ?2)")
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
		em.createNativeQuery("insert IGNORE into roles_personas_programas_ec (id_rol,id_persona,id_programa) values ((select id from roles where authority = ?1), ?2, ?3)")
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
		em.createNativeQuery("insert IGNORE into rol_persona_tip_pers (id_rol,id_persona,id_tipo_persona) values ((select id from roles where authority = ?1), ?2, (select id from tipos_persona where tipo_persona = ?3))")
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
		em.createNativeQuery("insert IGNORE into rol_persona_programa_per (id_rol,id_persona,id_tipo_persona,id_programa) values ((select id from roles where authority = ?1), ?2, (select id from tipos_persona where tipo_persona = ?3), ?4)")
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
		em.createNativeQuery("insert IGNORE into rol_persona_depto_per (id_rol,id_persona,id_tipo_persona,id_depto) values ((select id from roles where authority = ?1), ?2, (select id from tipos_persona where tipo_persona = ?3), ?4)")
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
		em.createNativeQuery("delete from personas_x_roles where id_persona=?1 and id_rol = (select id from roles where authority='ROLE_SNIES')")
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
	
	@Transactional
	@Modifying
	@Override
	public void asignarPermisosDirector(Long idDirPrograma, Long idPrograma) {
		
		
		/*****************rol para administrar eventos del programa del cual es director*****************/
		this.save("ROLE_MANAECCU",idDirPrograma);
		
		
		this.savePermisoParaEducacionContinua("ROLE_MANAECCU",idDirPrograma,idPrograma);
		
		/*
		 * ****************************rol para administrar personas ***********************
		 */
		this.save("ROLE_MANPEOPLE",idDirPrograma);
		this.savePermisoParaTipoPersonas("ROLE_MANPEOPLE", idDirPrograma, "Estudiante");
		this.savePermisoParaTipoPersonas("ROLE_MANPEOPLE", idDirPrograma, "Graduado");
		this.savePermisoParaTipoPersonas("ROLE_MANPEOPLE", idDirPrograma, "Administrativo");
		this.savePermisoParaTipoPersonas("ROLE_MANPEOPLE", idDirPrograma, "Externo");
		
		this.savePermisoParaPersonaPrograma("ROLE_MANPEOPLE", idDirPrograma, "Estudiante", idPrograma);
		this.savePermisoParaPersonaPrograma("ROLE_MANPEOPLE", idDirPrograma, "Graduado", idPrograma);
		
		/*
		 * ****************************rol para tomar asistencia app***********************
		 */
		this.save("ROLE_ATTENDANCE",idDirPrograma);
		
		/*
		 * ****************************rol para administrar informe snies***********************
		 */
		this.save("ROLE_SNIES",idDirPrograma);
		
		
	}

	@Transactional
	@Modifying
	@Override
	public void deleteRol(String authority, Long idPersona) {
		// TODO Auto-generated method stub
		em.createNativeQuery("delete from personas_x_roles where id_rol = (select id from roles where authority = ?1) and id_persona = ?2")
		.setParameter(1, authority)
		.setParameter(2, idPersona)
		.executeUpdate();
	}
	
	
	@Override
	public boolean findPermisosTipoPersona(Long idPersona, Long idTipoPersona) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("select count(*)=1")
				.append(" from rol_persona_tip_pers")
				.append(" where id_rol= ?1 and id_persona = ?2 and id_tipo_persona= ?3");
				
		Query q=em.createNativeQuery(query.toString())
				.setParameter(1, RolUtil.ROLE_MANPEOPLE)
				.setParameter(2, idPersona)
				.setParameter(3, idTipoPersona);
		List<Object > result=q.getResultList();
		
		return Integer.parseInt(String.valueOf(result.get(0)))==1;
		
	}

	@Override
	public List<ProgramaDto> findProgramasPermissionEstudiante(Long idPersonaGestionante, Long idPersonaGestionada){
		StringBuilder query = new StringBuilder();
		query.append("select p.id, p.programa, p.codigo")
				.append(" from rol_persona_programa_per rppp join programas p on rppp.id_programa=p.id ")
				.append(" where rppp.id_rol= ?1 and rppp.id_persona = ?2 and rppp.id_tipo_persona= ?3")
				.append(" UNION ")
				.append(" select p.id, p.programa, p.codigo from estudiantes e join programas p on e.id_programa = p.id")
				.append(" where e.id_persona= ?4 and e.estado = 1");
				
		Query q=em.createNativeQuery(query.toString())
				.setParameter(1, RolUtil.ROLE_MANPEOPLE)
				.setParameter(2, idPersonaGestionante)
				.setParameter(3, TipoPersonaUtil.ESTUDIANTE)
				.setParameter(4, idPersonaGestionada);
		List<Object[] > result=q.getResultList();
		List<ProgramaDto> list= new ArrayList<ProgramaDto>();
		for(Object[] o : result) {
			ProgramaDto dto= new ProgramaDto();
			dto.setId(Long.parseLong(String.valueOf(o[0])));
			dto.setPrograma(String.valueOf(o[1]));
			dto.setCodigo(String.valueOf(o[2]));
			list.add(dto);
		}
		return list;
	}
	
	@Override
	public List<ProgramaDto> findProgramasPermissionGraduados(Long idPersonaGestionante, Long idPersonaGestionada){
		StringBuilder query = new StringBuilder();
		query.append("select p.id, p.programa, p.codigo")
				.append(" from rol_persona_programa_per rppp join programas p on rppp.id_programa=p.id ")
				.append(" where rppp.id_rol= ?1 and rppp.id_persona = ?2 and rppp.id_tipo_persona= ?3")
				.append(" UNION ")
				.append(" select p.id, p.programa, p.codigo from graduados g join programas p on g.id_programa = p.id")
				.append(" where g.id_persona= ?4 and g.estado = 1");
				
		Query q=em.createNativeQuery(query.toString())
				.setParameter(1, RolUtil.ROLE_MANPEOPLE)
				.setParameter(2, idPersonaGestionante)
				.setParameter(3, TipoPersonaUtil.GRADUADO)
				.setParameter(4, idPersonaGestionada);
		List<Object[] > result=q.getResultList();
		List<ProgramaDto> list= new ArrayList<ProgramaDto>();
		for(Object[] o : result) {
			ProgramaDto dto= new ProgramaDto();
			dto.setId(Long.parseLong(String.valueOf(o[0])));
			dto.setPrograma(String.valueOf(o[1]));
			dto.setCodigo(String.valueOf(o[2]));
			list.add(dto);
		}
		return list;
	}
	
	@Override
	public List<DepartamentoDto> findDeptosPermissionDocentes(Long idPersonaGestionante, Long idPersonaGestionada){
		StringBuilder query = new StringBuilder();
		query.append("select d.id, d.departamento")
				.append(" from rol_persona_depto_per rpdp join departamentos d on rpdp.id_depto=d.id ")
				.append(" where rpdp.id_rol= ?1 and rpdp.id_persona = ?2 and rpdp.id_tipo_persona= ?3")
				.append(" UNION ")
				.append(" select d.id, d.departamento from docentes doc join departamentos d on doc.id_departamento=d.id")
				.append(" where doc.id_persona= ?4 and doc.estado = 1");
				
		Query q=em.createNativeQuery(query.toString())
				.setParameter(1, RolUtil.ROLE_MANPEOPLE)
				.setParameter(2, idPersonaGestionante)
				.setParameter(3, TipoPersonaUtil.DOCENTE)
				.setParameter(4, idPersonaGestionada);
		List<Object[] > result=q.getResultList();
		List<DepartamentoDto> list= new ArrayList<DepartamentoDto>();
		for(Object[] o : result) {
			DepartamentoDto dto= new DepartamentoDto();
			dto.setId(Long.parseLong(String.valueOf(o[0])));
			dto.setDepartamento(String.valueOf(o[1]));
			list.add(dto);
		}
		return list;
	}
	
}
