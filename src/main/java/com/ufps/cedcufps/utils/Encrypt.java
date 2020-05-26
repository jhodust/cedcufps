package com.ufps.cedcufps.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Encrypt {

    
	public static String encriptar(String s){
		Date fechaInscripcion = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    s=s+"_"+formateador.format(fechaInscripcion);
        try {
        	System.out.println("s final");
        	System.out.println(s);
			return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
	public static String desencriptar(String s){
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        try {
			return new String(decode, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}
