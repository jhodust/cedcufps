package com.ufps.cedcufps.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipos_educacion_continua")
public class TipoEducacionContinua implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column(name = "tipo_educacion_continua")
	private String tipoEduContinua;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoEduContinua() {
		return tipoEduContinua;
	}

	public void setTipoEduContinua(String tipoEduContinua) {
		this.tipoEduContinua = tipoEduContinua;
	}
	
	

}
