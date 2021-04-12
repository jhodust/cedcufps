package com.ufps.cedcufps.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="estudiantes", uniqueConstraints={
		   @UniqueConstraint(columnNames={"codigo", "id_programa"},name = "UK_code_program_student")})
@PrimaryKeyJoinColumn(name="id_persona", foreignKey=@ForeignKey(name = "FK_estudiante_persona"))
public class Estudiante extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "El campo código es requerido")
	//@Size(max = 7, message = "El campo código debe tener máximo 7 dígitos")
	private String codigo;

	@NotNull(message = "Seleccione el Programa Académico en el que se encuentra matriculado")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_programa", foreignKey=@ForeignKey(name = "FK_estudiante_programa"))
	private Programa programa;
	
	@Column(columnDefinition = "boolean default true")
	private boolean estado;
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
