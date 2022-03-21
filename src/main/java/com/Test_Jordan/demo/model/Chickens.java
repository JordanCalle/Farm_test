package com.Test_Jordan.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Chickens")
public class Chickens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Float price;
	private String status;
	private LocalDate startdate = LocalDate.now();
	private LocalDate transactiondate = LocalDate.now();
	private String purchasetype = "Purchase";
	private String salestype = "Sale";
	
	@OneToMany(mappedBy = "chickens", cascade = CascadeType.ALL)
	private Set<Movements> chickens;

	public Chickens() {

	}

	public Chickens(Integer id, Float price, String status, LocalDate startdate, LocalDate transactiondate,
			String purchasetype, String salestype, Set<Movements> chickens) {
		super();
		this.id = id;
		this.price = price;
		this.status = status;
		this.startdate = startdate;
		this.transactiondate = transactiondate;
		this.purchasetype = purchasetype;
		this.salestype = salestype;
		this.chickens = chickens;
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

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(LocalDate transactiondate) {
		this.transactiondate = transactiondate;
	}

	public String getPurchasetype() {
		return purchasetype;
	}

	public void setPurchasetype(String purchasetype) {
		this.purchasetype = purchasetype;
	}

	public String getSalestype() {
		return salestype;
	}

	public void setSalestype(String salestype) {
		this.salestype = salestype;
	}

	public Set<Movements> getChickens() {
		return chickens;
	}

	public void setChickens(Set<Movements> chickens) {
		this.chickens = chickens;
	}


	
}
