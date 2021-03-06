package com.Test_Jordan.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Test_Jordan.demo.model.Chickens;
import com.Test_Jordan.demo.repositories.IChickens;
import com.Test_Jordan.demo.service.IChickenService;

@Service
public class ChickenService implements IChickenService{

	@Autowired
	private IChickens data;
	
	@Override
	public List<Chickens> listchickens() {
		return (List<Chickens>) data.findAll();
	}
	
	@Override
	public List<Chickens> listchickinfarm() {
		return (List<Chickens>) data.findByStatusOrderByStatus("In farm");
	}

	@Override
	public Integer savechickens(Chickens a) {
		Chickens chickens=data.save(a);
		return 0;
	}

	@Override
	public Optional<Chickens> listarIdchickens(Integer id) {
		return data.findById(id);
	}

	@Override
	public Integer savechickpurch(Chickens a) {
		a.setStatus("In farm");
		Chickens chickens=data.save(a);
		return 0;
	}

	@Override
	public Integer savechicksales(Chickens a) {
		a.setStatus("Sold");
		Chickens chickens=data.save(a);
		return 0;
	}

}
