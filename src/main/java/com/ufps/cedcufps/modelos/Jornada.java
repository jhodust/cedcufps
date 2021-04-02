package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "jornadas")
public class Jornada implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message = "Ingrese la fecha y hora de inicio de la jornada")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm", timezone="America/Bogota")
	@Column(name = "hora_inicio")
	private Date horaInicio;
	
	@NotNull(message = "Ingrese la fecha y hora que termina la jornada")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm", timezone="America/Bogota")
	@Column(name = "hora_fin")
	private Date horaFin;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="educacion_continua_id", foreignKey=@ForeignKey(name = "FK_jornada_educontinua"))
	private EducacionContinua educacionContinua;

	@JsonIgnore
	@OneToMany(mappedBy = "jornada",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Asistencia> asistencias;
	
	public Jornada() {
		asistencias=new ArrayList<Asistencia>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public EducacionContinua getEducacionContinua() {
		return educacionContinua;
	}

	public void setEducacionContinua(EducacionContinua educacionContinua) {
		this.educacionContinua = educacionContinua;
	}

	public void addAsistencia(Asistencia a) {
		this.getAsistencias().add(a);
	}
	
	public List<Asistencia> getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	
	
	
}