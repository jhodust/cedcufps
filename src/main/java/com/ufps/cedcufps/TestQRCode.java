package com.ufps.cedcufps;


import com.ufps.cedcufps.utils.CodigoQR;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.text.MaskFormatter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestQRCode {

    public static void main(String[] args) {

    	/*BCryptPasswordEncoder password= new BCryptPasswordEncoder();
    	for(int i=0; i<8;i++) {
    		System.out.println(password.encode("12345"));
    	}*/
    	
    	 String strDateFormat = "dd/MM/yyyy hh:mm a"; // El formato de fecha está especificado  
	     SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
    	
    	
    	int total=65;
    	int hojas= total/30;
    	int residuo=total%30;
    	
    	
    	
    
}
}