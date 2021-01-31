package com.ufps.cedcufps.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.cedcufps.modelos.TipoDocumento;
import com.ufps.cedcufps.services.ITipoDocumentoService;

@Controller
@SessionAttributes("tipoDocumento")
public class TipoDocumentoController {

	/*@Autowired
	private ITipoDocumentoService tipoDocumentoService;
	
	@RequestMapping(value = "/tipo-documento/listar")
	public String listar(Model model) {
		model.addAttribute("titulo","TIPOS DOCUMENTOS");
		model.addAttribute("tiposDocumento",tipoDocumentoService.findAll());
		return "tipo_documento/index";
	}
	
	@RequestMapping(value = "/tipo-documento/registro")
	public String agregar(Map<String, Object> model) {
		TipoDocumento t= new TipoDocumento(); 
		model.put("titulo","FORMULARIO TIPOS DOCUMENTOS");
		model.put("tipoDocumento",t);
		return "tipo_documento/form";
	}
	
	@RequestMapping(value = "/tipo-documento/registro", method = RequestMethod.POST)
	public String save(TipoDocumento t, SessionStatus status) {
		tipoDocumentoService.save(t);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/tipo-documento/registro/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Optional<TipoDocumento> t= tipoDocumentoService.findOne(id); 
		model.put("titulo","FORMULARIO TIPOS DOCUMENTOS");
		model.put("tipoDocumento",t.get());
		return "tipo_documento/form";
	}*/
}
