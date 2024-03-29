package com.ufps.cedcufps.services;

import java.nio.file.Path;

import org.springframework.core.io.Resource;

public interface IFileStorageService {

	public Path dirFormatoReportes();
	public Path dirReportesSnies();
	public Path dirEducacionContinua();
	public String dirQrParticipantes();
	public String dirTarjetasInscripcion();
	public String dirDiplomasParticipantes();
	public String dirAnexos();
	public Path dirPlantillaCursos();
	public Path dirPlantillaEducacionContinua();
	public Path dirPlantillaParticipantesResponsables();
	public Path dirImgPdfAsistentes();
	public Resource loadFileTxt(Path filePath);
	
}
