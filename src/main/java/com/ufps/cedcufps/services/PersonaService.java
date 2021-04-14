package com.ufps.cedcufps.services;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.PerThisOrTargetPointcutVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
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
import com.ufps.cedcufps.dao.IDepartamentoCustomDao;
import com.ufps.cedcufps.dao.IDepartamentoDao;
import com.ufps.cedcufps.dao.IDocenteDao;
import com.ufps.cedcufps.dao.IEducacionContinuaCustomDao;
import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dao.IEstadoCivilDao;
import com.ufps.cedcufps.dao.IEstudianteDao;
import com.ufps.cedcufps.dao.IExternoDao;
import com.ufps.cedcufps.dao.IGeneroDao;
import com.ufps.cedcufps.dao.IGraduadoDao;
import com.ufps.cedcufps.dao.IPersonaCustomDao;
import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dao.IPersonaRolCustomDao;
import com.ufps.cedcufps.dao.IProgramaCustomDao;
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.dao.ITipoDocumentoDao;
import com.ufps.cedcufps.dao.ITipoPersonaDao;
import com.ufps.cedcufps.dao.impl.EducacionContinuaCustomDaoImpl;
import com.ufps.cedcufps.dto.DocenteDto;
import com.ufps.cedcufps.dto.PerfilRolUsuarioDto;
import com.ufps.cedcufps.dto.PermisosRegistroPersonaDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.PersonaDtoLogueada;
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
import com.ufps.cedcufps.utils.RolUtil;
import com.ufps.cedcufps.utils.TipoPersonaUtil;

@Service
public class PersonaService implements IPersonaService {

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
	private IProgramaCustomDao programaCustomDao;
	
	@Autowired
	private IDepartamentoCustomDao departamentoCustomDao;
	
	@Autowired
	private ITipoPersonaDao tipoPersonaDao;
	
	@Autowired
	private IEducacionContinuaDao educacionContinuaDao;
	
	@Autowired
	private IPersonaRolCustomDao personaRolCustomDao;
	
	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private IEducacionContinuaCustomDao educacionContinuaCustomDao;
	

	
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
	public Persona findPersonaLogueada() {
		// TODO Auto-generated method stub
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		return personaDao.findByUsername(userDetail.getUsername());*/
		logger.debug("metodo find persona logueada");
		return this.findByEmail(this.findEmailPersonaLogueada());
	}

	@Override
	public String findEmailPersonaLogueada() {
		logger.debug("antes de obtener autenticacion");
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		logger.debug("antes de token");
		OAuth2AuthenticationToken client= (OAuth2AuthenticationToken )a;
		logger.debug("antes de obtener email");
		String email=client.getPrincipal().getAttribute("email");
		logger.debug("el email es: " + email);
		return email;
	}
	
