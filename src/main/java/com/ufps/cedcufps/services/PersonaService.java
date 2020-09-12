package com.ufps.cedcufps.services;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.PerThisOrTargetPointcutVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.transaction.annotation.Transactional;

import com.ufps.cedcufps.dao.IAdministrativoDao;
import com.ufps.cedcufps.dao.IDepartamentoDao;
import com.ufps.cedcufps.dao.IDocenteDao;
import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dao.IEstadoCivilDao;
import com.ufps.cedcufps.dao.IEstudianteDao;
import com.ufps.cedcufps.dao.IExternoDao;
import com.ufps.cedcufps.dao.IGeneroDao;
import com.ufps.cedcufps.dao.IGraduadoDao;
import com.ufps.cedcufps.dao.IPersonaCustomDao;
import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dao.IPersonaRolCustomDao;
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.dao.ITipoDocumentoDao;
import com.ufps.cedcufps.dao.ITipoPersonaDao;
import com.ufps.cedcufps.dto.PerfilRolUsuarioDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.ProgramaDto;
import com.ufps.cedcufps.dto.UsuarioAppDto;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.mapper.IDepartamentoMapper;
import com.ufps.cedcufps.mapper.IEducacionContinuaMapper;
import com.ufps.cedcufps.mapper.IProgramaMapper;
import com.ufps.cedcufps.mapper.IUsuarioMapper;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.EstadoCivil;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Genero;
import com.ufps.cedcufps.modelos.Graduado;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.PersonaRol;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.modelos.RolPersonaPerDepto;
import com.ufps.cedcufps.modelos.RolPersonaPerPrograma;
import com.ufps.cedcufps.modelos.RolPersonaTipoPersona;
import com.ufps.cedcufps.modelos.TipoDocumento;
import com.ufps.cedcufps.modelos.TipoPersona;

@Service
public class PersonaService implements IPersonaService, UserDetailsService {

	@Autowired
	private IPersonaDao personaDao;
	
	@Autowired
	private IPersonaCustomDao personaCustomDao;
	
	@Autowired
	private IEstudianteDao estudianteDao;
	
	@Autowired
	private IDocenteDao docenteDao;
	
	@Autowired
	private IAdministrativoDao administrativoDao;
	
	@Autowired
	private IExternoDao externoDao;
	
	@Autowired
	private IGraduadoDao graduadoDao;
	
	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;
	
	@Autowired
	private IEstadoCivilDao estadoCivilDao;
	
	@Autowired
	private IGeneroDao generoDao;
	
	@Autowired
	private IUsuarioMapper usuarioMapper;
	
	@Autowired
	private IProgramaMapper programaMapper;
	
	@Autowired
	private IEducacionContinuaMapper educacionContinuaMapper;
	
	@Autowired
	private IDepartamentoMapper departamentoMapper;
	
	@Autowired
	private IProgramaDao programaDao;
	
	@Autowired
	private IDepartamentoDao departamentoDao;
	
	@Autowired
	private ITipoPersonaDao tipoPersonaDao;
	
	@Autowired
	private IEducacionContinuaDao educacionContinuaDao;
	
	@Autowired
	private IPersonaRolCustomDao personaRolDao;
	
	private Logger logger= LoggerFactory.getLogger(PersonaService.class);
	@Override
	public List<Persona> findAllPersonas() {
		// TODO Auto-generated method stub
		return (List<Persona>) personaDao.findAll();
	}

	@Override
	public List<TipoDocumento> findAllTiposDocumento() {
		// TODO Auto-generated method stub
		return (List<TipoDocumento>) tipoDocumentoDao.findAll();
	}
	
	@Override
	public List<TipoPersona> findAllTiposPersona() {
		// TODO Auto-generated method stub
		return (List<TipoPersona>) tipoPersonaDao.findAll();
	}

	@Override
	public List<Programa> findAllProgramas() {
		// TODO Auto-generated method stub
		return (List<Programa>)programaDao.findAll();
	}

