package com.ufps.cedcufps.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEducacionContinua {
	
	ACTIVO(1L, StatusDescription.ACTIVO), DESARROLLO (2L, StatusDescription.DESARROLLO), TERMINADO(3L, StatusDescription.TERMINADO);
	
	private Long id;
	private String status;
	
	
	public static List<StatusEducacionContinua> getAllStatusEducacionContinua() {
		return Arrays.asList(StatusEducacionContinua.values());
	}
	
	public static StatusEducacionContinua findStatusById(Long statusId) {
		Optional<StatusEducacionContinua> statusSearched = getAllStatusEducacionContinua().stream().filter(status -> status.getId().equals(statusId)).findFirst();
		if(statusSearched.isPresent()) {
			return statusSearched.get();
			
		}
		return null;
	}
	

	
}
