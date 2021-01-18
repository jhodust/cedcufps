package com.ufps.cedcufps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dao.IDiplomaDao;
import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dao.IFirmaDiplomaDao;
import com.ufps.cedcufps.dao.IImagenDiplomaDao;
import com.ufps.cedcufps.dao.ITextoDiplomaDao;
import com.ufps.cedcufps.dto.DiplomaDto;
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
	private IEducacionContinuaDao educacionContinuaDao;
	
	
	
	@Override
	public Diploma findOne(Long id) {
		// TODO Auto-generated method stub
		return diplomaDao.findById(id).get();
	}
	
	

	@Override
	public Long save(DiplomaDto d) {
		// TODO Auto-generated method stub
		
		System.out.println("ACTUALIZANDOOOOOOOOOOOOOOO DIPLOMMMMMMMMMMMMMMMMMMMMMMAAAAAAAAAAAAAA");
		Diploma diploma;
		if(d.getId() !=null ) {
			System.out.println("va a buscar diploma id");
			diploma = diplomaDao.findDiplomaById(d.getId());
			System.out.println("setea la estructura");
			diploma.setEstructuraDiploma(d.getEstructuraDiploma());
			System.out.println("va a guardar");
		}else {
			diploma= new Diploma();
			diploma.setId(d.getId());
			diploma.setEstructuraDiploma(d.getEstructuraDiploma());
			
			
		}
		diplomaDao.save(diploma);
		System.out.println("id despues de guardar diploma: " + diploma.getId());
		educacionContinuaDao.updateDiplomaEducacionContinua(diploma.getId(), d.getIdEduContinua());
		return diploma.getId();
	}

	
	
	

}
