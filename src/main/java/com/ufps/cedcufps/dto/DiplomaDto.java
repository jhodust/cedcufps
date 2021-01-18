package com.ufps.cedcufps.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DiplomaDto {
	
	private Long id;
	private Map<String, Object> estructuraDiploma;
	private Long idEduContinua;
	private Date fechaActualizacionDiploma;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Map<String, Object> getEstructuraDiploma() {
		return estructuraDiploma;
	}
	public void setEstructuraDiploma(Map<String, Object> estructuraDiploma) {
		this.estructuraDiploma = estructuraDiploma;
	}
	public Long getIdEduContinua() {
		return idEduContinua;
	}
	public void setIdEduContinua(Long idEduContinua) {
		this.idEduContinua = idEduContinua;
	}
	public Date getFechaActualizacionDiploma() {
		return fechaActualizacionDiploma;
	}
	public void setFechaActualizacionDiploma(Date fechaActualizacionDiploma) {
		this.fechaActualizacionDiploma = fechaActualizacionDiploma;
	}
	
}
