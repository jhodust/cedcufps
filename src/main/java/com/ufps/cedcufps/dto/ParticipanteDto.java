package com.ufps.cedcufps.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ParticipanteDto {

	private Long id;
	private Long idPersona;
	private String nombrePersona;
	private Long idTipoParticipante;
	private String tipoParticipante;
	private Long idTipoDocumento;
	private String numeroDocumento;
	private String tipoDocumento;
	private String tipoEduContinua;
	private Long idTipoEduContinua;
	private Long idEducacionContinua;
	private String educacionContinua;
	private Date fechaInicioEduContinua;
	private Date fechaFinEduContinua;
	private String lugarEducacionContinua;
	private Date fechaInscripcion;
	private String codigoQR;
	private String imagenQr;
	private String tarjetaInscripcion;
	Map<Long, Boolean> jornadasAsistencias;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Long idTipoPersona;
	private String diplomaParticipacion;
	private boolean aprobado;
	private Date fechaGeneracionDiploma;
	private String token;
	private boolean enableToCertificate;
	private String email;
	private Long idDiploma;
	private String estructuraDiploma;
	private Date fechaActualizacionDiploma;
	
	public ParticipanteDto() {
		this.jornadasAsistencias=new HashMap<>();
	}
	
	public void addAsistencia(Long idJornada, boolean status) {
		this.jornadasAsistencias.put(idJornada, status);
	}
	
	
	
	
	
}
