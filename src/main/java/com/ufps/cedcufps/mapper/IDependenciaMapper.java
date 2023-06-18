package com.ufps.cedcufps.mapper;

import com.ufps.cedcufps.modelos.Dependencia;

import com.ufps.cedcufps.dto.DependenciaDto;


public interface IDependenciaMapper {
	public DependenciaDto convertDependenciaToDependenciaDto(Dependencia d);
	public Dependencia convertDependenciaDtoToDependencia(DependenciaDto dto);
}
