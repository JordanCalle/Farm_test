package com.Test_Jordan.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Test_Jordan.demo.model.Cattle;
import com.Test_Jordan.demo.repositories.ICattle;
import com.Test_Jordan.demo.service.ICattleService;

@Service
public class CattleService implements ICattleService{

	@Autowired
	private ICattle data;
	@Override
	public List<Cattle> listcattle() {
		return (List<Cattle>) data.findAll();
	}

}
