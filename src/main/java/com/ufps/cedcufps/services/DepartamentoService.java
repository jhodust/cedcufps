package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufps.cedcufps.dao.IDepartamentoDao;
import com.ufps.cedcufps.dao.IFacultadDao;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Programa;

@Service
public class DepartamentoService implements IDepartamentoService {

	@Autowired
	private IDepartamentoDao departamentoDao;
	
	@Autowired
	private IFacultadDao facultadDao;
	
	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return (List<Departamento>) departamentoDao.findAll();
	}
	
	@Override
	public Page<Departamento> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return departamentoDao.findAll(pageable);
	}

	@Override
	@Transactional(rollbackFor = CustomException.class)
	public void save(Departamento d) {
		// TODO Auto-generated method stub
		if(departamentoDao.cantidadDeptosExistentes(d.getId(), d.getDepartamento())>0) {
			throw new CustomException("El nombre del departamento ingresado ya se encuentra registrado", HttpStatus.BAD_REQUEST);
		}
		if(d.getId()>0 ) {
			Departamento de=departamentoDao.findById(d.getId()).orElseThrow(() -> new CustomException("El Departamento Académico no fue encontrado en la base de datos"));
			de.setDepartamento(d.getDepartamento());
			de.setFacultad(facultadDao.findById(d.getFacultad().getId()).orElseThrow(() -> new CustomException("La facultad seleccionada no fue encontrada en la base de datos")));
			d=de;
		}
		departamentoDao.save(d);
		
		
	}

	@Override
	public Departamento findOne(Long id) {
		// TODO Auto-generated method stub
		return  departamentoDao.findById(id).orElseThrow(() -> new CustomException("El Departamento Académico no fue encontrado en la base de datos"));
	}

	@Override
	public List<Departamento> findByFacultad(String facultad) {
		// TODO Auto-generated method stub
		return departamentoDao.findByFacultad(facultad);
	}

	@Override
	public Page<Departamento> findByFacultad(String facultad,Pageable pageable) {
		// TODO Auto-generated method stub
		return departamentoDao.findByFacultad(facultad, pageable);
	}

	

	

}
