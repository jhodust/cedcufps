package com.ufps.cedcufps.services;

import java.util.List;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.ElementoDiploma;
import com.ufps.cedcufps.modelos.ImagenDiploma;
import com.ufps.cedcufps.modelos.TextoDiploma;

public interface IElementoDiplomaService {

	public void saveElementos(Long idDiploma,List<ElementoDiploma>elementos);
	
}
