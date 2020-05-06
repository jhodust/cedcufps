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

    	BCryptPasswordEncoder password= new BCryptPasswordEncoder();
    	for(int i=0; i<8;i++) {
    		System.out.println(password.encode("12345"));
    	}
    	
    	
    	int total=65;
    	int hojas= total/30;
    	int residuo=total%30;
    	System.out.println("hojas: " + hojas);
    	System.out.println("residuo: " + residuo);

    
}
}