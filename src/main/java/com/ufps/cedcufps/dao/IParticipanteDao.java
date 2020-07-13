package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;

public interface IParticipanteDao extends CrudRepository<Participante, Long> {

	@Query("select p from Participante p where p.educacionContinua.id = ?1 and p.tipoParticipante.id = '2'")
	public List<Participante> findAllPonentesOfOneEducacionContinua(Long idEducacionContinua);
	
	@Query("select p from Participante p where p.educacionContinua.id = ?1 and p.tipoParticipante.tipoParticipante=?2")
	  public List<Participante> findTipoParticipantesByIdEducacionContinua(Long idEducacionContinua, String tipoParticipante );
	
	@Query("select p from Participante p where p.educacionContinua.id = ?1 and p.persona.id=?2")
	  public Participante findParticipanteByIdEducacionContinuaAndIdPersona(Long idEducacionContinua, Long idPersona );

	@Query("select p from Participante p where p.persona.numeroDocumento = ?1 and p.educacionContinua.estado!='Terminado'")
	List<Participante> findAllParticipacionesActivasByParticipante(String numDocumento);
	
	@Query("select p from Participante p where p.educacionContinua.id = ?1 ORDER BY p.persona.primerApellido")
	List<Participante> findAllParticipantesByEducacionContinua(Long idEduContinua);
	
	@Query("select p from Participante p where p.educacionContinua.id = ?1 and p.persona.id = ?2")
	public Participante validarParticipanteYaInscrito(Long idEduContinua, Long idPersona);
}
