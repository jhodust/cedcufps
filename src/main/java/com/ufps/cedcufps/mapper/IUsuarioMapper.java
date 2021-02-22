package com.ufps.cedcufps.mapper;

import java.util.List;
import java.util.Optional;

import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.UsuarioAppDto;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Graduado;
import com.ufps.cedcufps.modelos.Persona;

public interface IUsuarioMapper {

	public Persona convertUsuarioToPersona(UsuarioDto u);
	
	public Estudiante convertUsuarioToEstudiante(UsuarioDto u, Long idPersona);
	
	public Docente convertUsuarioToDocente(UsuarioDto u, Long idPersona);
	
	public Administrativo convertUsuarioToAdministrativo(UsuarioDto u, Long idPersona);
	
	public Graduado convertUsuarioToGraduado(UsuarioDto u, Long idPersona);
	
	public Externo convertUsuarioToExterno(UsuarioDto u, Long idPersona);
	
	public UsuarioAppDto convertPersonaToUsuarioAppDto(Persona p);
	
	public List<PersonaDto> convertListPersonasToPersonaDto(List<Persona> personas);
	
	public PersonaDto convertPersonaToPersonaDto(Persona persona);
	
	public UsuarioDto convertPersonaToUsuarioDto(Persona p, Estudiante e, Docente d, 
			Administrativo a, Graduado g, Externo ex);
	
	public String convertFieldsFullName(Persona p);
}
