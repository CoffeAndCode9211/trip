/**
* @Author Edgeware
* @version 1.0
*
*  This is the Appointment Search object model details file,
*  it contains all the fields required to search the appointments.
*/

package com.trip.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ResponseStatusTO implements Serializable {
	
	private Boolean status;
    private Integer persistObjectId;
    private String multiplePersistedIds;
    private String errorMsg;
	public ResponseStatusTO() {
	}
    public ResponseStatusTO(Boolean status) {
		this.status = status;
	}
	public ResponseStatusTO(Boolean status, Integer persistObjectId) {
		this.status = status;
		this.persistObjectId = persistObjectId;
	}
	public ResponseStatusTO(Boolean status, String errorMsg) {
		this.status = status;
		this.errorMsg = errorMsg;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getPersistObjectId() {
		return persistObjectId;
	}
	public void setPersistObjectId(Integer persistObjectId) {
		this.persistObjectId = persistObjectId;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getMultiplePersistedIds() {
		return multiplePersistedIds;
	}
	public void setMultiplePersistedIds(String multiplePersistedIds) {
		this.multiplePersistedIds = multiplePersistedIds;
	}
    
    
    
   }
