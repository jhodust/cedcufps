package com.ufps.cedcufps.dao;

import com.ufps.cedcufps.dto.DocenteDto;

public interface IDocenteCustomDao {

	public DocenteDto findDocenteByIdPersona(Long idPersona);
}
