package com.ufps.cedcufps.services;

<<<<<<< HEAD
public interface IPersonaService {

=======
import java.util.List;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.TipoDocumento;
import com.ufps.cedcufps.modelos.TipoPersona;

public interface IPersonaService {

	public List<Persona> findAllPersonas();
	
	public List<TipoDocumento> findAllTiposDocumento();
	
	public List<TipoPersona> findAllTiposPersona();
	
	public List<Programa> findAllProgramas();
	
	public TipoPersona findByTipoPersona(String tipoPersona);
	
	public void save(Persona p);
>>>>>>> preparacion
}
