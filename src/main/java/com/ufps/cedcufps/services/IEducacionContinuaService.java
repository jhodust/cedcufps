package com.ufps.cedcufps.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.InfoEducacionContinuaDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.ClasificacionCine;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.TipoBeneficiario;
import com.ufps.cedcufps.modelos.TipoEducacionContinua;

public interface IEducacionContinuaService {

	public List<EducacionContinua> findAll();
	
	public List<EducacionContinua> educacionContinuaRecientes();
	
	public Page<EducacionContinua> educacionContinuaNoTerminadas(Pageable pageable);
	
	public void save(EducacionContinua ec);
	
	public Optional<EducacionContinua> findOne(Long id);
	
	public EducacionContinua findOneByNombre(String educacionContinua);
	
	public EducacionContinuaWebDto editarEducacionContinuaByNombre(String educacionContinua);
	
	public List<TipoEducacionContinua> findAllTiposEducacionContinua(Long id);
	
	public List<ClasificacionCine> findAllClasificacionCine();
	
	public List<TipoBeneficiario> findAllTipoBeneficiario();
	
	
	public List<EducacionContinua> educacionContinuasByTipoAndPrograma(Long idTipo, Long idPrograma);
	
	
	public String generarReporteSNIESFormatoEducacionContinua(String anio);
	
	public String generarReporteSNIESFormatoCurso(String anio);
	
	public List<EducacionContinua> findAllEducacionContinuaACargoDocente(String numDocumento);
	
	public List<EducacionContinua> findAllEducacionContinuaACargoDirector(String numDocumento, Long idProgramaDirector);
	
	public Diploma generarDiploma(Long idEduContinua);
	
	public List<JornadaAppDto> findJornadasByEducacionContinua(String eduContinua);
	
	
	public List<EducacionContinuaAppDto> findAllEducacionesApp();
	
	public List<JornadaAppDto> findAllJornadasByEduContinuaApp(Long idEduContinua);
	
	public List<EducacionContinuaAppDto> findPosiblesEduContinuaGestionar();
	
	public InfoEducacionContinuaDto detallesEducacionContinua(String nombreEducacionContinua);
	
	public Map<Integer, ParticipanteDto>  tomarAsistencia(Long idEducacionContinua, Long idJornada, String qr);
	
	public void saveEducacionContinua(MultipartFile file,  String id,
			 String nombre,  String fechaInicio,  String fechaFin,  String duracion, String cantMaxParticipantes,
			 String fechaLimInscripcion,  String costoInscripcion,  String lugar,
			 String costoEducacionContinua,  String requisitos,  String objetivo,
			 String porcentajeAsistencia,  String resumen,  String contenidoGeneral,
			 String idTipoEduContinua,  String tipoEduContinua,  String idProgramaResponsable,
			 String idDocenteResponsable,  String idClasificacionCine, String consecutivo, String idTipoBeneficiarios);
	
	public List<EducacionContinuaWebDto> findEducacionesContinuasBase(List<Programa> programasBase);
	
	public EducacionContinuaWebDto findEducacionContinuaBase(Long id);
	
	public EducacionContinuaWebDto createEducacionContinua();
	
	
	
	
	
}
