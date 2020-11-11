package com.ufps.cedcufps.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;

public interface IEducacionContinuaDao extends JpaRepository<EducacionContinua, Long> {

	
	@Query(value= "SELECT * FROM educacion_continua ec where ec.estado='Activo' ORDER BY ec.fecha_inicio DESC LIMIT 5",nativeQuery = true)
	public List<EducacionContinua> educacionesContinuasRecientes();
	
	@Query(value= "SELECT ec from EducacionContinua ec where ec.estado!='Terminado' ORDER BY ec.fechaInicio DESC")
	public Page<EducacionContinua> educacionesContinuasPanel(Pageable pageable);
	
	
	@Query("select e from EducacionContinua e where e.tipoEduContinua.id = ?1 or e.programaResponsable.id = ?2 order by e.consecutivo ASC")
	public List<EducacionContinua> educacionContinuasByTipoAndPrograma(Long idTipo, Long idPrograma);
	
	@Query(value="select consecutivo from educacion_continua where id_tipo_educacion_continua = ?1 and id_programa = ?2 order by consecutivo DESC limit 1", nativeQuery=true)
	public String findLastConsecutivo(Long idTipo, Long idPrograma);
	
	@Query("select e from EducacionContinua e where year(e.fechaInicio) = ?1")
	public List<EducacionContinua> findAllEducacionContinuaByAnioReporte(int anio);
	
	@Query("select e from EducacionContinua e where e.docenteResponsable.numeroDocumento = ?1")
	public List<EducacionContinua> findAllEducacionContinuaACargoDocente(String numDocumento);
	
	@Query("select e from EducacionContinua e where e.docenteResponsable.numeroDocumento = ?1 or e.programaResponsable.id = ?2")
	public List<EducacionContinua> findAllEducacionContinuaACargoDirector(String numDocumento,Long idProgramaDirector);
	
	
	public EducacionContinua findByNombre(String educacionContinua);
	
	@Query(value="select e from EducacionContinua e where e.nombre = ?1 and e.fechaInicio = ?2")
	public EducacionContinua findByNombreAndFechaInicio(String educacionContinua, Date fechaInicio);
	
	@Query(value= "select * from educacion_continua e where e.id=?1", nativeQuery = true)
	public EducacionContinua findEducacionContinuaById(Long id);
	
	@Query("select e from EducacionContinua e where e.id IN (?1)")
	public List<EducacionContinua> findByManyIds(List<Long> idsEduContinua);
	
	@Query(value = "select e.* from rol_persona_asistencia rpa join educacion_continua e on rpa.id_edu_continua=e.id where rpa.id_persona= ?1", nativeQuery = true)
	public List<EducacionContinua> findEduContinuasPermissionForAttendance(Long idPersona);
	
	
	@Query(value = "select e.* from educacion_continua e  where e.id_programa=?1", nativeQuery = true)
	public List<EducacionContinua> findEduContinuasPermissionForAttendanceByPrograma(Long idPrograma);
	
	
	@Query(value = "select e.* from rol_persona_asistencia rpa join educacion_continua e on rpa.id_edu_continua=e.id where rpa.id_persona = ?1 and e.id_programa != ?2", nativeQuery = true)
	public List<EducacionContinua> findEduContinuasPermissionForAttendanceExceptDirectorPrograma(Long idPersona, Long idPrograma);
	
	@Transactional
	@Modifying
	@Query(value = "update educacion_continua set imagen = ?1 where id = ?2", nativeQuery = true)
	public void updateImagenPortada(String imagen, Long idEducacionContinua);
	
	@Transactional
	@Modifying
	@Query(value = "update educacion_continua set consecutivo = ?1 where id = ?2", nativeQuery = true)
	public void updateConsecutivo(String consecutivo, Long idEducacionContinua);
	
	
	@Query(value = "select e from EducacionContinua e where e.id= ?1")
	public EducacionContinua findOneEducacionContinua (Long idEducacionContinua);
	
	@Transactional
	@Modifying
	@Query(value = "insert into educacion_continua_tipo_beneficiario (id_educacion_continua, id_tipo_beneficiario) values (?1, ?2)", nativeQuery = true)
	public void insertBeneficiarios(Long idEducacionContinua, Long idTipoBeneficiario);
	
	@Transactional
	@Modifying
	@Query(value = "delete from educacion_continua_tipo_beneficiario where id_educacion_continua = ?1", nativeQuery = true)
	public void deleteBeneficiarios(Long idEducacionContinua);
	
	@Query("select distinct e from EducacionContinua e where e.programaResponsable.id = ?1 ")
	public List<EducacionContinua> findEducacionContinuaByIdPrograma(Long idPrograma);
	
}
