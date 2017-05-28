package com.trip.service;


import java.util.List;

import com.trip.dto.ResponseStatusTO;
import com.trip.model.Plan;

public interface PlanEJBIf {

	public ResponseStatusTO addPlan(Plan plan) throws Exception;
	
	public ResponseStatusTO updatePlan(Plan plan) throws Exception;
	
	public Plan getPlanById(Integer id) throws Exception;
	
	public List<Plan> getPlanByFilter(Plan plan) throws Exception;
	
	public boolean deletePlan(Plan plan) throws Exception;
	
	
	
}
