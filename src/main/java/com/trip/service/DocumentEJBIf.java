package com.trip.service;

import java.util.Collection;

import com.trip.dto.ResponseStatusTO;
import com.trip.model.Document;


public interface DocumentEJBIf {

	public Collection<Document> getDocumentMasters(Document DocumentMaster);	
	
	public ResponseStatusTO addDocumentMasterData(Document DocumentMaster);
	
	public Document getDocumentMasterDataById(Document document);	
	
	public ResponseStatusTO updateDocumentMasterDataById(Document document);	
	
	public String deleteDocumentMasterDataById(Document document);	
}
