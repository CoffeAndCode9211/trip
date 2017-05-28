package com.trip.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trip.dto.ResponseStatusTO;
import com.trip.dto.StringUtil;
import com.trip.model.Users;

/**
 * Employee Implementation 
 *
 * @author  Ashish Kumar
 * @version 1.0
 * @since   2015-08-19 
 */

@Stateless
public class UsersEJBImpl implements UsersEJBIf {

	@PersistenceContext(unitName = "tripUnit" )
	private EntityManager em;
	
	@Resource
	private SessionContext sctx;

	private static final Logger logger = LoggerFactory
			.getLogger(UsersEJBImpl.class);

	public ResponseStatusTO addUsers(Users users) throws Exception {
		ResponseStatusTO res = null;
		try{
			res = new 	ResponseStatusTO();
			em.persist(users);
			res.setStatus(true);
			res.setPersistObjectId(users.getId());
		}catch(Exception e){
			res.setStatus(false);
			res.setErrorMsg(e.getMessage());
			logger.error("users"+e.getMessage());
		}
		
		return res;
	}

	public ResponseStatusTO updateUsers(Users users) throws Exception {
		ResponseStatusTO res = null;
		try{
			res = new 	ResponseStatusTO();
			em.merge(users);
			res.setStatus(true);
			res.setPersistObjectId(users.getId());
		}catch(Exception e){
			res.setStatus(false);
			res.setErrorMsg(e.getMessage());
			logger.error("users"+e.getMessage());
		}
		
		return res;
	}

	public Users getUsersById(Integer id) throws Exception {
		return em.find(Users.class, id);
	}

	public List<Users> getUsersByFilter(Users users)
			throws Exception {
		System.out.println("Session Id: "+sctx.getCallerPrincipal().getName());
		StringBuilder query = new StringBuilder();
		query.append("from Users e where 1=1 ");
		if(!StringUtil.isNullOrBlank(users.getEmail())){
			query.append(" and e.email like '");
			query.append(users.getEmail());
			query.append("%' ");
		}
		if(!StringUtil.isNullOrBlank(users.getFirstName())){
			query.append(" and e.firstName like '");
			query.append(users.getFirstName());
			query.append("%' ");
		}
		if(!StringUtil.isNullOrBlank(users.getLastName())){
			query.append(" and e.lastName like '");
			query.append(users.getLastName());
			query.append("%' ");
		}
		if(!StringUtil.isNullOrBlank(users.getPhone())){
			query.append(" and e.phone like '");
			query.append(users.getPhone());
			query.append("%' ");
		}
		List<Users> usersList = em.createQuery(query.toString(), Users.class).getResultList();
		return usersList;
	}

	public boolean deleteUsers(Users users) throws Exception {
		em.remove(users);
		em.flush();
		return true;
	}

	@SuppressWarnings("unchecked")
	public Boolean checkLogin(String username, String password) {

		Query q = em.createQuery("SELECT u FROM Users u WHERE u.userName = "
				+ " '"+username+"' and u.password = '"+password+"' ");
		
		Collection<Users> s = q.getResultList();
		if(s != null && !s.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	public Users getUserByName(String username) {

		Query q = em.createQuery("SELECT u FROM Users u WHERE u.userName = "
				+ " '"+username+"'  ");
		List<Users> s = q.getResultList();
		if(s != null && !s.isEmpty()){
			return s.get(0);
		}else{
			return null;
		}
	}

}
