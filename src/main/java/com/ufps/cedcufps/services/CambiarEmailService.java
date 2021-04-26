package com.ufps.cedcufps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.ICambioEmailDao;
import com.ufps.cedcufps.dao.IPersonaCustomDao;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.mapper.IUsuarioMapper;
import com.ufps.cedcufps.modelos.CambioEmail;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.utils.Archivo;

@Service
public class CambiarEmailService implements ICambiarEmailService {

	@Autowired
	private ICambioEmailDao cambioEmailDao;
	
	@Autowired
	private IPersonaCustomDao personaCustomDao;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private IUsuarioMapper usuarioMapper;
	
	@Override
	public void guardarSolicitudCambioEmail(String documento, String email) {
		// TODO Auto-generated method stub
		
		Persona p = personaCustomDao.findPersonaByNumeroDocumento(documento);
		if(p == null ) {
			throw new CustomException(" El documento ingresado no se encuentra registrado");
		}
		
		CambioEmail ce= cambioEmailDao.findCambioEmailByDocumento(documento);
		if(ce==null) {
			ce= new CambioEmail();
			ce.setNumeroDocumento(documento);
			ce.setToken(Archivo.getRandomString(30));
			cambioEmailDao.save(ce);
		}
		this.prepararEmailCambiarEmail(ce.getToken(), email, p);
	}

	@Override
	public UsuarioDto realizarSolicitudGenerada(String token) {
		// TODO Auto-generated method stub
		CambioEmail ce=this.cambioEmailDao.findCambioEmailByToken(token);
		if(ce!=null) {
			UsuarioDto dto=personaService.editarUsuarioByDocumento(ce.getNumeroDocumento());
			cambioEmailDao.eliminarSolicitud(token);
			return dto;
		}
		
		throw new CustomException("Token Inválido");
		
	}
	
	public void prepararEmailCambiarEmail(String token, String email, Persona p ) {
		
	     
		String contenido=String.format("Para actualizar tu información y poder iniciar sesión por favor da click en el siguiente enlace"); 
		
		emailService.sendEmailActualización(email,"Actualización de datos GEDUCO", contenido, token, usuarioMapper.convertFieldsFullName(p));
		
	}
	

}
