package com.ufps.cedcufps.dto;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class DiplomaPruebaDto {
	private Long id;
	
	private Map<String, Object> detalles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, Object> getDetalles() {
		return detalles;
	}

	public void setDetalles(Map<String, Object> detalles) {
		this.detalles = detalles;
	}
	
	
	
}
