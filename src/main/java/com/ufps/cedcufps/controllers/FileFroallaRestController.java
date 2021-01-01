package com.ufps.cedcufps.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
public class FileFroallaRestController {

	@PostMapping(value = "/xxxxx")
	public @ResponseBody ResponseEntity<?> saveImage(@RequestParam( required=false) MultipartFile file) {
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
