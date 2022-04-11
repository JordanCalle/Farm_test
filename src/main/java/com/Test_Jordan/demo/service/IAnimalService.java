package com.Test_Jordan.demo.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.Test_Jordan.demo.model.Animals;

public interface IAnimalService {
	public List<Animals>listar();
	public List<Animals>listegginfarm();
	public Integer save(Animals a);
	public Integer savepurchase(Animals a);
	public Integer savesales(Animals a);
	public Optional<Animals>listarId(Integer id);
	public Connection establishConnection() throws SQLException;
}
