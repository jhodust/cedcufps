package com.ufps.cedcufps.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dto.AnexosDto;

public interface IAnexosService {
 
	public void saveAnexo(MultipartFile file, String idEduContinua);
	
	public List<AnexosDto> findAnexosByEduContinuaIdAcceso(String idAccesoEduContinua);

	public void deleteAnexo(Long idAnexo);
}
