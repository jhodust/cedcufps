package com.ufps.cedcufps.controllers;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufps.cedcufps.dto.EducacionContinuaWebDto;
import com.ufps.cedcufps.dto.InfoEducacionContinuaDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.modelos.Docente;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IAsistenciaService;
import com.ufps.cedcufps.services.IDiplomaService;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.services.IProgramaService;
import com.ufps.cedcufps.utils.Archivo;
import com.ufps.cedcufps.utils.ManejoPdf;

@Controller
@SessionAttributes("educacionContinua")
public class EducacionContinuaController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IProgramaService programaService;
	
	@Autowired
	private IParticipanteService participanteService;
	
	@Autowired
	private IDiplomaService diplomaService;
	
	@Autowired
	private IAsistenciaService asistenciaService;
	
	@RequestMapping(value = "/educacion-continua")
	public String listar(Model model, Authentication auth) {
		model.addAttribute("titulo","EDUCACIÓN CONTINUA");
		model.addAttribute("educacionesContinuas",educacionContinuaService.findPosiblesEduContinuaGestionar());
		return "educacion_continua/index";
	}
	
	
	
	@RequestMapping(value = "/educacion-continua/registro")
	public String agregar(Map<String, Object> model, Authentication auth) {
		//EducacionContinuaWebDto ec= new EducacionContinuaWebDto(); 
		model.put("titulo","FORMULARIO EDUCACIÓN CONTINUA");
	
		/*if(buscarAuthority(auth, "ROLE_DOCENTE")) {
			ec.setDocenteResponsable((Docente)personaService.findPersonaLogueada());
		}*/
		System.out.println("------------------------------------------------------------------------");
		//System.out.println(ec.getIdClasificacion());
		model.put("educacionContinua",educacionContinuaService.createEducacionContinua());
		model.put("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinua(0L));//metodo que busca los tipos de educacion oficiales y el 'otro' si es de una educacion continua en especifico
		model.put("clasificacion_cine",educacionContinuaService.findAllClasificacionCine());
		model.put("tipo_beneficiarios",educacionContinuaService.findAllTipoBeneficiario());
		model.put("docentes",personaService.findAllDocentes());
		model.put("programas",programaService.findAll());
		List<Programa> programasBase=programaService.programasParaEduContinuaBase();
		model.put("programasBase",programasBase);
		model.put("educacionesContinuasBase",educacionContinuaService.findEducacionesContinuasBase(programasBase));
		
		return "educacion_continua/form";
	}
	
	
	
	/*@RequestMapping(value = "/educacion-continua/registro", method = RequestMethod.POST)
	public String save(@Valid EducacionContinua ec, BindingResult result, SessionStatus status, @RequestParam("file") MultipartFile imagen,Map<String, Object> model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.put("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinua());
			model.put("clasificacion_cine",educacionContinuaService.findAllClasificacionCine());
			model.put("tipo_beneficiarios",educacionContinuaService.findAllTipoBeneficiario());
			model.put("docentes",personaService.findAllDocentes());
			model.put("programas",programaService.findAll());
			model.put("errorMessage", "Debes diligenciar los campos correctamente...");
			return "educacion_continua/form";
			//return "redirect:/educacion-continua/registro";
		}
		educacionContinuaService.save(ec);
		educacionContinuaService.updateCodigoEducacionContinua(ec);
		System.out.println("id ec: " + ec.getId());
		//generarDirectoriosPropios(ec);
		/*if(imagen!=null) {
			guardarImagenPortada(ec,imagen);
		}*/
		/*System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!estado!!!!!!!!!!!!!!!!!!!!!!!!");
		/*System.out.println(ec.getEstado());
		/*status.setComplete();
		/*redirectAttributes.addFlashAttribute("successMessage", "Se ha guardado la información correctamente...");
		return "redirect:/educacion-continua";
	}*/
	
	/*@RequestMapping(value = "/educacion-continua/{id}/registro")
/*	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, Authentication auth,RedirectAttributes redirectAttributes) {
		EducacionContinua e= educacionContinuaService.findOne(id).get();*/
		/*if(buscarAuthority(auth, "ROLE_DIRPROGRAMA")) {
			Docente p= (Docente)personaService.findPersonaLogueada();
			if(e.getProgramaResponsable().getId() != p.getProgramaACargoDirector().getId() && e.getDocenteResponsable().getId()!=p.getId() ) {
				redirectAttributes.addFlashAttribute("errorMessage", "No tiene permisos para administrar la Educación Continua...");
				return "redirect:/educacion-continua";
			}
		}else if(buscarAuthority(auth, "ROLE_DOCENTE")) {
			
			Persona p= personaService.findPersonaLogueada();
			if(e.getDocenteResponsable().getId()!=p.getId()) {
				redirectAttributes.addFlashAttribute("errorMessage", "No tiene permisos para administrar la Educación Continua...");
				return "redirect:/educacion-continua";
			}
			
		}*/
		
		/*if(e.getEstado().equalsIgnoreCase("Terminado")) {
			redirectAttributes.addFlashAttribute("errorMessage", "El estado de la Educación Continua es " + e.getEstado());
			return "redirect:/educacion-continua/"+e.getId()+"/detalles";
		}
		
		model.put("titulo","FORMULARIO EDUCACIÓN CONTINUA");
		model.put("educacionContinua",e);
		model.put("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinua());
		model.put("clasificacion_cine",educacionContinuaService.findAllClasificacionCine());
		model.put("tipo_beneficiarios",educacionContinuaService.findAllTipoBeneficiario());
		model.put("docentes",personaService.findAllDocentes());
		model.put("programas",programaService.findAll());
		return "educacion_continua/form";
	}*/
	
	@RequestMapping(value = "/educacion-continua/jornadas")
	public String mostrarJornadas(@RequestParam(name="educacionContinua") String educacionContinua, Map<String, Object> model, Authentication auth, RedirectAttributes redirectAttributes) {
		//EducacionContinua e=educacionContinuaService.findOne(id).get();
		/*if(buscarAuthority(auth, "ROLE_DIRPROGRAMA")) {
			Docente p= (Docente)personaService.findPersonaLogueada();
			if(e.getProgramaResponsable().getId() != p.getProgramaACargoDirector().getId() && e.getDocenteResponsable().getId()!=p.getId() ) {
				redirectAttributes.addFlashAttribute("errorMessage", "No tiene permisos para administrar la Educación Continua...");
				return "redirect:/educacion-continua";
			}
		}else if(buscarAuthority(auth, "ROLE_DOCENTE")) {
			
			Persona p= personaService.findPersonaLogueada();
			if(e.getDocenteResponsable().getId()!=p.getId()) {
				redirectAttributes.addFlashAttribute("errorMessage", "No tiene permisos para administrar la Educación Continua...");
				return "redirect:/educacion-continua";
			}
			
		}*/
		/*if(e.getEstado().equalsIgnoreCase("Terminado")) {
			redirectAttributes.addFlashAttribute("errorMessage", "El estado de la Educación Continua es " + e.getEstado());
			return "redirect:/educacion-continua/"+e.getId()+"/detalles";
		}*/
		model.put("titulo","JORNADAS");
		model.put("educacionContinua",educacionContinuaService.detallesEducacionContinua(educacionContinua).getEducacionContinua());
		return "educacion_continua/jornada/index";
	}
	
	@RequestMapping(value = "/educacion-continua/ponentes")
	public String mostrarPonentes(@RequestParam(name="educacionContinua") String educacionContinua, Map<String, Object> model, Authentication auth, RedirectAttributes redirectAttributes) {
		/*if(buscarAuthority(auth, "ROLE_DIRPROGRAMA")) {
			Docente p= (Docente)personaService.findPersonaLogueada();
			if(e.getProgramaResponsable().getId() != p.getProgramaACargoDirector().getId() && e.getDocenteResponsable().getId()!=p.getId() ) {
				redirectAttributes.addFlashAttribute("errorMessage", "No tiene permisos para administrar la Educación Continua...");
				return "redirect:/educacion-continua";
			}
		}else if(buscarAuthority(auth, "ROLE_DOCENTE")) {
			
			Persona p= personaService.findPersonaLogueada();
			if(e.getDocenteResponsable().getId()!=p.getId()) {
				redirectAttributes.addFlashAttribute("errorMessage", "No tiene permisos para administrar la Educación Continua...");
				return "redirect:/educacion-continua";
			}
			
		}*/
		/*if(e.getEstado().equalsIgnoreCase("Terminado")) {
			redirectAttributes.addFlashAttribute("errorMessage", "El estado de la Educación Continua es " + e.getEstado());
			return "redirect:/educacion-continua/"+e.getId()+"/detalles";
		}*/
		model.put("titulo","JORNADAS");
		model.put("educacionContinua",educacionContinuaService.detallesEducacionContinua(educacionContinua).getEducacionContinua());
		model.put("ponentes",participanteService.findAllPonentesOfOneEducacionContinua(educacionContinua));
		model.put("posiblesPonentes",personaService.findAllPersonas());
		return "educacion_continua/ponente/index";
	}
	
	@RequestMapping(value = "/educacion-continua/detalles")
	public String mostrar(@RequestParam(name="educacionContinua") String educacionContinua, Map<String, Object> model,RedirectAttributes redirectAttributes) {
		model.put("titulo","DETALLES EDUCACIÓN CONTINUA");
		InfoEducacionContinuaDto dto= educacionContinuaService.detallesEducacionContinua(educacionContinua);
		if(dto.isHasPermission()) {
			model.put("ec",dto);
			EducacionContinuaWebDto e= educacionContinuaService.editarEducacionContinuaByNombre(educacionContinua);
			model.put("educacionContinua", e);
			model.put("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinua(e.getIdTipoEduContinua()));
			model.put("clasificacion_cine",educacionContinuaService.findAllClasificacionCine());
			model.put("tipo_beneficiarios",educacionContinuaService.findAllTipoBeneficiario());
			model.put("docentes",personaService.findAllDocentes());
			model.put("programas",programaService.findAll());
			model.put("asistenciaGlobal",asistenciaService.countAsistenciasByJornadas(educacionContinua));
			
			//model.put("participantes",participanteService.findAllParticipantesByEducacionContinua(educacionContinua));
			//if(dto.getEducacionContinua().getJornadas().size()>0) {
				//model.put("asistencias",asistenciaService.findAsistenciasByJornadas(dto.getEducacionContinua().getJornadas()));
				//model.put("asistenciaGlobal",asistenciaService.countAsistenciasByJornadas(educacionContinua));
				
			//}
		}else {
			redirectAttributes.addFlashAttribute("errorMessage", "No tiene permisos para administrar la Educación Continua indicada...");
			return "redirect:/educacion-continua";
		}
		
		return "educacion_continua/detalles";
	}
	
	@RequestMapping(value = "/preinscripcion/{nombre}")
	public String preinscripcionEducacionContinua(@PathVariable(value = "nombre") String nombreEvento, Map<String, Object> model) {
		EducacionContinua ec= educacionContinuaService.findOneByNombre(nombreEvento);
		model.put("titulo","DETALLES EDUCACIÓN CONTINUA");
		model.put("educacionContinua",ec);
		try {
		model.put("participante",participanteService.findByIdEducacionContinuaAndIdPersona(ec.getId(),personaService.findPersonaLogueada().getId()));
		}catch(Exception e) {
			model.put("participante",null);
		}
		return "preinscripcion";
	}
	/*
	@RequestMapping(value = "/educacion-continua/listado-participantes")
	public String listadoParticipantes(@RequestParam(name="educacionContinua") String educacionContinua, Map<String, Object> model, Authentication auth, RedirectAttributes redirectAttributes) {
		//EducacionContinua e=educacionContinuaService.findOne(id).get();
		/*if(buscarAuthority(auth, "ROLE_DIRPROGRAMA")) {
			Docente p= (Docente)personaService.findPersonaLogueada();
			if(e.getProgramaResponsable().getId() != p.getProgramaACargoDirector().getId() && e.getDocenteResponsable().getId()!=p.getId() ) {
				redirectAttributes.addFlashAttribute("errorMessage", "No tiene permisos para acceder al listado de participantes...");
				return "redirect:/educacion-continua";
			}
		}else if(buscarAuthority(auth, "ROLE_DOCENTE")) {
			
			Persona p= personaService.findPersonaLogueada();
			if(e.getDocenteResponsable().getId()!=p.getId()) {
				redirectAttributes.addFlashAttribute("errorMessage", "No tiene permisos para acceder al listado de participantes...");
				return "redirect:/educacion-continua";
			}
			
		}*/
	/*	InfoEducacionContinuaDto dto= educacionContinuaService.detallesEducacionContinua(educacionContinua);
		List<JornadaAppDto> jornadas=educacionContinuaService.findJornadasByEducacionContinua(educacionContinua);
		System.out.println("jornadas");
		System.out.println(jornadas.size());
		model.put("educacionContinua",dto.getEducacionContinua());
		model.put("jornadas",jornadas);
		model.put("participantes",participanteService.findAllParticipantesByEducacionContinua(educacionContinua));
		if(jornadas.size()>0) {
			model.put("asistencias",asistenciaService.findAsistenciasByJornadas(jornadas));
			model.put("asistenciaGlobal",asistenciaService.countAsistenciasByJornadas(educacionContinua));
			
		}
		
		return "educacion_continua/listadoParticipantes";
	}
	*/
	
	
	@RequestMapping(value = "/participaciones-educacion-continua")
	public String eventosActivosParticipante( Map<String, Object> model) {
		model.put("participaciones",participanteService.findAllParticipacionesActivasByParticipante(personaService.findPersonaLogueada().getNumeroDocumento()));
		return "educacion_continua/tarjetas_inscripcion/index";
	}
	
	@RequestMapping(value = "/educacion-continua/{id}/personalizar-diploma")
	public String personalizarDiploma(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		EducacionContinua e=educacionContinuaService.findOne(id).get();
		
		if(e.getDiploma()!=null) {
			model.put("imagenes", diplomaService.findImagenesByDiploma(e.getDiploma().getId()));
			model.put("textos", diplomaService.findTextoByDiploma(e.getDiploma().getId()));
			model.put("firmas", diplomaService.findFirmaByDiploma(e.getDiploma().getId()));
		}
		model.put("educacionContinua",e);
		//https://www.youtube.com/watch?v=C4vQ-nSNAgA
		
		return "educacion_continua/plantillaDiploma";
		//return "croppie";
	}
	
	
	
	
	
	
	/*@GetMapping(value = "/educacion-continua-a-cargo/{id}/registro",produces = "application/json")
	public String  editarEducacionContinuaACargoDocente(@PathVariable(value = "id") Long idEduContinua, Map<String, Object> model, RedirectAttributes redirectAttributes){
		Persona p= personaService.findPersonaLogueada();
		EducacionContinua e= educacionContinuaService.findOne(idEduContinua).get();
		if(e.getDocenteResponsable().getId()==p.getId()) {
			model.put("educacionContinua",e);
			model.put("tipos_educacion_continua",educacionContinuaService.findAllTiposEducacionContinua());
			model.put("clasificacion_cine",educacionContinuaService.findAllClasificacionCine());
			model.put("tipo_beneficiarios",educacionContinuaService.findAllTipoBeneficiario());
			model.put("docentes",personaService.findAllDocentes());
			model.put("programas",programaService.findAll());
			model.put("tipoAdminEduCon", 2);// 0 superadmin, 1 director programa, 2 docente
			return "educacion_continua/form";
		}
		redirectAttributes.addFlashAttribute("errorMessage", "No tiene permisos para administrar la Educación Continua...");
		return "redirect:/educacion-continua-a-cargo";
	}*/
	
	@RequestMapping(value = "/educacion-continua/listado-participantes/{eduContinuaNombre}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> listadoInscritos(@PathVariable(value = "eduContinuaNombre") String nombreEduContinua, Map<String, Object> model, RedirectAttributes redirectAttributes) {

        EducacionContinua e= educacionContinuaService.findOneByNombre(nombreEduContinua);
		
        ByteArrayInputStream bis = ManejoPdf.generarPDFParticipantes(participanteService.findAllParticipantesByEducacionContinua(nombreEduContinua),e);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=participantes.pdf");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	
}
