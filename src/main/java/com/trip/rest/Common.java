package com.trip.rest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;

import com.trip.common.StringUtils;
import com.trip.dto.ExpenseTO;
import com.trip.dto.PlanTO;
import com.trip.dto.UsersTO;
import com.trip.model.Expense;
import com.trip.model.Plan;
import com.trip.model.Users;

public class Common {

	public static UsersTO transformToUsersTO(Users usr){
		UsersTO userTO = new UsersTO();
		userTO.setLastName(usr.getLastName());
		userTO.setFirstName(usr.getFirstName());
		userTO.setEmail(usr.getEmail());
		userTO.setPhone(usr.getPhone());
		userTO.setUserName(usr.getUserName());
		userTO.setPaidAmt(usr.getPaidAmt());
		userTO.setBalanceAmt(usr.getBalanceAmt());
		userTO.setTotalAmt(usr.getTotalAmt());
		if(usr.getDocumentId() != null){
			userTO.setDocId(usr.getDocumentId().getId()+"");
		}
		if(usr.getId() != null){
			userTO.setId(usr.getId());
		}
		return userTO;
	}
	

	private static final String DATE_FORMAT="dd-MMM-yyyy";
	
	private static final String DATE_TIME_FORMAT="dd-MMM-yyyy HH:mm";

	public static Date convertStringToDate(String srcDate) throws ParseException{
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.parse(srcDate);
	}

	public static String convertDateToString(Date srcDate){
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.format(srcDate);
	}
	
	public static String convertDateTimeToString(Date srcDate){
		DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return formatter.format(srcDate);
	}

	public static Users transformToUsers(UsersTO usrTo){
		Users user = new Users();
		user.setLastName(usrTo.getLastName());
		user.setFirstName(usrTo.getFirstName());
		user.setEmail(usrTo.getEmail());
		user.setPhone(usrTo.getPhone());
		if(usrTo.getId() != null){
			user.setId(usrTo.getId());
		}else{
			user.setUserName(usrTo.getUserName());
			if(usrTo.getUserPassword() != null){
				user.setPassword(Common.hashPassword(usrTo.getUserPassword()));
			}
		}

		return user;
	}

	
	public static Plan transformToPlan(PlanTO planTO) throws ParseException{
		Plan plan = new Plan();
		if(planTO.getPlanDate() != null){
			plan.setPlanDate(convertStringToDate(planTO.getPlanDate()));
		}
		plan.setDescription(planTO.getPlanDescription());
		if(planTO.getPlanId() != null){
			plan.setId(planTO.getPlanId());
		}
		plan.setStatus("A");
		

		return plan;
	}

	public static PlanTO transformToPlanTO(Plan plan){
		PlanTO planTO = new PlanTO();
		if(plan.getPlanDate() != null){
			planTO.setPlanDate(convertDateToString(plan.getPlanDate()));
		}
		planTO.setPlanDescription(plan.getDescription());
		planTO.setPlanId(plan.getId());
		planTO.setStatus(plan.getStatus());
		if(plan.getUserId() != null){
			Users user = plan.getUserId();
			planTO.setUserId(user.getId().toString());
			planTO.setUserName(user.getUserName());
		}
		
		return planTO;
	}
	
	
	
	public static Expense transformToExpense(ExpenseTO expenseTO) throws ParseException{
		Expense expense = new Expense();
		expense.setDescription(expenseTO.getDescription());
		if(expenseTO.getExpenseId() != null){
			expense.setId(expenseTO.getExpenseId());
		}
		expense.setStatus("A");
		if(!StringUtils.isNullCombo(expenseTO.getAmount())){
			expense.setAmount(Double.valueOf(expenseTO.getAmount()));
		}
		if(!StringUtils.isNullCombo(expenseTO.getExpenseType())){
			expense.setType(expenseTO.getExpenseType());
		}
		return expense;
	}

	public static ExpenseTO transformToExpenseTO(Expense expense){
		ExpenseTO expenseTO = new ExpenseTO();
		if(expense.getCreatedDateTime() != null){
			expenseTO.setCreatedDatetime(convertDateTimeToString(expense.getCreatedDateTime()));
		}
		expenseTO.setDescription(expense.getDescription());
		expenseTO.setExpenseId(expense.getId());
		expenseTO.setStatus(expense.getStatus());
		if(expense.getUserId() != null){
			Users user = expense.getUserId();
			expenseTO.setUserId(user.getId().toString());
			expenseTO.setUserName(user.getUserName());
		}
		if(expense.getAmount() != null){
			expenseTO.setAmount(String.format("%.2f", expense.getAmount()));
		}
		expenseTO.setExpenseType(expense.getType());
		return expenseTO;
	}
	

	public static Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		Map<String, String> responseObj = new HashMap<String, String>();
		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}

	public static String hashPassword(String password) {
		String hashword = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");            
			md5.update(password.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			hashword = hash.toString(16);
		}catch (Exception e) {
			e.printStackTrace();
		}	        
		return hashword;
	}


}
