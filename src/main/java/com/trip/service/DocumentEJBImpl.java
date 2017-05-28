
package com.trip.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trip.common.StringUtils;
import com.trip.dto.ResponseStatusTO;
import com.trip.model.Document;

@Stateless
public class DocumentEJBImpl implements DocumentEJBIf{
	
	@PersistenceContext(name = "tripUnit")
	EntityManager em = null;	
	
	@EJB
	private CommonEJBIf cmif;
	
	@EJB
	private UsersEJBIf usrEJBIf;
	
	@javax.annotation.Resource
	private SessionContext sctx;
	

	private static final Logger logger = LoggerFactory
			.getLogger(DocumentEJBImpl.class);
	
	/**
	 * To get the list of DocumentMaster records by criteria
	 * @param Document Object Model
	 * @return List of DocumentMaster records using Collection<DocumentMasterEJBTO>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Document> getDocumentMasters(Document documentMaster) {
		Collection<Document> lstDocuments = new ArrayList<Document>();
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT d from Document d ");
			
			if(!StringUtils.isNullOrBlank(documentMaster.getStatus())){
				sb.append(" where d.status = '").append(documentMaster.getStatus()+"'");
			}else{
				sb.append(" where d.status ='A'");
			}
			if(documentMaster.getId() > 0){ //Used on update duplicate check
				sb.append(" and d.id not like '").append(documentMaster.getId()+"'");
			}
			
			if(!StringUtils.isNullOrBlank(documentMaster.getDocumentFilename())){
				sb.append(" and d.documentFilename = '").append(documentMaster.getDocumentFilename()+"'");
			}
			
			if(!StringUtils.isNullOrBlank(documentMaster.getDocumentMimeType())){
				sb.append(" and d.documentMimeType = '").append(documentMaster.getDocumentMimeType()+"'");
			}
		
			if(!StringUtils.isNullOrBlank(documentMaster.getDocumentTitle())){
				sb.append(" and d.documentTitle like '").append(documentMaster.getDocumentTitle()+"%'");
			}
			
			if(documentMaster.getDocumentType() != null){
				sb.append(" and d.documentType = '").append(documentMaster.getDocumentType()+"'");
			}			
						
		
			sb.append(" ORDER BY uploadedDateTime DESC");
			
//			logger.info("getDocumentMasters :"+sb.toString());
			
			Query q = em.createQuery(sb.toString());
			
			lstDocuments = q.getResultList();
			
		}catch(Exception e){
			logger.error("getDocumentsMasters  : "+e.getMessage());
		}
		return lstDocuments;
	}

	/**
	 * To add the DocumentMaster record
	 * @param Document Object Model
	 * @return Error String
	 */
	@Override
	public ResponseStatusTO addDocumentMasterData(Document document) {
		ResponseStatusTO res = null;
		try{
			res = new 	ResponseStatusTO();
			document.setUser(usrEJBIf.getUserByName(sctx.getCallerPrincipal().getName()));
			document.setUploadedDateTime(new Date());
			em.persist(document);
			res.setStatus(true);
			res.setPersistObjectId(document.getId());
		}catch(Exception e){
			res.setStatus(false);
			res.setErrorMsg(e.getMessage());
			logger.error("addDocumentMasterData"+e.getMessage());
		}
		
		return res;
	}

	/**
	 * To get the DocumentMaster record by ID
	 * @param DocumentMaster Entity 
	 * @return DocumentMaster record using DocumentMasterEJBTO Object Model
	 */
	@Override
	public Document getDocumentMasterDataById(Document documentById) {
	Document document = null;
	try{
		String sql = "SELECT d from Document d where d.id ='"+documentById.getId()+"'";
		TypedQuery<Document> q = em.createQuery(sql,Document.class);
		
		List<Document> lstData = q.getResultList();
		if(lstData != null && !lstData.isEmpty()){
			document = lstData.get(0);
		}	
	}catch(Exception e){
		logger.error("getDocumentMasterDataById"+e.getMessage());
	}	
		return document;
	}

	/**
	 * To update the DocumentMaster record by ID
	 * @param DocumentMaster Entity 
	 * @return No return type
	 */
	@Override
	public ResponseStatusTO updateDocumentMasterDataById(Document document) {
		ResponseStatusTO res = null;
		try{
			res = new ResponseStatusTO();
			document.setUser(usrEJBIf.getUserByName(sctx.getCallerPrincipal().getName()));
			document.setUploadedDateTime(new Date());
			em.merge(document);
			em.flush();
			res.setStatus(true);
			res.setPersistObjectId(document.getId());
		}catch(Exception e){
			res.setStatus(false);
			res.setErrorMsg(e.getMessage());
			logger.error("deleteDocumentMasterDataById"+e.getMessage());
		}
		
		return res;
	}

	/**
	 * To inactive the DocumentMaster record by ID
	 * @param DocumentMaster Entity
	 * @param No return type
	 */
	@Override
	public String deleteDocumentMasterDataById(Document document) {
		String errorString = null;
		try{
			em.merge(document);
		}catch(Exception e){
			errorString = e.getMessage();
			logger.error("deleteDocumentMasterDataById"+e.getMessage());
		}
		
		return errorString;
	}	
}
