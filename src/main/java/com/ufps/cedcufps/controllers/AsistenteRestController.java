package com.ufps.cedcufps.controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.modelos.Asistente;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.utils.Archivo;
import com.ufps.cedcufps.utils.CodigoQR;
import com.ufps.cedcufps.utils.Encrypt;

@RestController
public class AsistenteRestController {

	@Autowired
	private IParticipanteService participanteService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	
	@GetMapping(value = "/realizar-inscripcion/{id_evento}",produces = "application/json")
	public ResponseEntity<Participante> realizarInscripcion(@PathVariable(value = "id_evento") Long id, Map<String, Object> model) {
		
		EducacionContinua ec= educacionContinuaService.findOne(id).get();
		Persona p= personaService.findPersonaLogueada();
		Asistente a= new Asistente(); 
		a.setEducacionContinua(ec);
		a.setTipoParticipante(participanteService.findByTipoParticipante("Asistente"));
		a.setPersona(p);
		
		    
		String texto=ec.getProgramaResponsable().getCodigo()+"_"+ec.getTipoEduContinua().getId()+"_"+ec.getId()+"_"+a.getTipoParticipante().getId()+"_"+p.getNumeroDocumento();
		String nombreArchivo=p.getNumeroDocumento()+".png";
		System.out.println("texto original: " + texto);
		texto=Encrypt.encriptar(texto);
		try {
			CodigoQR.generateQR("uploads/educacion-continua/"+ec.getId()+"/qr-participantes/"+nombreArchivo, texto);
			System.out.println("encriptado: " + texto);
			System.out.println("desencriptado: " + Encrypt.desencriptar(texto));
			a.setImagenCodigoQR("/uploads/educacion-continua/"+ec.getId()+"/qr-participantes/"+nombreArchivo);
			a.setCodigoQR(texto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		participanteService.save(a);
		return ResponseEntity.ok(a);
	}
	
	@PostMapping(value = "/realizar-inscripcion/generar-tarjeta-inscripcion" ,produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> guardarTarjetaInscripcion(MultipartFile file, String idParticipante){
		System.out.println("imagen: " + file.getName());
		Participante p= participanteService.findParticipante(Long.parseLong(idParticipante));
		System.out.println("participante: " + idParticipante);
		System.out.println("participante educacion continua: " + p.getEducacionContinua().getId());
		p.setTarjetaInscripcion(Archivo.saveImage(file,"/uploads/educacion-continua/"+p.getEducacionContinua().getId()+"/tarjetas-inscripcion/inscripcion_"+p.getPersona().getNumeroDocumento()));
		participanteService.save(p);
		System.out.println("tarjeta participante: " + p.getTarjetaInscripcion());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/cancelar-inscripcion/{id_evento}",produces = "application/json")
	public ResponseEntity<Participante> cancelarInscripcion(@PathVariable(value = "id_evento") Long id, Map<String, Object> model) {
		
		Participante p=participanteService.findByIdEducacionContinuaAndIdPersona(id, personaService.findPersonaLogueada().getId());
		Archivo.deleteImage(p.getImagenCodigoQR());
		Archivo.deleteImage(p.getTarjetaInscripcion());
		participanteService.deleteParticipante(p);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
