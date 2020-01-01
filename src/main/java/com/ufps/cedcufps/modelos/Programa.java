package com.ufps.cedcufps.modelos;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
>>>>>>> preparacion
import javax.persistence.Table;

@Entity
@Table(name="programas")
public class Programa implements Serializable {//1
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String codigo;
	
	@Column(name = "programa")
	private String nombrePrograma;
	
<<<<<<< HEAD
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_programa")
	private List<Estudiante> estudiantes;

	
	public Programa() {
		estudiantes= new ArrayList<Estudiante>();
	}
=======
	
>>>>>>> preparacion

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	
<<<<<<< HEAD
	public void addEstudiante(Estudiante e) {
		this.estudiantes.add(e);
	}
=======
	
>>>>>>> preparacion
	
	

}
