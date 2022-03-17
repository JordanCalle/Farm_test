package com.Test_Jordan.demo.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Test_Jordan.demo.model.Animals;
import com.Test_Jordan.demo.model.Cattle;
import com.Test_Jordan.demo.model.Chickens;
import com.Test_Jordan.demo.model.Movements;
import com.Test_Jordan.demo.repositories.IMovements;
import com.Test_Jordan.demo.service.IAnimalService;
import com.Test_Jordan.demo.service.ICattleService;
import com.Test_Jordan.demo.service.IChickenService;
import com.Test_Jordan.demo.service.IMovementsService;

@Controller
@RequestMapping
public class Controllers {

	@Autowired
	private IAnimalService service; // Para implementar el método

	@GetMapping("/listeggs")
	public String listar(Model model) {
		List<Animals> animals = service.listar();
		model.addAttribute("animals", animals); // Envio todo el objeto al formulario
		return "index"; // Apunta a mi archivo HTML. Ver en "templates".
	}

	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("animals", new Animals());
		return "form";
	}

	@PostMapping("/savepurchase")
	public String savepurchase(@Valid Animals a, Model model) {
		service.savepurchase(a);
		Movements movements = new Movements(null, "Egg", a.getId(), a.getPrice(), a.getTransactiondate(), a.getTransactiontype(), null, a);
		servicemovements.savetransaction(movements);
		return "redirect:/listeggs";
	}
	
	@PostMapping("/save")
	public String save(@Valid Animals a, Model model) {
		service.save(a);
		return "redirect:/listeggs";
	}
	

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		Optional<Animals> animals = service.listarId(id);
		model.addAttribute("animals", animals);
		return "editeggsform";
	}

	@Autowired
	private IChickenService servicechickens;

	@GetMapping("/listchickens")
	public String listchickens(Model model) {
		List<Chickens> chickens = servicechickens.listchickens();
		model.addAttribute("chickens", chickens); // Envio todo el objeto al formulario
		return "Chickens"; // Apunta a mi archivo HTML. Ver en "templates".
	}

	@GetMapping("/newchicken")
	public String agregarchicken(Model model) {
		model.addAttribute("chickens", new Chickens());
		return "formchicken";
	}

	@PostMapping("/savechicken")
	public String save(@Valid Chickens a, Model model) {
		servicechickens.savechickens(a);
		return "redirect:/listchickens";
	}

	@GetMapping("/editarchicken/{id}")
	public String editarchicken(@PathVariable Integer id, Model model) {
		Optional<Chickens> chickens = servicechickens.listarIdchickens(id);
		model.addAttribute("chickens", chickens);
		return "editchickenform";
	}

	@Autowired
	private ICattleService servicecattle;

	@GetMapping("/listcattle")
	public String listcattle(Model model) {
		List<Cattle> cattle = servicecattle.listcattle();
		model.addAttribute("cattle", cattle); // Envio todo el objeto al formulario
		return "Cattle"; // Apunta a mi archivo HTML. Ver en "templates".
	}

	@Autowired
	private IMovementsService servicemovements;

	@GetMapping("/listmovements")
	public String listmovements(Model model) {
		List<Movements> movements = servicemovements.listmovements();
		model.addAttribute("movements", movements); // Envio todo el objeto al formulario
		return "Movements"; // Apunta a mi archivo HTML. Ver en "templates".
	}

	@GetMapping("/newtransaction")
	public String agregartransaction(Model model) {
		model.addAttribute("movements", new Movements());
		return "movementsform";
	}

	@PostMapping("/savetransaction")
	public String save(Movements b, Model model) {
		servicemovements.savetransaction(b);
		return "redirect:/listmovements";
	}

}
