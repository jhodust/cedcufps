package com.ufps.cedcufps.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufps.cedcufps.dto.DependenciaDto;
import com.ufps.cedcufps.modelos.Dependencia;

public interface IDependenciaService {

	public void save(DependenciaDto dto);

	public List<DependenciaDto> listAll();
	Page<Dependencia> listAll(Pageable pageable);

	public DependenciaDto findById(Long id);

	public DependenciaDto findByDependencia(String dependencia);

}
