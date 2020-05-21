package com.ufps.cedcufps.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.services.IProgramaService;
import com.ufps.cedcufps.utils.paginator.PageRender;

@Controller
@SessionAttributes("programa")
public class ProgramaController {

	@Autowired
	private IProgramaService programaService;
	
	@Autowired
	private IFacultadService facultadService;
	
	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value = "/programas-academicos")
	public String listar(@RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="facultad", defaultValue = "") String facultad,Map<String, Object> model) {
		Pageable pageRequest=PageRequest.of(page, 9);
		
		if(facultad.equalsIgnoreCase("")) {
			Page<Programa> programas=programaService.findAll(pageRequest);
			PageRender<Programa> pageRender= new PageRender<Programa>("/programas-academicos", programas);
			model.put("programas",programas);	
			model.put("page",pageRender);
			model.put("programasTotales",programaService.findAll());
			model.put("facultad",new Facultad());//para cuando el filtro es todos
		}else {
			Page<Programa> programas=programaService.findByFacultad(facultad, pageRequest);
			PageRender<Programa> pageRender= new PageRender<Programa>("/programas-academicos", programas);
			model.put("programas",programas);
			model.put("page",pageRender);
			model.put("programasTotales",programaService.findByFacultad(facultad));
			model.put("facultad",facultadService.findByFacultad(facultad));
		}
		model.put("facultades",facultadService.findAll());
		model.put("docentes",personaService.findAllDocentes());
		return "programa/index";
	}
	
	/*@RequestMapping(value = "/programas-academicos/filter/{facultad}")
	public String filtrarByFacultad(@PathVariable(value = "facultad") String facultad, Map<String, Object> model) {
		model.put("titulo","PROGRAMAS");
		model.put("programas",programaService.findByFacultad(facultad));
		model.put("facultad",facultadService.findByFacultad(facultad));
		model.put("facultades",facultadService.findAll());
		return "programa/index";
	}*/
	
	/*@RequestMapping(value = "/programas-academicos/registro")
	public String agregar(Map<String, Object> model) {
		Programa t= new Programa(); 
		model.put("titulo","FORMULARIO PROGRAMAS");
		model.put("programa",t);
		return "programa/form";
	}
	
	@RequestMapping(value = "/programas-academicos/registro", method = RequestMethod.POST)
	public String save(Programa p, SessionStatus status) {
		programaService.save(p);
		status.setComplete();
		return "redirect:/programas-academicos";
	}
	
	@RequestMapping(value = "/programas-academicos/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Optional<Programa> p= programaService.findOne(id); 
		model.put("titulo","FORMULARIO PROGRAMAS");
		model.put("programa",p.get());
		return "programa/form";
	}
	
*/
}
