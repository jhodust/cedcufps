package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.modelos.Programa;

@Service
public class ProgramaService implements IProgramaService {

	@Autowired
	private IProgramaDao programaDao;
	@Override
	public List<Programa> findAll() {
		// TODO Auto-generated method stub
		return (List<Programa>) programaDao.findAll();
	}

	@Override
	public void save(Programa p) {
		// TODO Auto-generated method stub
		programaDao.save(p);
	}

	@Override
	public Optional<Programa> findOne(Long id) {
		// TODO Auto-generated method stub
		return  programaDao.findById(id);
	}

}
