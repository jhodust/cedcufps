package com.ufps.cedcufps.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.Facultad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ProgramaDto {

	private Long id;
	private String programa;
	private String codigo;
	private Long idDirector;
	private Long idFacultad;
	
	
	
	
}
