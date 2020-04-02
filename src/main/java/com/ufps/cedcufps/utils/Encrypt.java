package com.ufps.cedcufps.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Encrypt {

    
	public static String encriptar(String s){
        try {
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
