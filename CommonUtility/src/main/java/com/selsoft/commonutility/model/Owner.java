package com.selsoft.commonutility.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OWNER")
public class Owner implements Serializable {
	
	private static final Logger logger = Logger.getLogger(Owner.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2689072918162828855L;
	
	@Id
	private String ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerStatus;
	private String email;
	private String ownerPhoneNumber;
	private String managerId;
	private double grossIncome;
	private double expense;
	private double netIncome;
	
	private List<Error> errors = null;
 
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}
	
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerStatus() {
		return ownerStatus;
	}

	public void setOwnerStatus(String ownerStatus) {
		this.ownerStatus = ownerStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailId) {
		this.email = emailId;
	}

	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}

	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public double getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(double grossIncome) {
		this.grossIncome = grossIncome;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public double getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}
	
	public JSONObject toJSON() throws JSONException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("managerId",  this.managerId);
		jsonObject.put("ownerId", this.ownerId);
		jsonObject.put("email", this.email);
		jsonObject.put("ownerFirstName", this.ownerFirstName);
		jsonObject.put("ownerLastName", this.ownerLastName);
		jsonObject.put("ownerStatus", this.getOwnerStatus());
		jsonObject.put("ownerPhoneNumber", this.ownerPhoneNumber);
		jsonObject.put("grossIncome", this.grossIncome);
		jsonObject.put("expense", this.expense);
		jsonObject.put("netIncome", this.netIncome);
		if(this.errors != null && this.errors.size() > 0) {
			jsonObject.put("errors", this.getErrorJSON());
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