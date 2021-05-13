package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufps.cedcufps.mapper.HashMapConverter;
import com.ufps.cedcufps.utils.Auditable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor

@Entity
@Table(name = "educacion_continua")
@Inheritance(strategy = InheritanceType.JOINED)
public class EducacionContinua extends Auditable<String> implements Serializable {

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
	@Size(max = 150, message="El campo nombre debe tener máximo 150 caracteres")
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
	
	@NotEmpty(message="El campo duración es requerido")
	@Min(value = 1,message="El campo duración es requerido")
	private String duracion;
	
	
	
	
	@Column(name = "cant_max_participantes")
	private String cantMaxParticipantes;
	
	
	@Column(name = "costo_inscripcion")
	private String costoInscripcion;
	
	@NotEmpty(message="El campo costo educación continua es requerido")
	@Column(name = "costo_educacion_continua")
	private String costoEducacionContinua;
	
	@NotEmpty(message="El campo porcentaje asistencia es requerido")
	@Column(name = "porcentaje_asistencia")
	@Min(value = 1, message = "El porcentaje de asistencia debe estar entre 1 y 100")
	@Max(value = 100, message = "El porcentaje de asistencia debe estar entre 1 y 100")
	private String porcentajeAsistencia;
	
	@NotNull(message="Seleccione el tipo de Educación Continua")
	@ManyToOne(fetch = FetchType.EAGER)//cambié lazy por eager
	@JoinColumn(name="id_tipo_educacion_continua", foreignKey=@ForeignKey(name = "FK_tipo_educontinua"))
	private TipoEducacionContinua tipoEduContinua;
	
	@JsonIgnore
	@OneToMany(mappedBy = "educacionContinua",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Jornada> jornadas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "educacionContinua",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Participante> participantes;

	@NotNull(message="El campo Programa Académico responsable es responsable")
	@ManyToOne(fetch = FetchType.EAGER)//cambié lazy por eager
	@JoinColumn(name="id_programa", foreignKey=@ForeignKey(name = "FK_programa_educontinua"))
	private Programa programaResponsable;
	
	@JsonIgnore
	@NotNull(message="El campo docente responsable es requerido")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_docente", foreignKey=@ForeignKey(name = "FK_docente_educontinua"))
	private Docente docenteResponsable;
	
	@JsonIgnore
	@NotNull(message="El campo Clasificación CINE es requerido")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_clasificacion_cine", foreignKey=@ForeignKey(name = "FK_clasificacion_educontinua"))
	private ClasificacionCine clasificacionCine;
	
	@JsonIgnore
	@OneToMany(mappedBy = "educacionContinua",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EducacionContinuaTipoBeneficiario> tipoBeneficiarios;

	
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="id_diploma", foreignKey=@ForeignKey(name = "FK_diploma_educontinua"))
	private Diploma diploma;

	private String estado="Activo";//activo, en curso, finalizado
	
	@NotNull
	@Column(name = "id_acceso")
	private String idAcceso;
	
	@Column(columnDefinition = "LONGTEXT",name = "info_adicional")
	private String infoAdicional;
	
	@Column(name="is_deleted",  columnDefinition = "TINYINT default 0")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isDeleted;
		
	@JsonIgnore
	@OneToMany(mappedBy = "educacionContinua",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Anexos> anexos;
	
	@Column(name = "status_preinscripcion_all_participantes", columnDefinition = "boolean default false")
	private boolean allParticipantesAprobadaPreinscipcion;
	
	
	public EducacionContinua() {
		this.jornadas=new ArrayList<Jornada>();
		this.participantes=new ArrayList<Participante>();
		this.anexos=new ArrayList<Anexos>();
	}
	
	
	
	
	
	
	
}
