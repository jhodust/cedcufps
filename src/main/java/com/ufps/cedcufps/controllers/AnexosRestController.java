package com.ufps.cedcufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.services.IAnexosService;

@RestController
@RequestMapping(value = "/educacion-continua/anexos")
public class AnexosRestController {

	@Autowired
	private IAnexosService anexosService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestParam(name="file", required=true) MultipartFile file,
			@RequestParam(name="id", required=true) String idEduContinuaAcceso) {
		
		
		anexosService.saveAnexo(file, idEduContinuaAcceso);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/delete")
	public ResponseEntity<?> save(@RequestParam(name="id", required=true) String id) {
		anexosService.deleteAnexo(Long.parseLong(id));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
