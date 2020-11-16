package com.ufps.cedcufps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IElementoDiplomaDao;
import com.ufps.cedcufps.dao.IImagenDiplomaDao;
import com.ufps.cedcufps.dao.ITextoDiplomaDao;
import com.ufps.cedcufps.modelos.ElementoDiploma;
import com.ufps.cedcufps.modelos.ImagenDiploma;
import com.ufps.cedcufps.modelos.TextoDiploma;

@Service
public class ElementoDiplomaService implements IElementoDiplomaService{

	@Autowired
	private IElementoDiplomaDao elementoDiplomaDao;

	@Override
	public void saveElementos(Long idDiploma, List<ElementoDiploma> elementos) {
		// TODO Auto-generated method stub
		//elementoDiplomaDao.limpiarElementosAntiguos(idDiploma);
		elementoDiplomaDao.saveAll(elementos);
	}
	
	
	

}
