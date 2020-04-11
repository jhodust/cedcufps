package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IInformeSnies;
import com.ufps.cedcufps.modelos.InformeSnies;

@Service
public class InformeSniesService implements IInformeSniesService {

	@Autowired
	private IInformeSnies informeSniesDao;

	@Override
	public List<InformeSnies> findAll() {
		// TODO Auto-generated method stub
		return (List<InformeSnies>) informeSniesDao.findAll();
	}

	@Override
	public void save(InformeSnies i) {
		// TODO Auto-generated method stub
		informeSniesDao.save(i);
	}

	@Override
	public Optional<InformeSnies> findOne(Long id) {
		// TODO Auto-generated method stub
		return informeSniesDao.findById(id);
	}

}
