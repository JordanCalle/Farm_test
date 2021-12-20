package com.Test_Jordan.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Animals")
public class Animals {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Para establecer que el id,name,price sean los que estén en la
														// tabla Animals
	// Variables Encapsuladas
	private Long id;
	private String name;
	private Float price;

	public Animals() {

	}

	// Constructor
	public Animals(Long id, String name, Float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	// Getters and Setters
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

}
