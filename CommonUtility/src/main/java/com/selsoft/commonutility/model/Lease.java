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

@Document(collection = "LEASE")
public class Lease implements Serializable {
	
	private static final Logger logger = Logger.getLogger(Lease.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1966991328777854719L;
	
	@Id
	private String leaseId;
	private String leaseType;
	private String leaseStartDate;
	private String leaseEndDate;
	private String moveInDate;
	private String tenure;
	private String leaseStatus;
	private double rent;
	private double deposit;
	private String propertyId;
	private String propertyName;
	private String ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String tenantId;
	private String tenantFirstName;
	private String tenantLastName;
	private String additionalTenant;
	private String rentalId;
	private String managerId;
	private RentalDetail rentalDetail;
	
	private List<Error> errors = null;
	
	public String getLeaseId() {
		return leaseId;
	}
	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
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
	public String getAdditionalTenant() {
		return additionalTenant;
	}
	public void setAdditionalTenant(String additionalTenant) {
		this.additionalTenant = additionalTenant;
	}
	public String getRentalId() {
		return rentalId;
	}
	public void setRentalId(String rentalId) {
		this.rentalId = rentalId;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getLeaseType() {
		return leaseType;
	}
	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}
	public String getLeaseStartDate() {
		return leaseStartDate;
	}
	public void setLeaseStartDate(String leaseStartDate) {
		this.leaseStartDate = leaseStartDate;
	}
	public String getLeaseEndDate() {
		return leaseEndDate;
	}
	public void setLeaseEndDate(String leaseEndDate) {
		this.leaseEndDate = leaseEndDate;
	}
	public String getMoveInDate() {
		return moveInDate;
	}
	public void setMoveInDate(String moveInDate) {
		this.moveInDate = moveInDate;
	}
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public String getLeaseStatus() {
		return leaseStatus;
	}
	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public RentalDetail getRentalDetail() {
		return rentalDetail;
	}
	public void setRentalDetail(RentalDetail rentalDetail) {
		this.rentalDetail = rentalDetail;
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
	
	public JSONObject toJSON() throws JSONException {
		//return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", loggedOnFlag=" + loggedOnFlag + ", lastAccessed=" + lastAccessed + ", userType=" + userType + "]";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("leaseId",  this.leaseId);
		jsonObject.put("leaseType", this.leaseType);
		jsonObject.put("leaseStartDate", this.leaseStartDate);
		jsonObject.put("leaseEndDate", this.leaseEndDate);
		jsonObject.put("leaseStatus", this.leaseStatus);
		jsonObject.put("tenure", this.tenure);
		jsonObject.put("rent", this.rent);
		jsonObject.put("deposit", this.deposit);
		jsonObject.put("propertyId", this.propertyId);
		jsonObject.put("propertyName", this.propertyName);
		jsonObject.put("managerId",  this.managerId);
		jsonObject.put("ownerId", this.ownerId);
		jsonObject.put("ownerFirstName", this.ownerFirstName);
		jsonObject.put("ownerLastName", this.ownerLastName);
		jsonObject.put("tenantId", this.tenantId);
		jsonObject.put("tenantFirstName", this.tenantFirstName);
		jsonObject.put("tenantLastName", this.tenantLastName);
		jsonObject.put("additionalTenant", this.additionalTenant);
		jsonObject.put("rentalId", this.rentalId);
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
