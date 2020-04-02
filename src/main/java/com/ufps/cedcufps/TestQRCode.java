package com.ufps.cedcufps;


import com.ufps.cedcufps.utils.CodigoQR;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;

public class TestQRCode {

    public static void main(String[] args) {

       int numero=0;
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
    }

    
}
