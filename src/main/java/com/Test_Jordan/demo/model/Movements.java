package com.Test_Jordan.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Movements")
public class Movements {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	// Variables Encapsuladas
	private Long id;
	private String name;
	private Float price;
	private LocalDate transactiondate = LocalDate.now();
	private Float newbalance;

	public Movements() {

	}

	public Movements(Long id, String name, Float price, LocalDate transactiondate, Float newbalance) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.transactiondate = transactiondate;
		this.newbalance = newbalance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public LocalDate getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(LocalDate transactiondate) {
		this.transactiondate = transactiondate;
	}

	public Float getNewbalance() {
		return newbalance;
	}

	public void setNewbalance(Float newbalance) {
		this.newbalance = newbalance;
	}
	
	
	
}
