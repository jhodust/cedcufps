package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dao.ITipoEducacionContinuaDao;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.TipoEducacionContinua;

@Service
public class EducacionContinuaService implements IEducacionContinuaService{

	@Autowired
	private IEducacionContinuaDao educacionContinuaDao;
	
	@Autowired
	private ITipoEducacionContinuaDao tipoEducacionContinuaDao;
	
	@Override
	public List<EducacionContinua> findAll() {
		// TODO Auto-generated method stub
		return (List<EducacionContinua>) educacionContinuaDao.findAll();
	}

	@Override
	public void save(EducacionContinua ec) {
		// TODO Auto-generated method stub
		educacionContinuaDao.save(ec);
	}

	@Override
	public Optional<EducacionContinua> findOne(Long id) {
		// TODO Auto-generated method stub
		return educacionContinuaDao.findById(id);
	}

	@Override
	public List<TipoEducacionContinua> findAllTiposEducacionContinua() {
		// TODO Auto-generated method stub
		return (List<TipoEducacionContinua>) tipoEducacionContinuaDao.findAll();
	}

	@Override
	public int cantidadCursos() {
		// TODO Auto-generated method stub
		return educacionContinuaDao.cantidadCursos();
	}

	@Override
	public int cantidadTalleres() {
		// TODO Auto-generated method stub
		return educacionContinuaDao.cantidadTalleres();
	}

	@Override
	public int cantidadDiplomados() {
		// TODO Auto-generated method stub
		return educacionContinuaDao.cantidadDiplomados();
	}

	@Override
	public int cantidadSeminariosCongresosSimposios() {
		// TODO Auto-generated method stub
		return educacionContinuaDao.cantidadSeminariosCongresosSimposios();
	}
	
	@Override
	public List<EducacionContinua> educacionContinuaRecientes() {
		// TODO Auto-generated method stub
		return (List<EducacionContinua>) educacionContinuaDao.educacionContinuaReciente();
	}


	

	

	

}
