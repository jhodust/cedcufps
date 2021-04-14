package com.ufps.cedcufps.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Programa;

@Repository
public interface IProgramaDao extends PagingAndSortingRepository<Programa, Long>{

	@Query("select p from Programa p where p.facultad.facultad = ?1")
	public List<Programa> findByFacultad(String facultad);
	
	@Query("select p from Programa p where p.facultad.facultad = ?1")
	public Page<Programa> findByFacultad(String facultad,Pageable pageable);
	
	@Query(value="select count(p.id) from programas p where p.id != ?1 and p.programa = ?2", nativeQuery=true)
	public int cantidadProgramaExistentes(Long id, String programa);
	
	
	
	@Query(value="select count(p.id) from programas p where p.id != ?1 and p.codigo = ?2", nativeQuery=true)
	public int cantidadCodigosExistentes(Long idPro, String codigo);
	
	@Query(value="select count(p.id) from programas p where p.id != ?1 and p.id_director = ?2", nativeQuery=true)
	public int cantidadDirProgramaExistentes(Long idPro, Long idDir);
	
	@Modifying
	@Query(value="update programas set id_director=null where id != ?1 and id_director = ?2", nativeQuery=true)
	public void desvincularDirectorPrograma(Long idPro, Long idDir);
	
	@Transactional
	@Modifying
	@Query(value="update programas set id_director= ?1  where id = ?2 ", nativeQuery=true)
	public void vincularDirectorPrograma(Long idDir, Long idPro);
	
	
}
