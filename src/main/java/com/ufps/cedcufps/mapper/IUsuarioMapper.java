package com.ufps.cedcufps.mapper;

import com.ufps.cedcufps.dto.UsuarioDto;
import com.ufps.cedcufps.modelos.Estudiante;
import com.ufps.cedcufps.modelos.Externo;

public interface IUsuarioMapper {

	public Estudiante convertUsuarioToEstudiante(UsuarioDto u);
	
	public Externo convertUsuarioToExterno(UsuarioDto u);
}
