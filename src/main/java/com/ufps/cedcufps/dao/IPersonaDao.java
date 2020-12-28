package com.ufps.cedcufps.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Persona;


public interface IPersonaDao extends JpaRepository<Persona, Long>, DataTablesRepository<Persona, Long> {

	@Query("select count(p) from Persona p where p.id != ?1 and p.numeroDocumento=?2")
	public int validarDocumentoRegistrado(Long idPersona, String numeroDocumento);
	
	@Query("select count(p) from Persona p where p.numeroDocumento != ?1 and p.email = ?2")
	public int validarEmailRegistrado(String numeroDocumento, String email);
	
	@Query(value="select p from Persona p where p.id = ?1")
	public Persona findPersonaById(Long id);
	
	@Query(value="select p from Persona p where p.idAcceso = ?1")
	public Persona findPersonaByIdAcceso(String idAcceso);
	
	public Persona findByUsername(String username);
	
	@Query(value="select p from Persona p where p.email = ?1")
	public Persona findPersonaByEmail(String email);
	
	@Query("select p from Persona p where p.id IN (?1)")
	public List<Persona> findManyPeople(List<Long> idsPersonas);
	
	@Query(value = "select count(rptp.id_persona) from rol_persona_tip_pers rptp join tipos_persona tp on tp.id=rptp.id_tipo_persona where rptp.id_persona=?1 and tp.tipo_persona='Administrativo'", nativeQuery=true)
	public int hasPermissionForAdminvos(Long idPersona);
	
	@Query(value = "select count(rptp.id_persona) from rol_persona_tip_pers rptp join tipos_persona tp on tp.id=rptp.id_tipo_persona where rptp.id_persona=?1 and tp.tipo_persona='Externo'", nativeQuery=true)
	public int hasPermissionForExternos(Long idPersona);
	
	@Transactional
	@Modifying
	@Query(value = "update personas set id_tipo_documento = ?1, numero_documento = ?2, fecha_expedicion_documento=?3, primer_nombre=?4, "
			+ "segundo_nombre=?5, primer_apellido=?6, segundo_apellido=?7, id_genero=?8, id_estado_civil=?9,"
			+ "fecha_nacimiento=?10, id_pais_nacimiento=?11, id_departamento_nacimiento=?12, id_municipio_nacimiento=?13,"
			+ "email=?14, direccion=?15, telefono=?16, is_estudiante=?17, is_docente=?18, is_administrativo=?19,"
			+ "is_graduado=?20, is_externo=?21, ids_tipo_persona = ?22 where id=?23", nativeQuery = true)
	public void updateOnlyPersona(Long idTipoDocumento, String numeroDocumento, Date fechaExpedicionDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, Long idGenero, Long idEstadoCivil, Date fechaNacimiento,
			String idPaisNacimiento, String idDepartamentoNacimiento, String idMunicipioNacimiento, String email, String direccion, String telefono,
			boolean isEstudiante, boolean isDocente, boolean isAdministrativo, boolean isGraduado, boolean isExterno, String idsTipoPersona, Long id );
	
	@Query(value ="select p " + 
			"from Persona p " + 
			"where CONCAT(p.primerNombre,' ', p.segundoNombre,' ',p.primerApellido,' ', p.segundoApellido) like ?1")	
	public List<Persona> findPosiblePonenteByNombre(String nombre);
	
	@Transactional
	@Modifying
	@Query(value = "update estudiantes set estado = ?1 where id_persona = ?2 ", nativeQuery = true)
	public void updateEstadoEstudiante(boolean estado, Long idPersona);
	
	@Transactional
	@Modifying
	@Query(value = "update docentes set estado = ?1 where id_persona = ?2 ", nativeQuery = true)
	public void updateEstadoDocente(boolean estado, Long idPersona);
	
	@Transactional
	@Modifying
	@Query(value = "update administrativos set estado = ?1 where id_persona = ?2 ", nativeQuery = true)
	public void updateEstadoAdministrativo(boolean estado, Long idPersona);
	
	@Transactional
	@Modifying
	@Query(value = "update graduados set estado = ?1 where id_persona = ?2 ", nativeQuery = true)
	public void updateEstadoGraduado(boolean estado, Long idPersona);
	
	@Transactional
	@Modifying
	@Query(value = "update externos set estado = ?1 where id_persona = ?2 ", nativeQuery = true)
	public void updateEstadoExterno(boolean estado, Long idPersona);
	
	@Query(value ="select p " + 
			"from Persona p " + 
			"where p.numeroDocumento = ?1")	
	public List<Persona> findPosiblePonenteByNumeroDocumento(String numeroDocumento);
	
	@Query(value ="select p " + 
			"from Persona p " + 
			"where p.email = ?1")	
	public List<Persona> findPosiblePonenteByEmail(String email);
	
	
	
}
