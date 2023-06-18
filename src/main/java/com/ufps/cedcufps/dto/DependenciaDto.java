package com.ufps.cedcufps.dto;

import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DependenciaDto {
	
	private Long id;
	private String dependencia;

}
