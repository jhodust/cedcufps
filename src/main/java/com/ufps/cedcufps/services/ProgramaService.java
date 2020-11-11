package com.ufps.cedcufps.services;

import java.util.ArrayList;
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
import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dao.IPersonaRolCustomDao;
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.dao.IRolDao;
import com.ufps.cedcufps.dao.ITipoPersonaDao;
import com.ufps.cedcufps.dto.PersonaRolDto;
import com.ufps.cedcufps.dto.PersonaRolEducacionContinuaDto;
import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.mapper.IProgramaMapper;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.PersonaRol;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.modelos.RolPersonaEduCPrograma;
import com.ufps.cedcufps.modelos.RolPersonaPerPrograma;
import com.ufps.cedcufps.modelos.RolPersonaTipoPersona;
import com.ufps.cedcufps.modelos.TipoPersona;

@Service
public class ProgramaService implements IProgramaService {

	@Autowired
	private IProgramaDao programaDao;
	
	@Autowired
	private IDocenteDao docenteDao;
	
	@Autowired
	private IFacultadDao facultadDao;
	
	@Autowired
	private IRolDao rolDao;
	
	@Autowired
	private IPersonaRolCustomDao personaRolCustomDao;
	
	@Autowired
	private IPersonaDao personaDao;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private ITipoPersonaDao tipoPersonaDao;
	
	@Autowired
	private IProgramaMapper programaMapper;
	
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
		boolean asignarPermisosDir=true;
		if(programaDao.cantidadProgramaExistentes(p.getId(), p.getPrograma())>0) {
			throw new CustomException("El nombre del programa ingresado ya se encuentra registrado", HttpStatus.BAD_REQUEST);
		}
		if(programaDao.cantidadCodigosExistentes(p.getId(), p.getCodigo())>0) {
			throw new CustomException("El código ingresado ya está asignado a otro programa", HttpStatus.BAD_REQUEST);
		}
		if(programaDao.cantidadDirProgramaExistentes(p.getId(),p.getDirectorPrograma().getId())>0) {
			programaDao.desvincularDirectorPrograma(p.getId(),p.getDirectorPrograma().getId());
			personaRolCustomDao.deleteRolesDirPrograma(p.getDirectorPrograma().getId());
		}
		
		if(p.getId()>0 ) {
			Programa pr=programaDao.findById(p.getId()).orElseThrow(() -> new CustomException("El Programa Académico no fue encontrado en la base de datos"));
			if(pr.getDirectorPrograma().getId().equals(p.getDirectorPrograma().getId())) {
				asignarPermisosDir=false;
			}else {
				personaRolCustomDao.deleteRolesDirPrograma(pr.getDirectorPrograma().getId());
				pr.setDirectorPrograma(docenteDao.findById(p.getDirectorPrograma().getId()).orElseThrow(() -> new CustomException("El docente seleccionado no fue encontrado en la base de datos")));
			}
			pr.setCodigo(p.getCodigo());
			pr.setPrograma(p.getPrograma());
			pr.setFacultad(facultadDao.findById(p.getFacultad().getId()).orElseThrow(() -> new CustomException("La facultad seleccionada no fue encontrada en la base de datos")));
			p=pr;
			
		}
		try {
			programaDao.save(p);
			if(asignarPermisosDir) {
				System.out.println("*******************************************************************");
				System.out.println("entra a asignar permisos");
				this.asignarPermisosDirector(p.getDirectorPrograma().getId(), p.getCodigo());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
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
	public List<ProgramaDto> findProgramaByDirector(Long idDir, Long idPrograma) {
		// TODO Auto-generated method stub
		return programaMapper.convertListProgramaToProgramaDto(programaDao.findOthersProgramasByDirector(idPrograma,idDir));

	}
	
	@Override
	public Programa findProgramaByDirector(Long idDir) {
		return programaDao.findByDirector(idDir);
	}
	
	public void asignarPermisosDirector(Long idDirPrograma, String codigo) {
		Programa p=programaDao.findByCodigo(codigo);
		
		
		/*****************rol para administrar eventos del programa del cual es director*****************/
		
		personaRolCustomDao.save("ROLE_MANAECCU",idDirPrograma);
		
		personaRolCustomDao.savePermisoParaEducacionContinua("ROLE_MANAECCU",idDirPrograma,p.getId());
		
		/*
		 * ****************************rol para administrar personas ***********************
		 */
		personaRolCustomDao.save("ROLE_MANPEOPLE",idDirPrograma);
		personaRolCustomDao.savePermisoParaTipoPersonas("ROLE_MANPEOPLE", idDirPrograma, "Estudiante");
		personaRolCustomDao.savePermisoParaTipoPersonas("ROLE_MANPEOPLE", idDirPrograma, "Graduado");
		personaRolCustomDao.savePermisoParaTipoPersonas("ROLE_MANPEOPLE", idDirPrograma, "Administrativo");
		personaRolCustomDao.savePermisoParaTipoPersonas("ROLE_MANPEOPLE", idDirPrograma, "Externo");
		
		personaRolCustomDao.savePermisoParaPersonaPrograma("ROLE_MANPEOPLE", idDirPrograma, "Estudiante", p.getId());
		personaRolCustomDao.savePermisoParaPersonaPrograma("ROLE_MANPEOPLE", idDirPrograma, "Graduado", p.getId());
		
		/*
		 * ****************************rol para administrar informe snies***********************
		 */
		personaRolCustomDao.save("ROLE_SNIES",idDirPrograma);
		
		
	}

	@Override
	public ProgramaDto searchProgramaById(Long id) {
		// TODO Auto-generated method stub
		Programa p=programaDao.findById(id).orElseThrow(() -> new CustomException("El Programa Académico no fue encontrado en la base de datos"));
		return programaMapper.convertProgramaToProgramaDto(p); 
	}

	@Override
	public List<Programa> programasParaEduContinuaBase(){
		Persona p= personaService.findPersonaLogueada();
		List<Programa> programas= new ArrayList<Programa>();
		if(personaService.isSuperAdmin(p)) {
			System.out.println("es admin y lista todo");
			return (List<Programa>)programaDao.findAll();
		}else if (personaService.isDirPrograma(p)) {
			System.out.println("es director y lista programas director");
			
			programas.add(programaDao.findByDirector(p.getId()));
			return programas;
		}else {
			System.out.println("es docente y lista null");
			return null;
		}
	}
}
