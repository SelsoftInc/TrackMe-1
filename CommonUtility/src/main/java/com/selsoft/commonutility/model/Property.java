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
import com.mongodb.util.JSON;

@Document(collection = "PROPERTY")
public class Property implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 622479757781692192L;

	private static final Logger logger = Logger.getLogger(Property.class);

	@Id
	private String propertyId;
	private String propertyName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipCode;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerId;
	private String managerId;
	private RentalDetail rentalDetail;
	private String propertyStatus;
	private double grossIncome;
	private double expense;
	private double netIncome;
	private Lease lease;

	private List<Error> errors = null; 

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	public RentalDetail getRentalDetail() {
		return rentalDetail;
	}

	public void setRentalDetail(RentalDetail rentalDetail) {
		this.rentalDetail = rentalDetail;
	}

	public String getPropertyStatus() {
		return propertyStatus;
	}

	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
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
	
	public Lease getLease() {
		return lease;
	}

	public void setLease(Lease lease) {
		this.lease = lease;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return JSON.serialize(toJSON());
	}
	
	public JSONObject toJSON() {
		//return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", loggedOnFlag=" + loggedOnFlag + ", lastAccessed=" + lastAccessed + ", userType=" + userType + "]";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("propertyId", this.propertyId);
		jsonObject.put("propertyName", this.propertyName);
		jsonObject.put("address1", this.address1);
		jsonObject.put("address2", this.address2);
		jsonObject.put("city", this.city);
		jsonObject.put("state", this.state);
		jsonObject.put("zipCode", this.zipCode);
		jsonObject.put("ownerId", this.ownerId);
		jsonObject.put("managerId", this.managerId);
		jsonObject.put("ownerFirstName", this.ownerFirstName);
		jsonObject.put("ownerLastName", this.ownerLastName);
		jsonObject.put("grossIncome", this.grossIncome);
		jsonObject.put("expense", this.expense);
		jsonObject.put("netIncome", this.netIncome);
		jsonObject.put("lease", this.lease);
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
