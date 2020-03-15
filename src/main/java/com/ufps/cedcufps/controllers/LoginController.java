package com.ufps.cedcufps.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	@GetMapping(value = "/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal, RedirectAttributes flash) {
		
		if(error != null) {
			model.addAttribute("error", "Nombre o pass incorrectos");
		}
		System.out.println("***************************valida login**********");
		return "login";
	}
	
	@GetMapping(value = "/diplomas")
	public String diplomas(Model model) {
		
		return "diplomas";
	}
	
}
