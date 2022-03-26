package com.Test_Jordan.demo.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
		return "index"; // Apunta a mi archivo HTML
	}

	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("animals", new Animals());
		return "form";
	}

	@PostMapping("/savepurchase")
	public String savepurchase(Movements b, @Valid Animals a, Model model) {

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farm?serverTimezone=GMT-3",
					"root", "H0l4c0m0.");
			//Llamo al stored procedure para contar la cantidad de huevos en estado In farm hay actualmente.
			CallableStatement stnc = con.prepareCall("{call COUNT_BY_STATUS}");
			ResultSet rs = stnc.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			//Llamo al stored procedure para que me devuelva el ultimo balance ingresado en la tabla movements.
			CallableStatement stnc2 = con.prepareCall("{call LAST_BALANCE}");
			ResultSet rs2 = stnc2.executeQuery();
			rs2.next();
			float tempbalance = rs2.getFloat(1);

			if (count < 17) {
				service.savepurchase(a);
				Movements movements = new Movements(null, "Egg", a.getId(), a.getPrice(), a.getTransactiondate(),
						a.getPurchasetype(),tempbalance-a.getPrice(), a, null);
				servicemovements.savetransaction(movements);
			} else {
				return "redirect:/error";
			}
		} catch (Exception e) {

		}

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

	// Sales
	@GetMapping("/vender/{id}")
	public String vender(@Valid Animals a, @PathVariable Integer id, Model model) {
		Optional<Animals> animals = service.listarId(id);
		model.addAttribute("animals", animals);
		return "selleggsform";
	}

	@PostMapping("/savesales")
	public String savesales(@Valid Animals a, Model model) {
		
		try {
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farm?serverTimezone=GMT-3",
					"root", "H0l4c0m0.");
			CallableStatement stnc = con.prepareCall("{call COUNT_CATTLE}");
			ResultSet rs = stnc.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			//Llamo al stored procedure para que me devuelva el ultimo balance ingresado en la tabla movements.
			CallableStatement stnc2 = con.prepareCall("{call LAST_BALANCE}");
			ResultSet rs2 = stnc2.executeQuery();
			rs2.next();
			float tempbalance = rs2.getFloat(1);
			
			if(count>3){
				Movements movements = new Movements(null, "Egg", a.getId(), a.getPrice(), a.getTransactiondate(),
						a.getSalestype(), tempbalance+a.getPrice(), a, null);
				servicemovements.savetransaction(movements);
			}else {
				return "redirect:/error";
			}
			
			
		}catch(Exception e) {
			
		}
		
		return "redirect:/listeggs";
	}
	// End Sales

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

	@PostMapping("/savechickpurch")
	public String savechickpurch(@Valid Chickens a, Model model) {

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farm?serverTimezone=GMT-3",
					"root", "H0l4c0m0.");
			CallableStatement stnc = con.prepareCall("{call COUNT_BY_STATUSCHICK}");
			ResultSet rs = stnc.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			//Llamo al stored procedure para que me devuelva el ultimo balance ingresado en la tabla movements.
			CallableStatement stnc2 = con.prepareCall("{call LAST_BALANCE}");
			ResultSet rs2 = stnc2.executeQuery();
			rs2.next();
			float tempbalance = rs2.getFloat(1);

			if (count < 6) {
				servicechickens.savechickpurch(a);
				Movements movements = new Movements(null, "Chicken", a.getId(), a.getPrice(), a.getTransactiondate(),
						a.getPurchasetype(), tempbalance-a.getPrice(), null, a);
				servicemovements.savetransaction(movements);
			} else {
				return "redirect:/error";
			}

		} catch (Exception e) {

		}

		return "redirect:/listchickens";
	}

	@GetMapping("/editarchicken/{id}")
	public String editarchicken(@PathVariable Integer id, Model model) {
		Optional<Chickens> chickens = servicechickens.listarIdchickens(id);
		model.addAttribute("chickens", chickens);
		return "editchickenform";
	}

	// Sales Chicken
	@GetMapping("/venderchick/{id}")
	public String venderchick(@Valid Chickens a, @PathVariable Integer id, Model model) {
		Optional<Chickens> chickens = servicechickens.listarIdchickens(id);
		model.addAttribute("chickens", chickens);
		return "sellchickens";
	}

	@PostMapping("/savechicksales")
	public String savechicksales(@Valid Chickens a, Model model) {
		
		try {
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farm?serverTimezone=GMT-3",
					"root", "H0l4c0m0.");
			CallableStatement stnc = con.prepareCall("{call COUNT_CATTLE}");
			ResultSet rs = stnc.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			//Llamo al stored procedure para que me devuelva el ultimo balance ingresado en la tabla movements.
			CallableStatement stnc2 = con.prepareCall("{call LAST_BALANCE}");
			ResultSet rs2 = stnc2.executeQuery();
			rs2.next();
			float tempbalance = rs2.getFloat(1);
			
			if(count>3) {
				Movements movements = new Movements(null, "Chicken", a.getId(), a.getPrice(), a.getTransactiondate(),
						a.getSalestype(), tempbalance+a.getPrice(), null, a);
				servicemovements.savetransaction(movements);
			}else {
				return "redirect:/error";
			}
			
		}catch(Exception e) {
			
		}
		return "redirect:/listchickens";
	}
	// End Sales Chicken

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

}
