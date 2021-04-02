package com.visio.rules.engine.persistance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PRODUCT")

public class Product implements Serializable{

	private static final long serialVersionUID = -669205202506370369L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="NAME", nullable=false)
	private String name; 
	
	@Column(name="INTEREST_RATE", nullable=false)
	private Double interestRate; 
	
	@Column(name="DISQUALIFIED")
	private Boolean disqualified;
	
	@Column(name="DT_CREATED")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dateCreated; 
	
	@Column(name="DT_UPDATED")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dateUpdated; 
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Boolean getDisqualified() {
		return disqualified;
	}
	
	public void setDisqualified(Boolean disqualified) {
		this.disqualified = disqualified;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	} 

}
