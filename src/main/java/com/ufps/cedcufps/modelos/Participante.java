package com.ufps.cedcufps.modelos;

import java.io.Serializable;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "participantes", uniqueConstraints={
		   @UniqueConstraint(columnNames={"id_persona", "educacion_continua_id"}, name = "UK_persona_educontinua_participante")})
@Inheritance(strategy = InheritanceType.JOINED)
public class Participante implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="educacion_continua_id", foreignKey=@ForeignKey(name = "FK_participante_educontinua"))
	private EducacionContinua educacionContinua;
	
	//*****************************************revisar asociacion**************
	@NotNull(message="El campo ponente es requerido")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_persona")
	private Persona persona;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_tipo_participante", foreignKey=@ForeignKey(name = "FK_tipoparticipante_participante"))
	private TipoParticipante tipoParticipante;
	

	private String codigoQR;
	
	@Column(name = "imagen_codigo_qr")
	private String imagenCodigoQR;
	
	@Column(name = "tarjeta_inscripcion")
	private String tarjetaInscripcion;
	
	@Column(name = "fecha_registro", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegisto;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "participante")
	private List<Asistencia> asistencias;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_persona", foreignKey=@ForeignKey(name = "FK_participante_type_people"))
	private TipoPersona tipoPersona;
	
	@Column(name = "fecha_generacion_diploma")
	private Date fechaGeneracionDiploma;
	
	@Column(name = "diploma_participacion")
	private String diplomaParticipacion;
	
	@Column(columnDefinition = "boolean default false")
	private boolean aprobado;
	
	@Column(name = "status_preinscripcion", columnDefinition = "boolean default false")
	private boolean statusPreinscripcion;
	
	@Column(name = "recibo_pago")
	private String reciboPago;
	
	@Column(name = "notificado_general", columnDefinition = "boolean default false")
	private boolean notificadoGeneral;
	
	@PrePersist
	public void generarFecha() {
		this.fechaRegisto = new Date();
	}
	
	@Column(name = "token")
	private String token;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public EducacionContinua getEducacionContinua() {
		return educacionContinua;
	}

	public void setEducacionContinua(EducacionContinua educacionContinua) {
		this.educacionContinua = educacionContinua;
	}

	public TipoParticipante getTipoParticipante() {
		return tipoParticipante;
	}

	public void setTipoParticipante(TipoParticipante tipoParticipante) {
		this.tipoParticipante = tipoParticipante;
	}

	public String getCodigoQR() {
		return codigoQR;
	}

	public void setCodigoQR(String codigoQR) {
		this.codigoQR = codigoQR;
	}

	public String getImagenCodigoQR() {
		return imagenCodigoQR;
	}

	public void setImagenCodigoQR(String imagenCodigoQR) {
		this.imagenCodigoQR = imagenCodigoQR;
	}

	public String getTarjetaInscripcion() {
		return tarjetaInscripcion;
	}

	public void setTarjetaInscripcion(String tarjetaInscripcion) {
		this.tarjetaInscripcion = tarjetaInscripcion;
	}

	public Date getFechaRegisto() {
		return fechaRegisto;
	}

	public void setFechaRegisto(Date fechaRegisto) {
		this.fechaRegisto = fechaRegisto;
	}

	public void addAsistencia(Asistencia a) {
		this.asistencias.add(a);
	}
	public List<Asistencia> getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public Date getFechaGeneracionDiploma() {
		return fechaGeneracionDiploma;
	}

	public void setFechaGeneracionDiploma(Date fechaGeneracionDiploma) {
		this.fechaGeneracionDiploma = fechaGeneracionDiploma;
	}

	public String getDiplomaParticipacion() {
		return diplomaParticipacion;
	}

	public void setDiplomaParticipacion(String diplomaParticipacion) {
		this.diplomaParticipacion = diplomaParticipacion;
	}

	public boolean isAprobado() {
		return aprobado;
	}

	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isStatusPreinscripcion() {
		return statusPreinscripcion;
	}

	public void setStatusPreinscripcion(boolean statusPreinscripcion) {
		this.statusPreinscripcion = statusPreinscripcion;
	}

	public String getReciboPago() {
		return reciboPago;
	}

	public void setReciboPago(String reciboPago) {
		this.reciboPago = reciboPago;
	}

	public boolean isNotificadoGeneral() {
		return notificadoGeneral;
	}

	public void setNotificadoGeneral(boolean notificadoGeneral) {
		this.notificadoGeneral = notificadoGeneral;
	}

	
		
}
