package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import com.ufps.cedcufps.modelos.TipoDocumento;

public interface ITipoDocumentoService {

	public List<TipoDocumento> findAll();
	
	public void save(TipoDocumento t);
	
	public Optional<TipoDocumento> findOne(Long id);
	
	
}
