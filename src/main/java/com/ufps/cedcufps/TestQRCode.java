package com.ufps.cedcufps;


import com.ufps.cedcufps.utils.CodigoQR;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Formatter;

import javax.swing.text.MaskFormatter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestQRCode {

    public static void main(String[] args) {

       /*int numero=0;
    	String numero2 = "010";
    	String codigo=String.format("%03d", Integer.parseInt(numero2)+1);
    	System.out.println(codigo);
    	System.out.println("------------------------------");
    	
    	numero = 1;
    	codigo=String.format("%03d", numero);
    	System.out.println(codigo);
    	System.out.println("------------------------------");
    	
    	numero = 20;
    	codigo=String.format("%03d", numero);
    	System.out.println(codigo);
    	System.out.println("------------------------------");
    	
    	numero = 300;
    	codigo=String.format("%03d", numero);
    	System.out.println(codigo);
    	System.out.println("------------------------------");
    	
    	numero = 405;
    	codigo=String.format("%03d", numero);
    	System.out.println(codigo);
    	System.out.println("------------------------------");
        
    	Long id = null;
    	if(id!=null) {
    		System.out.println("sisas");
    	}
    	
    	BCryptPasswordEncoder password= new BCryptPasswordEncoder();
    	for(int i=0; i<8;i++) {
    		System.out.println(password.encode("12345"));
    	}
    	
    	*/
			String cad="841584651";
			String nuevo="";
			for(int i=cad.length();i>=0;i-=3) {
				int  j=i-3;
				if(j>0) {
					if(nuevo.isEmpty()) {
						nuevo=cad.substring(j,i);
					}else {
						nuevo=cad.substring(j, i)+"."+nuevo;
					}
					
				}else {
					if(i>0) {
						nuevo=cad.substring(0, i)+"."+nuevo;
					}
					
				}
				
				
			}
			//System.out.println(nuevo.);
		System.out.println(cad.substring(4, 7));
	    System.out.println(cad.substring(1, 4));
	    System.out.println(cad.substring(0, 1));
			
    }

    
}
