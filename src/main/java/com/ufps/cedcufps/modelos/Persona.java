package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor

@Entity
@Table(name="personas", uniqueConstraints={
		   @UniqueConstraint(columnNames={"numero_documento"},name = "UK_document_people"),
		   @UniqueConstraint(columnNames={"email"},name = "UK_email_people")})
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {//*
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message = "Seleccione el tipo de documento")
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_documento", foreignKey=@ForeignKey(name = "FK_type_document_people"))
	private TipoDocumento tipoDocumento;
	
	@NotEmpty(message = "El campo número documento es requerido")
	@Pattern(regexp = "^([0-9])*$", message = "El código debe contener solo dígitos")
	@Column(name = "numero_documento")
	private String numeroDocumento;

    @DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "fecha_expedicion_documento")
	private Date fechaExpedicionDocumento;
    
	@NotEmpty(message = "El campo primer nombre es requerido")
	@Pattern(regexp = "^$|^[a-zA-ZñNáéíóúÁÉÍÓÚüÜ\\s]+$", message = "El campo primer nombre debe contener solo letras")
	@Size(max = 20, message = "El campo primer nombre debe tener máximo 20 caracteres")
	@Column(name = "primer_nombre")
	private String primerNombre;
	
	
	@Pattern(regexp = "^$|^[a-zA-ZñNáéíóúÁÉÍÓÚüÜ\\s]+$", message = "El campo segundo nombre debe contener solo letras")
	@Size(max = 20, message = "El campo segundo nombre debe tener máximo 20 caracteres")
	@Column(name = "segundo_nombre")
	private String segundoNombre;
	
	@NotEmpty(message = "El campo primer apellido es requerido")
	@Pattern(regexp = "^$|^[a-zA-ZñNáéíóúÁÉÍÓÚüÜ\\s]+$", message = "El campo primer apellido debe contener solo letras")
	@Size(max = 20, message = "El campo primer apellido debe tener máximo 20 caracteres")
	@Column(name = "primer_apellido")
	private String primerApellido;
	
	@Pattern(regexp = "^$|^[a-zA-ZñNáéíóúÁÉÍÓÚüÜ\\s]+$", message = "El campo segundo apellido debe contener solo letras")
	@Size(max = 20, message = "El campo segundo apellido debe tener máximo 20 caracteres")
	@Column(name = "segundo_apellido")
	private String segundoApellido;
	
	@NotNull(message = "El campo género es requerido")
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_genero", foreignKey=@ForeignKey(name = "FK_gender_people"))
	private Genero genero;
	
	@NotNull(message = "El campo estado civil es requerido")
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_estado_civil", foreignKey=@ForeignKey(name = "FK_est_civil_people"))
	private EstadoCivil estadoCivil;
	
	@NotNull(message = "Ingrese su fecha de nacimiento")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	@NotEmpty(message = "Seleccion el pais de nacimiento, es requerido")
	@Column(name="id_pais_nacimiento")
	private String idPaisNacimiento;
	
	
	@Column(name="id_departamento_nacimiento")
	private String idDepartamentoNacimiento;
	

	@Column(name="id_municipio_nacimiento")
	private String idMunicipioNacimiento;
	
	@Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", message = "El email ingresado no es válido")
	private String email;
	
	@Size(max = 100, message = "El campo dirección maximo debe tener 50 caracteres")
	private String direccion;
	
	@Pattern(regexp = "^([0-9])*$", message = "El campo telefono debe contener solo dígitos")
	private String telefono;
	
	
	
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_tipo_persona")
	private TipoPersona tipoPersona;
	*/
	
	@Column(name="is_estudiante", columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isEstudiante;
	
	@Column(name="is_docente", columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isDocente;
	
	@Column(name="is_administrativo", columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isAdministrativo;
	
	@Column(name="is_graduado",  columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isGraduado;
	
	@Column(name="is_externo",  columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isExterno;
	
	@Column(name = "ids_tipo_persona")
	private String idsTipoPersona;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "persona", cascade = CascadeType.ALL)
	private List<PersonaRol> personaXRoles;
	
	@NotNull
	@Column(name = "id_acceso")
	private String idAcceso;
	
	
	public Persona() {
		this.personaXRoles = new ArrayList<PersonaRol>();;
	}

	
	
	
	
}
