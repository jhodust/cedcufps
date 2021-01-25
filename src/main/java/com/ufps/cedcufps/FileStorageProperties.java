package com.ufps.cedcufps;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	
	private String dirFormatoReportes;
	private String dirReportesSnies;
	private String dirEducacionContinua;
	private String dirQrParticipantes;
	private String dirTarjetasInscripcion;
	private String dirDiplomasParticipantes;
	private String dirPlantillaCursos;
	private String dirPlantillaEducacionContinua;
	private String dirPlantillaParticipantesResponsables;
	private String dirImgPdfAsistentes;
			
	public String getDirFormatoReportes() {
		return dirFormatoReportes;
	}
	public void setDirFormatoReportes(String dirFormatoReportes) {
		this.dirFormatoReportes = dirFormatoReportes;
	}
	public String getDirReportesSnies() {
		return dirReportesSnies;
	}
	public void setDirReportesSnies(String dirReportesSnies) {
		this.dirReportesSnies = dirReportesSnies;
	}
	public String getDirEducacionContinua() {
		return dirEducacionContinua;
	}
	public void setDirEducacionContinua(String dirEducacionContinua) {
		this.dirEducacionContinua = dirEducacionContinua;
	}
	public String getDirQrParticipantes() {
		return dirQrParticipantes;
	}
	public void setDirQrParticipantes(String dirQrParticipantes) {
		this.dirQrParticipantes = dirQrParticipantes;
	}
	public String getDirTarjetasInscripcion() {
		return dirTarjetasInscripcion;
	}
	public void setDirTarjetasInscripcion(String dirTarjetasInscripcion) {
		this.dirTarjetasInscripcion = dirTarjetasInscripcion;
	}
	
	public String getDirDiplomasParticipantes() {
		return dirDiplomasParticipantes;
	}
	public void setDirDiplomasParticipantes(String dirDiplomasParticipantes) {
		this.dirDiplomasParticipantes = dirDiplomasParticipantes;
	}
	public String getDirPlantillaCursos() {
		return dirPlantillaCursos;
	}
	public void setDirPlantillaCursos(String dirPlantillaCursos) {
		this.dirPlantillaCursos = dirPlantillaCursos;
	}
	public String getDirPlantillaEducacionContinua() {
		return dirPlantillaEducacionContinua;
	}
	public void setDirPlantillaEducacionContinua(String dirPlantillaEducacionContinua) {
		this.dirPlantillaEducacionContinua = dirPlantillaEducacionContinua;
	}
	public String getDirPlantillaParticipantesResponsables() {
		return dirPlantillaParticipantesResponsables;
	}
	public void setDirPlantillaParticipantesResponsables(String dirPlantillaParticipantesResponsables) {
		this.dirPlantillaParticipantesResponsables = dirPlantillaParticipantesResponsables;
	}
	public String getDirImgPdfAsistentes() {
		return dirImgPdfAsistentes;
	}
	public void setDirImgPdfAsistentes(String dirImgPdfAsistentes) {
		this.dirImgPdfAsistentes = dirImgPdfAsistentes;
	}
	
	
}
