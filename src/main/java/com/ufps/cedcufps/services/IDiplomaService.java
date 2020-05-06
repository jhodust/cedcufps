package com.ufps.cedcufps.services;

import java.util.List;

import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.ElementoDiploma;
import com.ufps.cedcufps.modelos.FirmaDiploma;
import com.ufps.cedcufps.modelos.ImagenDiploma;
import com.ufps.cedcufps.modelos.TextoDiploma;

public interface IDiplomaService {

	
	public Diploma findOne(Long id);
	
	public List<ImagenDiploma> findImagenesDefault();
	
	public List<TextoDiploma> findTextoDefault();
	
	public List<ImagenDiploma> findImagenesByDiploma(Long idDiploma);
	
	public List<TextoDiploma> findTextoByDiploma(Long idDiploma);
	
	public List<FirmaDiploma> findFirmaByDiploma(Long idDiploma);
}
