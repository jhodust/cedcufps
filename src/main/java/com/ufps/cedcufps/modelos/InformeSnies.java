package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="informes_snies")
public class InformeSnies implements Serializable{

	/**EstadoCivil
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message="Ingrese la fecha inicio del reporte ")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	
	@NotNull(message="Ingrese la fecha limite del reporte ")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "fecha_fin")
	private Date fechaFin;
	
	//private String semestre;
	
	@Column(name = "informe_educacion_continua")
	private String informeEducacionContinua;
	
	@Column(name = "informe_cursos")
	private String informeCurso;
	
	@Column(name = "informe_participante")
	private String informeParticipante;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_programa", foreignKey=@ForeignKey(name = "FK_snies_programa"))
	private Programa programa;

	@Column(name = "descripcion")
	private String descripción;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	/*public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}*/

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getInformeEducacionContinua() {
		return informeEducacionContinua;
	}

	public void setInformeEducacionContinua(String informeEducacionContinua) {
		this.informeEducacionContinua = informeEducacionContinua;
	}

	public String getInformeCurso() {
		return informeCurso;
	}

	public void setInformeCurso(String informeCurso) {
		this.informeCurso = informeCurso;
	}

	public String getInformeParticipante() {
		return informeParticipante;
	}

	public void setInformeParticipante(String informeParticipante) {
		this.informeParticipante = informeParticipante;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public String getDescripción() {
		return descripción;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	
	
	
}

