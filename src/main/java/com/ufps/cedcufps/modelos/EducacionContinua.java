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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	private String consecutivo;
	
	
	private String imagen;
	
	@NotEmpty(message="El campo nombre es requerido")
	@Size(max = 100, message="El campo nombre debe tener máximo 100 caracteres")
	private String nombre;
	
	@NotNull(message="Ingrese la fecha y hora de inicio del evento")
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	
	@NotNull(message="Ingrese la fecha y hora de finalización del evento ")
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "fecha_fin")
	private Date fechaFin;
	
	@NotEmpty(message="El campo lugar es requerido")
	@Size(max = 40, message="El campo lugar debe tener máximo 40 caracteres")
	private String lugar;
	
	@NotNull(message = "Ingrese la fecha y hora límite de inscripción al evento")
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "fecha_lim_inscripcion")
	private Date fechaLimInscripcion;
	
	@Min(value = 1,message="El campo duración es requerido")
	private int duracion;
	
	
	private String requisitos;
	
	@Column(name = "contenido_general")
	private String contenidoGeneral;
	private String objetivo;
	
	
	@DecimalMin(value = "1")
	@Column(name = "cant_max_participantes")
	private int cantMaxParticipantes;
	
	private String resumen;
	
	@NotNull(message="El campo costo es requerido")
	private double costo;
	
	@NotNull(message="Seleccione el tipo de Educación Continua")
	@ManyToOne(fetch = FetchType.EAGER)//cambié lazy por eager
	@JoinColumn(name="id_tipo_educacion_continua")
	private TipoEducacionContinua tipoEduContinua;
	
	@JsonIgnore
	@OneToMany(mappedBy = "educacionContinua",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Jornada> jornadas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "educacionContinua",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Participante> participantes;

	@NotNull(message="El campo Programa Académico responsable es responsable")
	@ManyToOne(fetch = FetchType.EAGER)//cambié lazy por eager
	@JoinColumn(name="id_programa")
	private Programa programaResponsable;
	
	@JsonIgnore
	@NotNull(message="El campo docente responsable es requerido")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_docente")
	private Docente docenteResponsable;
	
	@JsonIgnore
	@NotNull(message="El campo Clasificación CINE es requerido")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_clasificacion_cine")
	private ClasificacionCine clasificacionCine;
	
	@JsonIgnore
	@NotNull(message="El campo tipo de beneficiario del evento es requerido")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_beneficiario")
	private TipoBeneficiario tipoBeneficiario;
	
	@Column(columnDefinition = "boolean default true")
	private boolean activo;
	
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="id_diploma")
	private Diploma diploma;

	@Column(columnDefinition = "varchar(10) default 'Activo'")
	private String estado;//activo, en curso, finalizado
	
	
	public EducacionContinua() {
		this.jornadas=new ArrayList<Jornada>();
		this.participantes=new ArrayList<Participante>();
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

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
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

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}
	
	public void addParticipante(Participante p) {
		this.participantes.add(p);
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Programa getProgramaResponsable() {
		return programaResponsable;
	}

	public void setProgramaResponsable(Programa programaResponsable) {
		this.programaResponsable = programaResponsable;
	}

	public Docente getDocenteResponsable() {
		return docenteResponsable;
	}

	public void setDocenteResponsable(Docente docenteResponsable) {
		this.docenteResponsable = docenteResponsable;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public ClasificacionCine getClasificacionCine() {
		return clasificacionCine;
	}

	public void setClasificacionCine(ClasificacionCine clasificacionCine) {
		this.clasificacionCine = clasificacionCine;
	}

	public TipoBeneficiario getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	public void setTipoBeneficiario(TipoBeneficiario tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Diploma getDiploma() {
		return diploma;
	}

	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
