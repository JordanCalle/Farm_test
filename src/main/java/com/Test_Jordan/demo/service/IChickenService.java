package com.Test_Jordan.demo.service;

import java.util.List;
import java.util.Optional;

import com.Test_Jordan.demo.model.Chickens;

public interface IChickenService {
	public List<Chickens>listchickens();
	public List<Chickens>listchickinfarm();
	public Integer savechickens(Chickens a);
	public Integer savechickpurch(Chickens a);
	public Integer savechicksales(Chickens a);
	public Optional<Chickens>listarIdchickens(Integer id);
}