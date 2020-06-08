package com.ufps.cedcufps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IRolDao;
import com.ufps.cedcufps.modelos.Rol;

@Service
public class RolService implements IRolService{

	@Autowired
	private IRolDao rolDao;

	@Override
	public void save(Rol r) {
		// TODO Auto-generated method stub
		rolDao.save(r);
	}

	@Override
	public void deleteRol(String rol, Long idPersona) {
		// TODO Auto-generated method stub
		rolDao.deleteRolByPersona(rol, idPersona);
	}
}
