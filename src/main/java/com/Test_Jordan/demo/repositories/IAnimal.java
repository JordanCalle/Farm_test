package com.Test_Jordan.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Test_Jordan.demo.model.Animals;

@Repository
public interface IAnimal extends CrudRepository<Animals, Integer>{ //Me provee los métodos genericos CRUD en el repositorio
	public List<Animals> findByStatusOrderByStatus(String status);
}