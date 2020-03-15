package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import com.ufps.cedcufps.modelos.EstadoCivil;
import com.ufps.cedcufps.modelos.Genero;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.TipoDocumento;
import com.ufps.cedcufps.modelos.TipoPersona;

public interface IPersonaService {

	public List<Persona> findAllPersonas();
	
	public List<EstadoCivil> findAllEstadosCiviles();
	
	public List<Genero> findAllGeneros();
	
	public List<TipoDocumento> findAllTiposDocumento();
	
	public List<TipoPersona> findAllTiposPersona();
	
	public List<Programa> findAllProgramas();
	
	public TipoPersona findByTipoPersona(String tipoPersona);
	
	public void save(Persona p);
	
	public Persona findPersonaLogueada();
	
	public Optional<Persona> findOne(Long id);

}
