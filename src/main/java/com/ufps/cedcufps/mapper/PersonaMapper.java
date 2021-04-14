package com.ufps.cedcufps.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ufps.cedcufps.modelos.EstadoCivil;
import com.ufps.cedcufps.modelos.Genero;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.TipoDocumento;

@Service
public class PersonaMapper implements IPersonaMapper{

	@Override
	public List<Persona> convertListObjectToListPersonas(List<Object[]> resultPersonas) {
		// TODO Auto-generated method stub
		List<Persona> personas=new ArrayList<Persona>();
		for(Object[] object:resultPersonas) {
			personas.add(this.convertObjectToPersona(object));
		}
		return personas;
	}

	@Override
	public Persona convertObjectToPersona(Object[] object) {
		// TODO Auto-generated method stub
		Persona per=new Persona();
		per.setId(Long.parseLong(String.valueOf(object[0])));
		per.setPrimerNombre((object[1] != null) ? String.valueOf(object[1]) : null);
		per.setSegundoNombre((object[2] != null) ? String.valueOf(object[2]) : null);
		per.setPrimerApellido((object[3] != null) ? String.valueOf(object[3]) : null);
		per.setSegundoApellido((object[4] != null) ? String.valueOf(object[4]) : null);
		per.setIdAcceso(String.valueOf(object[5]));
		per.setEstudiante(Integer.parseInt(String.valueOf(object[6]))==1);
		per.setDocente(Integer.parseInt(String.valueOf(object[7]))==1);
		per.setAdministrativo(Integer.parseInt(String.valueOf(object[8]))==1);
		per.setGraduado(Integer.parseInt(String.valueOf(object[9]))==1);
		per.setExterno(Integer.parseInt(String.valueOf(object[10]))==1);
		per.setEmail(String.valueOf(object[11]));
		TipoDocumento td= new TipoDocumento();
		td.setId(Long.parseLong(String.valueOf(object[12])));
		td.setTipoDocumento(String.valueOf(object[13]));
		td.setDescripcion(String.valueOf(object[27]));
		per.setTipoDocumento(td);
		per.setDireccion(String.valueOf(object[14]));
		per.setFechaExpedicionDocumento((Date)object[15]);
		per.setFechaNacimiento((Date)object[16]);
		per.setIdDepartamentoNacimiento(String.valueOf(object[17]));
		per.setIdMunicipioNacimiento(String.valueOf(object[18]));
		per.setIdPaisNacimiento(String.valueOf(object[19]));
		per.setIdsTipoPersona(String.valueOf(object[20]));
		per.setNumeroDocumento(String.valueOf(object[21]));
		per.setTelefono(String.valueOf(object[22]));
		EstadoCivil ec=new EstadoCivil();
		ec.setId(Long.parseLong(String.valueOf(object[23])));
		ec.setEstadoCivil(String.valueOf(object[24]));
		per.setEstadoCivil(ec);
		Genero g=new Genero();
		g.setId(Long.parseLong(String.valueOf(object[25])));
		g.setGenero(String.valueOf(object[26]));
		per.setGenero(g);
		return per;
	}

}
