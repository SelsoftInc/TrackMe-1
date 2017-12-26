package com.selsoft.commonutility.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OWNER")
public class Owner implements Serializable {
	
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
	
	private static final Logger logger = Logger.getLogger(Owner.class);
	
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

	/*@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Owner [ownerId=");
		builder.append(ownerId);
		builder.append(", ownerFirstName=");
		builder.append(ownerFirstName);
		builder.append(", ownerLastName=");
		builder.append(ownerLastName);
		builder.append(", ownerStatus=");
		builder.append(ownerStatus);
		builder.append(", emailId=");
		builder.append(email);
		builder.append(", ownerPhoneNumber=");
		builder.append(ownerPhoneNumber);
		builder.append("]");
		return builder.toString();
	}*/
	
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		//return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", loggedOnFlag=" + loggedOnFlag + ", lastAccessed=" + lastAccessed + ", userType=" + userType + "]";
		return toJSON().toString();
	}
	
	public JSONObject toJSON() {
		//return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", loggedOnFlag=" + loggedOnFlag + ", lastAccessed=" + lastAccessed + ", userType=" + userType + "]";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ownerId", this.ownerId);
		jsonObject.put("email", this.email);
		jsonObject.put("ownerFirstName", this.ownerFirstName);
		jsonObject.put("ownerLastName", this.ownerLastName);
		jsonObject.put("ownerStatus", this.getOwnerStatus());
		jsonObject.put("ownerPhoneNumber", this.ownerPhoneNumber);
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

	/*public Map toJSONString() {
		//return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", loggedOnFlag=" + loggedOnFlag + ", lastAccessed=" + lastAccessed + ", userType=" + userType + "]";
		return toJSON().toMap();
	}
	
	public JSONObject toJSON() {
		//return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", loggedOnFlag=" + loggedOnFlag + ", lastAccessed=" + lastAccessed + ", userType=" + userType + "]";
		
		JSONObject jObject = new JSONObject();
		try
		{
		    jObject.put("firstName", this.firstName);
		    jObject.put("lastName", this.lastName);
		    jObject.put("email", this.email);
		    jObject.put("loggedOnFlag", this.loggedOnFlag);
		    jObject.put("lastAccessedOn", this.lastAccessedOn);
		    jObject.put("userType", this.userType);
		    jObject.put("userStatus", this.userStatus);
		    jObject.put("errors", getErrorJSON());
		} catch (JSONException jse) {
		    logger.error(jse);
		}
		return jObject;
	}*/
	
	public void addError(Error error) {
		if(this.errors == null) {
			this.errors = new ArrayList<Error>();
		}
		this.errors.add(error);
	}
}