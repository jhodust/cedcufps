package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "educacion_continua")
@Inheritance(strategy = InheritanceType.JOINED)
public class EducacionContinua implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	
	@Column(name = "fecha_fin")
	private Date fechaFin;
	
	@Column(name = "fecha_lim_inscripcion")
	private Date fechaLimInscripcion;
	private int duracion;
	private String requitos;
	
	@Column(name = "contenido_general")
	private String contenidoGeneral;
	private String objetivo;
	
	@Column(name = "cant_max_participantes")
	private int cantMaxParticipantes;
	private String resumen;
	private double costo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_educacion_continua")
	private TipoEducacionContinua tipoEduContinua;
	
	@OneToMany(mappedBy = "educacionContinua",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Jornada> jornadas;

	public EducacionContinua() {
		this.jornadas=new ArrayList<Jornada>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

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

	public Date getFechaLimInscripcion() {
		return fechaLimInscripcion;
	}

	public void setFechaLimInscripcion(Date fechaLimInscripcion) {
		this.fechaLimInscripcion = fechaLimInscripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getRequitos() {
		return requitos;
	}

	public void setRequitos(String requitos) {
		this.requitos = requitos;
	}

	public String getContenidoGeneral() {
		return contenidoGeneral;
	}

	public void setContenidoGeneral(String contenidoGeneral) {
		this.contenidoGeneral = contenidoGeneral;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public int getCantMaxParticipantes() {
		return cantMaxParticipantes;
	}

	public void setCantMaxParticipantes(int cantMaxParticipantes) {
		this.cantMaxParticipantes = cantMaxParticipantes;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public TipoEducacionContinua getTipoEduContinua() {
		return tipoEduContinua;
	}
	

	public void setTipoEduContinua(TipoEducacionContinua tipoEduContinua) {
		this.tipoEduContinua = tipoEduContinua;
	}
	
	public List<Jornada> getJornadas() {
		return jornadas;
	}

	public void setJornadas(List<Jornada> jornadas) {
		this.jornadas = jornadas;
	}
	
	public void addJornada(Jornada j) {
		this.jornadas.add(j);
	}
	
	
}
