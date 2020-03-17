package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ufps.cedcufps.dao.IFacultadDao;
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
	public void save(Facultad p) {
		// TODO Auto-generated method stub
		facultadDao.save(p);
	}

	@Override
	public Optional<Facultad> findOne(Long id) {
		// TODO Auto-generated method stub
		return  facultadDao.findById(id);
	}

	@Override
	public Facultad findByFacultad(String facultad) {
		// TODO Auto-generated method stub
		return facultadDao.findByFacultad(facultad);
	}

}
