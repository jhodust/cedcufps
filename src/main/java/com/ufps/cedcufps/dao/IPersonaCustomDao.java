package com.ufps.cedcufps.dao;

import java.util.List;

import com.ufps.cedcufps.dto.DocenteDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Graduado;
import com.ufps.cedcufps.modelos.Persona;

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
	
	public List<Persona> findPersonasList(List<Long> ids);
	
}
