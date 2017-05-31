package com.trip.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="EXPENSE")
public class Expense implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="ID",unique=true, nullable=false)
	private Integer id;
	
	@Column(name="DESCRIPTION", nullable=false, length=1000)
	private String description;
	
	@Column(name = "STATUS", length = 1)
	private String status; 
	
	@Column(name = "TYPE", length = 1)
	private String type; 

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATETIME", nullable=false)
	private Date createdDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private Users userId;

	@Column(name = "AMOUNT")
	private Double amount; 

	public Expense() {
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

}