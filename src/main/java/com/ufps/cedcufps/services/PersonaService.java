package com.ufps.cedcufps.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufps.cedcufps.dao.IPersonaDao;
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.dao.ITipoDocumentoDao;
import com.ufps.cedcufps.dao.ITipoPersonaDao;
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
	private ITipoDocumentoDao tipoDocumentoDao;
	
	@Autowired
	private IProgramaDao programaDao;
	
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
		return new User(p.getUsername(), p.getPassword(), p.isEnabled(), true, true, true, authorities);
	}
	

}
