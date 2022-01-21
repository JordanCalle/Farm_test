package com.Test_Jordan.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Chickens")

public class Chickens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	// Variables Encapsuladas
	private Integer id;
	private Float price;
	private String status;
	private Integer startdate;

	public Chickens() {

	}

	public Chickens(Integer id, Float price, String status, Integer startdate) {
		super();
		this.id = id;
		this.price = price;
		this.status = status;
		this.startdate = startdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStartdate() {
		return startdate;
	}

	public void setStartdate(Integer startdate) {
		this.startdate = startdate;
	}

	
	
	
}
