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

import com.ufps.cedcufps.dao.IEstadoCivilDao;
import com.ufps.cedcufps.dao.IGeneroDao;
import com.ufps.cedcufps.dao.IProgramaDao;
import com.ufps.cedcufps.dao.ITipoDocumentoDao;
import com.ufps.cedcufps.dao.ITipoPersonaDao;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.modelos.EstadoCivil;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;
import com.ufps.cedcufps.modelos.Genero;
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
	
	@Override
	public Estudiante convertUsuarioToEstudiante(UsuarioDto u) {
		// TODO Auto-generated method stub
		Estudiante e= new Estudiante();
		TipoDocumento td = tipoDocumentoDao.findById(u.getTipoDocumento()).orElseThrow(() -> {
			throw new CustomException("No se encontró el tipo de documento en la base de datos");
		});
		Genero g= generoDao.findById(u.getGenero()).orElseThrow(() -> {
			throw new CustomException("No se encontró el género en la base de datos");
		});
		
		EstadoCivil ec= estadoCivilDao.findById(u.getEstadoCivil()).orElseThrow(() -> {
			throw new CustomException("No se encontró el estado civil en la base de datos");
		});
		
		TipoPersona tp= tipoPersonaDao.findById((long) 1).orElseThrow(() -> {
			throw new CustomException("No se encontró el tipo usuario en la base de datos");
		});
		
		Programa p= programaDao.findById(u.getPrograma()).orElseThrow(() -> {
			throw new CustomException("No se encontró el programa en la base de datos");
		});
		  
		
		e.setTipoDocumento(td);
		e.setNumeroDocumento(u.getNumeroDocumento());
		try {
			e.setFechaExpedicionDocumento(new SimpleDateFormat("dd/MM/yyyy").parse(u.getFechaExpedicionDocumento()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			throw new CustomException("Fecha Expedición Documento inválida");
		}
		e.setPrimerNombre(u.getPrimerNombre());
		e.setSegundoNombre(u.getSegundoNombre());
		e.setPrimerApellido(u.getPrimerApellido());
		e.setSegundoApellido(u.getSegundoApellido());
		e.setGenero(g);
		e.setEstadoCivil(ec);
		try {
			e.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(u.getFechaNacimiento()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			throw new CustomException("Fecha Nacimiento inválida");
		}
		e.setIdPaisNacimiento(u.getIdPaisNacimiento());
		e.setIdMunicipioNacimiento(u.getIdMunicipioNacimiento());
		e.setIdDepartamentoNacimiento(u.getIdDepartamentoNacimiento());
		e.setEmail(u.getEmail());
		e.setDireccion(u.getDireccion());
		e.setTelefono(u.getTelefono());
		e.setTipoPersona(tp);
		e.setPrograma(p);
		e.setCodigo(u.getCodigo());
		List<Rol> r= new ArrayList<>();
		Rol rol= new Rol();
		rol.setAuthority("ROLE_USER");
		r.add(rol);
		e.setRoles(r);
		return e;
	}
	
	

	@Override
	public Externo convertUsuarioToExterno(UsuarioDto u) {
		// TODO Auto-generated method stub
		Externo e= new Externo();
		TipoDocumento td = tipoDocumentoDao.findById(u.getTipoDocumento()).orElseThrow(() -> {
			throw new CustomException("No se encontró el tipo de documento en la base de datos");
		});
		Genero g= generoDao.findById(u.getGenero()).orElseThrow(() -> {
			throw new CustomException("No se encontró el género en la base de datos");
		});
		
		EstadoCivil ec= estadoCivilDao.findById(u.getEstadoCivil()).orElseThrow(() -> {
			throw new CustomException("No se encontró el estado civil en la base de datos");
		});
		
		TipoPersona tp= tipoPersonaDao.findById((long) 4).orElseThrow(() -> {
			throw new CustomException("No se encontró el tipo usuario en la base de datos");
		});
		
		
		e.setTipoDocumento(td);
		e.setNumeroDocumento(u.getNumeroDocumento());
		try {
			e.setFechaExpedicionDocumento(new SimpleDateFormat("dd/MM/yyyy").parse(u.getFechaExpedicionDocumento()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			throw new CustomException("Fecha Expedición Documento inválida");
		}
		e.setPrimerNombre(u.getPrimerNombre());
		e.setSegundoNombre(u.getSegundoNombre());
		e.setPrimerApellido(u.getPrimerApellido());
		e.setSegundoApellido(u.getSegundoApellido());
		e.setGenero(g);
		e.setEstadoCivil(ec);
		try {
			e.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(u.getFechaNacimiento()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			throw new CustomException("Fecha Nacimiento inválida");
		}
		e.setIdPaisNacimiento(u.getIdPaisNacimiento());
		e.setIdMunicipioNacimiento(u.getIdMunicipioNacimiento());
		e.setIdDepartamentoNacimiento(u.getIdDepartamentoNacimiento());
		e.setEmail(u.getEmail());
		e.setDireccion(u.getDireccion());
		e.setTelefono(u.getTelefono());
		e.setTipoPersona(tp);
		e.setProfesion(u.getProfesion());
		List<Rol> r= new ArrayList<>();
		Rol rol= new Rol();
		rol.setAuthority("ROLE_USER");
		r.add(rol);
		e.setRoles(r);
		return e;
	}

	

}
