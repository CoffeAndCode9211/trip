package com.trip.service;


import java.util.List;

import com.trip.dto.ResponseStatusTO;
import com.trip.model.Expense;

public interface ExpenseEJBIf {

	public ResponseStatusTO addExpense(Expense expense) throws Exception;
	
	public ResponseStatusTO updateExpense(Expense expense) throws Exception;
	
	public Expense getExpenseById(Integer id) throws Exception;
	
	public List<Expense> getExpenseByFilter(Expense expense) throws Exception;
	
	public boolean deleteExpense(Expense expense) throws Exception;
	
	
	
}
