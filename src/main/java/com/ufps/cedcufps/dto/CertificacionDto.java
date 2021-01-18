package com.ufps.cedcufps.dto;

public class CertificacionDto {

	private ParticipanteDto participanteDto;
	private DiplomaDto diplomaDto;
	private boolean updateDiploma;
	
	public ParticipanteDto getParticipanteDto() {
		return participanteDto;
	}
	public void setParticipanteDto(ParticipanteDto participanteDto) {
		this.participanteDto = participanteDto;
	}
	public DiplomaDto getDiplomaDto() {
		return diplomaDto;
	}
	public void setDiplomaDto(DiplomaDto diplomaDto) {
		this.diplomaDto = diplomaDto;
	}
	public boolean isUpdateDiploma() {
		return updateDiploma;
	}
	public void setUpdateDiploma(boolean updateDiploma) {
		this.updateDiploma = updateDiploma;
	}
	
	
}
