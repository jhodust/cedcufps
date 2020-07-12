package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufps.cedcufps.dao.IFacultadDao;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Facultad;

@Service
public class FacultadService implements IFacultadService {

	@Autowired
	private IFacultadDao facultadDao;
	
	@Override
	public List<Facultad> findAll() {
		// TODO Auto-generated method stub
		return (List<Facultad>) facultadDao.findAll();
	}
	
	@Override
	public Page<Facultad> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return facultadDao.findAll(pageable);
	}

	@Override
	@Transactional(rollbackFor = CustomException.class)
	public void save(Facultad f) {
		// TODO Auto-generated method stub
		if(facultadDao.cantidadFacultadesExistentes(f.getId(), f.getFacultad())>0) {
			throw new CustomException("El nombre de la facultad ingresada ya se encuentra registrado", HttpStatus.BAD_REQUEST);
		}
		facultadDao.save(f);
	}
	

	@Override
	public Facultad findOne(Long id) {
		// TODO Auto-generated method stub
		return  facultadDao.findById(id).orElseThrow(() -> new CustomException("La facultad no fue encontrado en la base de datos"));
	}

	@Override
	public Facultad findByFacultad(String facultad) {
		// TODO Auto-generated method stub
		return facultadDao.findByFacultad(facultad);
	}

	

}
