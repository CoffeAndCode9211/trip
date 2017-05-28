/**
 * Document Screen Master file
 * 
 * Copyright (c) 2013 Edgeware Technologies. All Rights Reserved.
 */
package com.trip.dto;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 * This is a Master file for Document Screen
 * 
 * @version 1.0
 * @author Muthu
 */

@XmlRootElement(name="documentMaster")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentMaster  {

	@XmlElement(name = "documentId")
	private int documentId;

	@XmlAttribute(name="href")
	private String href;
	
	@FormParam("description")
	private String description;

	@NotNull
	@FormParam("documentFilename")	
	private String documentFilename;

	@FormParam("documentMimeType")
	private String documentMimeType;

	@FormParam("documentData")
	@PartType("application/octet-stream")
	private byte[] documentData;

	@FormParam("documentTitle")
	private String documentTitle;

	@FormParam("documentType")
	private String documentType;

	@FormParam("uploadedDateTime")
	private String uploadedDateTime;
	
	@FormParam("insuranceCompanyId")
	private String insuranceCompanyId;
	
	@FormParam("patientId")
	private String patientId;
	
	@FormParam("patientInsuranceId")
	private String patientInsuranceId;

	@FormParam("documentCategoryId")
	private String documentCategoryId;
	
	@FormParam("selectedDocumentTypeId")
	private String selectedDocumentTypeId;
	
	@FormParam("patientHRN")
	private String patientHRN;

	@FormParam("enteredBy")
	private String enteredBy;
	
	@FormParam("status")
	private String status;
	
	@FormParam("facilityId")
	private String facilityId;
	
	@FormParam("userId")
	private String userId;
	
	@FormParam("userName")
	private String userName;
	
	public int getId() {
		return documentId;
	}

	public void setId(int documentId) {
		this.documentId = documentId;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocumentFilename() {
		return documentFilename;
	}

	public void setDocumentFilename(String documentFilename) {
		this.documentFilename = documentFilename;
	}

	public String getDocumentMimeType() {
		return documentMimeType;
	}

	public void setDocumentMimeType(String documentMimeType) {
		this.documentMimeType = documentMimeType;
	}

	public byte[] getDocumentData() {
		return documentData;
	}

	public void setDocumentData(byte[] documentData) {
		this.documentData = documentData;
	}

	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getUploadedDateTime() {
		return uploadedDateTime;
	}

	public void setUploadedDateTime(String uploadedDateTime) {
		this.uploadedDateTime = uploadedDateTime;
	}

	public String getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(String insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientInsuranceId() {
		return patientInsuranceId;
	}

	public void setPatientInsuranceId(String patientInsuranceId) {
		this.patientInsuranceId = patientInsuranceId;
	}

	public String getDocumentCategoryId() {
		return documentCategoryId;
	}

	public void setDocumentCategoryId(String documentCategoryId) {
		this.documentCategoryId = documentCategoryId;
	}

	public String getSelectedDocumentTypeId() {
		return selectedDocumentTypeId;
	}

	public void setSelectedDocumentTypeId(String selectedDocumentTypeId) {
		this.selectedDocumentTypeId = selectedDocumentTypeId;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public String getPatientHRN() {
		return patientHRN;
	}

	public void setPatientHRN(String patientHRN) {
		this.patientHRN = patientHRN;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}