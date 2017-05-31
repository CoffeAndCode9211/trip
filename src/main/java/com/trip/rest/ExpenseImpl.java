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

import com.trip.common.StringUtils;
import com.trip.dto.ExpenseTO;
import com.trip.dto.ResponseStatusTO;
import com.trip.model.Expense;
import com.trip.service.ExpenseEJBIf;

@Stateless
public class ExpenseImpl implements ExpenseIf{

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(ExpenseImpl.class);

	@EJB
	private ExpenseEJBIf empEJbIf;
	
	@Inject
	private Validator validator;

	public List<ExpenseTO> getExpenseDetails(String type) {
		List<ExpenseTO> lstEmpTo = null;
		try{
			Expense emp = new Expense();
			if(!StringUtils.isNullCombo(type)){
				emp.setType(type);
			}
			
			List<Expense>  lstExpense = empEJbIf.getExpenseByFilter(emp);
			if(lstExpense != null && !lstExpense.isEmpty()){
				lstEmpTo = new ArrayList<ExpenseTO>();
				Iterator<Expense > itr = lstExpense.iterator();
				while(itr.hasNext()){
					logger.info("next Data");
					Expense e = itr.next();
					lstEmpTo.add(Common.transformToExpenseTO(e));
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return lstEmpTo;
	}

	public ExpenseTO getExpenseById(int id) {
		ExpenseTO employeeTO = null;
		try{
			Expense emp = empEJbIf.getExpenseById(id);
			if(emp != null ){
				employeeTO = Common.transformToExpenseTO(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeTO;
	}

	public Response addExpense(ExpenseTO empTo) {
		try{
			logger.info(empTo.toString());
			validateExpense(empTo);
			Expense emp = Common.transformToExpense(empTo);
			ResponseStatusTO response = empEJbIf.addExpense(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response.getStatus()){
				responseObj.put("Success", "Expense saved successfully");
				if(response.getPersistObjectId() != null){
					responseObj.put("userId", response.getPersistObjectId().toString());
				}
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while saving Expense");
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

	public Response updateExpense(ExpenseTO empTo, int id) {
		try{
			Expense emp = Common.transformToExpense(empTo);
			emp.setId(id);
			ResponseStatusTO response = empEJbIf.updateExpense(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response.getStatus()){
				responseObj.put("Success", "Expense updated successfully");
				responseObj.put("userId", id+"");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while updating Expense");
				return Response.noContent().entity(responseObj).build();
			}
		}catch(Exception e){
			e.printStackTrace();
			return Response.noContent().entity(e.getMessage()).build();
		}
	}

	public Response deleteExpense(int id) {
		try{

			Expense emp = empEJbIf.getExpenseById(id);
			Boolean response = empEJbIf.deleteExpense(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "Expense deleted successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while deleting Expense");
				return Response.noContent().entity(responseObj).build();
			}

		}catch(Exception e){
			e.printStackTrace();
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("Error", "Error while deleting Expense");
			return Response.noContent().entity(responseObj).build();
		}
	}


	
	
	public void validateExpense(ExpenseTO empTO) throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<ExpenseTO>> violations = validator.validate(empTO);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

}
