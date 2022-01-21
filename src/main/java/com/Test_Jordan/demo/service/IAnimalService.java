package com.Test_Jordan.demo.service;

import java.util.List;
import java.util.Optional;

import com.Test_Jordan.demo.model.Animals;

public interface IAnimalService {
	public List<Animals>listar(); //Lo utilizo para poder almacenar y mostrar la secuencia de objetos ingresados de forma ordenada
	public Integer save(Animals a);
	public Optional<Animals>listarId(Integer id);
}
