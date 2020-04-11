package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



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
	
	private String anio;
	
	//private String semestre;
	
	@Column(name = "informe_educacion_continua")
	private String informeEducacionContinua;
	
	@Column(name = "informe_cursos")
	private String informeCurso;
	
	@Column(name = "informe_participante")
	private String informeParticipante;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	/*public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}*/

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
	
	
	
}

