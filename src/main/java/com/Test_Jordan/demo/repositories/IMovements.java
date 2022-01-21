package com.Test_Jordan.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Test_Jordan.demo.model.Movements;

@Repository
public interface IMovements extends CrudRepository<Movements, Integer>{ //Me provee los métodos genericos CRUD en el repositorio

}
