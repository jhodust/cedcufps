package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufps.cedcufps.dao.ITipoDocumentoDao;
import com.ufps.cedcufps.modelos.TipoDocumento;

@Service
public class TipoDocumentoService implements ITipoDocumentoService {

	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;

	@Override
	@Transactional(readOnly = true)
	public List<TipoDocumento> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoDocumento>) tipoDocumentoDao.findAll();
	}

	@Override
	@Transactional
	public void save(TipoDocumento t) {
		// TODO Auto-generated method stub
		tipoDocumentoDao.save(t);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TipoDocumento> findOne(Long id) {
		// TODO Auto-generated method stub
		return tipoDocumentoDao.findById(id);
	}
}
