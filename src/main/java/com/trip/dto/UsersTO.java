package com.trip.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;


public class UsersTO {

	private Integer id;

	
	@NotNull
	@Size(min = 1, message = "Please select First Name")
	@FormParam("txtUsrFirstName")
	private String firstName;

	@FormParam("txtUsrLastName")
	private String lastName;

	@FormParam("txtUsrEmail")
	private String email;

	@FormParam("txtUsrPhone")
	private String phone;
	
	private String userName;
	private String userPassword;
	
	private String totalAmt;
	
	private String paidAmt;
	
	private String balanceAmt;

	private String docId;
	
	
	
	public UsersTO(){
		
	}
	
	public UsersTO(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
	}

	public String getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(String balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return lastName+" : "+firstName+" : "+email+" : "+phone;
	}
}
