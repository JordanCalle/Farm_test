package com.Test_Jordan.demo.service;

import java.sql.SQLException;

import com.Test_Jordan.demo.model.Connection;

public interface IConnectionService {
	public void establishConnection(Connection connection) throws SQLException;
}
