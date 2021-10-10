package com.ufps.cedcufps.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dao.IEducacionContinuaCustomDao;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.dto.JornadaDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;

@Repository
public class JornadaMapper implements IJornadaMapper {

	
	
	
	@Override
	public List<JornadaAppDto> convertJornadasToJornadaAppDto(List<Jornada> jornadas) {
		// TODO Auto-generated method stub
		List<JornadaAppDto> jadto= new ArrayList<JornadaAppDto>();
		if(!jornadas.isEmpty()) {
			for(Jornada j: jornadas) {
				jadto.add(this.convertJornadaToJornadaAppDto(j));
			}
		}
		return jadto;
	}

	@Override
	public JornadaAppDto convertJornadaToJornadaAppDto(Jornada j) {
		// TODO Auto-generated method stub
		JornadaAppDto dto= new JornadaAppDto();
		dto.setId(j.getId());
		dto.setHoraFin(j.getHoraFin());
		dto.setHoraInicio(j.getHoraInicio());
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatHour = new SimpleDateFormat("HH:mm");
		dto.setFechaJornadaString(formatDate.format(j.getHoraInicio()));
		dto.setHoraInicioString(formatHour.format(j.getHoraInicio()));
		dto.setHoraFinString(formatHour.format(j.getHoraFin()));
		return dto;
	}

	@Override
	public Jornada convertJornadaDtoToJornada(JornadaDto jornadaDto, EducacionContinua edC) {
		// TODO Auto-generated method stub
		
		Date horaInicioFormat = null;
		Date horaFinFormat = null;
		try {
			String format="dd/MM/yyyy HH:mm";
			horaInicioFormat = new SimpleDateFormat(format).parse(jornadaDto.getHoraInicio());
			horaFinFormat= new SimpleDateFormat(format).parse(jornadaDto.getHoraFin());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Jornada jornada = new Jornada();
		jornada.setHoraInicio(horaInicioFormat);
		jornada.setHoraFin(horaFinFormat);
		jornada.setEducacionContinua(edC);
		jornada.setId(jornadaDto.getId());
		return jornada;
	}
	
	

}
