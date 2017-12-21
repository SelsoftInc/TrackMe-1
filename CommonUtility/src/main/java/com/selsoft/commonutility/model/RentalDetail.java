package com.selsoft.commonutility.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "RENTALDETAIL")
public class RentalDetail {
	
	@Id
	private String rentId;
	private String propertyId;
	private int propertyRent;
	private int deposit;
	private String leaseType;
	private Date effectiveDate;
	public String getRentId() {
		return rentId;
	}
	public void setRentId(String rentId) {
		this.rentId = rentId;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setProperytId(String propertyId) {
		this.propertyId = propertyId;
	}
	public int getPropertyRent() {
		return propertyRent;
	}
	public void setPropertyRent(int propertyRent) {
		this.propertyRent = propertyRent;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getLeaseType() {
		return leaseType;
	}
	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RentalDetail [rentId=");
		builder.append(rentId);
		builder.append(", PropertId=");
		builder.append(propertyId);
		builder.append(", propertyRent=");
		builder.append(propertyRent);
		builder.append(", deposit=");
		builder.append(deposit);
		builder.append(", leaseType=");
		builder.append(leaseType);
		builder.append(", effectiveDate=");
		builder.append(effectiveDate);
		builder.append("]");
		return builder.toString();
	}
	
}
