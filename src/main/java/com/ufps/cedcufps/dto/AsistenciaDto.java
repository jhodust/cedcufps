package com.ufps.cedcufps.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDto {
	
	private Long idAsistencia;
	private Long idJornada;
	private Long idParticipante;
	private Date fechaAsistencia;
}
