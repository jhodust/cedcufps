package com.ufps.cedcufps.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.dao.IAnexosDao;
import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dto.AnexosDto;
import com.ufps.cedcufps.dto.PersonaDto;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.mapper.EducacionContinuaMapper;
import com.ufps.cedcufps.modelos.Anexos;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.utils.Archivo;

@Service
public class AnexosService implements IAnexosService {

	@Autowired
	private IFileStorageService fileStorageService;
	
	@Autowired
	private IEducacionContinuaDao educacionContinuaDao;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IAnexosDao anexosDao;
	
	@Autowired
	private EducacionContinuaMapper educacionContinuaMapper;
	
	@Override
	public void saveAnexo(MultipartFile file, String idEduContinua) {
		// TODO Auto-generated method stub
		System.out.println("create dir edu continua");
		educacionContinuaService.createDirEducacionContinua(Long.parseLong(idEduContinua));
		String originalName=Archivo.getNameWithoutExtension(file.getOriginalFilename());
		String fileName=Archivo.saveImageAboutEducacionContinua(file,originalName,fileStorageService.dirEducacionContinua().resolve(String.valueOf(idEduContinua)).resolve(fileStorageService.dirAnexos()));
		EducacionContinua e= educacionContinuaDao.findEducacionContinuaById(Long.parseLong(idEduContinua));
		if(e==null) {
			throw new CustomException("La educación continua a la cuál le va a agregar el anexo no se encuentra registrada");
		}
		Anexos a = new Anexos();
		a.setNombre(originalName);
		a.setFile(fileName);
		a.setFechaRegisto(new Date());
		a.setEducacionContinua(e);
		
		anexosDao.save(a);
	}

	@Override
	public List<AnexosDto> findAnexosByEduContinuaIdAcceso(String idAccesoEduContinua) {
		// TODO Auto-generated method stub
		return educacionContinuaMapper.convertListAnexoToListAnexoDto(anexosDao.findAnexosByEduContinuaIdAcceso(idAccesoEduContinua));
	}

	@Override
	public void deleteAnexo(Long idAnexo) {
		// TODO Auto-generated method stub
		Anexos a= this.anexosDao.findAnexoById(idAnexo);
		Archivo.deleteImage(a.getFile());
		anexosDao.deleteById(idAnexo);
	}
	
	

}
