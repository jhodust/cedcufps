package com.ufps.cedcufps.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.cedcufps.modelos.Asistente;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Persona;
import com.ufps.cedcufps.modelos.Rol;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.services.IRolService;
import com.ufps.cedcufps.utils.CodigoQR;
import com.ufps.cedcufps.utils.Encrypt;


@RestController
@RequestMapping(value = "/usuarios")
public class RoleRestController {

	@Autowired
	private IRolService roleService;
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping(value = "/update-roles",produces = "application/json")
	public ResponseEntity<?> asignarRole(@RequestParam("idPersona") String idP,@RequestParam("permisosEdC") boolean hasPermisosForEdC,
			@RequestParam("permisosPersonas") boolean hasPermisosForPeople,@RequestParam("permisosAttendance") boolean hasPermisosForAttendance,
			@RequestParam(name = "idsPEdC[]",required = false) List<Long> idsProgramasEdC,@RequestParam(name ="idsPEs[]",required = false) List<Long> idsProgramasEst,
			@RequestParam(name ="idsDD[]",required = false) List<Long> idsDeptosDoc,	@RequestParam(name ="idsPG[]",required = false) List<Long> idsProgramasGrad,
			@RequestParam("permisoAdminvo") boolean hasPermisosForAdminvo,@RequestParam("permisoExterno") boolean hasPermisosForExternos,
			@RequestParam(name ="idsEdCA[]",required = false) List<Long> idsEducacionContinuaForAtt, @RequestParam("isDirPrograma") boolean isDirPrograma,
			@RequestParam("isDoc") boolean isDocente, @RequestParam(name = "idProgramaDirector") String idProgramaDirector) {
		
		Long idPersona=null;
		if(idP !=null && !idP.isEmpty()) {
			idPersona=Long.parseLong(idP);
		}
		
		Long idPDirector=null;
		if(idProgramaDirector !=null && !idProgramaDirector.isEmpty()) {
			idPDirector=Long.parseLong(idProgramaDirector);
		}
		personaService.updatePermisos(idPersona, hasPermisosForEdC, hasPermisosForPeople,
				hasPermisosForAttendance, idsProgramasEdC, idsProgramasEst, idsDeptosDoc, idsProgramasGrad, 
				idsEducacionContinuaForAtt, hasPermisosForAdminvo, hasPermisosForExternos, isDirPrograma, isDocente, idPDirector);
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
}
