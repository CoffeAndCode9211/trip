package com.trip.dto;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

public class ExpenseTO {

	private String createdDatetime;
	
	@Size(min = 1, message = "Please enter Description")
	@FormParam("description")
	private String description;
	
	private String status;
	private String userId;
	private String userName;
	private Integer expenseId;
	
	@Size(min = 1, message = "Please enter amount")
	@FormParam("amount")
	private String amount;
	private String expenseType;
	
	public String getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	
	
}
