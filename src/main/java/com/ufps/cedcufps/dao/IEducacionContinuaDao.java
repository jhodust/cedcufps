package com.ufps.cedcufps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;

public interface IEducacionContinuaDao extends CrudRepository<EducacionContinua, Long> {

	@Query("select count(e) from EducacionContinua e where e.tipoEduContinua.id = '1'")
	public int cantidadCursos();
	
	@Query("select count(e) from EducacionContinua e where e.tipoEduContinua.id = '4'")
	public int cantidadTalleres();
	
	@Query("select count(e) from EducacionContinua e where e.tipoEduContinua.id = '2'")
	public int cantidadDiplomados();
	
	@Query("select count(e) from EducacionContinua e where e.tipoEduContinua.id = '3' or e.tipoEduContinua.id = '5' or e.tipoEduContinua.id = '6'")
	public int cantidadSeminariosCongresosSimposios();
	
	@Query(value= "SELECT * FROM educacion_continua ec ORDER BY ec.fecha_inicio DESC ", nativeQuery = true)
	public List<EducacionContinua> educacionContinuaReciente();
	
	//@Query("select e from EducacionContinua e where e.tipoEduContinua.id = ?1 or e.programaResponsable.id = ?2 order by e.consecutivo DESC")
	//public List<EducacionContinua> educacionContinuasByTipoAndPrograma(Long idTipo, Long idPrograma);
	
	
	
}
