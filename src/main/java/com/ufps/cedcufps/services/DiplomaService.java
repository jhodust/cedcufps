package com.ufps.cedcufps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dao.IDiplomaDao;
import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dto.DiplomaDto;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
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
		
		Diploma diploma;
		if(d.getId() !=null ) {
			diploma = diplomaDao.findDiplomaById(d.getId());
			diploma.setEstructuraDiploma(d.getEstructuraDiploma());
		}else {
			diploma= new Diploma();
			diploma.setId(d.getId());
			diploma.setEstructuraDiploma(d.getEstructuraDiploma());
			
			
		}
		diplomaDao.save(diploma);
		educacionContinuaDao.updateDiplomaEducacionContinua(diploma.getId(), d.getIdEduContinua());
		return diploma.getId();
	}

	
	
	

}
