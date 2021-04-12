package com.ufps.cedcufps.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.ufps.cedcufps.exception.CustomException;
import com.ufps.cedcufps.modelos.EducacionContinua;


public class Archivo {

	//public final static String rutaEducacionContinua="files/uploads/educacion-continua/";
	
	
	
	
	public static String saveImageAboutEducacionContinua(MultipartFile imagen, String imagenSinExtension,Path path) {
		String nombreImagen;
		try {
			
			nombreImagen = path.resolve(imagenSinExtension+"."+Files.getFileExtension(imagen.getOriginalFilename())).toString();
			byte[] bytes = imagen.getBytes();
			guardarImagen(bytes,nombreImagen);
			return nombreImagen;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			throw new CustomException("File Not Found");
		}
		
	}
	
	public static String saveImage(MultipartFile imagen, String imagenSinExtension) {
		String nombreImagen=imagenSinExtension+"."+Files.getFileExtension(imagen.getOriginalFilename());
		try {
			byte[] bytes = imagen.getBytes();
			guardarImagen(bytes,nombreImagen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return nombreImagen;
	}
	
	public static void guardarImagen(byte[] bytes, String nombreImagen) {
		try {
			Path rutaArchivo=Paths.get(nombreImagen);
			Files.write(bytes, rutaArchivo.toFile().getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteImage(String imagen) {
		File archivo=Paths.get(imagen).toFile();
		archivo.delete();
	}
	
	public static String saveImagenBase64(String filename,String encoded) {
		try {
			File archivo=Paths.get(filename).toFile();
			byte[] decodedBytes = Base64.getMimeDecoder().decode(encoded.split(",")[1]);
			//guardarImagen(decodedBytes,filename);
			FileUtils.writeByteArrayToFile(archivo, decodedBytes);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		 return filename;
		
	}
	
	public static String generarNombreAleatorio() {
		char n;
    	Random rnd = new Random();
    	String cadena = new String();
    	for (int i=0; i < 20 ; i++) {
    	n = (char)(rnd.nextDouble() * 26.0 + 65.0 );
    	cadena += n; }
    	return cadena.toLowerCase();
	}
	
	public static String getNameWithoutExtension(String fileName) {
		   int dotIndex = fileName.lastIndexOf('.');
		   return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
	}
}
