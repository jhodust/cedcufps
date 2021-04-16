package com.ufps.cedcufps.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.dao.IAsistenciaCustomDao;
import com.ufps.cedcufps.dao.IAsistenciaDao;
import com.ufps.cedcufps.dao.IJornadaDao;
import com.ufps.cedcufps.dto.AsistenciaDto;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.modelos.Asistencia;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.modelos.Participante;

@Service
public class AsistenciaService implements IAsistenciaService {

	@Autowired
	private IAsistenciaDao asistenciaDao;
	
	@Autowired
	private IAsistenciaCustomDao asistenciaCustomDao;
	
	@Autowired
	private IJornadaDao jornadaDao;

	
	
	@Override
	public List<?> countAsistenciasByJornadas(String eduContinua, String fechaInicio) {
		// TODO Auto-generated method stub
		try {
			return asistenciaDao.countByJornada(eduContinua,new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaInicio));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AsistenciaDto findAsistenciaByJornadaAndParticipante(Long idJornada, Long idParticipante) {
		// TODO Auto-generated method stub
		return asistenciaCustomDao.findAsistenciasByJornadaAndParticipante(idJornada, idParticipante);
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
