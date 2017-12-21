package com.selsoft.commonutility.model;


import java.io.Serializable;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "TRANSACTION")
public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6289825957981384083L;

	private static final Logger logger = Logger.getLogger(Transaction.class);
	
	
	@Id
	private String transactionId;
	private String propertyId;
	private String ownerId;
	private String managerId;
	private String amount;
	private String transactionType;
	private String transactionCode;
	private int paidById;
	private String paidOn;
	private String category;
	private String enteredOn;
	private String filePath;
	
	private List<Error> errors = null;
	
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public int getPaidById() {
		return paidById;
	}

	public void setPaidById(int paidById) {
		this.paidById = paidById;
	}

	public String getPaidOn() {
		return paidOn;
	}

	public void setPaidOn(String paidOn) {
		this.paidOn = paidOn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEnteredOn() {
		return enteredOn;
	}

	public void setEnteredOn(String enteredOn) {
		this.enteredOn = enteredOn;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [transactionId=");
		builder.append(transactionId);
		builder.append(", propertyId=");
		builder.append(propertyId);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", managerId=");
		builder.append(managerId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", transactionType=");
		builder.append(transactionType);
		builder.append(", transactionCode=");
		builder.append(transactionCode);
		builder.append(", paidById=");
		builder.append(paidById);
		builder.append(", paidOn=");
		builder.append(paidOn);
		builder.append(", category=");
		builder.append(category);
		builder.append(", enteredOn=");
		builder.append(enteredOn);
		builder.append(", filePath=");
		builder.append(filePath);
		builder.append(", errors=");
		builder.append(errors);
		builder.append("]");
		return builder.toString();
	}

	public JSONObject toJSON() {
		//return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", loggedOnFlag=" + loggedOnFlag + ", lastAccessed=" + lastAccessed + ", userType=" + userType + "]";
		
		JSONObject jsonObject = new JSONObject();
		try
		{
		    jsonObject.put("transactionId", this.transactionId);
		    jsonObject.put("propertyId", this.propertyId);
		    jsonObject.put("ownerId", this.ownerId);
		   jsonObject.put("paidById", this.paidById);
		    jsonObject.put("managerId", this.managerId);
		    jsonObject.put("amount", this.amount);
		    jsonObject.put("transactionCode", this.transactionCode);
		    jsonObject.put("transactionType", this.transactionType);
		    jsonObject.put("paidOn", this.paidOn);
		    jsonObject.put("enteredon",this.enteredOn);
		    if(this.errors != null && this.errors.size() > 0) {
				jsonObject.put("errors", this.getErrorJSON());
			}
		} catch (JSONException jse) {
		    logger.error(jse);
		}
		return jsonObject;
	}
	
	public JSONObject getErrorJSON() {
		if(this.errors == null || this.errors.size() == 0) return null;
		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		try
		{
		    for (Error error : this.errors)
		    {
		         JSONObject errorJSON = new JSONObject();
		         errorJSON.put("errorType", error.getErrorType());
		         errorJSON.put("error", error.getError());
		         jArray.put(errorJSON);
		    }
		    jObject.put("errors", jArray);
		} catch (JSONException jse) {
		    logger.error(jse);
		}
		return jObject;
	}
	
	public void addError(Error error) {
		if(this.errors == null) {
			this.errors = new ArrayList<Error>();
		}
		this.errors.add(error);
	}
	 
}
