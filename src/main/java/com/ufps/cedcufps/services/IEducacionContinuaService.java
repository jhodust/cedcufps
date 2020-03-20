package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.TipoEducacionContinua;

public interface IEducacionContinuaService {

	public List<EducacionContinua> findAll();
	
	public void save(EducacionContinua ec);
	
	public Optional<EducacionContinua> findOne(Long id);
	
	public List<TipoEducacionContinua> findAllTiposEducacionContinua();
	
	public int cantidadCursos();
	
	public int cantidadTalleres();
		
	public int cantidadDiplomados();
	
	public int cantidadSeminariosCongresosSimposios();
	
}
