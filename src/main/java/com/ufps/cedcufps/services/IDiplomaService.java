package com.ufps.cedcufps.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dto.DiplomaDto;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.ElementoDiploma;
import com.ufps.cedcufps.modelos.FirmaDiploma;
import com.ufps.cedcufps.modelos.ImagenDiploma;
import com.ufps.cedcufps.modelos.TextoDiploma;

public interface IDiplomaService {

	
	public Diploma findOne(Long id);
	
	public Long save(DiplomaDto d);
	
}
