package com.ufps.cedcufps.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.DepartamentoDto;
import com.ufps.cedcufps.modelos.Departamento;

@Repository
public class DepartamentoMapper implements IDepartamentoMapper {

	@Override
	public List<DepartamentoDto> convertListDepartamentoToDepartamentosDto(List<Departamento> departamentos) {
		// TODO Auto-generated method stub
		List<DepartamentoDto> list= new ArrayList<DepartamentoDto>();
		for(Departamento d: departamentos) {
			list.add(this.convertDepartamentoToDepartamentoTo(d));
		}
		return list;
	}

	@Override
	public DepartamentoDto convertDepartamentoToDepartamentoTo(Departamento d) {
		// TODO Auto-generated method stub
		DepartamentoDto dto= new DepartamentoDto();
		dto.setId(d.getId());
		dto.setDepartamento(d.getDepartamento());
		dto.setIdFacultad(d.getFacultad().getId());
		dto.setNombreFacultad(d.getFacultad().getFacultad());
		return dto;
	}

}
