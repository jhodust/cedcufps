package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ufps.cedcufps.modelos.TipoDocumento;
import com.ufps.cedcufps.services.ITipoDocumentoService;

@Controller
@SessionAttributes("tipoDocumento")
public class TipoDocumentoController {

	@Autowired
	private ITipoDocumentoService tipoDocumentoService;
	
	@RequestMapping(value = "/tipo-documento/listar")
	public String listar(Model model) {
		model.addAttribute("titulo","TIPOS DOCUMENTOS");
		model.addAttribute("tiposDocumento",tipoDocumentoService.findAll());
		return "tipo_documento/index";
	}
	
	@RequestMapping(value = "/tipo-documento/agregar")
	public String save(Map<String, Object> model) {
		TipoDocumento t= new TipoDocumento(); 
		model.put("titulo","FORMULARIO TIPOS DOCUMENTOS");
		model.put("tipoDocumento",t);
		return "tipo_documento/form";
	}
	
	@RequestMapping(value = "/tipo-documento/agregar", method = RequestMethod.POST)
	public String save(TipoDocumento t) {
		tipoDocumentoService.save(t);
		return "redirect:listar";
	}
}
