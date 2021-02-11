package com.ufps.cedcufps.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
	
	private Long id;
	private Long idTipoDocumento;
	private String tipoDocumento;
	private String numeroDocumento;
	private Date fechaExpedicionDocumento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Long idGenero;
	private String genero;
	private Long idEstadoCivil;
	private String estadoCivil;
	private Date fechaNacimiento;
	private String idPaisNacimiento;
	private String paisNacimiento;
	private String idDepartamentoNacimiento;
	private String deptoNacimiento;
	private String idMunicipioNacimiento;
	private String mpioNacimiento;
	private String email;
	private String direccion;
	private String telefono;
	
	private String profesion;
	private String empresa;
	private boolean estudiante;
	private boolean docente;
	private boolean administrativo;
	private boolean graduado;
	private boolean externo;
	private String codigoProgramaEstudiante;
	private Long idProgramaEstudiante;
	private String programaEstudiante;
	private Long idProgramaGraduado;
	private String programaGraduado;
	private String anioGraduado;
	private Long idDeptoAdscrito;
	private String deptoAdscrito;
	private String codigoDocente;
	private String dependencia;
	private String cargo;
	
}
