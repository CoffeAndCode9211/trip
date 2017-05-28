package com.trip.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trip.dto.PlanTO;
import com.trip.dto.ResponseStatusTO;
import com.trip.model.Plan;
import com.trip.service.PlanEJBIf;

@Stateless
public class PlanImpl implements PlanIf{

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(PlanImpl.class);

	@EJB
	private PlanEJBIf empEJbIf;
	
	@Inject
	private Validator validator;

	public List<PlanTO> getPlanDetails( ) {
		List<PlanTO> lstEmpTo = null;
		try{
			Plan emp = new Plan();
			List<Plan>  lstPlan = empEJbIf.getPlanByFilter(emp);
			if(lstPlan != null && !lstPlan.isEmpty()){
				lstEmpTo = new ArrayList<PlanTO>();
				Iterator<Plan > itr = lstPlan.iterator();
				while(itr.hasNext()){
					logger.info("next Data");
					Plan e = itr.next();
					lstEmpTo.add(Common.transformToPlanTO(e));
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return lstEmpTo;
	}

	public PlanTO getPlanById(int id) {
		PlanTO employeeTO = null;
		try{
			Plan emp = empEJbIf.getPlanById(id);
			if(emp != null ){
				employeeTO = Common.transformToPlanTO(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeTO;
	}

	public Response addPlan(PlanTO empTo) {
		try{
			logger.info(empTo.toString());
			validatePlan(empTo);
			Plan emp = Common.transformToPlan(empTo);
			ResponseStatusTO response = empEJbIf.addPlan(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response.getStatus()){
				responseObj.put("Success", "Plan saved successfully");
				if(response.getPersistObjectId() != null){
					responseObj.put("userId", response.getPersistObjectId().toString());
				}
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while saving Plan");
				return Response.noContent().entity(responseObj).build();
			}

		}catch (ConstraintViolationException ce) {
			logger.error("There is an ConstraintViolationException");
			builder = Common.createViolationResponse(ce.getConstraintViolations());
			return builder.build();
		} catch (ValidationException e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("code", "Code Exist");
			logger.error("There is an ValidationException");
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			return builder.build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.noContent().entity(e.getMessage()).build();
		}
	}

	public Response updatePlan(PlanTO empTo, int id) {
		try{
			Plan emp = Common.transformToPlan(empTo);
			emp.setId(id);
			ResponseStatusTO response = empEJbIf.updatePlan(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response.getStatus()){
				responseObj.put("Success", "Plan updated successfully");
				responseObj.put("userId", id+"");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while updating Plan");
				return Response.noContent().entity(responseObj).build();
			}
		}catch(Exception e){
			e.printStackTrace();
			return Response.noContent().entity(e.getMessage()).build();
		}
	}

	public Response deletePlan(int id) {
		try{

			Plan emp = empEJbIf.getPlanById(id);
			Boolean response = empEJbIf.deletePlan(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "Plan deleted successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while deleting Plan");
				return Response.noContent().entity(responseObj).build();
			}

		}catch(Exception e){
			e.printStackTrace();
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("Error", "Error while deleting Plan");
			return Response.noContent().entity(responseObj).build();
		}
	}


	
	
	public void validatePlan(PlanTO empTO) throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<PlanTO>> violations = validator.validate(empTO);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

}
