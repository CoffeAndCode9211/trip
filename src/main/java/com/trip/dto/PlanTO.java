package com.trip.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

public class PlanTO {

	@NotNull
	@Size(min = 1, message = "Please select Date")
	@FormParam("txtPlanDate")
	private String planDate;
	
	@Size(min = 1, message = "Please select Description")
	@FormParam("planDescription")
	private String planDescription;
	
	private String status;
	private String userId;
	private String userName;
	private Integer planId;
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public String getPlanDescription() {
		return planDescription;
	}
	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
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
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	
	
	
	
}
