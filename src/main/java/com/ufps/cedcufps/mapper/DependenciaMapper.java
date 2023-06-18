package com.ufps.cedcufps.mapper;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.DependenciaDto;
import com.ufps.cedcufps.modelos.Dependencia;

@Repository
public class DependenciaMapper implements IDependenciaMapper {

	@Override
	public DependenciaDto convertDependenciaToDependenciaDto(Dependencia d) {
		// TODO Auto-generated method stub
		return DependenciaDto.builder().id(d.getId()).dependencia(d.getDependencia()).build();
	}

	@Override
	public Dependencia convertDependenciaDtoToDependencia(DependenciaDto dto) {
		// TODO Auto-generated method stub
		return Dependencia.builder().id(dto.getId()).dependencia(dto.getDependencia()).build();
	}

}
