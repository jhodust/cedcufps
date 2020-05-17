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

public class Archivo {

	public static void crearDirectorio(String ruta) {
		Path directorioRecursos=Paths.get("src//main//resources//static//"+ruta);
		File folder=directorioRecursos.toFile();
		if(!folder.exists() || !folder.isDirectory()) {
			folder.mkdir();
		}
	}
	
	
	public static String saveImage(MultipartFile imagen, String imagenSinExtension) {
		System.out.println("extension imagen: " + Files.getFileExtension(imagen.getOriginalFilename()));
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
			
			Path rutaArchivo=Paths.get("src//main//resources//static"+nombreImagen);
			Files.write(bytes, rutaArchivo.toFile().getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteImage(String imagen) {
		System.out.println("*****************eliminando imagen**************************");
		File archivo=Paths.get("src//main//resources//static"+imagen).toFile();
		System.out.println(archivo.getAbsolutePath());
		archivo.delete();
	}
	
	public static String saveImagenBase64(String filename,String encoded) {
		try {
			File archivo=Paths.get("src//main//resources//static"+filename).toFile();
			
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
}
