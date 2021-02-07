package com.ufps.cedcufps.modelos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="facultades",uniqueConstraints={@UniqueConstraint(columnNames={"facultad"},name = "UK_facultad")})
public class Facultad implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotEmpty(message = "El campo facultad es requerido")
	@Size(max = 60, message = "El campo facultad no puede exceder los 60 caracteres")
	private String facultad;


	
	
	
	

}

