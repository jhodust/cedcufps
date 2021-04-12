package com.ufps.cedcufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufps.cedcufps.services.IAnexosService;
import com.ufps.cedcufps.services.IEducacionContinuaService;

@Controller
public class AnexoController {

	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IAnexosService anexosService;
	
	@RequestMapping(value = "/educacion-continua/detalles/anexos/{id}")
	public String reloadListAnexos(@PathVariable(value = "id") String idAcceso, Map<String, Object> model) {
		model.put("anexos",anexosService.findAnexosByEduContinuaIdAcceso(idAcceso));
		return "educacion_continua/anexos/listadoAnexos :: listadoAnexos";
	}
}
