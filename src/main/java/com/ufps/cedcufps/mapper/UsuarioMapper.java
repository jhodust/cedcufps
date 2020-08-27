package com.ufps.cedcufps.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IDepartamentoDao;
import com.ufps.cedcufps.dao.IEstadoCivilDao;
import com.ufps.cedcufps.dao.IGeneroDao;
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.dao.ITipoDocumentoDao;
import com.ufps.cedcufps.dao.ITipoPersonaDao;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.dto.UsuarioAppDto;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.exception.CustomException;
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

@Repository
public class UsuarioMapper implements IUsuarioMapper {

	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;
	
	@Autowired
	private IGeneroDao generoDao;
	
	@Autowired
	private IEstadoCivilDao estadoCivilDao;
	
	@Autowired
	private ITipoPersonaDao tipoPersonaDao;
	
	@Autowired
	private IProgramaDao programaDao;
	
	@Autowired
	private IDepartamentoDao departamentoDao;
	
	@Override
	public Persona convertUsuarioToPersona(UsuarioDto u) {
		// TODO Auto-generated method stub
		Persona pe=new Persona();
		TipoDocumento td=null;
		Genero g=null;
		EstadoCivil ec=null;
		TipoPersona tp=null;
		Programa p=null;
		try {
			td = tipoDocumentoDao.findById(u.getTipoDocumento()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el tipo de documento en la base de datos");
		}
		
		try {
			g= generoDao.findById(u.getGenero()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el género en la base de datos");
		}
		
		try {
			ec= estadoCivilDao.findById(u.getEstadoCivil()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el estado civil en la base de datos");
		}
		
		try {
			tp= tipoPersonaDao.findById((long) 1).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el tipo usuario en la base de datos");
		}
			
		
		try {
			p= programaDao.findById(u.getPrograma()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el programa en la base de datos");
		}	
			
		  
		
		pe.setTipoDocumento(td);
		pe.setNumeroDocumento(u.getNumeroDocumento());
		try {
			pe.setFechaExpedicionDocumento(new SimpleDateFormat("dd/MM/yyyy").parse(u.getFechaExpedicionDocumento()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			throw new CustomException("Fecha Expedición Documento inválida");
		}
		pe.setPrimerNombre(u.getPrimerNombre());
		pe.setSegundoNombre(u.getSegundoNombre());
		pe.setPrimerApellido(u.getPrimerApellido());
		pe.setSegundoApellido(u.getSegundoApellido());
		pe.setGenero(g);
		pe.setEstadoCivil(ec);
		try {
			pe.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(u.getFechaNacimiento()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			throw new CustomException("Fecha Nacimiento inválida");
		}
		pe.setIdPaisNacimiento(u.getIdPaisNacimiento());
		pe.setIdMunicipioNacimiento(u.getIdMunicipioNacimiento());
		pe.setIdDepartamentoNacimiento(u.getIdDepartamentoNacimiento());
		pe.setEmail(u.getEmail());
		pe.setDireccion(u.getDireccion());
		pe.setTelefono(u.getTelefono());
		
		if(u.getIsExterno()==1) {
			pe.setExterno(true);
		}else {
			if(u.getIsEstudiante()==1) {
				pe.setEstudiante(true);
			}
			if(u.getIsDocente()==1) {
				pe.setDocente(true);
			}
			if(u.getIsAdministrativo()==1) {
				pe.setAdministrativo(true);
			}
			if(u.getIsGraduado()==1) {
				pe.setGraduado(true);
			}
			
		}
		
		List<Rol> r= new ArrayList<>();
		Rol rol= new Rol();
		rol.setAuthority("ROLE_USER");
		r.add(rol);
		//pe.setRoles(r);
		return pe;
	}
	
	@Override
	public Estudiante convertUsuarioToEstudiante(UsuarioDto u, Long idPersona) {
		// TODO Auto-generated method stub
		
		Programa p=null;
		
		try {
			p= programaDao.findById(u.getPrograma()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el programa en la base de datos");
		}	
		Estudiante e= new Estudiante();
		e.setId(idPersona);
		e.setPrograma(p);
		e.setCodigo(u.getCodigo());
		
		return e;
	}
	
	

	@Override
	public Externo convertUsuarioToExterno(UsuarioDto u,Long idPersona) {
		// TODO Auto-generated method stub
		Externo e=new Externo();
		e.setId(idPersona);
		e.setEmpresa(u.getEmpresa());
		e.setProfesion(u.getProfesion());
		return e;
	}

	@Override
	public Docente convertUsuarioToDocente(UsuarioDto u, Long idPersona) {
		// TODO Auto-generated method stub
		
		Departamento de=null;
		
		try {
			de= departamentoDao.findById(u.getDeptoAdscrito()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el departamento en la base de datos");
		}	
		Docente d= new Docente();
		d.setId(idPersona);
		d.setDepartamento(de);
		d.setCodigo(u.getCodigoDocente());
		return d;
	}

	@Override
	public Administrativo convertUsuarioToAdministrativo(UsuarioDto u, Long idPersona) {
		// TODO Auto-generated method stub
		Administrativo a= new Administrativo();
		a.setId(idPersona);
		a.setCargo(u.getCargo());
		a.setDependencia(u.getDependencia());
		return a;
	}

	@Override
	public Graduado convertUsuarioToGraduado(UsuarioDto u, Long idPersona) {
		// TODO Auto-generated method stub
		Programa p=null;
		
		try {
			p= programaDao.findById(u.getPrograma()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el programa del cuál se graduó en la base de datos");
		}
		
		Graduado g= new Graduado();
		g.setId(idPersona);
		g.setPrograma(p);
		g.setAnio(u.getAnioGraduado());
		return g;
	}

	@Override
	public UsuarioAppDto convertPersonaToUsuarioAppDto(Persona p) {
		// TODO Auto-generated method stub
		UsuarioAppDto dto=new UsuarioAppDto();
		dto.setId(p.getId());
		dto.setPrimerNombre(p.getPrimerNombre());
		dto.setSegundoNombre(p.getSegundoNombre());
		dto.setPrimerApellido(p.getPrimerApellido());
		dto.setSegundoApellido(p.getSegundoApellido());
		dto.setEmail(p.getEmail());
		dto.setTelefono(p.getEmail());
		dto.setEstudiante(p.isEstudiante());
		dto.setDocente(p.isDocente());
		dto.setAdministrativo(p.isAdministrativo());
		dto.setGraduado(p.isGraduado());
		dto.setExterno(p.isExterno());
		return dto;
	}

	@Override
	public List<PersonaDto> convertListPersonasToPersonaDto(List<Persona> personas) {
		// TODO Auto-generated method stub
		List<PersonaDto> list= new ArrayList<PersonaDto>();
		for(Persona p: personas) {
			list.add(this.convertPersonaToPersonaDto(p));
		}
		return list;
	}

	@Override
	public PersonaDto convertPersonaToPersonaDto(Persona persona) {
		// TODO Auto-generated method stub
		PersonaDto dto= new PersonaDto();
		dto.setId(persona.getId());
		dto.setDocumento(persona.getNumeroDocumento());
		dto.setEmail(persona.getEmail());
		String nombre="";
		if( persona.getPrimerNombre()!=null) {
			nombre=persona.getPrimerNombre();
		}
		
		if( persona.getSegundoNombre()!=null) {
			nombre=nombre + " " + persona.getSegundoNombre();
		}
		
		if(persona.getPrimerApellido()!=null ) {
			nombre=nombre + " " + persona.getPrimerApellido();
		}
		
		if( persona.getSegundoApellido()!=null) {
			nombre=nombre + " " + persona.getSegundoApellido();
		}
		
		dto.setNombre(nombre);
		
		String perfiles="";
		if(persona.isEstudiante()) {
			if(perfiles.isEmpty()) {
				perfiles="estudiante";
			}
		}
		
		if(persona.isDocente()) {
			if(perfiles.isEmpty()) {
				perfiles="docente";
			}else {
				perfiles=perfiles+"-docente";
			}
		}
		
		if(persona.isAdministrativo()) {
			if(perfiles.isEmpty()) {
				perfiles="administrativo";
			}else {
				perfiles=perfiles+"-administrativo";
			}
		}
		
		if(persona.isGraduado()) {
			if(perfiles.isEmpty()) {
				perfiles="graduado";
			}else {
				perfiles=perfiles+"-graduado";
			}
		}
		
		if(persona.isExterno()) {
			if(perfiles.isEmpty()) {
				perfiles="externo";
			}else {
				perfiles=perfiles+"-externo";
			}
		}
		dto.setPerfiles(perfiles);
		return dto;
	}

	
	
}