	@Override
	public PersonaDto findOne(Long id) {
		// TODO Auto-generated method stub
		return usuarioMapper.convertPersonaToPersonaDto(personaCustomDao.findPersonaById(id));
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
	public List<Estudiante> findAllEstudiantes() {
		// TODO Auto-generated method stub
		return (List<Estudiante>)estudianteDao.findAll();
	}

	@Override
	public List<DocenteDto> findAllDocentes() {
		// TODO Auto-generated method stub
		return personaCustomDao.findAllDocentesActivos();
		
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
	@Transactional(rollbackFor = CustomException.class)
	public Persona findByEmail(String email) {
		// TODO Auto-generated method stub
		try {
			Persona p=personaCustomDao.findPersonaByEmail(email);
			if(p!=null) {
				p.setPersonaXRoles(personaCustomDao.findRolesPersona(p.getId()));
			}
			
			return p;
		}catch(Exception e) {
			return null;
		}
		
		
	}

	@Override
	public void guardar(UsuarioDto u) {
		// TODO Auto-generated method stub
		if(u.getId()==0) {
			this.addUsuario(u);
			this.prepararEmailRegistro(u);
		}else {
			this.updateUsuario(u);
		}
		
		
	}

	public void addUsuario(UsuarioDto u) {
		Persona p= usuarioMapper.convertUsuarioToPersona(u);
		Persona per=personaDao.save(p);
		personaRolCustomDao.save("ROLE_USER", per.getId());
		logger.info("guarda la persona");
		if(u.isExterno()) {
			logger.info("Es externo");
			Externo e=usuarioMapper.convertUsuarioToExterno(u,per.getId());
			logger.info(e.getId().toString());
			logger.info(e.getEmpresa());
			logger.info(e.getProfesion());
			personaCustomDao.saveExterno(e);
		}else {
			if(u.isEstudiante()) {
				logger.info("Es estudiante");
				Estudiante e=usuarioMapper.convertUsuarioToEstudiante(u,per.getId());
				logger.info(e.getId().toString());
				logger.info(e.getPrograma().getPrograma());
				logger.info(e.getCodigo());
				personaCustomDao.saveEstudiante(e);
			}
			if(u.isDocente()) {
				logger.info("Es docente");
				Docente d= usuarioMapper.convertUsuarioToDocente(u,per.getId());
				logger.info(d.getId().toString());
				logger.info(d.getDepartamento().getDepartamento());
				logger.info(d.getCodigo());
				personaCustomDao.saveDocente(d);
			}
			if(u.isAdministrativo()) {
				logger.info("Es administrativo");
				Administrativo a= usuarioMapper.convertUsuarioToAdministrativo(u,per.getId());
				logger.info(a.getId().toString());
				logger.info(a.getDependencia());
				logger.info(a.getCargo());
				personaCustomDao.saveAdministrativo(a);
			}
			if(u.isGraduado()) {
				logger.info("Es graduado");
				Graduado g= usuarioMapper.convertUsuarioToGraduado(u,per.getId());
				logger.info(g.getId().toString());
				logger.info(g.getPrograma().getPrograma());
				logger.info(g.getAnio());
				personaCustomDao.saveGraduado(g);
			}
		}
	}
	
	public void updateUsuario(UsuarioDto u) {
		Persona p= usuarioMapper.convertUsuarioToPersona(u);
		logger.info("llega de mappear");
		//personaDao.save(p);
		logger.info("guarda la persona");
		personaDao.updateOnlyPersona(p.getTipoDocumento().getId(), p.getNumeroDocumento(), p.getFechaExpedicionDocumento(),
				p.getPrimerNombre(), p.getSegundoNombre(), p.getPrimerApellido(), p.getSegundoApellido(), p.getGenero().getId(),
				p.getEstadoCivil().getId(), p.getFechaNacimiento(), p.getIdPaisNacimiento(), p.getIdDepartamentoNacimiento(),
				p.getIdMunicipioNacimiento(), p.getEmail(), p.getDireccion(), p.getTelefono(), p.isEstudiante(), p.isDocente(), 
				p.isAdministrativo(), p.isGraduado(), p.isExterno(), p.getIdsTipoPersona(), p.getId());
		
		//deshabilitar estados de los perfiles existentes para actualizar los nuevos
		personaDao.updateEstadoEstudiante(false, p.getId());
		personaDao.updateEstadoDocente(false, p.getId());
		personaDao.updateEstadoAdministrativo(false, p.getId());
		personaDao.updateEstadoGraduado(false, p.getId());
		personaDao.updateEstadoExterno(false, p.getId());
		
		if(u.isExterno()) {
			logger.info("Es externo");
			Externo e=usuarioMapper.convertUsuarioToExterno(u,p.getId());
			logger.info(e.getId().toString());
			logger.info(e.getEmpresa());
			logger.info(e.getProfesion());
			externoDao.updateOnlyExterno(e.getProfesion(), e.getEmpresa(), p.getId());
		}else {
			if(u.isEstudiante()) {
				logger.info("Es estudiante");
				Estudiante e=usuarioMapper.convertUsuarioToEstudiante(u,p.getId());
				logger.info(e.getId().toString());
				logger.info(e.getPrograma().getPrograma());
				logger.info(e.getCodigo());
				estudianteDao.updateOnlyEstudiante(e.getCodigo(), e.getPrograma().getId(), p.getId());
				//personaCustomDao.saveEstudiante(e);
			}
			if(u.isDocente()) {
				logger.info("Es docente");
				Docente d= usuarioMapper.convertUsuarioToDocente(u,p.getId());
				logger.info(d.getId().toString());
				logger.info(d.getDepartamento().getDepartamento());
				logger.info(d.getCodigo());
				docenteDao.updateOnlyDocente(d.getCodigo(),d.getDepartamento().getId(), p.getId());
				//personaCustomDao.saveDocente(d);
			}
			if(u.isAdministrativo()) {
				logger.info("Es administrativo");
				Administrativo a= usuarioMapper.convertUsuarioToAdministrativo(u,p.getId());
				logger.info(a.getId().toString());
				logger.info(a.getDependencia());
				logger.info(a.getCargo());
				administrativoDao.updateOnlyAdministrativo(a.getDependencia(), a.getCargo(), p.getId());
				//personaCustomDao.saveAdministrativo(a);
			}
			if(u.isGraduado()) {
				logger.info("Es graduado");
				Graduado g= usuarioMapper.convertUsuarioToGraduado(u,p.getId());
				logger.info(g.getId().toString());
				logger.info(g.getPrograma().getPrograma());
				logger.info(g.getAnio());
				graduadoDao.updateOnlyGraduado(g.getAnio(), g.getPrograma().getId(), p.getId());
				//personaCustomDao.saveGraduado(g);
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
		Persona p=this.findPersonaLogueada();
		if(this.isSuperAdmin(p)) {
			List<Persona> personas=personaCustomDao.findPersonasList(null, true);
			personas=this.removePersona(personas, p);
			return usuarioMapper.convertListPersonasToPersonaDto(personas);
		}else {
			if(this.hasPermissionForPeople(p)) {
				this.personaCustomDao.listAllPossiblePeople(p.getId());
				List<Persona> personas=personaCustomDao.findPersonasList(this.personaCustomDao.listAllPossiblePeople(p.getId()),false);
				personas=this.removePersona(personas, p);
				return usuarioMapper.convertListPersonasToPersonaDto(personas);
			}
		}
		
		return null;
		
	}
	
	public List<Persona> removePersona(List<Persona> personas, Persona p){
		for(Persona per: personas) {
			if(per.getId()==p.getId()) {
				personas.remove(per);
				break;
			}
		}
		return personas;
	}
	
	@Override
	public boolean isSuperAdmin() {
		Persona p=this.findPersonaLogueada();
		
		for(PersonaRol pr: p.getPersonaXRoles()) {
			if(pr.getRol().getAuthority().equalsIgnoreCase("ROLE_SUPERADMIN")) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isSuperAdmin(Persona p) {
		if(p!=null) {
			for(PersonaRol pr: p.getPersonaXRoles()) {
				if(pr.getRol().getAuthority().equalsIgnoreCase("ROLE_SUPERADMIN")) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public boolean isDirPrograma() {
		Persona p=this.findPersonaLogueada();
		return (this.programaCustomDao.findProgramaDtoByDirector(p.getId()) != null );
		
	}
	
	@Override
	public boolean isDirPrograma(Persona p) {
		return (this.programaCustomDao.findProgramaDtoByDirector(p.getId()) != null );
		
	}
	
	@Override
	public boolean isDocente() {
		Persona p=this.findPersonaLogueada();
		return (this.docenteDao.findOnlyDocente(p.getId()) > 0 );
		
	}
	
	@Override
	public boolean isDocente(Persona p) {
		logger.debug("id persona en is docente: " + p.getId());
		logger.info("id persona en is docente: " + p.getId());
		return this.docenteDao.findOnlyDocente(p.getId()) > 0  ;
		
	}
	
	
	
	
	@Override
	public boolean hasPermissionForPeople(Persona p) {
		if(p!=null) {
			for(PersonaRol pr: p.getPersonaXRoles()) {
				if(pr.getRol().getAuthority().equalsIgnoreCase("ROLE_MANPEOPLE")) {
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	@Override
	public boolean hasPermissionForEduContinua(Long idPersona) {
		return personaCustomDao.hasPermisos(idPersona, RolUtil.ROLE_MANAECCU);
	}
	
	@Override
	public boolean hasPermissionForAttendance(Long idPersona) {
		
		return personaCustomDao.hasPermisos(idPersona, RolUtil.ROLE_ATTENDANCE);
	}

	@Override
	public boolean hasPermissionForAttendance(Persona p) {
		// TODO Auto-generated method stub
		if(p!=null) {
			for(PersonaRol pr: p.getPersonaXRoles()) {
				if(pr.getRol().getAuthority().equalsIgnoreCase("ROLE_ATTENDANCE")) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public PerfilRolUsuarioDto findPermisos(String idAcceso) {
		// TODO Auto-generated method stub
		Persona autoridad= this.findPersonaLogueada();
		Persona usuario= personaCustomDao.findPersonaByIdAcceso(idAcceso);
		PerfilRolUsuarioDto dto= new PerfilRolUsuarioDto();
		if(usuario!=null) {
			dto.setIdPersona(usuario.getId());
			dto.setNombre(usuarioMapper.convertFieldsFullName(usuario));
			dto.setDocumento(usuario.getTipoDocumento().getTipoDocumento() + " - " + usuario.getNumeroDocumento());
			dto.setEstudiante(usuario.isEstudiante());
			dto.setDocente(usuario.isDocente());
			dto.setAdministrativo(usuario.isAdministrativo());
			dto.setGraduado(usuario.isGraduado());
			dto.setExterno(usuario.isExterno());
			dto.setSuperadmin(autoridad.getId().equals(usuario.getId()));
			if(this.isSuperAdmin(autoridad)) {//quien asigna permisos es superadmin
				ProgramaDto p=programaCustomDao.findProgramaDtoByDirector(usuario.getId());
				dto.setDirPrograma(p!=null);
				if(p!=null) {//usuario a quien le vamos a dar permisos es director programa
					dto.setIdProgramaDirector(p.getId());
					dto.setProgramaDirector(p.getPrograma());
					dto.setHasPermissionForEduContinua(this.hasPermissionForEduContinua(usuario.getId()));
					dto.setHasPermissionForUsuarios(this.hasPermissionForPeople(usuario));
					dto.setHasPermissionForAttendance(this.hasPermissionForAttendance(usuario.getId()));
					dto.setProgramasForEduContinua(this.programaCustomDao.findProgramasPermisosEduContinuaForDirProgramaExceptOwn(usuario.getId(), p.getId()));
					List<Programa> programasPosiblesSelectEduC=(List<Programa>)this.programaDao.findAll();
					programasPosiblesSelectEduC.remove(p);
					dto.setSelectProgramasForEduContinua(programaMapper.convertListProgramaToProgramaDto(programasPosiblesSelectEduC));
					
					dto.setProgramasForEstudiantes(this.programaCustomDao.findProgramasPermisosEstudiantesForDirProgramaExceptOwn(usuario.getId(), p.getId()));
					dto.setProgramasForGraduados(this.programaCustomDao.findProgramasPermisosGraduadosForDirProgramaExceptOwn(usuario.getId(), p.getId()));
					dto.setDeptosForDocentes(this.departamentoCustomDao.findDeptosPermisosDocentesForDocEstAdminvo(usuario.getId()));// el director tiene comportamiento de docente para la gestión de otros docentes de un departamento
					dto.setSelectDeptosForDocentes(this.departamentoCustomDao.findAll());
					List<Programa> programasPosiblesSelectEst=(List<Programa>)this.programaDao.findAll();
					programasPosiblesSelectEst.remove(p);
					dto.setSelectProgramasForEstudiantes(programaMapper.convertListProgramaToProgramaDto(programasPosiblesSelectEst));
					List<Programa> programasPosiblesSelectGra=(List<Programa>)this.programaDao.findAll();
					programasPosiblesSelectGra.remove(p);
					dto.setSelectProgramasForGraduados(programaMapper.convertListProgramaToProgramaDto(programasPosiblesSelectGra));
					dto.setSelectEduContinuasForAttendance(educacionContinuaCustomDao.findEduContinuasPermissionForAttendance(autoridad.getId(), true, false));
					dto.setEduContinuasForAttendance(educacionContinuaCustomDao.findEduContinuasPermissionForAttendance(usuario.getId()));
					dto.setHasPermissionForAdminvos(this.personaDao.hasPermissionForAdminvos(usuario.getId())>0);
					dto.setHasPermissionForExternos(this.personaDao.hasPermissionForExternos(usuario.getId())>0);
					List<ProgramaDto> programaEdCIntocable= new ArrayList<ProgramaDto>();
					programaEdCIntocable.add(p);
					dto.setProgramasForEduContinuaIntocables(programaEdCIntocable);
					List<ProgramaDto> programaEstIntocable= new ArrayList<ProgramaDto>();
					programaEstIntocable.add(p);
					dto.setProgramasForEstudiantesIntocables(programaEstIntocable);
					List<ProgramaDto> programaGradIntocable= new ArrayList<ProgramaDto>();
					programaGradIntocable.add(p);
					dto.setProgramasForGraduadosIntocables(programaGradIntocable);
					return dto;
				}else {
					dto.setIdProgramaDirector(null);
					dto.setProgramaDirector(null);
					dto.setHasPermissionForEduContinua(this.hasPermissionForEduContinua(usuario.getId()));
					dto.setHasPermissionForUsuarios(this.hasPermissionForPeople(usuario));
					dto.setHasPermissionForAttendance(this.hasPermissionForAttendance(usuario.getId()));
					dto.setProgramasForEduContinua(this.programaCustomDao.findProgramasPermisosEduContinuaForDocEstAdminvo(usuario.getId()));
					dto.setSelectProgramasForEduContinua(programaMapper.convertListProgramaToProgramaDto((List<Programa>)this.programaDao.findAll()));
					dto.setProgramasForEstudiantes((this.programaCustomDao.findProgramasPermisosEstudiantesForDocEstAdminvo(usuario.getId())));
					dto.setProgramasForGraduados((this.programaCustomDao.findProgramasPermisosGraduadosForDocEstAdminvo(usuario.getId())));
					dto.setDeptosForDocentes(this.departamentoCustomDao.findDeptosPermisosDocentesForDocEstAdminvo(usuario.getId()));
					dto.setSelectDeptosForDocentes(this.departamentoCustomDao.findAll());
					dto.setSelectProgramasForEstudiantes(programaMapper.convertListProgramaToProgramaDto((List<Programa>)this.programaDao.findAll()));
					dto.setSelectProgramasForGraduados(programaMapper.convertListProgramaToProgramaDto((List<Programa>)this.programaDao.findAll()));
					dto.setSelectEduContinuasForAttendance(educacionContinuaCustomDao.findEduContinuasPermissionForAttendance(autoridad.getId(), true, false));
					dto.setEduContinuasForAttendance(educacionContinuaCustomDao.findEduContinuasPermissionForAttendance(usuario.getId()));
					dto.setHasPermissionForAdminvos(this.personaDao.hasPermissionForAdminvos(usuario.getId())>0);
					dto.setHasPermissionForExternos(this.personaDao.hasPermissionForExternos(usuario.getId())>0);
					return dto;
				}
			}else if (this.isDirPrograma()) {
				ProgramaDto pDirector=programaCustomDao.findProgramaDtoByDirector(autoridad.getId());
				dto.setIdProgramaDirector(null);
				dto.setProgramaDirector(null);
				dto.setHasPermissionForEduContinua(this.hasPermissionForEduContinua(usuario.getId()));
				dto.setHasPermissionForUsuarios(this.hasPermissionForPeople(usuario));
				dto.setHasPermissionForAttendance(this.hasPermissionForAttendance(usuario.getId()));
				dto.setProgramasForEduContinua(this.programaCustomDao.findProgramasPermisosEduContinuaForDocEstAdminvo(usuario.getId()));
				List<ProgramaDto> selectProgramasDir= new ArrayList<ProgramaDto>();
				selectProgramasDir.add(pDirector);
				dto.setSelectProgramasForEduContinua(selectProgramasDir);
				dto.setProgramasForEstudiantes(this.programaCustomDao.findProgramasPermisosEstudiantesForDocEstAdminvo(usuario.getId()));
				dto.setProgramasForGraduados(this.programaCustomDao.findProgramasPermisosGraduadosForDocEstAdminvo(usuario.getId()));
				dto.setDeptosForDocentes(this.departamentoCustomDao.findDeptosPermisosDocentesForDocEstAdminvo(usuario.getId()));
				dto.setSelectDeptosForDocentes(this.departamentoCustomDao.findAll());
				dto.setSelectProgramasForEstudiantes(selectProgramasDir);
				dto.setSelectProgramasForGraduados(selectProgramasDir);
				dto.setSelectEduContinuasForAttendance(educacionContinuaCustomDao.findEduContinuasPermissionForAttendance(autoridad.getId(), false, true));
				dto.setEduContinuasForAttendance(educacionContinuaCustomDao.findEduContinuasPermissionForAttendance(usuario.getId()));
				dto.setHasPermissionForAdminvos(this.personaDao.hasPermissionForAdminvos(usuario.getId())>0);
				dto.setHasPermissionForExternos(this.personaDao.hasPermissionForExternos(usuario.getId())>0);
				dto.setProgramasForEduContinuaIntocables(this.programaCustomDao.findProgramasPermisosEduContinuaForDirProgramaExceptOwn(usuario.getId(), pDirector.getId()));
				dto.setProgramasForEstudiantesIntocables(this.programaCustomDao.findProgramasPermisosEstudiantesForDirProgramaExceptOwn(usuario.getId(), pDirector.getId()));
				dto.setProgramasForGraduadosIntocables(this.programaCustomDao.findProgramasPermisosGraduadosForDirProgramaExceptOwn(usuario.getId(), pDirector.getId()));
				dto.setEduContinuasForAttendanceIntocables(educacionContinuaCustomDao.findEduContinuasPermissionForAttendanceExceptDirectorPrograma(usuario.getId(),pDirector.getId()));
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
		this.personaRolCustomDao.updateRolesPersona(idPersona, hasPermisosEduC, hasPermisosPer, hasPermisosAtt, idsProEduContinua,
				idsProEst, idsDeptoDoc, idsProGrad, idsEduAtt, hasPermisosAdminvo, hasPermisosExter, isDirPrograma, isDocente, idProgramaDirector);
		return false;
	}

	@Override
	public UsuarioDto editarUsuario(String idAcceso) {
		// TODO Auto-generated method stub
		Persona p= personaCustomDao.findPersonaByIdAcceso(idAcceso);
		return this.convertPersonaToUsuarioDto(p);
	}
	
	public UsuarioDto convertPersonaToUsuarioDto(Persona p) {
		Estudiante es=personaCustomDao.findOnlyEstudiante(p.getId());
		Docente doc=personaCustomDao.findOnlyDocente(p.getId());
		Administrativo ad=personaCustomDao.findOnlyAdministrativo(p.getId());
		Graduado gr=personaCustomDao.findOnlyGraduado(p.getId());
		Externo ex=personaCustomDao.findOnlyExterno(p.getId());
		if(p!=null) {
			UsuarioDto dto = usuarioMapper.convertPersonaToUsuarioDto(p,es,doc,ad,gr,ex);
			dto.setSuperAdmin(this.isSuperAdmin(p));
			dto.setDirPrograma(this.isDirPrograma(p));
			return dto;
		}
		return null;
	}

	@Override
	public DataTablesOutput<PersonaDto> findPossiblePonente(int tipoBusqueda, String value) {
		// TODO Auto-generated method stub
		List<Persona> p=null;
		DataTablesOutput<PersonaDto> dto= new DataTablesOutput<>();
		
		switch(tipoBusqueda) {
			case 1:
				p=personaCustomDao.findPersonasByNumeroDocumento(value);
				break;
			case 2:
				p=personaCustomDao.findPosiblePonenteByNombre("%"+value+"%");
				break;
			case 3:
				p=estudianteDao.findEstudianteByCodigo(value);
				break;
			case 4:
				p=docenteDao.findDocenteByCodigo(value);
				break;
			case 5:
				p=personaCustomDao.findPersonasByEmail(value);
				break;
		}
		
		if(p!=null) {
			List<PersonaDto> personasDto=usuarioMapper.convertListPersonasToPersonaDto(p);
			dto.setData(personasDto);
			dto.setRecordsFiltered(personasDto.size());
			dto.setRecordsTotal(p.size());
			return dto;
		}
		
		return null;
	}
	
	
	
	@Override
	public PersonaDtoLogueada findPersonaLogueadaDto(Persona p) {
		PersonaDtoLogueada dto = new PersonaDtoLogueada();
		dto.setIdPersona(p.getId());
		dto.setSuperAdmin(this.isSuperAdmin(p));
		dto.setDirPrograma(this.isDirPrograma(p));
		dto.setDocente(p.isDocente());
		dto.setHasPermisosEdC(this.hasPermissionForEduContinua(p.getId()));
		dto.setHasPermisosOnlyMyEdC(this.hasPermisosOnlyMyEdC(p.getId()));
		return dto;
	}
	
	public boolean hasPermisosOnlyMyEdC(Long idPersona) {
		
		return personaCustomDao.hasPermisosOnlyMyEdC(idPersona, RolUtil.ROLE_MANAECCU);
	}

	@Override
	public Persona findPersonaById(Long idPersona) {
		// TODO Auto-generated method stub
		return personaCustomDao.findPersonaById(idPersona);
	}

	public void prepararEmailRegistro(UsuarioDto usuario) {
		
	     
		String contenido="La inscripción al sistema Gestión para la Educación Continua "
				+ " se ha realizado exitosamente. Recuerde iniciar sesión mediante este correo para inscribirse y"
				+ " participar de las educaciones continuas ofertadas por la UFPS";
		String nombre="";
		if(!usuario.getPrimerNombre().isEmpty()) {
			nombre=usuario.getPrimerNombre();
		}
		if(!usuario.getSegundoNombre().isEmpty()) {
			nombre=nombre + ' ' + usuario.getSegundoNombre();
		}
		if(!usuario.getPrimerApellido().isEmpty()) {
			nombre=nombre + ' ' + usuario.getPrimerApellido();
		}
		if(!usuario.getSegundoApellido().isEmpty()) {
			nombre=nombre + ' ' + usuario.getSegundoApellido();
		}
		notificarViaEmail(usuario.getEmail(), "Registro realizado", contenido, nombre);
	}
	
	
	
	public void notificarViaEmail(String email, String asunto, String contenido, String nombreUsuario) {
		emailService.sendEmailRegistro(email, asunto, contenido, nombreUsuario);
		
	}

	@Override
	public UsuarioDto findMyInfo() {
		// TODO Auto-generated method stub
		Persona p= this.findPersonaLogueada();
		return this.convertPersonaToUsuarioDto(p);
	}

	@Override
	public PersonaDto findUsuarioAppByDocumento(String documento) {
		// TODO Auto-generated method stub
		
		Persona p=personaCustomDao.findPersonaByNumeroDocumento(documento);
		if(p!=null) {
			return usuarioMapper.convertPersonaToPersonaDto(p);
		}
		return null;
		
	}
	
	

	@Override
	public void updateSuperAdmin(String documento) {
		// TODO Auto-generated method stub
		
		
		Persona p = personaCustomDao.findPersonaByNumeroDocumento(documento);
		if(p!=null) {
			
			personaRolCustomDao.deleteRol("ROLE_SUPERADMIN", this.findPersonaLogueada().getId());
			personaRolCustomDao.save("ROLE_SUPERADMIN", p.getId());
		}else {
			throw new CustomException("No se encontró el usuario al cuál se le va a otorgar el rol de super admin");
		}
	}

	@Override
	public void updateDirPrograma(String documento) {
		// TODO Auto-generated method stub
		Persona p = personaCustomDao.findPersonaByNumeroDocumento(documento);
		if(p!=null) {
			Persona personaLogueada=this.findPersonaLogueada();
			ProgramaDto programa=programaCustomDao.findProgramaDtoByDirector(personaLogueada.getId());
			personaRolCustomDao.deleteRolesDirPrograma(personaLogueada.getId());
			personaRolCustomDao.deleteRolesDirPrograma(p.getId());
			personaRolCustomDao.asignarPermisosDirector(p.getId(), programa.getId());
			programaDao.vincularDirectorPrograma(p.getId(), programa.getId());
			
		}else {
			throw new CustomException("No se encontró el usuario al cuál se le va a otorgar el rol de super admin");
		}
	}

	

	@Override
	public PermisosRegistroPersonaDto findPermisosRegistrarPersonas(Long idPersonaEdit, boolean registroParaOtro) {
		// TODO Auto-generated method stub
		
		PermisosRegistroPersonaDto dto=new PermisosRegistroPersonaDto();
		
		if(registroParaOtro) {
			Persona p = this.findPersonaLogueada();
			if(this.isSuperAdmin(p)) {
				dto.setAbleEditEstudiantes(true);
				dto.setAbleEditDocentes(true);
				dto.setAbleEditAdministrativos(true);
				dto.setAbleEditGraduados(true);
				dto.setAbleEditExternos(true);
				dto.setProgramasEstudiantes(programaCustomDao.findAllProgramas());
				dto.setProgramasGraduados(programaCustomDao.findAllProgramas());
				dto.setDepartamentosDocentes(departamentoCustomDao.findAll());
				return dto;
			}else if(this.hasPermissionForPeople(p)) {
				dto.setAbleEditEstudiantes(personaRolCustomDao.findPermisosTipoPersona(p.getId(), TipoPersonaUtil.ESTUDIANTE));
				dto.setAbleEditDocentes(personaRolCustomDao.findPermisosTipoPersona(p.getId(), TipoPersonaUtil.DOCENTE));
				dto.setAbleEditAdministrativos(personaRolCustomDao.findPermisosTipoPersona(p.getId(), TipoPersonaUtil.ADMINISTRATIVO));
				dto.setAbleEditGraduados(personaRolCustomDao.findPermisosTipoPersona(p.getId(), TipoPersonaUtil.GRADUADO));
				dto.setAbleEditExternos(personaRolCustomDao.findPermisosTipoPersona(p.getId(), TipoPersonaUtil.EXTERNO));
				dto.setProgramasEstudiantes(personaRolCustomDao.findProgramasPermissionEstudiante(p.getId(), idPersonaEdit));
				dto.setProgramasGraduados(personaRolCustomDao.findProgramasPermissionGraduados(p.getId(), idPersonaEdit));
				dto.setDepartamentosDocentes(personaRolCustomDao.findDeptosPermissionDocentes(p.getId(), idPersonaEdit));
				return dto;
			}
		}else {
			dto.setAbleEditEstudiantes(true);
			dto.setAbleEditDocentes(true);
			dto.setAbleEditAdministrativos(true);
			dto.setAbleEditGraduados(true);
			dto.setAbleEditExternos(true);
			dto.setProgramasEstudiantes(programaCustomDao.findAllProgramas());
			dto.setProgramasGraduados(programaCustomDao.findAllProgramas());
			dto.setDepartamentosDocentes(departamentoCustomDao.findAll());
			return dto;
		}
			
		
		
		
		return null;
	}
}
