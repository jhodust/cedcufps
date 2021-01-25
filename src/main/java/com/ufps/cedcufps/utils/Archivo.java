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
		System.out.println("entra a metodo save Image about educontinua");
		System.out.println("extension imagen: " + Files.getFileExtension(imagen.getOriginalFilename()));
		String nombreImagen;
		try {
			
			System.out.println("sout nombre imagen en save image about");
			System.out.println(path.resolve(imagenSinExtension+"."+Files.getFileExtension(imagen.getOriginalFilename())).toFile().getCanonicalPath());
			System.out.println(path.resolve(imagenSinExtension+"."+Files.getFileExtension(imagen.getOriginalFilename())).toString());
			System.out.println(path.resolve(imagenSinExtension+"."+Files.getFileExtension(imagen.getOriginalFilename())).toAbsolutePath());
			nombreImagen = path.resolve(imagenSinExtension+"."+Files.getFileExtension(imagen.getOriginalFilename())).toString();
			System.out.println("nombre imagennnnnnnnnnnnnnnnnn en save image about educacion continua");
			System.out.println(nombreImagen);
			byte[] bytes = imagen.getBytes();
			guardarImagen(bytes,nombreImagen);
			System.out.println("retorno nombre Imagen en saveImagenAboutEduContinua");
			System.out.println(nombreImagen);
			return nombreImagen;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("File Not FOund");
			throw new CustomException("File Not Found");
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
			System.out.println("genera error en save imagen");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return nombreImagen;
	}
	
	public static void guardarImagen(byte[] bytes, String nombreImagen) {
		try {
			System.out.println("--------------guardando imagen nombre--------------");
			System.out.println(nombreImagen);
			Path rutaArchivo=Paths.get(nombreImagen);
			Files.write(bytes, rutaArchivo.toFile().getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("genera error en guardar imagen");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void deleteImage(String imagen) {
		System.out.println("*****************eliminando imagen**************************");
		System.out.println(imagen);
		File archivo=Paths.get(imagen).toFile();
		System.out.println(archivo.getAbsolutePath());
		archivo.delete();
	}
	
	public static String saveImagenBase64(String filename,String encoded) {
		try {
			File archivo=Paths.get(filename).toFile();
			System.out.println("pathhhhhhhhhhhhhhhhhhhhhhhhhh");
			System.out.println(archivo.getAbsolutePath());
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
	
	/*public static void generarDirectoriosPropiosEducacionContinua(Long idEducacionContinua, Path dirEducacionContinua) {
		Files.createDirectories(dirEducacionContinua.);
		Archivo.crearDirectorio(String.valueOf(idEducacionContinua));//directorio de la educacion continua
		Archivo.crearDirectorio(idEducacionContinua+"/qr-participantes");//directorio interno de los qr de participantes de la educacion continua
		Archivo.crearDirectorio(idEducacionContinua+"/tarjetas-inscripcion");
		Archivo.crearDirectorio(idEducacionContinua+"/plantilla-diploma");
		Archivo.crearDirectorio(idEducacionContinua+"/diplomas-participantes");
	}*/
}