	@Override
	public TipoPersona findByTipoPersona(String tipoPersona) {
		// TODO Auto-generated method stub
		return tipoPersonaDao.findByTipoPersona(tipoPersona);
	}

	@Override
	public void save(Persona p) {
		// TODO Auto-generated method stub
		personaDao.save(p);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Persona p=personaDao.findByUsername(username);
		
		if(p==null) {
			logger.error("Error login: no existe el usuario '"+username+"'");
			throw new UsernameNotFoundException("usuario "+ username +" no existe");
		}
		List<GrantedAuthority> authorities= new ArrayList<GrantedAuthority>();
		/*for(Rol r:p.getRoles()) {
			logger.info("Role:".concat(r.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(r.getAuthority()));
		}*/
		
		if(authorities.isEmpty()) {
			logger.error("Error login: no tiene roles el usuario '"+username+"'");
			throw new UsernameNotFoundException("usuario "+ username +" no tiene roles");
		}
		logger.info("Entra al metodo loadUserByUsername de personaService");
		return new User(p.getUsername(), p.getPassword(), p.isEnabled(), true, true, true, authorities);
	}

	@Override
	public Persona findPersonaLogueada() {
		// TODO Auto-generated method stub
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		return personaDao.findByUsername(userDetail.getUsername());*/
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationToken client= (OAuth2AuthenticationToken )a;
		String email=client.getPrincipal().getAttribute("email");
		System.out.println("email del usuario logueadoen metodo findPersonaLogueada de personaService");
		System.out.println(email);
		
		
	    return this.findByEmail(email);
	}

	@Override
	public Optional<Persona> findOne(Long id) {
		// TODO Auto-generated method stub
		return personaDao.findById(id);
	}

	@Override
	public List<EstadoCivil> findAllEstadosCiviles() {
		// TODO Auto-generated method stub
		return (List<EstadoCivil>) estadoCivilDao.findAll();
	}

	@Override
	public List<Genero> findAllGeneros() {
		// TODO Auto-generated method stub
		return (List<Genero>) generoDao.findAll();
	}

	@Override
	public List<Departamento> findAllDepartamentos() {
		// TODO Auto-generated method stub
		return (List<Departamento>)departamentoDao.findAll();
	}

	@Override
	public List<Estudiante> findAllEstudiantes() {
		// TODO Auto-generated method stub
		return (List<Estudiante>)estudianteDao.findAll();
	}

	@Override
	public List<Docente> findAllDocentes() {
		// TODO Auto-generated method stub
		List<Docente> docentes=docenteDao.findDocentes();
		System.out.println("tamaño docentes: " + docentes.size() );
		for(Docente d: docentes) {
			System.out.println(d.getCodigo() + " - " +d.getPrimerNombre());
		}
		return docentes;
	}

	@Override
	public List<Administrativo> findAllAdministrativos() {
		// TODO Auto-generated method stub
		return (List<Administrativo>)administrativoDao.findAll();
	}

	@Override
	public List<Externo> findAllExternos() {
		// TODO Auto-generated method stub
		return (List<Externo>)externoDao.findAll();
	}

	@Override
	public Persona findByUsername(String username) {
		// TODO Auto-generated method stub
		return personaDao.findByUsername(username);
	}
	
