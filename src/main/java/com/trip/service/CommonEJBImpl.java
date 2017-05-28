package com.trip.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trip.dto.ActivityLogTO;
import com.trip.dto.StringUtil;
import com.trip.model.OptionConfig;
import com.trip.model.RestActivityLog;
import com.trip.model.Users;

/**
 * Employee Implementation 
 *
 * @author  Ashish Kumar
 * @version 1.0
 * @since   2015-08-19 
 */

@Stateless
public class CommonEJBImpl implements CommonEJBIf {

	@PersistenceContext(unitName = "tripUnit" )
	private EntityManager em;
	
	@Resource
	private SessionContext sctx;

	private static final Logger logger = LoggerFactory
			.getLogger(CommonEJBImpl.class);

	
	@Override
	public void activityLogIt(ActivityLogTO activityLogTO){
		//set to to entity

		RestActivityLog activityLog = new RestActivityLog();
		activityLog.setPath(activityLogTO.getPath());
		activityLog.setCookies(activityLogTO.getCookies());
		activityLog.setRequestURI(activityLogTO.getRequestURI());
		activityLog.setFormData(activityLogTO.getFormData());
		activityLog.setHeaders(activityLogTO.getHeaders());
		activityLog.setMethod(activityLogTO.getMethod());
		activityLog.setRequestStartTime(activityLogTO.getRequestStartTime());
		activityLog.setRequestStopTime(activityLogTO.getRequestStopTime());
		activityLog.setTotalTimeTaken(activityLogTO.getTotalTimeTaken());

		if(!StringUtil.isNullCombo(activityLogTO.getUserId())){
			Users user = new Users();
			user.setId(Integer.parseInt(activityLogTO.getUserId()));
			activityLog.setUserId(user);
		}
		em.persist(activityLog);
		em.flush();
	}
	
	@Override
	public OptionConfig getOptionConfig(String optionkey) {
		OptionConfig value = null;

		String sql = "Select o  FROM  OptionConfig o  where  o.key = '"+optionkey+"' ";

		TypedQuery<OptionConfig> q = em.createQuery(sql,OptionConfig.class);
		List<OptionConfig> lstData = q.getResultList();
		if(lstData != null && !lstData.isEmpty()){
			value = lstData.get(0);
		}		
		return value;
	}

}
