package com.ufps.cedcufps.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dto.DiplomaDto;
import com.ufps.cedcufps.modelos.Diploma;

public interface IDiplomaService {

	
	public Diploma findOne(Long id);
	
	public Long save(DiplomaDto d);
	
}
