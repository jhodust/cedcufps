package com.ufps.cedcufps.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IAsistenciaDao;
import com.ufps.cedcufps.dao.IJornadaDao;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;

@Service
public class AsistenciaService implements IAsistenciaService {

	@Autowired
	private IAsistenciaDao asistenciaDao;
	
	@Autowired
	private IJornadaDao jornadaDao;

	@Override
	public List<Asistencia> findAll() {
		// TODO Auto-generated method stub
		return (List<Asistencia>) asistenciaDao.findAll();
	}
	
	@Override
	public List<Asistencia> findAsistenciasByJornadas(List<JornadaAppDto> jornadas) {
		// TODO Auto-generated method stub
		List<Long> idsJornadas=new ArrayList<>();
		for(JornadaAppDto j: jornadas) {
			idsJornadas.add(j.getId());
		}
		return asistenciaDao.findAsistenciasByJornadas(idsJornadas);
	}

	@Override
	public List<?> countAsistenciasByJornadas(String eduContinua) {
		// TODO Auto-generated method stub
		return asistenciaDao.countByJornada(eduContinua);
	}

	@Override
	public Asistencia findAsistenciaByJornadaAndParticipante(Long idJornada, Long idParticipante) {
		// TODO Auto-generated method stub
		return asistenciaDao.findAsistenciasByJornadaAndParticipante(idJornada, idParticipante);
	}

	@Override
	public void saveAsistencias(Long idJornada) {
		// TODO Auto-generated method stub
			Jornada j= jornadaDao.findOneById(idJornada);
			ArrayList <Asistencia> asistencias=new ArrayList<>();
			for(Participante p:j.getEducacionContinua().getParticipantes()) {
				Asistencia a=new Asistencia();
				a.setJornada(j);
				a.setParticipante(p);
				if(this.findAsistenciaByJornadaAndParticipante(a.getJornada().getId(), a.getParticipante().getId())==null){
					asistencias.add(a);
				}
			}
		asistenciaDao.saveAll(asistencias);
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

	@Override
	public void deleteAsistenciasByJornada(Long idJornada) {
		// TODO Auto-generated method stub
		asistenciaDao.deleteAsistenciasByJornada(idJornada);
	}

	@Override
	public boolean marcarAsistencia(Long idJornada, Long idParticipante, int cantParticipantes) {
		// TODO Auto-generated method stub
		asistenciaDao.matchAsistencia(new Date(), idJornada, idParticipante);
		
		return asistenciaDao.countAsistenciasOfJornada(idJornada)==cantParticipantes;
	}

	
}
