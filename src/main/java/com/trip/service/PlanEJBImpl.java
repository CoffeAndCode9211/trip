package com.trip.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trip.dto.ResponseStatusTO;
import com.trip.model.Plan;

/**
 * Employee Implementation 
 *
 * @author  Ashish Kumar
 * @version 1.0
 * @since   2015-08-19 
 */

@Stateless
public class PlanEJBImpl implements PlanEJBIf {

	@PersistenceContext(unitName = "tripUnit" )
	private EntityManager em;
	
	
	@EJB
	private UsersEJBIf usrEJBIf;
	
	@javax.annotation.Resource
	private SessionContext sctx;
	
	private static final Logger logger = LoggerFactory
			.getLogger(PlanEJBImpl.class);

	public ResponseStatusTO addPlan(Plan plan) throws Exception {
		ResponseStatusTO res = null;
		try{
			res = new 	ResponseStatusTO();
			plan.setUserId(usrEJBIf.getUserByName(sctx.getCallerPrincipal().getName()));
			em.persist(plan);
			res.setStatus(true);
			res.setPersistObjectId(plan.getId());
		}catch(Exception e){
			res.setStatus(false);
			res.setErrorMsg(e.getMessage());
			logger.error("plan"+e.getMessage());
		}
		
		return res;
	}

	public ResponseStatusTO updatePlan(Plan plan) throws Exception {
		ResponseStatusTO res = null;
		try{
			res = new 	ResponseStatusTO();
			plan.setUserId(usrEJBIf.getUserByName(sctx.getCallerPrincipal().getName()));
			em.merge(plan);
			res.setStatus(true);
			res.setPersistObjectId(plan.getId());
		}catch(Exception e){
			res.setStatus(false);
			res.setErrorMsg(e.getMessage());
			logger.error("plan"+e.getMessage());
		}
		
		return res;
	}

	public Plan getPlanById(Integer id) throws Exception {
		return em.find(Plan.class, id);
	}

	public List<Plan> getPlanByFilter(Plan plan)
			throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("from Plan e where e.status='A' ");
		List<Plan> planList = em.createQuery(query.toString(), Plan.class).getResultList();
		return planList;
	}

	public boolean deletePlan(Plan plan) throws Exception {
		plan.setStatus("I");
		em.merge(plan);
		em.flush();
		return true;
	}



}
