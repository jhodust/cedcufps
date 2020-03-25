package com.ufps.cedcufps.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;

@Controller
@SessionAttributes("educacionContinua")
public class EducacionContinuaController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IParticipanteService participanteService;
	
	@RequestMapping(value = "/educacion-continua")
	public String listar(Model model) {
		int cantCursos=educacionContinuaService.cantidadCursos();
		int cantTalleres=educacionContinuaService.cantidadTalleres();
		int cantDiplomados=educacionContinuaService.cantidadDiplomados();
		int cantSemConSimp=educacionContinuaService.cantidadSeminariosCongresosSimposios();
		int total=cantCursos+cantTalleres+cantDiplomados+cantSemConSimp;
		model.addAttribute("titulo","EDUCACIÓN CONTINUA");
		model.addAttribute("educacionesContinuas",educacionContinuaService.findAll());
		model.addAttribute("cantCursos",cantCursos);
		model.addAttribute("cantTalleres",cantTalleres);
		model.addAttribute("cantDiplomados",cantCursos);
		model.addAttribute("cantSemConSimp",cantSemConSimp);
		model.addAttribute("porcCursos",cantCursos*100/total);
		model.addAttribute("porcTalleres",cantTalleres*100/total);
		model.addAttribute("porcDiplomados",cantDiplomados*100/total);
		model.addAttribute("porcSemConSimp",cantSemConSimp*100/total);
		model.addAttribute("posiblesPonentes",personaService.findAllPersonas());
		return "educacion_continua/index";
	}
	
	@RequestMapping(value = "/educacion-continua/registro")
	public String agregar(Map<String, Object> model) {
		EducacionContinua ec= new EducacionContinua(); 
		model.put("titulo","FORMULARIO EDUCACIÓN CONTINUA");
		model.put("educacionContinua",ec);
		model.put("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinua());
		return "educacion_continua/form";
	}
	
	
	
	@RequestMapping(value = "/educacion-continua/registro", method = RequestMethod.POST)
	public String save(EducacionContinua ec, SessionStatus status, @RequestParam("file") MultipartFile imagen) {
		
			if(!imagen.isEmpty()) {
				Path directorioRecursos=Paths.get("src//main//resources//static//uploads");
				String rutaFolder=directorioRecursos.toFile().getAbsolutePath();
				try {
					byte[] bytes = imagen.getBytes();
					Path rutaArchivo=Paths.get(rutaFolder+"//"+imagen.getOriginalFilename());
					Files.write(rutaArchivo, bytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ec.setImagen("/uploads/"+imagen.getOriginalFilename());
			}
		
		educacionContinuaService.save(ec);
		status.setComplete();
		return "redirect:/educacion-continua";
	}
	
	@RequestMapping(value = "/educacion-continua/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Optional<EducacionContinua> ec= educacionContinuaService.findOne(id); 
		model.put("titulo","FORMULARIO EDUCACIÓN CONTINUA");
		model.put("educacionContinua",ec.get());
		model.put("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinua());
		return "educacion_continua/form";
	}
	
	@RequestMapping(value = "/educacion-continua/{id}/detalles")
	public String mostrar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Optional<EducacionContinua> ec= educacionContinuaService.findOne(id); 
		model.put("titulo","DETALLES EDUCACIÓN CONTINUA");
		model.put("educacionContinua",ec.get());
		try {
		model.put("participante",participanteService.findByIdEducacionContinuaAndIdPersona(id,personaService.findPersonaLogueada().getId()));
		}catch(Exception e) {
			model.put("participante",null);
		}
		return "educacion_continua/detalles";
	}
	
	@RequestMapping(value = "/educacion-continua/{id}/personalizar-diploma")
	public String listarDiplomas(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		model.put("educacionContinua",educacionContinuaService.findOne(id).get());
		//https://www.youtube.com/watch?v=C4vQ-nSNAgA
		return "educacion_continua/diplomas";
	}
	
	
}
