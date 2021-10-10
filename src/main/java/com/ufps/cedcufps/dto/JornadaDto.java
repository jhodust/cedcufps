package com.ufps.cedcufps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JornadaDto {

	private Long id;
	private String horaInicio;
	private String horaFin;
	private Long idEducacionContinua;
}
