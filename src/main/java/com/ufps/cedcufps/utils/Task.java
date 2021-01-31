package com.ufps.cedcufps.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ufps.cedcufps.dao.IEducacionContinuaDao;



@Component
public class Task {

	@Autowired
	private IEducacionContinuaDao educacionContinuaDao;
	
	@Scheduled(cron = "${app.scheduled-task.estado-educacion-continua.frequency}", zone = "America/Bogota")
	public void checkAssignements() {
		System.out.println("PROCESO SCHEDULEDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		this.educacionContinuaDao.updateEstadoEduContinua();
	}
}

