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
	
	@PostMapping(value = "/educacion-continua/generar-plantilla-diploma" ,produces = "application/json")
	@ResponseBody // ImagenDiploma[] imagenes MultipartFile file @RequestBody  EducacionContinua edu
	public ResponseEntity<?> guardarPlantillaDiploma(@RequestBody EducacionContinua eduContinua ){
		//Diploma d=educacionContinuaService.generarDiploma(educacionContinua.getId());
		
		EducacionContinua e= educacionContinuaService.findOne(eduContinua.getId()).get();
		if(e.getDiploma()==null) {
			e.setDiploma(new Diploma());
		}
		e.getDiploma().setImagenPlantilla(Archivo.saveImagenBase64("/uploads/educacion-continua/"+e.getId()+"/diploma_base.jpg", eduContinua.getDiploma().getImagenPlantilla()));
		
		if(eduContinua.getDiploma().getTextos()!=null) {
			for(TextoDiploma t:eduContinua.getDiploma().getTextos()) {
				t.setDiploma(e.getDiploma());
			}
			e.getDiploma().setTextos(eduContinua.getDiploma().getTextos());
		}
		
		if(eduContinua.getDiploma().getImagenes()!=null) {
			for(ImagenDiploma i:eduContinua.getDiploma().getImagenes()) {
				i.setDiploma(e.getDiploma());
				i.setRuta(Archivo.saveImagenBase64("/uploads/educacion-continua/"+e.getId()+"/plantilla-diploma/"+Archivo.generarNombreAleatorio()+".png", i.getRuta()));
			}
			e.getDiploma().setImagenes(eduContinua.getDiploma().getImagenes());
		}
		
		
		if(eduContinua.getDiploma().getFirmas()!=null) {
			for(FirmaDiploma f:eduContinua.getDiploma().getFirmas()) {
				f.setDiploma(e.getDiploma());
				f.setImagenFirmaDigital(Archivo.saveImagenBase64("/uploads/educacion-continua/"+e.getId()+"/plantilla-diploma/"+Archivo.generarNombreAleatorio()+".png", f.getImagenFirmaDigital()));
			}
			e.getDiploma().setFirmas(eduContinua.getDiploma().getFirmas());
		}
		
		
		educacionContinuaService.save(e);
		
		return new ResponseEntity<>(e,HttpStatus.OK);
		//return "sisas " + String.valueOf(e.getId());
		/*
		List<ElementoDiploma> elementos=new ArrayList<ElementoDiploma>();
		texto1.setId(null);
		texto2.setId(null);
		texto1.setDiploma(d);
		texto2.setDiploma(d);
		elementos.add(texto1);
		elementos.add(texto2);
		elementoDiplomaService.saveElementos(d.getId(), elementos);
		/*System.out.println("imagen: " + file.getName());
		Participante p= participanteService.findParticipante(Long.parseLong(idParticipante));
		System.out.println("participante: " + idParticipante);
		System.out.println("participante educacion continua: " + p.getEducacionContinua().getId());
		p.setTarjetaInscripcion(Archivo.saveImage(file,"/uploads/educacion-continua/"+p.getEducacionContinua().getId()+"/tarjetas-inscripcion/inscripcion_"+p.getPersona().getNumeroDocumento()));
		participanteService.save(p);
		System.out.println("tarjeta participante: " + p.getTarjetaInscripcion());
		return new ResponseEntity<>(HttpStatus.OK);*/
		/*if(d!=null) {
			return new ResponseEntity<>(d,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(d,HttpStatus.BAD_REQUEST);
		}*/
		
		
	}
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