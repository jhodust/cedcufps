package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IJornadaDao;
import com.ufps.cedcufps.modelos.Jornada;

@Service
public class JornadaService implements IJornadaService{

	@Autowired
	private IJornadaDao jornadaDao;
	
	@Override
	public List<Jornada> findAllByIdEducacionContinua(Long idEducacionContinua) {
		// TODO Auto-generated method stub
		return jornadaDao.findByIdEducacionContinua(idEducacionContinua);
	}

	@Override
	public void save(Jornada j) {
		// TODO Auto-generated method stub
		jornadaDao.save(j);
	}

	@Override
	public Optional<Jornada> findOne(Long id) {
		// TODO Auto-generated method stub
		return jornadaDao.findById(id);
	}

	

}
