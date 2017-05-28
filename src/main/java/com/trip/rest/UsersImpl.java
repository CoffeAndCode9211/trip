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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trip.dto.ResponseStatusTO;
import com.trip.dto.UsersTO;
import com.trip.model.Users;
import com.trip.service.UsersEJBIf;

@Stateless
public class UsersImpl implements UsersIf{

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(UsersImpl.class);

	@EJB
	private UsersEJBIf empEJbIf;

	
	@Inject
	private Validator validator;

	public List<UsersTO> getUsersDetails( String lastName, 
			String firstName, String email, String phone) {
		List<UsersTO> lstEmpTo = null;
		try{
			Users emp = new Users();
			emp.setEmail(email);
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setPhone(phone);
			List<Users>  lstUsers = empEJbIf.getUsersByFilter(emp);
			if(lstUsers != null && !lstUsers.isEmpty()){
				lstEmpTo = new ArrayList<UsersTO>();
				Iterator<Users > itr = lstUsers.iterator();
				while(itr.hasNext()){
					logger.info("next Data");
					Users e = itr.next();
					lstEmpTo.add(Common.transformToUsersTO(e));
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return lstEmpTo;
	}

	public UsersTO getUsersById(int id) {
		UsersTO employeeTO = null;
		try{
			logger.info("id is :"+id);
			if(empEJbIf == null){
				logger.info("hola");
			}
			Users emp = empEJbIf.getUsersById(id);
			if(emp != null ){
				employeeTO = Common.transformToUsersTO(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeTO;
	}

	public Response addUsers(UsersTO empTo) {
		try{
			logger.info(empTo.toString());
			validateUsers(empTo);
			Users emp = Common.transformToUsers(empTo);
			ResponseStatusTO response = empEJbIf.addUsers(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response.getStatus()){
				responseObj.put("Success", "Users saved successfully");
				if(response.getPersistObjectId() != null){
					responseObj.put("userId", response.getPersistObjectId().toString());
				}
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while saving Users");
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

	public Response updateUsers(UsersTO empTo, int id) {
		try{
			Users uu = empEJbIf.getUsersById(id);
			Users emp = Common.transformToUsers(empTo);
			emp.setId(id);
			emp.setDocumentId(uu.getDocumentId());
			ResponseStatusTO response = empEJbIf.updateUsers(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response.getStatus()){
				responseObj.put("Success", "Users updated successfully");
				responseObj.put("userId", id+"");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while updating Users");
				return Response.noContent().entity(responseObj).build();
			}
		}catch(Exception e){
			e.printStackTrace();
			return Response.noContent().entity(e.getMessage()).build();
		}
	}

	public Response deleteUsers(int id) {
		try{

			Users emp = empEJbIf.getUsersById(id);
			Boolean response = empEJbIf.deleteUsers(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "Users deleted successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while deleting Users");
				return Response.noContent().entity(responseObj).build();
			}

		}catch(Exception e){
			e.printStackTrace();
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("Error", "Error while deleting Users");
			return Response.noContent().entity(responseObj).build();
		}
	}

	
	public void logout(HttpServletRequest req){
		try{
			HttpSession session = req.getSession();
			session.invalidate();
			session = req.getSession(false);
		}catch(Exception e){
			logger.error("There is an exception");
			e.printStackTrace();
		}
	}

	
	
	public void validateUsers(UsersTO empTO) throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<UsersTO>> violations = validator.validate(empTO);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

}
