package com.ufps.cedcufps.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.cedcufps.FileStorageProperties;


@Service
public class FileStorageService implements IFileStorageService {

		private final Logger log = LoggerFactory.getLogger(FileStorageService.class);
		
		private FileStorageProperties env;
		private Path dirFormatoReportes;
		private Path dirReportesSnies;
		private Path dirEducacionContinua;
		private Path dirPlantillaCursos;
		private Path dirPlantillaEducacionContinua;
		private Path dirPlantillaParticipantesResponsables;
		private Path dirImgPdfAsistentes;
		
		@Autowired
		public FileStorageService(FileStorageProperties env) {
			this.env = env;
			
			this.dirFormatoReportes = Paths.get(env.getDirFormatoReportes()).normalize();
			this.dirReportesSnies = Paths.get(env.getDirReportesSnies()).normalize();
			this.dirEducacionContinua = Paths.get(env.getDirEducacionContinua()).normalize();
			this.dirPlantillaCursos = Paths.get(env.getDirPlantillaCursos()).normalize();
			this.dirPlantillaEducacionContinua = Paths.get(env.getDirPlantillaEducacionContinua()).normalize();
			this.dirPlantillaParticipantesResponsables = Paths.get(env.getDirPlantillaParticipantesResponsables()).normalize();
			this.dirImgPdfAsistentes = Paths.get(env.getDirImgPdfAsistentes()).normalize();
			
			try {
				Files.createDirectories(this.dirFormatoReportes);
				Files.createDirectories(this.dirReportesSnies);
				Files.createDirectories(this.dirEducacionContinua);
			} catch (Exception ex) {
				log.error("Could not create the directory where the uploaded files will be stored.", ex);
			}

		}


		@Override
		public Path dirFormatoReportes() {
			// TODO Auto-generated method stub
			return this.dirFormatoReportes;
		}


		@Override
		public Path dirReportesSnies() {
			// TODO Auto-generated method stub
			return this.dirReportesSnies;
		}


		@Override
		public Path dirEducacionContinua() {
			// TODO Auto-generated method stub
			return this.dirEducacionContinua;
		}


		@Override
		public String dirQrParticipantes() {
			// TODO Auto-generated method stub
			return env.getDirQrParticipantes();
		}


		@Override
		public String dirTarjetasInscripcion() {
			// TODO Auto-generated method stub
			return env.getDirTarjetasInscripcion();
		}




		@Override
		public String dirDiplomasParticipantes() {
			// TODO Auto-generated method stub
			return env.getDirDiplomasParticipantes();
		}


		@Override
		public Path dirPlantillaCursos() {
			// TODO Auto-generated method stub
			return this.dirPlantillaCursos;
		}


		@Override
		public Path dirPlantillaEducacionContinua() {
			// TODO Auto-generated method stub
			return this.dirPlantillaEducacionContinua;
		}


		@Override
		public Path dirPlantillaParticipantesResponsables() {
			// TODO Auto-generated method stub
			return this.dirPlantillaParticipantesResponsables;
		}

		@Override
		public Path dirImgPdfAsistentes() {
			// TODO Auto-generated method stub
			return this.dirImgPdfAsistentes;
		}


		@Override
		public String dirAnexos() {
			// TODO Auto-generated method stub
			return env.getDirAnexos();
		}
}