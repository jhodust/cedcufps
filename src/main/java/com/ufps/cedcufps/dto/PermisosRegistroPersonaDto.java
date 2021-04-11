package com.ufps.cedcufps.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermisosRegistroPersonaDto {

	private boolean ableEditEstudiantes;
	private boolean ableEditDocentes;
	private boolean ableEditAdministrativos;
	private boolean ableEditGraduados;
	private boolean ableEditExternos;
	
	private List<ProgramaDto> programasEstudiantes;
	private List<DepartamentoDto> departamentosDocentes;
	private List<ProgramaDto> programasGraduados;
	
	public PermisosRegistroPersonaDto() {
		this.programasEstudiantes=new ArrayList<ProgramaDto>();
		this.departamentosDocentes=new ArrayList<DepartamentoDto>();
		this.programasGraduados=new ArrayList<ProgramaDto>();
	}
}
