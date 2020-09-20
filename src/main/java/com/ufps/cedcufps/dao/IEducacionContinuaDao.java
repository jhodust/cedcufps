package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;

public interface IEducacionContinuaDao extends CrudRepository<EducacionContinua, Long> {

	@Query("select count(e) from EducacionContinua e where e.tipoEduContinua.id = '1'")
	public int cantidadCursos();
	
	@Query("select count(e) from EducacionContinua e where e.tipoEduContinua.id = '4'")
	public int cantidadTalleres();
	
	@Query("select count(e) from EducacionContinua e where e.tipoEduContinua.id = '2'")
	public int cantidadDiplomados();
	
	@Query("select count(e) from EducacionContinua e where e.tipoEduContinua.id = '3'")
	public int cantidadSimposios();
	
	@Query("select count(e) from EducacionContinua e where e.tipoEduContinua.id = '5'")
	public int cantidadSeminarios();
	
	@Query("select count(e) from EducacionContinua e where e.tipoEduContinua.id = '6'")
	public int cantidadCongresos();
	
	@Query(value= "SELECT * FROM educacion_continua ec where ec.estado='Activo' ORDER BY ec.fecha_inicio DESC LIMIT 5",nativeQuery = true)
	public List<EducacionContinua> educacionesContinuasRecientes();
	
	@Query(value= "SELECT ec from EducacionContinua ec where ec.estado!='Terminado' ORDER BY ec.fechaInicio DESC")
	public Page<EducacionContinua> educacionesContinuasPanel(Pageable pageable);
	
	
	@Query("select e from EducacionContinua e where e.tipoEduContinua.id = ?1 or e.programaResponsable.id = ?2 order by e.consecutivo ASC")
	public List<EducacionContinua> educacionContinuasByTipoAndPrograma(Long idTipo, Long idPrograma);
	
	@Query("select e from EducacionContinua e where year(e.fechaInicio) = ?1")
	public List<EducacionContinua> findAllEducacionContinuaByAñoReporte(int año);
	
	@Query("select e from EducacionContinua e where e.docenteResponsable.numeroDocumento = ?1")
	public List<EducacionContinua> findAllEducacionContinuaACargoDocente(String numDocumento);
	
	@Query("select e from EducacionContinua e where e.docenteResponsable.numeroDocumento = ?1 or e.programaResponsable.id = ?2")
	public List<EducacionContinua> findAllEducacionContinuaACargoDirector(String numDocumento,Long idProgramaDirector);
	
	
	public EducacionContinua findByNombre(String educacionContinua);
	
	@Query("select e from EducacionContinua e where e.id IN (?1)")
	public List<EducacionContinua> findByManyIds(List<Long> idsEduContinua);
	
	@Query(value = "select e.* from rol_persona_asistencia rpa join educacion_continua e on rpa.id_edu_continua=e.id where rpa.id_persona= ?1", nativeQuery = true)
	public List<EducacionContinua> findEduContinuasPermissionForAttendance(Long idPersona);
	
	
	@Query(value = "select e.* from educacion_continua e  where e.id_programa=?1", nativeQuery = true)
	public List<EducacionContinua> findEduContinuasPermissionForAttendanceByPrograma(Long idPrograma);
	
	
	@Query(value = "select e.* from rol_persona_asistencia rpa join educacion_continua e on rpa.id_edu_continua=e.id where rpa.id_persona = ?1 and e.id_programa != ?2", nativeQuery = true)
	public List<EducacionContinua> findEduContinuasPermissionForAttendanceExceptDirectorPrograma(Long idPersona, Long idPrograma);
	
	
}
