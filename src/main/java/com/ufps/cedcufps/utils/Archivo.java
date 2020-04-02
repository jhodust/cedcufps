package com.ufps.cedcufps.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		String rutaImagen=imagenSinExtension+"."+Files.getFileExtension(imagen.getOriginalFilename());
		try {
			byte[] bytes = imagen.getBytes();
			Path rutaArchivo=Paths.get("src//main//resources//static"+rutaImagen);
			Files.write(bytes, rutaArchivo.toFile().getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rutaImagen;
	}
	
	public static void deleteImage(String imagen) {
		System.out.println("*****************eliminando imagen**************************");
		Path ruta=Paths.get(Paths.get("src//main//resources//static").toFile().getAbsolutePath()+imagen);
		File archivo=ruta.toFile();
		System.out.println(archivo.getAbsolutePath());
		archivo.delete();
	}
}
