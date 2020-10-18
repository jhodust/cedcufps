package com.ufps.cedcufps.dto;

import java.util.List;

public class DiplomaDto {
	
	private Long id;
	private List<ImagenesDiplomaDto> imagenes;
	private List<TextosDiplomaDto> textos;
	private List<FirmasDiplomaDto> firmas;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<ImagenesDiplomaDto> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<ImagenesDiplomaDto> imagenes) {
		this.imagenes = imagenes;
	}
	public List<TextosDiplomaDto> getTextos() {
		return textos;
	}
	public void setTextos(List<TextosDiplomaDto> textos) {
		this.textos = textos;
	}
	public List<FirmasDiplomaDto> getFirmas() {
		return firmas;
	}
	public void setFirmas(List<FirmasDiplomaDto> firmas) {
		this.firmas = firmas;
	}
	
	public void addTexto(TextosDiplomaDto textoDiplomaDto) {
		this.textos.add(textoDiplomaDto);
	}
	
	public void addFirmas(FirmasDiplomaDto firmasDiplomaDto) {
		this.firmas.add(firmasDiplomaDto);
	}
	
	public void addImagenes(ImagenesDiplomaDto imagenesDiplomaDto) {
		this.imagenes.add(imagenesDiplomaDto);
	}
}
