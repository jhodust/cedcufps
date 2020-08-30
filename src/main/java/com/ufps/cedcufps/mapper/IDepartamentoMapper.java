package com.ufps.cedcufps.mapper;

import java.util.List;

import com.ufps.cedcufps.dto.DepartamentoDto;
import com.ufps.cedcufps.modelos.Departamento;

public interface IDepartamentoMapper {

	public List<DepartamentoDto> convertListDepartamentoToDepartamentosDto(List<Departamento> departamentos);
	
	public DepartamentoDto convertDepartamentoToDepartamentoTo(Departamento d);
}
