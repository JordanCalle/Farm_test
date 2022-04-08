package com.Test_Jordan.demo.service.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.Test_Jordan.demo.service.IConnectionService;

@Service
public class ConnectionService implements IConnectionService{

	
	@Override
	public void establishConnection(com.Test_Jordan.demo.model.Connection connection) throws SQLException {
		Connection con = DriverManager.getConnection(connection.getUrl(),
				connection.getUsername(), connection.getPassword());
		con.close();
	}
	
}

