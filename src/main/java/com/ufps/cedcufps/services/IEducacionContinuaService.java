package com.ufps.cedcufps.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ufps.cedcufps.modelos.ClasificacionCine;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.TipoBeneficiario;
import com.ufps.cedcufps.modelos.TipoEducacionContinua;

public interface IEducacionContinuaService {

	public List<EducacionContinua> findAll();
	
	public List<EducacionContinua> educacionContinuaRecientes();
	
	public void save(EducacionContinua ec);
	
	public Optional<EducacionContinua> findOne(Long id);
	
	public List<TipoEducacionContinua> findAllTiposEducacionContinua();
	
	public List<ClasificacionCine> findAllClasificacionCine();
	
	public List<TipoBeneficiario> findAllTipoBeneficiario();
	
	public int cantidadCursos();
	
	public int cantidadTalleres();
		
	public int cantidadDiplomados();
	
	public int cantidadSeminariosCongresosSimposios();
	
	public List<EducacionContinua> educacionContinuasByTipoAndPrograma(Long idTipo, Long idPrograma);
	
	public void updateCodigoEducacionContinua(EducacionContinua ec);
	
	public void generarReporteSNIESEducacionContinua(String año);
	
	
}
