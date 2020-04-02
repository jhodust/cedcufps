package com.ufps.cedcufps.controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.cedcufps.modelos.Asistente;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.utils.Archivo;
import com.ufps.cedcufps.utils.CodigoQR;
import com.ufps.cedcufps.utils.Encrypt;

@Controller
public class AsistenteController {

	@Autowired
	private IParticipanteService participanteService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/realizar-inscripcion/{id_evento}")
	public String realizarInscripcion(@PathVariable(value = "id_evento") Long id, Map<String, Object> model) {
		
		EducacionContinua ec= educacionContinuaService.findOne(id).get();
		Persona p= personaService.findPersonaLogueada();
		Asistente a= new Asistente(); 
		a.setEducacionContinua(ec);
		a.setTipoParticipante(participanteService.findByTipoParticipante("Asistente"));
		a.setPersona(p);
		Path directorioRecursos=Paths.get("src//main//resources//static//uploads//educacion-continua//"+ec.getId()+"//qr-participantes");
		if(directorioRecursos.toFile().exists()) {
			Date fechaInscripcion = new Date();
		    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		    
			String texto=ec.getTipoEduContinua().getTipoEduContinua()+"_"+ec.getNombre()+"_"+a.getTipoParticipante().getTipoParticipante()+"_"+p.getNumeroDocumento()+"_"+p.getPrimerNombre()+"_"+p.getPrimerApellido()+"_"+formateador.format(fechaInscripcion);
			String nombreArchivo=p.getNumeroDocumento()+".png";
			System.out.println("texto original: " + texto);
			File file=Paths.get(directorioRecursos+"//"+nombreArchivo).toFile();
			texto=Encrypt.encriptar(texto);
			try {
				CodigoQR.generateQR(file, texto);
				System.out.println("encriptado: " + texto);
				System.out.println("desencriptado: " + Encrypt.desencriptar(texto));
				a.setImagenCodigoQR("/uploads/educacion-continua/"+ec.getId()+"/qr-participantes/"+nombreArchivo);
				a.setCodigoQR(texto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		participanteService.save(a);
		String url="/educacion-continua/"+id+"/detalles";
		return "redirect:"+url;
	}
	
	@RequestMapping(value = "/cancelar-inscripcion/{id_evento}")
	public String cancelarInscripcion(@PathVariable(value = "id_evento") Long id, Map<String, Object> model) {
		Participante p=participanteService.findByIdEducacionContinuaAndIdPersona(id, personaService.findPersonaLogueada().getId());
		Archivo.deleteImage(p.getImagenCodigoQR());
		participanteService.deleteParticipante(p);
		String url="/educacion-continua/"+id+"/detalles";
		return "redirect:"+url;
	}
}
