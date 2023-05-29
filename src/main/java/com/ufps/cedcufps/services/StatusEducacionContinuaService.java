package com.ufps.cedcufps.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufps.cedcufps.utils.StatusEducacionContinua;

@Service
public class StatusEducacionContinuaService implements IStatusEducacionContinua {

	@Override
	public List<StatusEducacionContinua> getAllStatus() {
		// TODO Auto-generated method stub
		return StatusEducacionContinua.getAllStatusEducacionContinua();
	}

	
}
