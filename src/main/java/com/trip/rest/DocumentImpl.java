package com.trip.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trip.common.StringUtils;
import com.trip.dto.DocumentMaster;
import com.trip.dto.ResponseStatusTO;
import com.trip.model.Document;
import com.trip.model.OptionConfig;
import com.trip.model.Users;
import com.trip.service.CommonEJBIf;
import com.trip.service.DocumentEJBIf;
import com.trip.service.UsersEJBIf;


@Stateless
public class DocumentImpl implements DocumentIf {

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(DocumentImpl.class);

	@EJB
	DocumentEJBIf documentEJBIf;
	
	@EJB
	UsersEJBIf usersEJBIf;
	
	@EJB
	CommonEJBIf commonEJBIf = null;


	public Response addDocumentMaster(DocumentMaster documentMaster) {
		try{			

			OptionConfig optionConfig = commonEJBIf.getOptionConfig("DOCUMENT_ROOT");

			if(optionConfig != null && optionConfig.getValue() != null){					

				logger.info("File Name : " + documentMaster.getDocumentFilename());

				if(documentMaster.getDocumentFilename() == null || documentMaster.getDocumentFilename().isEmpty()){
					documentMaster.setDocumentFilename(null);
				}				

				String DOCUMENT_ROOT = optionConfig.getValue();
				String defaultUpload = "docs";
				if(!StringUtils.isNullCombo(documentMaster.getUserName())){
					defaultUpload += "/"+documentMaster.getUserName();
				}

				Document document = new Document();	

				document.setDocumentTitle(documentMaster.getDocumentTitle());

				document.setStatus("A");

				document.setDocumentFilename(documentMaster.getDocumentFilename());

				document.setDescription(StringUtils.blankToNull(documentMaster.getDescription()));				
				document.setDocumentFilename(documentMaster.getDocumentFilename());
				document.setDocumentMimeType(documentMaster.getDocumentMimeType());
				document.setDocumentType("PP");
				document.setDocumentPath(DOCUMENT_ROOT+"/"+defaultUpload);

				if(documentMaster.getEnteredBy() != null){
					Users user = new Users();
					user.setId(Integer.parseInt(documentMaster.getEnteredBy()));
					document.setUser(user);
				}

				document.setUploadedDateTime(new Date());

							

				try{
					// logger.info("Final path where document will save :");
					logger.info("documentMaster.getDocumentData() : "+documentMaster.getDocumentData());
					logger.info("document.getDocumentPath() : "+document.getDocumentPath());
					logger.info("document.getDocumentFilename() : "+document.getDocumentFilename());
					writeFile(documentMaster.getDocumentData(), document.getDocumentPath()+"/"+document.getDocumentFilename());

				}catch(IOException e) {     
					Map<String, String> responseObj = new HashMap<String, String>();
					responseObj.put("UPLOADFAILED", "Upload failed. Reason-> "+e.getMessage().replaceAll("\\,", ""));
					builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
					return builder.build();
				}catch(Exception e){
					Map<String, String> responseObj = new HashMap<String, String>();
					responseObj.put("UPLOADFAILED", "Upload failed. Reason: "+e.getMessage().replaceAll("\\,", ""));
					builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
					return builder.build();
				}					

				FileDataSource ds = new FileDataSource(document.getDocumentFilename());  
				String documentMimeType = ds.getContentType();

				// logger.info("Content-type :"+documentMimeType);

				document.setDocumentMimeType(documentMimeType);

				ResponseStatusTO msg =  documentEJBIf.addDocumentMasterData(document);

				if(msg.getStatus() == false){

					Map<String, String> responseObj = new HashMap<String, String>();
					responseObj.put("uniquekey", msg.getErrorMsg());
					builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
					return builder.build();
				}else{

					Map<String, String> responseObj = new HashMap<String, String>();
					responseObj.put("documentId", msg.getPersistObjectId()+"");
					
					if(documentMaster.getUserId() != null){
						Users user =  usersEJBIf.getUsersById(Integer.parseInt(documentMaster.getUserId()));
						Document doc = new Document();
						doc.setId(msg.getPersistObjectId());
						user.setDocumentId(doc);
						usersEJBIf.updateUsers(user);
					}
					
					builder = Response.status(Response.Status.OK).entity(responseObj);
				}
			}else{
				Map<String, String> responseObj = new HashMap<String, String>();
				responseObj.put("DOCUMENTROOTNOTFOUND", "Document root not found. Please contact the admin");
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
				return builder.build();
			}

		} catch (Exception e) {
			logger.error("There is an Exception "+e.getMessage());
		}
		return builder.build();
	}

	private void writeFile(byte[] content, String filename) throws IOException {
		File file = new File(filename);
 
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();		
		}
 
		FileOutputStream fop = new FileOutputStream(file);
 
		fop.write(content);
		fop.flush();
		fop.close();		
}	

	public Response updateDocumentImage(DocumentMaster documentMaster, int id) {
		return null;
	}


	public Response getDocumentToOpen(int id) {		
		File file = null;
		Document doc = null;
		URI uri = null;
		String fileName = null;
		String docMimeType  = null;
		try{
			
			Document document = new Document();
			document.setId(id);
			
			// logger.info("getDocumentToOpen by id"+id);
			
			doc = documentEJBIf.getDocumentMasterDataById(document);
			logger.info("getDocumentToOpen by id"+id+" doc:"+doc);
			if(doc != null){
				fileName = doc.getDocumentFilename();
				docMimeType  = doc.getDocumentMimeType();
				logger.info("filename"+fileName+" docType:"+docMimeType);
				logger.info("Open the file :"+doc.getDocumentPath()+"/"+fileName);
				file = new File(doc.getDocumentPath()+"/"+fileName);
				uri = file.toURI();
				logger.info("URI"+uri.getPath());
			}
			
			
		}catch(Exception e){
			e.printStackTrace();

		}
		return  Response.ok((Object)file,MediaType.MULTIPART_FORM_DATA)
				.header("content-disposition", "inline; filename=\"" + fileName+"\"")
				.contentLocation(uri)
				.type(docMimeType)
				.build();
	}




}
