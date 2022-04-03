package com.Test_Jordan.demo.service.impl;

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
		return (List<Animals>) data.findAll(); //Lista completa de la tabla
	}
	
	@Override
	public List<Animals> listegginfarm() {
		return (List<Animals>) data.findByStatusOrderByStatus("In farm"); //Lista filtrada por status
	}
	
	
	@Override
	public Integer save(Animals a) {
		Animals animals=data.save(a);
		return 0;
	}
	
	@Override
	public Integer savepurchase(Animals a) {
		
		a.setStatus("In farm");
		Animals animals=data.save(a);
		
		return 0;
	}
	
	@Override
	public Integer savesales(Animals a) {
		a.setStatus("Sold");
		Animals animals=data.save(a);
		return 0;
	}
	

	@Override
	public Optional<Animals> listarId(Integer id) {	
		return data.findById(id);
	}
}
