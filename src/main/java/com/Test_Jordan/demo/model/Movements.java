package com.Test_Jordan.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
//@Table(name="Movements")
public class Movements {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	// Variables Encapsuladas
	private Long id;
	private String name;
	private Integer animalid;
	private Float price;
	private LocalDate transactiondate = LocalDate.now();
	private String transactiontype = "Sale";
	private Float newbalance=(float) 500;
	
	@ManyToOne
	@JoinColumn
	private Animals animals;
	
	@ManyToOne
	@JoinColumn
	private Chickens chickens;

	public Movements() {

	}

	public Movements(Long id, String name, Integer animalid, Float price, LocalDate transactiondate,
			String transactiontype, Float newbalance, Animals animals, Chickens chickens) {
		super();
		this.id = id;
		this.name = name;
		this.animalid = animalid;
		this.price = price;
		this.transactiondate = transactiondate;
		this.transactiontype = transactiontype;
		this.newbalance = newbalance;
		this.animals = animals;
		this.chickens = chickens;
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

	public Integer getAnimalid() {
		return animalid;
	}

	public void setAnimalid(Integer animalid) {
		this.animalid = animalid;
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

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public Float getNewbalance() {
		return newbalance;
	}

	public void setNewbalance(Float newbalance) {
		this.newbalance = newbalance;
	}

	public Animals getAnimals() {
		return animals;
	}

	public void setAnimals(Animals animals) {
		this.animals = animals;
	}

	public Chickens getChickens() {
		return chickens;
	}

	public void setChickens(Chickens chickens) {
		this.chickens = chickens;
	}

	
}
