package com.trip.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;

import com.trip.dto.UsersTO;
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
	

	private static final String DATE_TIME_FORMAT="dd-MMM-yyyy";

	public static Date convertStringToDate(String srcDate) throws ParseException{
		DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return formatter.parse(srcDate);
	}

	public static String convertDateToString(Date srcDate){
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
		}

		return user;
	}


	

	public static Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		Map<String, String> responseObj = new HashMap<String, String>();
		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}

}
