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

import com.ufps.cedcufps.SpringSecurityConfig;
import com.ufps.cedcufps.modelos.Departamento;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.services.IFacultadService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.services.IProgramaService;
import com.ufps.cedcufps.utils.paginator.PageRender;

@Controller
@SessionAttributes("programa")
@RequestMapping("/programas-academicos")
public class ProgramaController {

	@Autowired
	private IProgramaService programaService;
	
	@Autowired
	private IFacultadService facultadService;
	
	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping
	public String listar(Map<String, Object> model) {
		Pageable pageRequest=PageRequest.of(0, 3);
		
		Page<Programa> programas=programaService.findAll(pageRequest);
		PageRender<Programa> pageRender= new PageRender<Programa>("/programas-academicos/reload", programas);
		model.put("programas",programas);	
		model.put("page",pageRender);
		model.put("programasTotales",programaService.findAll());
		model.put("facultad",new Facultad());//para cuando el filtro es todos
	
		
		model.put("facultades",facultadService.findAll());
		model.put("docentes",personaService.findAllDocentes());
		model.put("photoUser", SpringSecurityConfig.getInfoSession().getPhoto());
		model.put("nameUser", SpringSecurityConfig.getInfoSession().getName());
		return "programa/index";
	}
	
	@RequestMapping(value = "/reload")
	public String reloadList(@RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="facultad", defaultValue = "") String facultad,Map<String, Object> model) {
		Pageable pageRequest=PageRequest.of(page, 3);
		
		if(facultad.equalsIgnoreCase("")) {
			Page<Programa> programas=programaService.findAll(pageRequest);
			PageRender<Programa> pageRender= new PageRender<Programa>("/programas-academicos/reload", programas);
			model.put("programas",programas);	
			model.put("page",pageRender);
			model.put("programasTotales",programaService.findAll());
		}else {
			Page<Programa> programas=programaService.findByFacultad(facultad, pageRequest);
			PageRender<Programa> pageRender= new PageRender<Programa>("/programas-academicos/reload?facultad="+facultad.replaceAll("\\s", "%20"), programas);
			model.put("programas",programas);
			model.put("page",pageRender);
			model.put("programasTotales",programaService.findByFacultad(facultad));
		}
		
		return "programa/index :: listProgramas";
	}
	
}
