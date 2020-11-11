package com.ufps.cedcufps.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.ElementoDiploma;
import com.ufps.cedcufps.modelos.FirmaDiploma;
import com.ufps.cedcufps.modelos.ImagenDiploma;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.TextoDiploma;
import com.ufps.cedcufps.services.IDiplomaService;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IElementoDiplomaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.utils.Archivo;

@RestController
public class ElementoDiplomaRestController {

	@Autowired
	private IDiplomaService diplomaService;
	
	@Autowired
	private IElementoDiplomaService elementoDiplomaService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	
}

//escribir texto en una imagen
/*
try {
BufferedImage image= ImageIO.read(new URL("http://localhost:8080/uploads/educacion-continua/5/diploma_base.jpg"));

Graphics2D g2d = (Graphics2D)image.getGraphics();
g2d.setFont(new Font("Serif",Font.PLAIN,100));
g2d.setColor(Color.BLACK);
g2d.drawString("HelloWord",435,400);
System.out.println("info imagen");
System.out.println(image.getData());

ImageIO.write(image, "png", new File("test.png"));
} catch (MalformedURLException ex) {
//TODO Auto-generated catch block
ex.printStackTrace();
} catch (IOException ex) {
//TODO Auto-generated catch block
ex.printStackTrace();
}
*/