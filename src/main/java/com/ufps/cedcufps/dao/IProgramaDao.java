package com.ufps.cedcufps.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Programa;

@Repository
public interface IProgramaDao extends PagingAndSortingRepository<Programa, Long>{

	@Query("select p from Programa p where p.facultad.facultad = ?1")
	public List<Programa> findByFacultad(String facultad);
	
	@Query("select p from Programa p where p.facultad.facultad = ?1")
	public Page<Programa> findByFacultad(String facultad,Pageable pageable);
	
	@Query(value="select count(p.id) from programas p where p.id != ?1 and p.programa = ?2", nativeQuery=true)
	public int cantidadProgramaExistentes(Long id, String programa);
	
	@Query("select p from Programa p where p.id = ?1")
	public Programa findProgramaById(Long id);
	
	@Query("select p from Programa p where p.directorPrograma.id = ?1")
	public Programa findByDirector(Long idDir);
	
	@Query(value="select count(p.id) from programas p where p.id != ?1 and p.codigo = ?2", nativeQuery=true)
	public int cantidadCodigosExistentes(Long idPro, String codigo);
	
	@Query(value="select count(p.id) from programas p where p.id != ?1 and p.id_director = ?2", nativeQuery=true)
	public int cantidadDirProgramaExistentes(Long idPro, Long idDir);
	
	@Modifying
	@Query(value="update programas set id_director=null where id != ?1 and id_director = ?2", nativeQuery=true)
	public void desvincularDirectorPrograma(Long idPro, Long idDir);
	
	@Transactional
	@Modifying
	@Query(value="update programas set id_director= ?1  where id = ?2 ", nativeQuery=true)
	public void vincularDirectorPrograma(Long idDir, Long idPro);
	
	@Query("select p from Programa p where p.id != ?1 and p.directorPrograma.id = ?2")
	public Programa findOthersProgramasByDirector(Long idPro, Long idDir);
	
	@Query
	public Programa findByCodigo(String codigo);
	
	@Query("select p from Programa p where p.id NOT IN (?1)")
	public List<Programa> findProgramasExceptSome(List<Long> idProgramas);
	
	@Query(value = "select p.* from roles_personas_programas_ec rppe join programas p on rppe.id_programa=p.id where rppe.id_persona = ?1 and rppe.id_programa != ?2",nativeQuery = true)
	public List<Programa> findProgramasPermisosEduContinuaForDirProgramaExceptOwn(Long idDirector, Long idPrograma);
	
	@Query(value = "select p.* from roles_personas_programas_ec rppe join programas p on rppe.id_programa=p.id where rppe.id_persona = ?1",nativeQuery = true)
	public List<Programa> findProgramasPermisosEduContinuaForDocEstAdminvo(Long idPersona);
	
	@Query(value = "select p.* from rol_persona_programa_per rppp join programas p on rppp.id_programa=p.id join tipos_persona tp on tp.id=rppp.id_tipo_persona where rppp.id_persona = ?1 and rppp.id_programa != ?2 and tp.tipo_persona='Estudiante' ",nativeQuery = true)
	public List<Programa> findProgramasPermisosEstudiantesForDirProgramaExceptOwn(Long idDirector, Long idPrograma);
	
	@Query(value = "select p.* from rol_persona_programa_per rppp join programas p on rppp.id_programa=p.id join tipos_persona tp on tp.id=rppp.id_tipo_persona where rppp.id_persona = ?1 and tp.tipo_persona='Estudiante' ",nativeQuery = true)
	public List<Programa> findProgramasPermisosEstudiantesForDocEstAdminvo(Long idPeronsa);
	
	@Query(value = "select p.* from rol_persona_programa_per rppp join programas p on rppp.id_programa=p.id join tipos_persona tp on tp.id=rppp.id_tipo_persona where rppp.id_persona = ?1 and rppp.id_programa != ?2 and tp.tipo_persona='Graduado'",nativeQuery = true)
	public List<Programa> findProgramasPermisosGraduadosForDirProgramaExceptOwn(Long idDirector, Long idPrograma);
	
	@Query(value = "select p.* from rol_persona_programa_per rppp join programas p on rppp.id_programa=p.id join tipos_persona tp on tp.id=rppp.id_tipo_persona where rppp.id_persona = ?1 and tp.tipo_persona='Graduado'",nativeQuery = true)
	public List<Programa> findProgramasPermisosGraduadosForDocEstAdminvo(Long idDirector);
	
	@Query(value = "select p.* from programas p where p.id in (select rpp.id_programa from  roles_personas_programas_ec rpp where id_persona = ?1)",nativeQuery = true)
	public List<Programa> findProgramasOfPermissionEdCPersona(Long idPersona);
	
	@Query(value = "select p.* from programas p where p.id in (select rpp.id_programa from  roles_personas_programas_ec rpp where id_persona = ?1) or p.id in (select distinct e.id_programa from educacion_continua e where e.id_docente = ?1) ",nativeQuery = true)
	public List<Programa> findProgramasEducacionContinuaBase(Long idPersona);
	
	@Query(value = "select p.* from programas p where p.id_director=?1 ",nativeQuery = true)
	public List<Programa> findProgramasDashboard(Long idPersona);
}
