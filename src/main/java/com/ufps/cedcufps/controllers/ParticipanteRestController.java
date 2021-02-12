package com.ufps.cedcufps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.dao.IFacultadDao;
import com.ufps.cedcufps.dao.IParticipanteDao;
import com.ufps.cedcufps.dto.ParticipanteDto;
import com.ufps.cedcufps.modelos.Facultad;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.services.IParticipanteService;

@RestController
public class ParticipanteRestController {

	@Autowired
	private IParticipanteService participanteService;
	
	@Autowired
	private IFacultadDao facultadDao;
	
	@GetMapping(value="/listparticipantes", produces = "application/json")
    public @ResponseBody DataTablesOutput<ParticipanteDto> listadoParticipantes(@RequestParam String token , DataTablesInput input) {
		
		System.out.println("**************************************************");
		System.out.println("**************************************************");
		System.out.println("**************************************************");
		System.out.println(input.getDraw());
		
        return  participanteService.findParticipantesByEducacionContinua(token, input);
    }
}
