package com.Test_Jordan.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cattle")

public class Cattle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	// Variables Encapsuladas
	private Long id;
	private String status;
	private LocalDate startdate = LocalDate.now();

	public Cattle() {

	}

	public Cattle(Long id, String status, LocalDate startdate) {
		super();
		this.id = id;
		this.status = status;
		this.startdate = startdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	
	
	
	
}
