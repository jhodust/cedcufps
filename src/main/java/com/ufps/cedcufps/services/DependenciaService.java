package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IDependenciaDao;
import com.ufps.cedcufps.dto.DependenciaDto;
import com.ufps.cedcufps.mapper.IDependenciaMapper;
import com.ufps.cedcufps.modelos.Dependencia;
import com.ufps.cedcufps.modelos.Facultad;

@Service
public class DependenciaService implements IDependenciaService {

	@Autowired
	private IDependenciaDao dependenciaDao;
	
	@Autowired
	private IDependenciaMapper dependenciaMapper;
	
	@Override
	public void save(DependenciaDto dto) {
		// TODO Auto-generated method stub
		dependenciaDao.save(dependenciaMapper.convertDependenciaDtoToDependencia(dto));
	}

	@Override
	public List<DependenciaDto> listAll() {
		// TODO Auto-generated method stub
		return ((List<Dependencia>) dependenciaDao.findAll()).stream().map(dependencia -> dependenciaMapper.convertDependenciaToDependenciaDto(dependencia)).collect(Collectors.toList());
	}
	
	@Override
	public Page<Dependencia> listAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return dependenciaDao.findAll(pageable);
	}

	@Override
	public DependenciaDto findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Dependencia> dependencia = dependenciaDao.findById(id);
		return dependencia.isPresent() ? dependenciaMapper.convertDependenciaToDependenciaDto(dependencia.get()) : null;
	}

	@Override
	public DependenciaDto findByDependencia(String nombreDependencia) {
		// TODO Auto-generated method stub
		Dependencia dependencia = dependenciaDao.findByDependencia(nombreDependencia);
		return dependencia != null ? dependenciaMapper.convertDependenciaToDependenciaDto(dependencia) : null;
	}

}
