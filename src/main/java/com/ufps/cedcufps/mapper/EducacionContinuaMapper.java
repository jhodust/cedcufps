package com.ufps.cedcufps.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufps.cedcufps.dto.EducacionContinuaAppDto;
import com.ufps.cedcufps.dto.EducacionContinuaAppDto2;
import com.ufps.cedcufps.dto.JornadaAppDto;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Jornada;
import com.ufps.cedcufps.services.JornadaService;

@Repository
public class EducacionContinuaMapper implements IEducacionContinuaMapper {

	
	@Override
	public List<EducacionContinuaAppDto2> convertEducacionContinuaToApp(List<EducacionContinua> edc) {
		// TODO Auto-generated method stub
		List<EducacionContinuaAppDto2> ecadto=new ArrayList<>();
		
		if(!edc.isEmpty()) {
			for(EducacionContinua e:edc) {
				EducacionContinuaAppDto2 dto= new EducacionContinuaAppDto2();
				dto.setId(e.getId());
				dto.setNombre(e.getNombre());
				dto.setFechaInicio(e.getFechaInicio());
				dto.setFechaFin(e.getFechaFin());
				dto.setTipoEduContinua(e.getTipoEduContinua().getTipoEduContinua());
				dto.setCantidadParticipantes(10);
				dto.setProgramaResponsable(e.getProgramaResponsable().getPrograma());
				String nombreResponsable=null;
				if(e.getDocenteResponsable().getPrimerNombre()!="") {
					nombreResponsable=e.getDocenteResponsable().getPrimerNombre();
				}
				if(e.getDocenteResponsable().getSegundoNombre()!="") {
					nombreResponsable=nombreResponsable + " " +e.getDocenteResponsable().getSegundoNombre();
				}
				if(e.getDocenteResponsable().getPrimerApellido()!="") {
					nombreResponsable=nombreResponsable + " " +e.getDocenteResponsable().getPrimerApellido();
				}
				if(e.getDocenteResponsable().getSegundoApellido()!="") {
					nombreResponsable=nombreResponsable + " " +e.getDocenteResponsable().getSegundoApellido();
				}
				
				dto.setDocenteResponsable(nombreResponsable);
				List<JornadaAppDto>jadto= new ArrayList<JornadaAppDto>();
				for(Jornada j:e.getJornadas()) {
					JornadaAppDto jornada= new JornadaAppDto();
					jornada.setId(j.getId());
					jornada.setHoraFin(j.getHoraFin());
					jornada.setHoraInicio(j.getHoraInicio());
					jornada.setIdEducacionContinua(j.getEducacionContinua().getId());
					jornada.setFechaInicioEduContinua(j.getEducacionContinua().getFechaInicio());
					jornada.setFechaFinEduContinua(j.getEducacionContinua().getFechaFin());
					jadto.add(jornada);
				}
				dto.setJornadas(jadto);
				ecadto.add(dto);
			}
		}
		
		return ecadto;
	}

}
