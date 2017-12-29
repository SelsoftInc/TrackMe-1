package com.selsoft.auto.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TENANT")
public class Tenant implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 114690934067135620L;

	private static final Logger logger = Logger.getLogger(Tenant.class);
	
	@Id
	private String tenantId;
	private String tenantFirstName;
	private String tenantLastName;
	private String tenantEmailId;
	private String tenantPhoneNumber;
	private String tenantStatus;
	private String managerId;
	private String propertyId;
	private String ownerId;
	
	private List<Error> errors = null;

	public Tenant() {
		this.tenantStatus = TenantStatus.NEW.toString();
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantFirstName() {
		return tenantFirstName;
	}

	public void setTenantFirstName(String tenantFirstName) {
		this.tenantFirstName = tenantFirstName;
	}

	public String getTenantLastName() {
		return tenantLastName;
	}

	public void setTenantLastName(String tenantLastName) {
		this.tenantLastName = tenantLastName;
	}

	public String getTenantEmailId() {
		return tenantEmailId;
	}

	public void setTenantEmailId(String tenantEmailId) {
		this.tenantEmailId = tenantEmailId;
	}

	public String getTenantPhoneNumber() {
		return tenantPhoneNumber;
	}

	public void setTenantPhoneNumber(String tenantPhoneNumber) {
		this.tenantPhoneNumber = tenantPhoneNumber;
	}

	public String getTenantStatus() {
		return tenantStatus;
	}

	public void setTenantStatus(String tenantStatus) {
		this.tenantStatus = tenantStatus;
	}
	
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
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
	
	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		//return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", loggedOnFlag=" + loggedOnFlag + ", lastAccessed=" + lastAccessed + ", userType=" + userType + "]";
		try {
			return toJSON().toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managerId;
	}
	
	public JSONObject toJSON() throws JSONException {
		//return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", loggedOnFlag=" + loggedOnFlag + ", lastAccessed=" + lastAccessed + ", userType=" + userType + "]";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tenantId", tenantId);
		jsonObject.put("tenantFirstName", this.tenantFirstName);
		jsonObject.put("tenantLastName", this.tenantLastName);
		jsonObject.put("tenantEmailId", this.tenantEmailId);
		jsonObject.put("tenantPhoneNumber", this.tenantPhoneNumber);
		jsonObject.put("tenantStatus", this.tenantStatus);
		jsonObject.put("managerId",  this.managerId);
		jsonObject.put("propertyId",  this.propertyId);
		jsonObject.put("ownerId", this.ownerId);
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