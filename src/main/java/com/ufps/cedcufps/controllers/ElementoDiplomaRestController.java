package com.ufps.cedcufps.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.json.simple.JSONObject;

import com.ufps.cedcufps.dao.IDiplomaDao;
import com.ufps.cedcufps.dao.IEducacionContinuaDao;
import com.ufps.cedcufps.dto.DiplomaDto;
import com.ufps.cedcufps.dto.DiplomaPruebaDto;
import com.ufps.cedcufps.modelos.Diploma;
import com.ufps.cedcufps.modelos.EducacionContinua;
import com.ufps.cedcufps.modelos.ElementoDiploma;
import com.ufps.cedcufps.modelos.FirmaDiploma;
import com.ufps.cedcufps.modelos.ImagenDiploma;
import com.ufps.cedcufps.modelos.Participante;
import com.ufps.cedcufps.modelos.Programa;
import com.ufps.cedcufps.modelos.TextoDiploma;
import com.ufps.cedcufps.services.IDiplomaService;
import com.ufps.cedcufps.services.IEducacionContinuaService;
import com.ufps.cedcufps.services.IElementoDiplomaService;
import com.ufps.cedcufps.services.IParticipanteService;
import com.ufps.cedcufps.services.IPersonaService;
import com.ufps.cedcufps.utils.Archivo;

@RestController
public class ElementoDiplomaRestController {
	
	/*
	@Autowired
	private IDiplomaService diplomaService;
	
	@Autowired
	private IDiplomaDao diplomaDao;
	
	@Autowired
	private IElementoDiplomaService elementoDiplomaService;
	
	@Autowired
	private IEducacionContinuaService educacionContinuaService;
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IEducacionContinuaDao educacionContinuaDao;
	
	
	
	
	
	public String convertToDatabaseColumn(JSONObject jsonData) {
        String json;
        try{
            json = jsonData.toString();
        }
        catch (NullPointerException ex)
        {
        	System.out.println("ENTRA ACÁAAAAAAAAAA");
            //extend error handling here if you want
            json = "";
        }
        return json;
    }
	
	private static void procesando(JsonNode node) {
		System.out.println(node.getNodeType());
		System.out.println(node.isArray());
		System.out.println(node.isContainerNode());
		System.out.println(node.isObject());
		System.out.println(node.isValueNode());
		if(node.isArray()) {
			System.out.println("es array");
			ArrayNode arrayNode = (ArrayNode) node;
			if(arrayNode.size()>0) {
				Iterator<JsonNode> nodos= arrayNode.elements();
				while(nodos.hasNext()) {
					procesando(nodos.next());
				}
			}else {
				System.out.println("no tiene elementos el array");
			}
			
		}else if(node.isObject()) {
			System.out.println("es json container");
			Iterator<JsonNode>hijos=node.elements();
			while(hijos.hasNext()) {
				procesando(hijos.next());
			}
			
		}else if(node.isValueNode()) {
			System.out.println("is value node");
	    	System.out.println(node.asText());
		}
	}
	private static void processNode(JsonNode node) {
		
	    if(node.isArray()) {
	        // if the node is a list of items,
	        //  go through all the items and process them individually
	        System.out.println("=== Array start ===");
	        for (final JsonNode objInArray : node) {
	            System.out.println("--- Array element start ---");
	            // process the item in the array
	            processNode(objInArray);
	            System.out.println("--- Array element end ---");
	        }
	        System.out.println("=== Array end ===");
	    } else if(node.isContainerNode()) {
	        // if the node is an object,
	        //  go through all fields within the object
	        System.out.println("/// Object start ///");
	        Iterator<Map.Entry<String, JsonNode>> it = node.fields();
	        while (it.hasNext()) {
	            Map.Entry<String, JsonNode> field = it.next();
	            System.out.println("key: " + field.getKey());
	            //process every field in the array
	            processNode(field.getValue());
	        }
	        System.out.println("/// Object end ///");
	    } else {
	        // if node is a simple value (like string or int) so let's print it
	        System.out.println("value: " + node);
	    }
	}
	
	private static void process(String prefix, JsonNode currentNode) {
		
		if (currentNode.isArray()) {
			System.out.println("despues de preguntar si es array");
			System.out.println("antes de castear arraynode");
	        ArrayNode arrayNode = (ArrayNode) currentNode;
	        System.out.println("iterando");
	        Iterator<JsonNode> node = arrayNode.elements();
	        int index = 1;
	        System.out.println("while");
	        while (node.hasNext()) {
	        	System.out.println("dentro de while");
	            process(!prefix.isEmpty() ? prefix + "-" + index : String.valueOf(index), node.next());
	            index += 1;
	        }
	    }
		else if (currentNode.isObject()) {
			System.out.println("despues de preguntar si es object");
	        currentNode.fields().forEachRemaining(entry -> process(!prefix.isEmpty() ? prefix + "-" + entry.getKey() : entry.getKey(), entry.getValue()));
	    }
	    else {
	    	System.out.println("else");
	        System.out.println(prefix + ": " + currentNode.toString());
	    }
	}*/
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