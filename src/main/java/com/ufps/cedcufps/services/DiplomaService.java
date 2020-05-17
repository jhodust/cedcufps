package com.ufps.cedcufps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dao.IDiplomaDao;
import com.ufps.cedcufps.dao.IFirmaDiplomaDao;
import com.ufps.cedcufps.dao.IImagenDiplomaDao;
import com.ufps.cedcufps.dao.ITextoDiplomaDao;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.FirmaDiploma;
import com.ufps.cedcufps.modelos.ImagenDiploma;
import com.ufps.cedcufps.modelos.TextoDiploma;
import com.ufps.cedcufps.utils.Archivo;

@Service
public class DiplomaService implements IDiplomaService {

	@Autowired 
	private IDiplomaDao diplomaDao;
	
	@Autowired 
	private IImagenDiplomaDao imagenDiplomaDao;
	
	@Autowired 
	private ITextoDiplomaDao textoDiplomaDao;
	
	@Autowired 
	private IFirmaDiplomaDao firmaDiplomaDao;
	
	@Override
	public Diploma findOne(Long id) {
		// TODO Auto-generated method stub
		return diplomaDao.findById(id).get();
	}
	
	@Override
	public List<ImagenDiploma> findImagenesDefault() {
		// TODO Auto-generated method stub
		return imagenDiplomaDao.findAllDefault();
	}

	@Override
	public List<TextoDiploma> findTextoDefault() {
		// TODO Auto-generated method stub
		return textoDiplomaDao.findAllDefault();
	}

	@Override
	public List<ImagenDiploma> findImagenesByDiploma(Long idDiploma) {
		// TODO Auto-generated method stub
		return imagenDiplomaDao.findByDiploma(idDiploma);
	}

	@Override
	public List<TextoDiploma> findTextoByDiploma(Long idDiploma) {
		// TODO Auto-generated method stub
		return textoDiplomaDao.findByDiploma(idDiploma);
	}

	@Override
	public List<FirmaDiploma> findFirmaByDiploma(Long idDiploma) {
		// TODO Auto-generated method stub
		return firmaDiplomaDao.findByDiploma(idDiploma);
	}

	@Override
	public void save(Diploma d) {
		// TODO Auto-generated method stub
		diplomaDao.save(d);
	}

	
	
	

}
