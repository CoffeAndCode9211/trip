package com.trip.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="DOCUMENT")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="ID",unique=true, nullable=false)
	private int id;

	@Lob
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="DOCUMENT_FILENAME", nullable=false, length=50)
	private String documentFilename;

	@Lob
	@Column(name="DOCUMENT_MIME_TYPE", nullable=false)
	private String documentMimeType;

	@Lob
	@Column(name="DOCUMENT_PATH", nullable=false)
	private String documentPath;

	@Column(name="DOCUMENT_TITLE", nullable=false, length=50)
	private String documentTitle;

	@Column(name="DOCUMENT_TYPE")
	private String documentType;

	@Column(name = "STATUS", length = 2)
	private String status; 

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPLOADED_DATE_TIME", nullable=false)
	private Date uploadedDateTime;


	//bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="UPLOADED_BY", nullable=false)
	private Users user;


	public Document() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getDocumentPath() {
		return documentPath;
	}


	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getUploadedDateTime() {
		return uploadedDateTime;
	}


	public void setUploadedDateTime(Date uploadedDateTime) {
		this.uploadedDateTime = uploadedDateTime;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}




}