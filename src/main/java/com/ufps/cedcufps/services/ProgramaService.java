package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufps.cedcufps.dao.IDocenteDao;
import com.ufps.cedcufps.dao.IFacultadDao;
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.modelos.Programa;

@Service
public class ProgramaService implements IProgramaService {

	@Autowired
	private IProgramaDao programaDao;
	
	@Autowired
	private IDocenteDao docenteDao;
	
	@Autowired
	private IFacultadDao facultadDao;
	
	
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
	@Transactional(rollbackFor = CustomException.class) 
	public void save(Programa p) {
		//las siguientes validaciones son porque no sé porque no captura en todos los casos de duplicidad la excepcion DataIntegrityViolationException
		if(programaDao.cantidadCodigosExistentes(p.getId(), p.getCodigo())>0) {
			throw new CustomException("El código ingresado ya está asignado a otro programa", HttpStatus.BAD_REQUEST);
		}
		if(programaDao.cantidadDirProgramaExistentes(p.getId(),p.getDirectorPrograma().getId())>0) {
			programaDao.desvincularDirectorPrograma(p.getId(),p.getDirectorPrograma().getId());
		}
		
		if(p.getId()>0 ) {
			Programa pr=programaDao.findById(p.getId()).orElseThrow(() -> new CustomException("El Programa Académico no fue encontrado en la base de datos"));
			pr.setCodigo(p.getCodigo());
			pr.setDirectorPrograma(docenteDao.findById(p.getDirectorPrograma().getId()).orElseThrow(() -> new CustomException("El docente seleccionado no encontrado en la base de datos")));
			pr.setPrograma(p.getPrograma());
			pr.setFacultad(facultadDao.findById(p.getFacultad().getId()).orElseThrow(() -> new CustomException("La facultad seleccionada no fue encontrada en la base de datos")));
			p=pr;
		}
		programaDao.save(p);
	}

	@Override
	public Programa findOne(Long id) {
		// TODO Auto-generated method stub
		return  programaDao.findById(id).orElseThrow(() -> new CustomException("El Programa Académico no fue encontrado en la base de datos"));
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
	public List<Programa> findProgramaByDirector(Long idDir, Long idPrograma) {
		// TODO Auto-generated method stub
		return programaDao.findOthersProgramasByDirector(idPrograma,idDir);
	}

}
