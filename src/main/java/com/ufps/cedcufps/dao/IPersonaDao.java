package com.ufps.cedcufps.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.Persona;

public interface IPersonaDao extends CrudRepository<Persona, Long> {

	public Persona findByUsername(String username);
	
	public Optional<Persona> findByEmail(String email);
	
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
			+ "is_graduado=?20, is_externo=?21 where id=?22", nativeQuery = true)
	public void updateOnlyPersona(Long idTipoDocumento, String numeroDocumento, Date fechaExpedicionDocumento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, Long idGenero, Long idEstadoCivil, Date fechaNacimiento,
			String idPaisNacimiento, String idDepartamentoNacimiento, String idMunicipioNacimiento, String email, String direccion, String telefono,
			boolean isEstudiante, boolean isDocente, boolean isAdministrativo, boolean isGraduado, boolean isExterno, Long id);
	
	
	

}
