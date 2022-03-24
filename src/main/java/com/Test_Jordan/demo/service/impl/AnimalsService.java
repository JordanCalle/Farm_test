package com.Test_Jordan.demo.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Test_Jordan.demo.model.Animals;
import com.Test_Jordan.demo.repositories.IAnimal;
import com.Test_Jordan.demo.service.IAnimalService;

@Service //Bean
public class AnimalsService implements IAnimalService { // Implementa y trae los métodos de mi interfaz IAnimalService

	@Autowired // Enlazar con dependencias y métodos correspondientes, e inicializar
	private IAnimal data;

	@Override
	public List<Animals> listar() {
		return (List<Animals>) data.findAll(); // Nos retorna una lista
	}
	
	@Override
	public Integer save(Animals a) {
		Animals animals=data.save(a);
		return 0;
	}
	
	@Override
	public Integer savepurchase(Animals a) {
		
		/*try {
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/farm?serverTimezone=GMT-3","root","H0l4c0m0.");
			CallableStatement stnc=con.prepareCall("{call COUNT_BY_STATUS}");
			ResultSet rs=stnc.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			
			if(count<5) {
				Animals animals=data.save(a);
				
			} else {
				System.out.println(count);
				
			}
		}catch(Exception e) {
			
		}*/
		
		Animals animals=data.save(a);
		
		return 0;
	}
	
	@Override
	public Integer savesales(Animals a) {
		Animals animals=data.save(a);
		return 0;
	}
	
	/*@Override
	public Integer changestatus(Animals a) {
		a.setStatus("Sold");
		Animals animals=data.save(a);
		return 0;
	}*/

	@Override
	public Optional<Animals> listarId(Integer id) {	
		return data.findById(id);
	}
}
