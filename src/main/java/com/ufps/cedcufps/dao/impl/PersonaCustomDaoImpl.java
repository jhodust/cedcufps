package com.ufps.cedcufps.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IPersonaCustomDao;
import com.ufps.cedcufps.dto.DocenteDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.mapper.IPersonaMapper;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.EstadoCivil;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Genero;
import com.ufps.cedcufps.modelos.Graduado;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.PersonaRol;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.modelos.TipoDocumento;
import com.ufps.cedcufps.utils.RolUtil;
import com.ufps.cedcufps.utils.TipoPersonaUtil;

@Repository
public class PersonaCustomDaoImpl implements IPersonaCustomDao {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IPersonaMapper personaMapper;
	
	@Transactional
	@Override
	public int saveEstudiante(Estudiante e) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("insert into estudiantes (id_persona,id_programa,codigo) values (?,?,?)")
				.setParameter(1, e.getId())
				.setParameter(2, e.getPrograma().getId())
				.setParameter(3, e.getCodigo())
				.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public int saveDocente(Docente d) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("insert into docentes (id_persona,id_departamento,codigo) values (?,?,?)")
				.setParameter(1, d.getId())
				.setParameter(2, d.getDepartamento().getId())
				.setParameter(3, d.getCodigo())
				.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public int saveAdministrativo(Administrativo a) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("insert into administrativos (id_persona,dependencia,cargo) values (?,?,?)")
				.setParameter(1, a.getId())
				.setParameter(2, a.getDependencia())
				.setParameter(3, a.getCargo())
				.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public int saveGraduado(Graduado g) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("insert into graduados (id_persona,id_programa,anio) values (?,?,?)")
				.setParameter(1, g.getId())
				.setParameter(2, g.getPrograma().getId())
				.setParameter(3, g.getAnio())
		 		.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public int saveExterno(Externo e) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("insert into externos (id_persona,profesion,empresa) values (?,?,?)")
				.setParameter(1, e.getId())
				.setParameter(2, e.getProfesion())
				.setParameter(3, e.getEmpresa())
				.executeUpdate();
		
	}

	@Override
	public List<Long> listAllPossiblePeople(Long idPersona) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("(SELECT r.id_tipo_persona from rol_persona_tip_pers r where r.id_persona=?1 and r.id_rol=(select ro.id from roles  ro where ro.authority='ROLE_MANPEOPLE'))");
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		List<Object> result=q.getResultList();
		query = new StringBuilder();
		
		int n=1;
		for(Object i: result) {
			if(String.valueOf(i).equalsIgnoreCase("1")) {
				query.append("(select p.id");
				query.append(" from personas p join estudiantes e on p.id=e.id_persona ");
				query.append(" where e.id_programa IN (select rppp.id_programa from rol_persona_programa_per rppp");
				query.append(" where rppp.id_tipo_persona='1' and rppp.id_rol=(select ro.id from roles ro where ro.authority='ROLE_MANPEOPLE') and rppp.id_persona=?1))" );
			}
			if(String.valueOf(i).equalsIgnoreCase("2")) {
				query.append("(select p.id");
				query.append(" from personas p join docentes d on p.id=d.id_persona");
				query.append(" where d.id_departamento IN (select rppp.id_depto from rol_persona_depto_per rppp");
				query.append(" where rppp.id_tipo_persona='2' and rppp.id_rol=(select ro.id from roles ro where ro.authority='ROLE_MANPEOPLE') and rppp.id_persona=?1))" );
				
			}
			if(String.valueOf(i).equalsIgnoreCase("3")) {
				query.append("(select p.id from personas p join administrativos a on p.id=a.id_persona)");
				
			}
			if(String.valueOf(i).equalsIgnoreCase("4")) {
				query.append("(select p.id");
				query.append(" from personas p join graduados g on p.id=g.id_persona ");
				query.append(" where g.id_programa IN (select rppp.id_programa from rol_persona_programa_per rppp");
				query.append(" where rppp.id_tipo_persona='4' and rppp.id_rol=(select ro.id from roles ro where ro.authority='ROLE_MANPEOPLE') and rppp.id_persona=?1))" );
				
			}
			if(String.valueOf(i).equalsIgnoreCase("5")) {
				query.append("(select p.id from personas p join externos ex on p.id=ex.id_persona)");
				
			}
			
			if(n!=result.size()) {
				query.append(" UNION DISTINCT");
			}
			n++;
		}
		
		q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		
		List<Long> list=new  ArrayList<>();
		for(Object o: q.getResultList()) {
			list.add(Long.parseLong(String.valueOf(o)));
		}
		return list;
	}
	
	@Override
	public Estudiante findOnlyEstudiante(Long idPersona) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("select  e.id_persona,e.codigo, e.id_programa, p.programa from estudiantes e join programas p on e.id_programa=p.id where e.id_persona=?1 and e.estado");
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		List<Object []> result=q.getResultList();
		Estudiante e=null;
		if(result.size()>0) {
			e=new Estudiante();
			e.setId(Long.parseLong(String.valueOf(result.get(0)[0])));
			e.setCodigo(String.valueOf(result.get(0)[1]));
			Programa p= new Programa();
			p.setId(Long.parseLong(String.valueOf(result.get(0)[2])));
			p.setPrograma(String.valueOf(result.get(0)[3]));
			e.setPrograma(p);
		}
		return e;
		
	}
	
	@Override
	public Docente findOnlyDocente(Long idPersona) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("select  d.id_persona, d.codigo, d.id_departamento, depto.departamento from docentes d join departamentos depto on d.id_departamento=depto.id where d.id_persona=?1 and d.estado");
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		List<Object []> result=q.getResultList();
		Docente d=null;
		if(result.size()>0) {
			d=new Docente();
			d.setId(Long.parseLong(String.valueOf(result.get(0)[0])));
			d.setCodigo(String.valueOf(result.get(0)[1]));
			Departamento depto= new Departamento();
			depto.setId(Long.parseLong(String.valueOf(result.get(0)[2])));
			depto.setDepartamento(String.valueOf(result.get(0)[3]));
			d.setDepartamento(depto);
		}
		return d;
		
	}
	
	@Override
	public Graduado findOnlyGraduado(Long idPersona) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("select  g.id_persona,g.anio, g.id_programa, p.programa from graduados g join programas p on g.id_programa=p.id where g.id_persona=?1 and g.estado");
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		List<Object []> result=q.getResultList();
		Graduado g=null;
		if(result.size()>0) {
			g=new Graduado();
			g.setId(Long.parseLong(String.valueOf(result.get(0)[0])));
			g.setAnio(String.valueOf(result.get(0)[1]));
			Programa p= new Programa();
			p.setId(Long.parseLong(String.valueOf(result.get(0)[2])));
			p.setPrograma(String.valueOf(result.get(0)[3]));
			g.setPrograma(p);
		}
		return g;
		
	}
	
	@Override
	public Administrativo findOnlyAdministrativo(Long idPersona) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("select id_persona, dependencia, cargo from administrativos a where a.id_persona=?1 and a.estado");
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		List<Object []> result=q.getResultList();
		Administrativo a=null;
		if(result.size()>0) {
			a=new Administrativo();
			a.setId(Long.parseLong(String.valueOf(result.get(0)[0])));
			a.setDependencia(String.valueOf(result.get(0)[1]));
			a.setCargo(String.valueOf(result.get(0)[2]));
		}
		return a;
		
	}
	
	@Override
	public Externo findOnlyExterno(Long idPersona) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("select  id_persona, profesion, empresa from externos e where id_persona=?1 and e.estado");
		Query q=em.createNativeQuery(query.toString());
		q.setParameter(1, idPersona);
		List<Object []> result=q.getResultList();
		Externo e=null;
		if(result.size()>0) {
			e=new Externo();
			e.setId(Long.parseLong(String.valueOf(result.get(0)[0])));
			e.setProfesion(String.valueOf(result.get(0)[1]));
			e.setEmpresa(String.valueOf(result.get(0)[2]));
		}
		return e;
		
	}
	
	
	@Override
	public List<DocenteDto> findAllDocentesActivos() {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id, d.codigo, CONCAT(COALESCE(p.primer_nombre,''),' ',COALESCE(p.segundo_nombre,''),' ',")
				.append("COALESCE(p.primer_apellido,''),' ',COALESCE(p.segundo_apellido,'')) from docentes as d")
				.append(" join personas as p on d.id_persona=p.id")
				.append(" where d.estado");
		Query q=em.createNativeQuery(query.toString());
		List<Object []> result=q.getResultList();
		List<DocenteDto> list= new ArrayList<DocenteDto>();
		for(Object[] o: result) {
			DocenteDto dto=new DocenteDto();
			dto.setId(Long.parseLong(String.valueOf(o[0])));
			dto.setCodigo(String.valueOf(o[1]));
			dto.setNombre(String.valueOf(o[2]));
			list.add(dto);
		}
		
		return list;
		
	}

	@Override
	public Docente findDocenteResponsable(Long idPersona) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido, d.codigo")
				.append(" from docentes as d")
				.append(" join personas as p on d.id_persona=p.id")
				.append(" where p.id = ?1");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, idPersona);
		List<Object[]> result= q.getResultList();
		if(result.size()==1) {
			Docente d= new Docente();
			d.setId(Long.parseLong(String.valueOf(result.get(0)[0])));
			d.setPrimerNombre(String.valueOf(result.get(0)[1]));
			d.setSegundoNombre(String.valueOf(result.get(0)[2]));
			d.setPrimerApellido(String.valueOf(result.get(0)[3]));
			d.setSegundoApellido(String.valueOf(result.get(0)[4]));
			d.setCodigo(String.valueOf(result.get(0)[5]));
			return d;
		}
		
		return null;
	}
	
	@Override
	public Docente findDocenteDirPrograma(Long idPrograma, Long idPersona) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido, d.codigo")
				.append(" from programas pro join docentes d on pro.id_director=d.id_persona" )
				.append(" join personas as p on d.id_persona=p.id")
				.append(" where pro.id = ?1 and p.id = ?2");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, idPrograma).setParameter(2, idPersona);
		List<Object[]> result= q.getResultList();
		if(result.size()==1) {
			Docente d= new Docente();
			d.setId(Long.parseLong(String.valueOf(result.get(0)[0])));
			d.setPrimerNombre(String.valueOf(result.get(0)[1]));
			d.setSegundoNombre(String.valueOf(result.get(0)[2]));
			d.setPrimerApellido(String.valueOf(result.get(0)[3]));
			d.setSegundoApellido(String.valueOf(result.get(0)[4]));
			d.setCodigo(String.valueOf(result.get(0)[5]));
			return d;
		}
		
		return null;
	}

	@Override
	public List<Persona> findPersonasList(List<Long> ids, boolean superAdmin) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id as id_persona, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido,")
			 .append(" p.id_acceso, p.is_estudiante, p.is_docente, p.is_administrativo, p.is_graduado, p.is_externo,")
			 .append(" p.email, tp.id as id_tipo_documento, tp.tipo_documento, p.direccion, p.fecha_expedicion_documento,")
			 .append(" p.fecha_nacimiento, p.id_departamento_nacimiento, p.id_municipio_nacimiento, p.id_pais_nacimiento,")
			 .append(" p.ids_tipo_persona, p.numero_documento, p.telefono,ec.id as id_estado_civil, ec.estado_civil,  ")
			 .append(" g.id as id_genero, g.genero, tp.descripcion")
				.append(" from personas as p")
				.append(" left join tipos_documento tp on p.id_tipo_documento=tp.id")
				.append(" left join estados_civiles ec on p.id_estado_civil=ec.id")
				.append(" left join generos g on p.id_genero=g.id");
		
		if(!superAdmin) {
			query.append(" where p.id in ( ?1 )");
		}
				
				
		
		Query q= em.createNativeQuery(query.toString());
		
		if(!superAdmin) {
			q.setParameter(1, ids);
		}
		
		List<Object[]> result= q.getResultList();
		
		
		
		return personaMapper.convertListObjectToListPersonas(result);
	}
	
	
	
	@Override
	public Persona findPersonaById(Long id) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id as id_persona, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido,")
			 .append(" p.id_acceso, p.is_estudiante, p.is_docente, p.is_administrativo, p.is_graduado, p.is_externo,")
			 .append(" p.email, tp.id as id_tipo_documento, tp.tipo_documento, p.direccion, p.fecha_expedicion_documento,")
			 .append(" p.fecha_nacimiento, p.id_departamento_nacimiento, p.id_municipio_nacimiento, p.id_pais_nacimiento,")
			 .append(" p.ids_tipo_persona, p.numero_documento, p.telefono,ec.id as id_estado_civil, ec.estado_civil,  ")
			 .append(" g.id as id_genero, g.genero, tp.descripcion")
				.append(" from personas as p")
				.append(" left join tipos_documento tp on p.id_tipo_documento=tp.id")
				.append(" left join estados_civiles ec on p.id_estado_civil=ec.id")
				.append(" left join generos g on p.id_genero=g.id")
			.append(" where p.id = ?1 ");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, id);
		List<Object[]> result= q.getResultList();
		if(result.size()==1) {
			return personaMapper.convertObjectToPersona(result.get(0));
		}
		return null;
	}
	
	
	@Override
	public Persona findPersonaByIdAcceso(String idAcceso) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id as id_persona, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido,")
			 .append(" p.id_acceso, p.is_estudiante, p.is_docente, p.is_administrativo, p.is_graduado, p.is_externo,")
			 .append(" p.email, tp.id as id_tipo_documento, tp.tipo_documento, p.direccion, p.fecha_expedicion_documento,")
			 .append(" p.fecha_nacimiento, p.id_departamento_nacimiento, p.id_municipio_nacimiento, p.id_pais_nacimiento,")
			 .append(" p.ids_tipo_persona, p.numero_documento, p.telefono,ec.id as id_estado_civil, ec.estado_civil,  ")
			 .append(" g.id as id_genero, g.genero, tp.descripcion")
				.append(" from personas as p")
				.append(" left join tipos_documento tp on p.id_tipo_documento=tp.id")
				.append(" left join estados_civiles ec on p.id_estado_civil=ec.id")
				.append(" left join generos g on p.id_genero=g.id")
			.append(" where p.id_acceso = ?1 ");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, idAcceso);
		List<Object[]> result= q.getResultList();
		if(result.size()==1) {
			return personaMapper.convertObjectToPersona(result.get(0));
		}
		return null;
	}
	
	@Override
	public Persona findPersonaByNumeroDocumento(String documento) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id as id_persona, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido,")
			 .append(" p.id_acceso, p.is_estudiante, p.is_docente, p.is_administrativo, p.is_graduado, p.is_externo,")
			 .append(" p.email, tp.id as id_tipo_documento, tp.tipo_documento, p.direccion, p.fecha_expedicion_documento,")
			 .append(" p.fecha_nacimiento, p.id_departamento_nacimiento, p.id_municipio_nacimiento, p.id_pais_nacimiento,")
			 .append(" p.ids_tipo_persona, p.numero_documento, p.telefono,ec.id as id_estado_civil, ec.estado_civil,  ")
			 .append(" g.id as id_genero, g.genero, tp.descripcion")
				.append(" from personas as p")
				.append(" left join tipos_documento tp on p.id_tipo_documento=tp.id")
				.append(" left join estados_civiles ec on p.id_estado_civil=ec.id")
				.append(" left join generos g on p.id_genero=g.id")
			.append(" where p.numero_documento = ?1 ");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, documento);
		List<Object[]> result= q.getResultList();
		if(result.size()==1) {
			return personaMapper.convertObjectToPersona(result.get(0));
		}
		return null;
	}
	
	
	@Override
	public Persona findPersonaByEmail(String email) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id as id_persona, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido,")
			 .append(" p.id_acceso, p.is_estudiante, p.is_docente, p.is_administrativo, p.is_graduado, p.is_externo,")
			 .append(" p.email, tp.id as id_tipo_documento, tp.tipo_documento, p.direccion, p.fecha_expedicion_documento,")
			 .append(" p.fecha_nacimiento, p.id_departamento_nacimiento, p.id_municipio_nacimiento, p.id_pais_nacimiento,")
			 .append(" p.ids_tipo_persona, p.numero_documento, p.telefono,ec.id as id_estado_civil, ec.estado_civil,  ")
			 .append(" g.id as id_genero, g.genero, tp.descripcion")
				.append(" from personas as p")
				.append(" left join tipos_documento tp on p.id_tipo_documento=tp.id")
				.append(" left join estados_civiles ec on p.id_estado_civil=ec.id")
				.append(" left join generos g on p.id_genero=g.id")
			.append(" where p.email = ?1 ");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, email);
		List<Object[]> result= q.getResultList();
		if(result.size()==1) {
			return personaMapper.convertObjectToPersona(result.get(0));
		}
		return null;
	}
	
	
	
	
	@Override
	public List<Persona> findPersonasByNumeroDocumento(String documento) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id as id_persona, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido,")
			 .append(" p.id_acceso, p.is_estudiante, p.is_docente, p.is_administrativo, p.is_graduado, p.is_externo,")
			 .append(" p.email, tp.id as id_tipo_documento, tp.tipo_documento, p.direccion, p.fecha_expedicion_documento,")
			 .append(" p.fecha_nacimiento, p.id_departamento_nacimiento, p.id_municipio_nacimiento, p.id_pais_nacimiento,")
			 .append(" p.ids_tipo_persona, p.numero_documento, p.telefono,ec.id as id_estado_civil, ec.estado_civil,  ")
			 .append(" g.id as id_genero, g.genero, tp.descripcion")
				.append(" from personas as p")
				.append(" join tipos_documento tp on p.id_tipo_documento=tp.id")
				.append(" join estados_civiles ec on p.id_estado_civil=ec.id")
				.append(" join generos g on p.id_genero=g.id")
			.append(" where p.numero_documento = ?1 ");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, documento);
		List<Object[]> result= q.getResultList();
		return personaMapper.convertListObjectToListPersonas(result);
	}
	
	
	@Override
	public List<Persona> findPersonasByEmail(String email) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id as id_persona, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido,")
			 .append(" p.id_acceso, p.is_estudiante, p.is_docente, p.is_administrativo, p.is_graduado, p.is_externo,")
			 .append(" p.email, tp.id as id_tipo_documento, tp.tipo_documento, p.direccion, p.fecha_expedicion_documento,")
			 .append(" p.fecha_nacimiento, p.id_departamento_nacimiento, p.id_municipio_nacimiento, p.id_pais_nacimiento,")
			 .append(" p.ids_tipo_persona, p.numero_documento, p.telefono,ec.id as id_estado_civil, ec.estado_civil,  ")
			 .append(" g.id as id_genero, g.genero, tp.descripcion")
				.append(" from personas as p")
				.append(" left join tipos_documento tp on p.id_tipo_documento=tp.id")
				.append(" left join estados_civiles ec on p.id_estado_civil=ec.id")
				.append(" left join generos g on p.id_genero=g.id")
			.append(" where p.email = ?1 ");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, email);
		List<Object[]> result= q.getResultList();
		return personaMapper.convertListObjectToListPersonas(result);
		
	}
	
	
	
	@Override
	public List<Persona> findManyPeople(List<Long> idsPersonas) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id as id_persona, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido,")
			 .append(" p.id_acceso, p.is_estudiante, p.is_docente, p.is_administrativo, p.is_graduado, p.is_externo,")
			 .append(" p.email, tp.id as id_tipo_documento, tp.tipo_documento, p.direccion, p.fecha_expedicion_documento,")
			 .append(" p.fecha_nacimiento, p.id_departamento_nacimiento, p.id_municipio_nacimiento, p.id_pais_nacimiento,")
			 .append(" p.ids_tipo_persona, p.numero_documento, p.telefono,ec.id as id_estado_civil, ec.estado_civil,  ")
			 .append(" g.id as id_genero, g.genero, tp.descripcion")
				.append(" from personas as p")
				.append(" left join tipos_documento tp on p.id_tipo_documento=tp.id")
				.append(" left join estados_civiles ec on p.id_estado_civil=ec.id")
				.append(" left join generos g on p.id_genero=g.id")
			.append(" where p.id in ( ?1 ) ");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, idsPersonas);
		List<Object[]> result= q.getResultList();
		return personaMapper.convertListObjectToListPersonas(result);
		
	}
	
	
	@Override
	public List<Persona> findPosiblePonenteByNombre(String nombre) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id as id_persona, p.primer_nombre, p.segundo_nombre, p.primer_apellido, p.segundo_apellido,")
			 .append(" p.id_acceso, p.is_estudiante, p.is_docente, p.is_administrativo, p.is_graduado, p.is_externo,")
			 .append(" p.email, tp.id as id_tipo_documento, tp.tipo_documento, p.direccion, p.fecha_expedicion_documento,")
			 .append(" p.fecha_nacimiento, p.id_departamento_nacimiento, p.id_municipio_nacimiento, p.id_pais_nacimiento,")
			 .append(" p.ids_tipo_persona, p.numero_documento, p.telefono,ec.id as id_estado_civil, ec.estado_civil,  ")
			 .append(" g.id as id_genero, g.genero, tp.descripcion")
				.append(" from personas as p")
				.append(" left join tipos_documento tp on p.id_tipo_documento=tp.id")
				.append(" left join estados_civiles ec on p.id_estado_civil=ec.id")
				.append(" left join generos g on p.id_genero=g.id")
			.append(" where CONCAT(p.primer_nombre,' ', p.segundo_nombre,' ',p.primer_apellido,' ', p.segundo_apellido) like ?1");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, nombre);
		List<Object[]> result= q.getResultList();
		return personaMapper.convertListObjectToListPersonas(result);
		
	}
	
	
	
	@Override
	public boolean hasPermisos(Long idPersona, Long rol) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT count(*) ")
				.append(" from personas_x_roles ")
			.append(" where id_persona= ?1 and id_rol = ?2");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, idPersona).setParameter(2, rol);
		List<Object> result= q.getResultList();
		
		return Integer.parseInt(String.valueOf(result.get(0)))==1;
		
	}
	
	
	@Override
	public boolean hasPermisosOnlyMyEdC(Long idPersona, Long rol) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT count(*) ")
				.append(" from roles_personas_programas_ec ")
			.append(" where id_persona= ?1 and id_rol = ?2");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, idPersona).setParameter(2, rol);
		List<Object> result= q.getResultList();
		
		return Integer.parseInt(String.valueOf(result.get(0)))==0;
		
	}
	
	
	@Override
	public List<PersonaRol> findRolesPersona(Long id) {
		// TODO Auto-generated method 
		StringBuilder query = new StringBuilder();
		query.append("SELECT r.id, r.authority")
				.append(" from personas_x_roles as pr")
				.append(" join roles r on pr.id_rol=r.id")
			.append(" where pr.id_persona = ?1 ");
		
		Query q= em.createNativeQuery(query.toString()).setParameter(1, id);
		List<Object[]> result= q.getResultList();
		List<PersonaRol> rolesPersona=new ArrayList<PersonaRol>();
		for(Object[] object: result) {
			PersonaRol pr= new PersonaRol();
			Rol r=new Rol();
			r.setId(Long.parseLong(String.valueOf(object[0])));
			r.setAuthority(String.valueOf(object[1]));
			pr.setRol(r);
			rolesPersona.add(pr);
		}
		return rolesPersona;
	}

}
