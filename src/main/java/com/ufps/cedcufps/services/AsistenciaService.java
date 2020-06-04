package com.ufps.cedcufps.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IAsistenciaDao;
import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Jornada;

@Service
public class AsistenciaService implements IAsistenciaService {

	@Autowired
	private IAsistenciaDao asistenciaDao;

	@Override
	public List<Asistencia> findAll() {
		// TODO Auto-generated method stub
		return (List<Asistencia>) asistenciaDao.findAll();
	}
	
	@Override
	public List<Asistencia> findAsistenciasByJornadas(List<Jornada> jornadas) {
		// TODO Auto-generated method stub
		return asistenciaDao.findAsistenciasByJornadas(jornadas);
	}

	@Override
	public List<?> countAsistenciasByJornadas(Long idEduContinua) {
		// TODO Auto-generated method stub
		return asistenciaDao.countByJornada(idEduContinua);
	}

	@Override
	public Asistencia findAsistenciaByJornadaAndParticipante(Long idJornada, Long idParticipante) {
		// TODO Auto-generated method stub
		return asistenciaDao.findAsistenciasByJornadaAndParticipante(idJornada, idParticipante);
	}

	@Override
	public void saveAsistencias(ArrayList<Asistencia> asistencias) {
		// TODO Auto-generated method stub
		asistenciaDao.saveAll(asistencias);
	}

	@Override
	public void save(Asistencia a) {
		// TODO Auto-generated method stub
		asistenciaDao.save(a);
		
	}
	
	@Override
	public void deleteAll(List<Asistencia> asistencias) {
		// TODO Auto-generated method stub
		asistenciaDao.deleteAll(asistencias);
		
	}

	
	@Override
	public void deleteAsistencia(Long idJornada, Long idParticipante) {
		// TODO Auto-generated method stub
		asistenciaDao.deleteAsistencia(idJornada, idParticipante);
	}

	
}
