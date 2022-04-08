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
import com.Test_Jordan.demo.service.IAnimalService;
import com.Test_Jordan.demo.service.ICattleService;
import com.Test_Jordan.demo.service.IChickenService;
import com.Test_Jordan.demo.service.IMovementsService;
import com.Test_Jordan.demo.service.impl.ConnectionService;
import com.Test_Jordan.demo.service.IConnectionService;

@Controller
@RequestMapping
public class Controllers {
//EGGS
	@Autowired
	private IAnimalService service;// Para implementar el método
	private IConnectionService serviceconnection;

	@GetMapping("/listeggs")//Lista completa de todos los huevos en la tabla
	public String listar(Model model) {
		List<Animals> animals = service.listar();
		model.addAttribute("animals", animals); // Envio todo el objeto al formulario
		return "index"; // Apunta a mi archivo HTML
	}
	
	@GetMapping("/listegginfarm")//Lista donde se filtra por status
	public String listEggInFarm(Model model) {
		List<Animals> animals = service.listegginfarm();
		model.addAttribute("animals", animals);
		return "index";
	}
	
	@GetMapping("/skipdays")//Proceso del paso de los días, se hace a través de un botón para actualizar la tabla
	public String skipDays(Model model) {
		
		try {
			//Conecto con la DB para hacer llamado al Stored procedure
			/*Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farm?serverTimezone=GMT-3",
					"root", "H0l4c0m0.");*/
			serviceconnection.establishConnection(null);
			CallableStatement stnc = con.prepareCall("{call SKIP_DAYS}");
			ResultSet rs = stnc.executeQuery();
			List<Animals> animals = service.listar();
			model.addAttribute("animals", animals);
			return "index";
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}

	@GetMapping("/new")//Compra de huevos
	public String newEgg(Model model) {
		model.addAttribute("animals", new Animals());
		return "form";
	}

	@PostMapping("/savepurchase")//Guarda la compra de huevos
	public String savePurchase(Movements b, @Valid Animals a, Model model) {

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

			//Condicional que establece el LIMITE de huevos con status "In farm".
			if (count < 15) {
				if(tempbalance>a.getPrice()) {
				service.savepurchase(a);
				Movements movements = new Movements(null, "Egg", a.getId(), a.getPrice(), a.getTransactiondate(),
						a.getPurchasetype(),tempbalance-a.getPrice(), a, null);
				servicemovements.savetransaction(movements);
				}else {
					return "redirect:/error";
				}
			} else {
				return "redirect:/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/listeggs";
	}

	@PostMapping("/save")
	public String saveEgg(@Valid Animals a, Model model) {
		service.save(a);
		return "redirect:/listeggs";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		Optional<Animals> animals = service.listarId(id);
		model.addAttribute("animals", animals);
		return "editeggsform";
	}

	// Venta de huevos
	@GetMapping("/vender/{id}")
	public String vender(@Valid Animals a, @PathVariable Integer id, Model model) {
		Optional<Animals> animals = service.listarId(id);
		model.addAttribute("animals", animals);
		return "selleggsform";
	}

	@PostMapping("/savesales")
	public String saveSales(@Valid Animals a, Model model) {
		
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
			
			//Condicional que establece el minimo de ganado que debe poseer la granja para poder vender.
			if(count>3){
				service.savesales(a);
				Movements movements = new Movements(null, "Egg", a.getId(), a.getPrice(), a.getTransactiondate(),
						a.getSalestype(), tempbalance+a.getPrice(), a, null);
				servicemovements.savetransaction(movements);
			}else {
				return "redirect:/error";
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/listeggs";
	}
	// Fin de venta de huevos

//CHICKENS
	@Autowired
	private IChickenService servicechickens;

	@GetMapping("/listchickens")
	public String listChickens(Model model) {
		List<Chickens> chickens = servicechickens.listchickens();
		model.addAttribute("chickens", chickens);
		return "Chickens";
	}
	
	@GetMapping("/listchickinfarm")
	public String listChickInFarm(Model model) {
		List<Chickens> chickens = servicechickens.listchickinfarm();
		model.addAttribute("chickens", chickens);
		return "Chickens";
	}

	@GetMapping("/newchicken")
	public String newChicken(Model model) {
		model.addAttribute("chickens", new Chickens());
		return "formchicken";
	}

	@PostMapping("/savechicken")
	public String saveChicken(@Valid Chickens a, Model model) {
		servicechickens.savechickens(a);
		return "redirect:/listchickens";
	}

	@PostMapping("/savechickpurch")
	public String saveChickPurch(@Valid Chickens a, Model model) {

		try {

			//Stored procedure para contar la cantidad de pollos con status "In farm" hay.
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

			//Condicional que establece el LIMITE de pollos con status "In farm" puede almacenar la granja.
			if (count < 6) {
				if(tempbalance>a.getPrice()) {
				servicechickens.savechickpurch(a);
				Movements movements = new Movements(null, "Chicken", a.getId(), a.getPrice(), a.getTransactiondate(),
						a.getPurchasetype(), tempbalance-a.getPrice(), null, a);
				servicemovements.savetransaction(movements);
				}else {
					return "redirect:/error";
				}
			} else {
				return "redirect:/error";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/listchickens";
	}

	@GetMapping("/editarchicken/{id}")
	public String editChicken(@PathVariable Integer id, Model model) {
		Optional<Chickens> chickens = servicechickens.listarIdchickens(id);
		model.addAttribute("chickens", chickens);
		return "editchickenform";
	}

	// Venta de pollos
	@GetMapping("/venderchick/{id}")
	public String sellChick(@Valid Chickens a, @PathVariable Integer id, Model model) {
		Optional<Chickens> chickens = servicechickens.listarIdchickens(id);
		model.addAttribute("chickens", chickens);
		return "sellchickens";
	}

	@PostMapping("/savechicksales")
	public String saveChickSales(@Valid Chickens a, Model model) {
		
		try {
			
			//Stored procedure que cuenta la cantidad de ganado hay en la granja.
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
			
			//Condicional que establece el minimo de ganado que debe poseer la granja para poder vender.
			if(count>3) {
				servicechickens.savechicksales(a);
				Movements movements = new Movements(null, "Chicken", a.getId(), a.getPrice(), a.getTransactiondate(),
						a.getSalestype(), tempbalance+a.getPrice(), null, a);
				servicemovements.savetransaction(movements);
			}else {
				return "redirect:/error";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listchickens";
	}
	// Fin de venta de pollos
//CATTLE
	@Autowired
	private ICattleService servicecattle;

	@GetMapping("/listcattle")
	public String listCattle(Model model) {
		List<Cattle> cattle = servicecattle.listcattle();
		model.addAttribute("cattle", cattle);
		return "Cattle";
	}
//MOVEMENTS
	@Autowired
	private IMovementsService servicemovements;

	@GetMapping("/listmovements")
	public String listMovements(Model model) {
		List<Movements> movements = servicemovements.listmovements();
		model.addAttribute("movements", movements);
		return "Movements";
	}

}
