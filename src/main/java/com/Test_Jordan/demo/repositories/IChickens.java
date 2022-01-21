package com.Test_Jordan.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Test_Jordan.demo.model.Chickens;

@Repository
public interface IChickens extends CrudRepository<Chickens, Integer>{ //Me provee los métodos genericos CRUD en el repositorio

}