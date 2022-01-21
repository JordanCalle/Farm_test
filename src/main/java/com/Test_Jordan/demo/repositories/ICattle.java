package com.Test_Jordan.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Test_Jordan.demo.model.Cattle;

@Repository
public interface ICattle extends CrudRepository<Cattle, Integer>{ //Me provee los métodos genericos CRUD en el repositorio

}