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
import org.springframework.transaction.annotation.Transactional;

import com.ufps.cedcufps.dao.IAdministrativoDao;
import com.ufps.cedcufps.dao.IDepartamentoDao;
import com.ufps.cedcufps.dao.IDocenteDao;
import com.ufps.cedcufps.dao.IEstadoCivilDao;
import com.ufps.cedcufps.dao.IEstudianteDao;
import com.ufps.cedcufps.dao.IExternoDao;
import com.ufps.cedcufps.dao.IGeneroDao;
import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.dao.ITipoDocumentoDao;
import com.ufps.cedcufps.dao.ITipoPersonaDao;
import com.ufps.cedcufps.modelos.Administrativo;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.EstadoCivil;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Genero;
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
	private IEstudianteDao estudianteDao;
	
	@Autowired
	private IDocenteDao docenteDao;
	
	@Autowired
	private IAdministrativoDao administrativoDao;
	
	@Autowired
	private IExternoDao externoDao;
	
	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;
	
	@Autowired
	private IEstadoCivilDao estadoCivilDao;
	
	@Autowired
	private IGeneroDao generoDao;
	
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		return personaDao.findByUsername(userDetail.getUsername());
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
		return (List<Docente>)docenteDao.findAll();
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

	

}
