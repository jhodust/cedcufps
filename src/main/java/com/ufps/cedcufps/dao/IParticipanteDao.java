package com.ufps.cedcufps.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;


public interface IParticipanteDao extends JpaRepository<Participante, Long>{

	@Query("select p from Participante p where p.educacionContinua.id = (select e.id from EducacionContinua e where e.nombre = ?1) and p.tipoParticipante.id = '2'")
	public List<Participante> findAllPonentesOfOneEducacionContinua(String educacionContinua);
	
	@Query("select p from Participante p where p.educacionContinua.id = ?1 and p.tipoParticipante.id = '2'")
	public List<Participante> findAllPonentesOfOneEducacionContinuaById(Long idEducacionContinua);
	
	@Query("select p from Participante p where p.educacionContinua.id = ?1 and p.tipoParticipante.tipoParticipante=?2")
	  public List<Participante> findTipoParticipantesByIdEducacionContinua(Long idEducacionContinua, String tipoParticipante );
	
	@Query("select p from Participante p where p.educacionContinua.id = ?1 and p.persona.id=?2")
	  public Participante findParticipanteByIdEducacionContinuaAndIdPersona(Long idEducacionContinua, Long idPersona );

	@Query("select p from Participante p where p.persona.numeroDocumento = ?1 and p.educacionContinua.estado!='Terminado'")
	List<Participante> findAllParticipacionesActivasByParticipante(String numDocumento);
	
	@Query("select p from Participante p where p.educacionContinua.id = ?1 ORDER BY p.persona.primerApellido")
	List<Participante> findAllParticipantesEducacionContinua(Long idEduContinua);
	
	@Query("select p from Participante p where p.educacionContinua.id = (select e.id from EducacionContinua e where e.nombre = ?1) ORDER BY p.persona.primerApellido")
	List<Participante> findAllParticipantesByEducacionContinua(String eduContinua);
	
	@Query("select p from Participante p where p.educacionContinua.id = ?1 and p.persona.id = ?2")
	public Participante validarParticipanteYaInscrito(Long idEduContinua, Long idPersona);
	
	@Query("select p from Participante p where p.educacionContinua.id = ?1 and p.persona.numeroDocumento = ?2")
	public Participante validarParticipanteYaInscritoApp(Long idEduContinua, String documento);
	
	@Query("select p from Participante p where p.codigoQR = ?1 ")
	public Participante validarQr(String qr);
	
	@Transactional
	@Modifying
	@Query(value = "delete from participantes  where id = ?1 ", nativeQuery=true)
	public void deleteParticipante(Long idParticipante);
	
	@Query("select p from Participante p where p.id = ?1 ")
	public Participante findParticipanteById(Long idParticipante);
	
	@Transactional
	@Modifying
	@Query(value="insert into asistentes (id_participante) values (?1) ", nativeQuery = true)
	public void insertAsistente(Long idParticipante);
	
	@Transactional
	@Modifying
	@Query(value="insert into ponentes (id_participante, tema) values (?1, ?2) ", nativeQuery = true)
	public void insertPonente(Long idParticipante, String tema);
	
	@Transactional
	@Modifying
	@Query(value="update ponentes set tema = ?1 where id_participante = ?2 ", nativeQuery = true)
	public void updatePonente(String tema, Long idParticipante);
	
	@Transactional
	@Modifying
	@Query(value="update participantes set tarjeta_inscripcion = ?1 where id = ?2 ", nativeQuery = true)
	public void updateTarjetaInscripcion(String tarjetaInscripcion, Long idParticipante);
	
	@Query(value="select count(*) from participantes where educacion_continua_id = ?1 and id_tipo_participante = '1'", nativeQuery = true)
	public int countTotalParticipantes(Long idEduContinua);
	
	
}
