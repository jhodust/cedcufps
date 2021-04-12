package com.ufps.cedcufps.mapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IDepartamentoDao;
import com.ufps.cedcufps.dao.IEstadoCivilDao;
import com.ufps.cedcufps.dao.IGeneroDao;
import com.ufps.cedcufps.dao.IPersonaDao;
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
	private IPersonaDao personaDao;
	
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
		try {
			td = tipoDocumentoDao.findById(u.getIdTipoDocumento()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el tipo de documento en la base de datos");
		}
		
		try {
			g= generoDao.findById(u.getIdGenero()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el género en la base de datos");
		}
		
		try {
			ec= estadoCivilDao.findById(u.getIdEstadoCivil()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el estado civil en la base de datos");
		}
		
		
			
		if(personaDao.validarDocumentoRegistrado(u.getId(), u.getNumeroDocumento())>0) {
			throw new CustomException("El documento ingresado ya fue registrado en la base de datos");
		}
		
		
		if(personaDao.validarEmailRegistrado(u.getNumeroDocumento(), u.getEmail())>0) {
			throw new CustomException("El email ingresado ya fue registrado en la base de datos");
		}
		
			
		  
		pe.setId(u.getId());
		pe.setTipoDocumento(td);
		pe.setNumeroDocumento(u.getNumeroDocumento());
		
			pe.setFechaExpedicionDocumento(u.getFechaExpedicionDocumento());
		
		pe.setPrimerNombre(u.getPrimerNombre());
		pe.setSegundoNombre(u.getSegundoNombre());
		pe.setPrimerApellido(u.getPrimerApellido());
		pe.setSegundoApellido(u.getSegundoApellido());
		pe.setGenero(g);
		pe.setEstadoCivil(ec);
		
			pe.setFechaNacimiento(u.getFechaNacimiento());
		
		pe.setIdPaisNacimiento(u.getIdPaisNacimiento());
		pe.setIdMunicipioNacimiento(u.getIdMunicipioNacimiento());
		pe.setIdDepartamentoNacimiento(u.getIdDepartamentoNacimiento());
		pe.setEmail(u.getEmail());
		pe.setDireccion(u.getDireccion());
		pe.setTelefono(u.getTelefono());
		pe.setEstudiante(u.isEstudiante());
		pe.setDocente(u.isDocente());
		pe.setAdministrativo(u.isAdministrativo());
		pe.setGraduado(u.isGraduado());
		pe.setExterno(u.isExterno());
		pe.setIdAcceso(String.valueOf(System.currentTimeMillis()));
		pe.setIdsTipoPersona(this.convertIdsTiposPersona(u));
		
		/*List<Rol> r= new ArrayList<>();
		Rol rol= new Rol();
		rol.setAuthority("ROLE_USER");
		r.add(rol);
		pe.setr.setRoles(r);*/
		return pe;
	}
	
	@Override
	public Estudiante convertUsuarioToEstudiante(UsuarioDto u, Long idPersona) {
		// TODO Auto-generated method stub
		
		Programa p=null;
		
		try {
			p= programaDao.findById(u.getIdProgramaEstudiante()).get();
		}catch(Exception ex) {
			throw new CustomException("No se encontró el programa en la base de datos");
		}	
		Estudiante e= new Estudiante();
		e.setId(idPersona);
		e.setPrograma(p);
		e.setCodigo(u.getCodigoProgramaEstudiante());
		
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
			de= departamentoDao.findById(u.getIdDeptoAdscrito()).get();
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
			p= programaDao.findById(u.getIdProgramaGraduado()).get();
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
		
		UsuarioAppDto dto=null;
		if(p!=null) {
			dto=new UsuarioAppDto();
			dto.setId(p.getId());
			dto.setPrimerNombre(p.getPrimerNombre());
			dto.setSegundoNombre(p.getSegundoNombre());
			dto.setPrimerApellido(p.getPrimerApellido());
			dto.setSegundoApellido(p.getSegundoApellido());
			dto.setEmail(p.getEmail());
			dto.setDocumento(p.getNumeroDocumento());
			dto.setTelefono(p.getTelefono());
			dto.setEstudiante(p.isEstudiante());
			dto.setDocente(p.isDocente());
			dto.setAdministrativo(p.isAdministrativo());
			dto.setGraduado(p.isGraduado());
			dto.setExterno(p.isExterno());
		}
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
		dto.setTipoDocumento(persona.getTipoDocumento().getTipoDocumento());
		dto.setDocumento(persona.getNumeroDocumento());
		dto.setEmail(persona.getEmail());
		dto.setNombre(this.convertFieldsFullName(persona));
		dto.setPerfiles(this.convertPerfiles(persona));
		dto.setIdAcceso(persona.getIdAcceso());
		dto.setDocente(persona.isDocente());
		dto.setEstudiante(persona.isEstudiante());
		dto.setAdministrativo(persona.isAdministrativo());
		dto.setGraduado(persona.isGraduado());
		dto.setExterno(persona.isExterno());
		return dto;
	}

	@Override
	public UsuarioDto convertPersonaToUsuarioDto(Persona p, Estudiante e, Docente d, 
			Administrativo a, Graduado g, Externo ex) {
		// TODO Auto-generated method stub
		UsuarioDto dto= new UsuarioDto();
		dto.setId(p.getId());
		dto.setPrimerNombre(p.getPrimerNombre());
		dto.setSegundoNombre(p.getSegundoNombre());
		dto.setPrimerApellido(p.getPrimerApellido());
		dto.setSegundoApellido(p.getSegundoApellido());
		dto.setIdTipoDocumento(p.getTipoDocumento().getId());
		dto.setTipoDocumento(p.getTipoDocumento().getTipoDocumento() + " - " +p.getTipoDocumento().getDescripcion());
		dto.setNumeroDocumento(p.getNumeroDocumento());
		dto.setIdGenero(p.getGenero().getId());
		dto.setGenero(p.getGenero().getGenero());
		dto.setIdEstadoCivil(p.getEstadoCivil().getId());
		dto.setEstadoCivil(p.getEstadoCivil().getEstadoCivil());
		dto.setFechaNacimiento(p.getFechaNacimiento());
		dto.setFechaExpedicionDocumento(p.getFechaExpedicionDocumento());
		dto.setIdPaisNacimiento(p.getIdPaisNacimiento());
		dto.setPaisNacimiento(this.findPaisNacimiento(dto.getIdPaisNacimiento()));
		dto.setIdDepartamentoNacimiento(p.getIdDepartamentoNacimiento());
		dto.setDeptoNacimiento(this.findDeptoNacimiento(dto.getIdDepartamentoNacimiento()));
		dto.setIdMunicipioNacimiento(p.getIdMunicipioNacimiento());
		dto.setMpioNacimiento(this.findMpioNacimiento(dto.getIdMunicipioNacimiento()));
		dto.setEmail(p.getEmail());
		dto.setDireccion(p.getDireccion());
		dto.setTelefono(p.getTelefono());
		dto.setEstudiante(p.isEstudiante());
		dto.setDocente(p.isDocente());
		dto.setAdministrativo(p.isAdministrativo());
		dto.setGraduado(p.isGraduado());
		dto.setExterno(p.isExterno());
		
		if(e!=null) {
			dto.setIdProgramaEstudiante(e.getPrograma().getId());
			dto.setCodigoProgramaEstudiante(e.getCodigo());
			dto.setProgramaEstudiante(e.getPrograma().getPrograma());
		}
		
		if(d!=null) {
			dto.setIdDeptoAdscrito(d.getDepartamento().getId());
			dto.setCodigoDocente(d.getCodigo());
			dto.setDeptoAdscrito(d.getDepartamento().getDepartamento());
		}
		
		if(a!=null) {
			dto.setCargo(a.getCargo());
			dto.setDependencia(a.getDependencia());
		}
		
		if(g!=null) {
			dto.setIdProgramaGraduado(g.getPrograma().getId());
			dto.setAnioGraduado(g.getAnio());
			dto.setProgramaGraduado(g.getPrograma().getPrograma());
		}
		
		if(ex!=null) {
			dto.setEmpresa(ex.getEmpresa());
			dto.setProfesion(ex.getProfesion());
		}
		
		return dto;
	}

	private JSONArray readJsonPaises() {
		
		try {
			 JSONParser parser= new JSONParser();
	            File f= new File("src/main/resources/static/data/paises.json");
	            Object object=parser.parse(new FileReader(f.getAbsolutePath()));
	            JSONArray jsonRead= (JSONArray) object;
	            return jsonRead;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        
	}
	private String findPaisNacimiento(String idPaisNacimiento) {
		JSONArray json= this.readJsonPaises();
			 if(json!=null) {
				Iterator<JSONObject> paises=json.iterator();
	            while(paises.hasNext()){
	                JSONObject p= paises.next();
	                String idP=(String)p.get("id");
	                if(idP.equalsIgnoreCase(idPaisNacimiento)){
	                    return (String) p.get("pais");
	                }
	            }
			 }
		return "";
	}
	

private JSONArray readJsonDivipola() {
		
		try {
			 JSONParser parser= new JSONParser();
	            File f= new File("src/main/resources/static/data/divipola.json");
	            Object object=parser.parse(new FileReader(f.getAbsolutePath()));
	            JSONArray jsonRead= (JSONArray) object;
	            return jsonRead;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        
	}

	private String findDeptoNacimiento(String idDeptoNacimiento) {
		if(idDeptoNacimiento!=null) {
			JSONArray json= this.readJsonDivipola();
			 if(json!=null) {
				Iterator<JSONObject> deptos=json.iterator();
	            while(deptos.hasNext()){
	                JSONObject d= deptos.next();
	                String idDepto=(String)d.get("cod_depto");
	                if(idDepto.equalsIgnoreCase(idDeptoNacimiento)){
	                    return (String) d.get("dpto");
	                }
	            }
			 }
		}
		
		return "";
	}
	
	private String findMpioNacimiento(String idMpioNacimiento) {
		if(idMpioNacimiento!=null) {
			JSONArray json= this.readJsonDivipola();
			 if(json!=null) {
				Iterator<JSONObject> municipios=json.iterator();
	            while(municipios.hasNext()){
	                JSONObject m= municipios.next();
	                String idMpio=(String)m.get("cod_mpio");
	                if(idMpio.equalsIgnoreCase(idMpioNacimiento)){
	                    return (String) m.get("nom_mpio");
	                }
	            }
			 }
		}
		
		return "";
	}
	
	@Override
	public String convertFieldsFullName(Persona p) {
		String nombreResponsable=null;
		if(p.getPrimerNombre()!="") {
			nombreResponsable=p.getPrimerNombre();
		}
		if(p.getSegundoNombre() != null && p.getSegundoNombre()!="") {
			nombreResponsable=nombreResponsable + " " +p.getSegundoNombre();
		}
		if(p.getPrimerApellido()!="") {
			nombreResponsable=nombreResponsable + " " +p.getPrimerApellido();
		}
		if(p.getSegundoApellido()!="") {
			nombreResponsable=nombreResponsable + " " +p.getSegundoApellido();
		}
		return nombreResponsable;
	}
	
	public String convertPerfiles(Persona persona) {
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
		return perfiles;
	}
	
	public String convertIdsTiposPersona(UsuarioDto u) {
		String tiposPersona="";
		if(u.isEstudiante()) {
			if(tiposPersona.isEmpty()) {
				tiposPersona="1";
			}
		}
		if(u.isDocente()) {
			if(tiposPersona.isEmpty()) {
				tiposPersona="2";
			}else {
				tiposPersona=tiposPersona+",2";
			}
		}
		if(u.isAdministrativo()) {
			if(tiposPersona.isEmpty()) {
				tiposPersona="3";
			}else {
				tiposPersona=tiposPersona+",3";
			}
		}
		if(u.isGraduado()) {
			if(tiposPersona.isEmpty()) {
				tiposPersona="4";
			}else {
				tiposPersona=tiposPersona+",4";
			}
		}
		if(u.isExterno()) {
			tiposPersona="5";
		}
		return tiposPersona;
	}
}