	@Override
	@Transactional(rollbackFor = CustomException.class)
	public Persona findByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<Persona> p=personaDao.findByEmail(email);
		try {
			return p.get();
		}catch(Exception e) {
			return null;
		}
		
		
	}

	@Override
	public void guardar(UsuarioDto u) {
		// TODO Auto-generated method stub
		/*if(String.valueOf(u.getTipoPersona()).equalsIgnoreCase("1")) {
			Estudiante e= usuarioMapper.convertUsuarioToEstudiante(u);
			System.out.println("save estudiante");
			personaDao.save(e);
		}
		
		if (String.valueOf(u.getTipoPersona()).equalsIgnoreCase("4")) {
			Externo e= usuarioMapper.convertUsuarioToExterno(u);
			System.out.println("save externo");
			personaDao.save(e);
		}*/
		if(u.getId() != null) {
			Optional<Persona> o=personaDao.findById(u.getId());
			Persona persona=null;
			if(o!=null) {
				persona=o.get();
			}
		}
		Persona p= usuarioMapper.convertUsuarioToPersona(u);
		logger.info("llega de mappear");
		personaDao.save(p);
		logger.info("guarda la persona");
		if(u.isExterno()) {
			logger.info("Es externo");
			Externo e=usuarioMapper.convertUsuarioToExterno(u,p.getId());
			logger.info(e.getId().toString());
			logger.info(e.getEmpresa());
			logger.info(e.getProfesion());
			personaCustomDao.saveExterno(e);
		}else {
			if(u.isEstudiante()) {
				logger.info("Es estudiante");
				Estudiante e=usuarioMapper.convertUsuarioToEstudiante(u,p.getId());
				logger.info(e.getId().toString());
				logger.info(e.getPrograma().getPrograma());
				logger.info(e.getCodigo());
				personaCustomDao.saveEstudiante(e);
			}
			if(u.isDocente()) {
				logger.info("Es docente");
				Docente d= usuarioMapper.convertUsuarioToDocente(u,p.getId());
				logger.info(d.getId().toString());
				logger.info(d.getDepartamento().getDepartamento());
				logger.info(d.getCodigo());
				personaCustomDao.saveDocente(d);
			}else {
				if(u.getId() !=null) {//actualizar
					Optional<Docente> doc=docenteDao.findById(u.getId());
					if(doc!=null) {
						Docente d= doc.get();
						d.setEstado(false);
						personaCustomDao.saveDocente(d);
					}
					
				}
			}
			if(u.isAdministrativo()) {
				logger.info("Es administrativo");
				Administrativo a= usuarioMapper.convertUsuarioToAdministrativo(u,p.getId());
				logger.info(a.getId().toString());
				logger.info(a.getDependencia());
				logger.info(a.getCargo());
				personaCustomDao.saveAdministrativo(a);
			}
			if(u.isGraduado()) {
				logger.info("Es graduado");
				Graduado g= usuarioMapper.convertUsuarioToGraduado(u,p.getId());
				logger.info(g.getId().toString());
				logger.info(g.getPrograma().getPrograma());
				logger.info(g.getAnio());
				personaCustomDao.saveGraduado(g);
			}
		}
	}

	@Override
	public UsuarioAppDto convertPersonaLogueadaApp(Persona p) {
		// TODO Auto-generated method stub
		return usuarioMapper.convertPersonaToUsuarioAppDto(p);
	}

	@Override
	public List<PersonaDto> findAllPersonasPosibles() {
		// TODO Auto-generated method stub
		
		if(this.isSuperAdmin()) {
			return usuarioMapper.convertListPersonasToPersonaDto((List<Persona>)personaDao.findAll());
		}else {
			Persona p=this.findPersonaLogueada();
			if(this.hasPermissionForPeople(p.getId())) {
				return usuarioMapper.convertListPersonasToPersonaDto(personaDao.findManyPeople(this.personaCustomDao.listAllPossiblePeople(p.getId())));
			}
		}
		
		return null;
		
	}
	
	@Override
	public boolean isSuperAdmin() {
		Persona p=this.findPersonaLogueada();
		
		for(PersonaRol pr: p.getRoles()) {
			if(pr.getRol().getAuthority().equalsIgnoreCase("ROLE_SUPERADMIN")) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isDirPrograma() {
		Persona p=this.findPersonaLogueada();
		return (this.programaDao.findByDirector(p.getId()) != null );
		
	}
	
	@Override
	public boolean hasPermissionForPeople(Long idPersona) {
		Persona p=personaDao.findById(idPersona).get();
		if(p!=null) {
			for(PersonaRol pr: p.getRoles()) {
				if(pr.getRol().getAuthority().equalsIgnoreCase("ROLE_MANPEOPLE")) {
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	@Override
	public boolean hasPermissionForEduContinua(Long idPersona) {
		Persona p=personaDao.findById(idPersona).get();
		if(p!=null) {
			for(PersonaRol pr: p.getRoles()) {
				if(pr.getRol().getAuthority().equalsIgnoreCase("ROLE_MANAECCU")) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean hasPermissionForAttendance(Long idPersona) {
		Persona p=personaDao.findById(idPersona).get();
		if(p!=null) {
			for(PersonaRol pr: p.getRoles()) {
				if(pr.getRol().getAuthority().equalsIgnoreCase("ROLE_ATTENDANCE")) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public PerfilRolUsuarioDto findPermisos(Long idUsuario) {
		// TODO Auto-generated method stub
		Persona autoridad= this.findPersonaLogueada();
		Persona usuario= this.findOne(idUsuario).get();
		PerfilRolUsuarioDto dto= new PerfilRolUsuarioDto();
		if(usuario!=null) {
			dto.setIdPersona(usuario.getId());
			dto.setEstudiante(usuario.isEstudiante());
			dto.setDocente(usuario.isDocente());
			dto.setAdministrativo(usuario.isAdministrativo());
			dto.setGraduado(usuario.isGraduado());
			dto.setExterno(usuario.isExterno());
			dto.setSuperadmin(autoridad.getId().equals(usuario.getId()));
			if(this.isSuperAdmin()) {//quien asigna permisos es superadmin
				Programa p=programaDao.findByDirector(usuario.getId());
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println(dto.isDirPrograma());
				dto.setDirPrograma(p!=null);
				if(p!=null) {//usuario a quien le vamos a dar permisos es director programa
					dto.setIdProgramaDirector(p.getId());
					dto.setProgramaDirector(p.getPrograma());
					dto.setHasPermissionForEduContinua(this.hasPermissionForEduContinua(usuario.getId()));
					dto.setHasPermissionForUsuarios(this.hasPermissionForPeople(usuario.getId()));
					dto.setHasPermissionForAttendance(this.hasPermissionForAttendance(usuario.getId()));
					dto.setProgramasForEduContinua(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosEduContinuaForDirProgramaExceptOwn(usuario.getId(), p.getId())));
					List<Programa> programasPosiblesSelectEduC=(List<Programa>)this.programaDao.findAll();
					programasPosiblesSelectEduC.remove(p);
					dto.setSelectProgramasForEduContinua(programaMapper.convertListProgramaToProgramaDto(programasPosiblesSelectEduC));
					
					dto.setProgramasForEstudiantes(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosEstudiantesForDirProgramaExceptOwn(usuario.getId(), p.getId())));
					dto.setProgramasForGraduados(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosGraduadosForDirProgramaExceptOwn(usuario.getId(), p.getId())));
					dto.setDeptosForDocentes(departamentoMapper.convertListDepartamentoToDepartamentosDto(this.departamentoDao.findDeptosPermisosDocentesForDocEstAdminvo(usuario.getId())));// el director tiene comportamiento de docente para la gestión de otros docentes de un departamento
					dto.setSelectDeptosForDocentes(departamentoMapper.convertListDepartamentoToDepartamentosDto((List<Departamento>)this.departamentoDao.findAll()));
					List<Programa> programasPosiblesSelectEst=(List<Programa>)this.programaDao.findAll();
					programasPosiblesSelectEst.remove(p);
					dto.setSelectProgramasForEstudiantes(programaMapper.convertListProgramaToProgramaDto(programasPosiblesSelectEst));
					List<Programa> programasPosiblesSelectGra=(List<Programa>)this.programaDao.findAll();
					programasPosiblesSelectGra.remove(p);
					dto.setSelectProgramasForGraduados(programaMapper.convertListProgramaToProgramaDto(programasPosiblesSelectGra));
					dto.setSelectEduContinuasForAttendance(educacionContinuaMapper.convertEducacionContinuaToApp((List<EducacionContinua>)educacionContinuaDao.findAll()));
					dto.setEduContinuasForAttendance(educacionContinuaMapper.convertEducacionContinuaToApp(educacionContinuaDao.findEduContinuasPermissionForAttendance(usuario.getId())));
					dto.setHasPermissionForAdminvos(this.personaDao.hasPermissionForAdminvos(usuario.getId())>0);
					dto.setHasPermissionForExternos(this.personaDao.hasPermissionForExternos(usuario.getId())>0);
					List<ProgramaDto> programaEdCIntocable= new ArrayList<ProgramaDto>();
					programaEdCIntocable.add(programaMapper.convertProgramaToProgramaDto(p));
					dto.setProgramasForEduContinuaIntocables(programaEdCIntocable);
					List<ProgramaDto> programaEstIntocable= new ArrayList<ProgramaDto>();
					programaEstIntocable.add(programaMapper.convertProgramaToProgramaDto(p));
					dto.setProgramasForEstudiantesIntocables(programaEstIntocable);
					List<ProgramaDto> programaGradIntocable= new ArrayList<ProgramaDto>();
					programaGradIntocable.add(programaMapper.convertProgramaToProgramaDto(p));
					dto.setProgramasForGraduadosIntocables(programaGradIntocable);
					return dto;
				}else {
					dto.setIdProgramaDirector(null);
					dto.setProgramaDirector(null);
					dto.setHasPermissionForEduContinua(this.hasPermissionForEduContinua(usuario.getId()));
					dto.setHasPermissionForUsuarios(this.hasPermissionForPeople(usuario.getId()));
					dto.setHasPermissionForAttendance(this.hasPermissionForAttendance(usuario.getId()));
					dto.setProgramasForEduContinua(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosEduContinuaForDocEstAdminvo(usuario.getId())));
					dto.setSelectProgramasForEduContinua(programaMapper.convertListProgramaToProgramaDto((List<Programa>)this.programaDao.findAll()));
					dto.setProgramasForEstudiantes(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosEstudiantesForDocEstAdminvo(usuario.getId())));
					dto.setProgramasForGraduados(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosGraduadosForDocEstAdminvo(usuario.getId())));
					dto.setDeptosForDocentes(departamentoMapper.convertListDepartamentoToDepartamentosDto(this.departamentoDao.findDeptosPermisosDocentesForDocEstAdminvo(usuario.getId())));
					dto.setSelectDeptosForDocentes(departamentoMapper.convertListDepartamentoToDepartamentosDto((List<Departamento>)this.departamentoDao.findAll()));
					dto.setSelectProgramasForEstudiantes(programaMapper.convertListProgramaToProgramaDto((List<Programa>)this.programaDao.findAll()));
					dto.setSelectProgramasForGraduados(programaMapper.convertListProgramaToProgramaDto((List<Programa>)this.programaDao.findAll()));
					dto.setSelectEduContinuasForAttendance(educacionContinuaMapper.convertEducacionContinuaToApp((List<EducacionContinua>)educacionContinuaDao.findAll()));
					dto.setEduContinuasForAttendance(educacionContinuaMapper.convertEducacionContinuaToApp(educacionContinuaDao.findEduContinuasPermissionForAttendance(usuario.getId())));
					dto.setHasPermissionForAdminvos(this.personaDao.hasPermissionForAdminvos(usuario.getId())>0);
					dto.setHasPermissionForExternos(this.personaDao.hasPermissionForExternos(usuario.getId())>0);
					return dto;
				}
			}else if (this.isDirPrograma()) {
				Programa pDirector=programaDao.findByDirector(autoridad.getId());
				dto.setIdProgramaDirector(null);
				dto.setProgramaDirector(null);
				dto.setHasPermissionForEduContinua(this.hasPermissionForEduContinua(usuario.getId()));
				dto.setHasPermissionForUsuarios(this.hasPermissionForPeople(usuario.getId()));
				dto.setHasPermissionForAttendance(this.hasPermissionForAttendance(usuario.getId()));
				dto.setProgramasForEduContinua(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosEduContinuaForDocEstAdminvo(usuario.getId())));
				List<ProgramaDto> selectProgramasDir= new ArrayList<ProgramaDto>();
				selectProgramasDir.add(programaMapper.convertProgramaToProgramaDto(pDirector));
				dto.setSelectProgramasForEduContinua(selectProgramasDir);
				dto.setProgramasForEstudiantes(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosEstudiantesForDocEstAdminvo(usuario.getId())));
				dto.setProgramasForGraduados(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosGraduadosForDocEstAdminvo(usuario.getId())));
				dto.setDeptosForDocentes(departamentoMapper.convertListDepartamentoToDepartamentosDto(this.departamentoDao.findDeptosPermisosDocentesForDocEstAdminvo(usuario.getId())));
				dto.setSelectDeptosForDocentes(departamentoMapper.convertListDepartamentoToDepartamentosDto((List<Departamento>)this.departamentoDao.findAll()));
				dto.setSelectProgramasForEstudiantes(selectProgramasDir);
				dto.setSelectProgramasForGraduados(selectProgramasDir);
				dto.setSelectEduContinuasForAttendance(educacionContinuaMapper.convertEducacionContinuaToApp((List<EducacionContinua>)educacionContinuaDao.findAll()));
				dto.setEduContinuasForAttendance(educacionContinuaMapper.convertEducacionContinuaToApp(educacionContinuaDao.findEduContinuasPermissionForAttendance(usuario.getId())));
				dto.setHasPermissionForAdminvos(this.personaDao.hasPermissionForAdminvos(usuario.getId())>0);
				dto.setHasPermissionForExternos(this.personaDao.hasPermissionForExternos(usuario.getId())>0);
				dto.setProgramasForEduContinuaIntocables(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosEduContinuaForDirProgramaExceptOwn(usuario.getId(), pDirector.getId())));
				dto.setProgramasForEstudiantesIntocables(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosEstudiantesForDirProgramaExceptOwn(usuario.getId(), pDirector.getId())));
				dto.setProgramasForGraduadosIntocables(programaMapper.convertListProgramaToProgramaDto(this.programaDao.findProgramasPermisosGraduadosForDirProgramaExceptOwn(usuario.getId(), pDirector.getId())));
				return dto;
			}
		}
		
		return null;
	}

	@Override
	public boolean updatePermisos(Long idPersona, boolean hasPermisosEduC, boolean hasPermisosPer,
			boolean hasPermisosAtt, List<Long> idsProEduContinua, List<Long> idsProEst, List<Long> idsDeptoDoc,
			List<Long> idsProGrad, List<Long> idsEduAtt, boolean hasPermisosAdminvo, boolean hasPermisosExter, 
			boolean isDirPrograma, boolean isDocente,Long idProgramaDirector) {
		// TODO Auto-generated method stub
		System.out.println("*********************************************************************************************************************");
		System.out.println("*********************************************************************************************************************");
		System.out.println("*********************************************************************************************************************");
		System.out.println(this.personaRolDao.updateRolesPersona(idPersona, hasPermisosEduC, hasPermisosPer, hasPermisosAtt, idsProEduContinua,
				idsProEst, idsDeptoDoc, idsProGrad, idsEduAtt, hasPermisosAdminvo, hasPermisosExter, isDirPrograma, isDocente, idProgramaDirector));
		return false;
	}

	@Override
	public UsuarioDto editarUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		Optional<Persona> p= personaDao.findById(idUsuario);
		Optional<Estudiante> es=estudianteDao.findById(idUsuario);
		Optional<Docente> doc=docenteDao.findById(idUsuario);
		Optional<Administrativo> ad=administrativoDao.findById(idUsuario);
		Optional<Graduado> gr=graduadoDao.findById(idUsuario);
		Optional<Externo> ex=externoDao.findById(idUsuario);
		if(p!=null) {
			return usuarioMapper.convertPersonaToUsuarioDto(p.get(),es,doc,ad,gr,ex);
		}
		return null;
	}

}
