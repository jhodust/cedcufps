package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ufps.cedcufps.dao.IDepartamentoDao;
import com.ufps.cedcufps.modelos.Departamento;

@Service
public class DepartamentoService implements IDepartamentoService {

	@Autowired
	private IDepartamentoDao departamentoDao;
	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return (List<Departamento>) departamentoDao.findAll();
	}

	@Override
	public void save(Departamento p) {
		// TODO Auto-generated method stub
		departamentoDao.save(p);
	}

	@Override
	public Optional<Departamento> findOne(Long id) {
		// TODO Auto-generated method stub
		return  departamentoDao.findById(id);
	}

	@Override
	public List<Departamento> findByFacultad(String facultad) {
		// TODO Auto-generated method stub
		return departamentoDao.findByFacultad(facultad);
	}

	

}
