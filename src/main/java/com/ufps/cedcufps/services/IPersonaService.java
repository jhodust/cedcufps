package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.UsuarioAppDto;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.EstadoCivil;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Genero;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.TipoDocumento;
import com.ufps.cedcufps.modelos.TipoPersona;

public interface IPersonaService {

	public List<Persona> findAllPersonas();
	
	public List<Estudiante> findAllEstudiantes();
	
	public List<Docente> findAllDocentes();
	
	public List<Administrativo> findAllAdministrativos();
	
	public List<Externo> findAllExternos();
	
	public List<EstadoCivil> findAllEstadosCiviles();
	
	public List<Genero> findAllGeneros();
	
	public List<TipoDocumento> findAllTiposDocumento();
	
	public List<TipoPersona> findAllTiposPersona();
	
	public List<Programa> findAllProgramas();
	
	public List<Departamento> findAllDepartamentos();
	
	public TipoPersona findByTipoPersona(String tipoPersona);
	
	public void save(Persona p);
	
	public Persona findPersonaLogueada();
	
	public UsuarioAppDto convertPersonaLogueadaApp(Persona p);
	
	public Optional<Persona> findOne(Long id);
	
	public Persona findByUsername(String username);

	public Persona findByEmail(String email);
	
	public void registrarse(UsuarioDto u);
	
	public List<PersonaDto> findAllPersonasPosibles();
}
