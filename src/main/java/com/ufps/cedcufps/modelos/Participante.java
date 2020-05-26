package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "participantes", uniqueConstraints={
		   @UniqueConstraint(columnNames={"id_persona", "educacion_continua_id"})})
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
	private EducacionContinua educacionContinua;
	
	//*****************************************revisar asociacion**************
	@NotNull(message="El campo ponente es requerido")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_persona")
	private Persona persona;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_participante")
	private TipoParticipante tipoParticipante;
	

	private String codigoQR;
	
	@Column(name = "imagen_codigo_qr")
	private String imagenCodigoQR;
	
	@Column(name = "tarjeta_inscripcion")
	private String tarjetaInscripcion;
	
	@Column(name = "fecha_registro", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegisto;
	
	@PrePersist
	public void generarFecha() {
		this.fechaRegisto = new Date();
	}
	
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

	
		
}
