package com.ufps.cedcufps.dao;

public interface IDiplomaCustomDao {

	public Long createDiploma(String imagen );
	public void saveImagenDiploma(Long idDiploma,String rutaImagen,int x, int y );
	public void saveTexto(Long idDiploma,String texto, String categoria,int x, int y);
	public void saveFirma(Long idDiploma, String cargo, int xCargo, int yCargo, 
			String nombre, int xNombre, int yNombre, String rutaImagen, int x, int y);
	public void updateDiplomaEduContinua(Long idDiploma, Long idEduContinua);
}
