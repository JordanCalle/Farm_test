package com.Test_Jordan.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Test_Jordan.demo.model.Animals;
import com.Test_Jordan.demo.service.IAnimalService;

@Controller
@RequestMapping
public class Controllers {

	@Autowired
	private IAnimalService service; //Para implementar el método
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Animals>animals=service.listar();
		model.addAttribute("animals", animals); //Envio todo el objeto al formulario
		return "index"; //Apunta a mi archivo HTML. Ver en "templates".
	}
}
