package com.ufps.cedcufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IEducacionContinuaCustomDao;
import com.ufps.cedcufps.dao.IJornadaDao;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.JornadaDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.mapper.IJornadaMapper;
import com.ufps.cedcufps.modelos.Jornada;

@Service
public class JornadaService implements IJornadaService{

	@Autowired
	private IJornadaDao jornadaDao;
	
	@Autowired
	private IJornadaMapper jornadaMapper;
	
	@Autowired
	private IEducacionContinuaCustomDao educacionContinuaCustomDao;
	
	@Override
	public List<JornadaAppDto> findAllByIdEducacionContinua(Long idEducacionContinua) {
		// TODO Auto-generated method stub
		return jornadaMapper.convertJornadasToJornadaAppDto(jornadaDao.findByIdEducacionContinua(idEducacionContinua));
	}

	@Override
	public void save(JornadaDto jornadaDto) {
		// TODO Auto-generated method stub
		jornadaDao.save(jornadaMapper.convertJornadaDtoToJornada(jornadaDto, educacionContinuaCustomDao.findEducacionContinuaById(jornadaDto.getIdEducacionContinua())));
	}

	@Override
	public JornadaAppDto findOne(Long id) {
		// TODO Auto-generated method stub
		Jornada j=jornadaDao.findOneById(id);
		if(j==null) {
        	throw new CustomException("No se encontró la jornada");
        }
		return jornadaMapper.convertJornadaToJornadaAppDto(j);
		
	}

	@Override
	public void deleteJornada(Long idJornada) {
		// TODO Auto-generated method stub
		try {
			jornadaDao.deleteJornada(idJornada);
		}catch(Exception e) {
			throw new CustomException("La jornada ya tiene asistencias registradas");
		}
		
	}

	

}
