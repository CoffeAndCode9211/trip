package com.trip.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trip.dto.ResponseStatusTO;
import com.trip.model.Expense;

/**
 * Employee Implementation 
 *
 * @author  Ashish Kumar
 * @version 1.0
 * @since   2015-08-19 
 */

@Stateless
public class ExpenseEJBImpl implements ExpenseEJBIf {

	@PersistenceContext(unitName = "tripUnit" )
	private EntityManager em;
	
	
	@EJB
	private UsersEJBIf usrEJBIf;
	
	@javax.annotation.Resource
	private SessionContext sctx;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ExpenseEJBImpl.class);

	public ResponseStatusTO addExpense(Expense expense) throws Exception {
		ResponseStatusTO res = null;
		try{
			res = new 	ResponseStatusTO();
			expense.setUserId(usrEJBIf.getUserByName(sctx.getCallerPrincipal().getName()));
			expense.setCreatedDateTime(new Date());
			em.persist(expense);
			res.setStatus(true);
			res.setPersistObjectId(expense.getId());
		}catch(Exception e){
			res.setStatus(false);
			res.setErrorMsg(e.getMessage());
			logger.error("expense"+e.getMessage());
		}
		
		return res;
	}

	public ResponseStatusTO updateExpense(Expense expense) throws Exception {
		ResponseStatusTO res = null;
		try{
			res = new 	ResponseStatusTO();
			expense.setUserId(usrEJBIf.getUserByName(sctx.getCallerPrincipal().getName()));
			expense.setCreatedDateTime(new Date());
			em.merge(expense);
			res.setStatus(true);
			res.setPersistObjectId(expense.getId());
		}catch(Exception e){
			res.setStatus(false);
			res.setErrorMsg(e.getMessage());
			logger.error("expense"+e.getMessage());
		}
		
		return res;
	}

	public Expense getExpenseById(Integer id) throws Exception {
		return em.find(Expense.class, id);
	}

	public List<Expense> getExpenseByFilter(Expense expense)
			throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("from Expense e where e.status='A' ");
		if(expense.getType() != null){
			query.append(" AND e.type='"+expense.getType()+"' ");
		}
		List<Expense> expenseList = em.createQuery(query.toString(), Expense.class).getResultList();
		return expenseList;
	}

	public boolean deleteExpense(Expense expense) throws Exception {
		expense.setStatus("I");
		em.merge(expense);
		em.flush();
		return true;
	}



}
