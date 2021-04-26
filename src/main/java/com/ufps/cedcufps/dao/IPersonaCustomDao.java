package com.ufps.cedcufps.dao;

import java.util.List;

import javax.persistence.Query;

import com.ufps.cedcufps.dto.DocenteDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Graduado;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.PersonaRol;

public interface IPersonaCustomDao {

	public int saveEstudiante(Estudiante e);
	
	public int saveDocente(Docente d);
	
	public int saveAdministrativo(Administrativo a);
	
	public int saveGraduado(Graduado g);
	
	public int saveExterno(Externo e);
	
	public List<Long> listAllPossiblePeople(Long idPersona);
	
	public Estudiante findOnlyEstudiante(Long idPersona);
	
	public Docente findOnlyDocente(Long idPersona);
	
	public Administrativo findOnlyAdministrativo(Long idPersona);
	
	public Graduado findOnlyGraduado(Long idPersona);
	
	public Externo findOnlyExterno(Long idPersona);
	
	public List<DocenteDto> findAllDocentesActivos();
	
	public Docente findDocenteResponsable(Long idPersona);
	
	public Docente findDocenteDirPrograma(Long idPrograma, Long idPersona);
	
	public List<Persona> findPersonasList(List<Long> ids, boolean superAdmin);
	
	public Persona findPersonaById(Long id);
	
	public Persona findPersonaByIdAcceso(String idAcceso);
	
	public Persona findPersonaByNumeroDocumento(String documento);
	
	public Persona findPersonaByEmail(String email);
	
	public List<Persona> findPersonasByNumeroDocumento(String numeroDocumento);
	
	public List<Persona> findPersonasByEmail(String email);
	
	public List<Persona> findManyPeople(List<Long> idsPersonas);
		
	public List<Persona> findPosiblePonenteByNombre(String nombre);
	
	public boolean hasPermisos(Long idPersona, Long rol);
	
	public boolean hasPermisosOnlyMyEdC(Long idPersona, Long rol);
	
	public List<PersonaRol> findRolesPersona(Long id);
	
	
}
