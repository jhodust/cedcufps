package com.ufps.cedcufps.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.modelos.ClasificacionCine;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.TipoBeneficiario;
import com.ufps.cedcufps.modelos.TipoEducacionContinua;

public interface IEducacionContinuaService {

	public List<EducacionContinua> findAll();
	
	public List<EducacionContinua> educacionContinuaRecientes();
	
	public Page<EducacionContinua> educacionContinuaNoTerminadas(Pageable pageable);
	
	public void save(EducacionContinua ec);
	
	public Optional<EducacionContinua> findOne(Long id);
	
	public EducacionContinua findOneByNombre(String educacionContinua);
	
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
	
	public List<EducacionContinua> findAllEducacionContinuaACargoDocente(String numDocumento);
	
	public List<EducacionContinua> findAllEducacionContinuaACargoDirector(String numDocumento, Long idProgramaDirector);
	
	public Diploma generarDiploma(Long idEduContinua);
	
	public List<Jornada> findJornadasByEducacionContinua(Long idEduContinua);
}
