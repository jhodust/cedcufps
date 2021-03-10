package com.ufps.cedcufps.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/educacion-continua/anexos")
public class AnexosRestController {

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestParam(name="file", required=true) MultipartFile file,
			@RequestParam(name="id", required=true) String idEduContinuaAcceso) {
		
		System.out.println("fileeeeeeeeeeeeeeeeeeeeeeeeeee");
		System.out.println(file.getOriginalFilename());
		System.out.println("idAccessoooo");
		System.out.println(idEduContinuaAcceso);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
