package com.Test_Jordan.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Test_Jordan.demo.model.Movements;
import com.Test_Jordan.demo.repositories.IMovements;
import com.Test_Jordan.demo.service.IMovementsService;

@Service
public class MovementsService implements IMovementsService {

	@Autowired
	private IMovements data;

	@Override
	public List<Movements> listmovements() {
		return (List<Movements>) data.findAll();
	}

	@Override
	public Integer savetransaction(Movements a) {
		
		Movements movements = data.save(a);
		

		return 0;
	}
	

}
