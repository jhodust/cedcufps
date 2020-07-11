package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public Page<Programa> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return programaDao.findAll(pageable);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class) 
	public void save(Programa p) {
		// TODO Auto-generated method stub
		programaDao.desvincularDirectorPrograma(p.getDirectorPrograma().getId());
		programaDao.save(p);
	}

	@Override
	public Optional<Programa> findOne(Long id) {
		// TODO Auto-generated method stub
		return  programaDao.findById(id);
	}

	@Override
	public List<Programa> findByFacultad(String facultad) {
		// TODO Auto-generated method stub
		return programaDao.findByFacultad(facultad);
	}

	
	@Override
	public Page<Programa> findByFacultad(String facultad, Pageable pageable) {
		// TODO Auto-generated method stub
		return programaDao.findByFacultad(facultad, pageable);
	}

	@Override
	public List<Programa> findProgramaByDirector(Long idDir) {
		// TODO Auto-generated method stub
		
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("Tamaño");
		System.out.println(programaDao.findByDirector(idDir).size());
		return programaDao.findByDirector(idDir);
	}

}
