package com.ufps.cedcufps.services;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.ufps.cedcufps.dao.IEstadoCivilDao;
import com.ufps.cedcufps.dao.IEstudianteDao;
import com.ufps.cedcufps.dao.IExternoDao;
import com.ufps.cedcufps.dao.IGeneroDao;
import com.ufps.cedcufps.dao.IGraduadoDao;
import com.ufps.cedcufps.dao.IPersonaCustomDao;
import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.dao.ITipoDocumentoDao;
import com.ufps.cedcufps.dao.ITipoPersonaDao;
import com.ufps.cedcufps.dto.UsuarioAppDto;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.mapper.IUsuarioMapper;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.EstadoCivil;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Genero;
import com.ufps.cedcufps.modelos.Graduado;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.Rol;
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
	private IProgramaDao programaDao;
	
	@Autowired
	private IDepartamentoDao departamentoDao;
	
	@Autowired
	private ITipoPersonaDao tipoPersonaDao;
	
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
		for(Rol r:p.getRoles()) {
			logger.info("Role:".concat(r.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(r.getAuthority()));
		}
		
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
	public void registrarse(UsuarioDto u) {
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
		Persona p= usuarioMapper.convertUsuarioToPersona(u);
		logger.info("llega de mappear");
		personaDao.save(p);
		logger.info("guarda la persona");
		System.out.println(u.getIsEstudiante());
		System.out.println(u.getIsDocente());
		System.out.println(u.getIsAdministrativo());
		System.out.println(u.getIsGraduado());
		System.out.println(u.getIsExterno());
		if(u.getIsExterno()==1) {
			logger.info("Es externo");
			Externo e=usuarioMapper.convertUsuarioToExterno(u,p.getId());
			logger.info(e.getId().toString());
			logger.info(e.getEmpresa());
			logger.info(e.getProfesion());
			personaCustomDao.saveExterno(e);
		}else {
			if(u.getIsEstudiante()==1) {
				logger.info("Es estudiante");
				Estudiante e=usuarioMapper.convertUsuarioToEstudiante(u,p.getId());
				logger.info(e.getId().toString());
				logger.info(e.getPrograma().getPrograma());
				logger.info(e.getCodigo());
				personaCustomDao.saveEstudiante(e);
			}
			if(u.getIsDocente()==1) {
				logger.info("Es docente");
				Docente d= usuarioMapper.convertUsuarioToDocente(u,p.getId());
				logger.info(d.getId().toString());
				logger.info(d.getDepartamento().getDepartamento());
				logger.info(d.getCodigo());
				personaCustomDao.saveDocente(d);
			}
			if(u.getIsAdministrativo()==1) {
				logger.info("Es administrativo");
				Administrativo a= usuarioMapper.convertUsuarioToAdministrativo(u,p.getId());
				logger.info(a.getId().toString());
				logger.info(a.getDependencia());
				logger.info(a.getCargo());
				personaCustomDao.saveAdministrativo(a);
			}
			if(u.getIsGraduado()==1) {
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

	

}
