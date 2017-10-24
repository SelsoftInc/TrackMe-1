package com.selsoft.trackme.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "LEASE")
public class Lease {

	private int leaseId;
	
	@JsonProperty("propertyname")
	private String propertyName;
	@JsonProperty("ownerFirstName")
	private String ownerFirstName;
	@JsonProperty("ownerLastName")
	private String ownerLastName;
	@JsonProperty("tenantFirstName")
	private String tenantFirstName;
	@JsonProperty("tenantLastName")
	private String tenantLastName;
	@JsonProperty("additionalTenant")
	private String additionalTenant;
	@JsonProperty("rentalId")
	private  int rentalId;
	@JsonProperty("propertyId")
	private int propertyId;
	@JsonProperty("leaseType")
	private String leaseType;
	@JsonProperty("leaseStartDate")
	private Date leaseStartDate;
	@JsonProperty("leaseEndDate")
	private Date leaseEndDate;
	@JsonProperty("tenure")
	private double tenure;
	@JsonProperty("propertyManagerId")
	private int propertyManagerId;
	public int getLeaseId() {
		return leaseId;
	}
	public void setLeaseId(int leaseId) {
		this.leaseId = leaseId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
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
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public int getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	public String getLeaseType() {
		return leaseType;
	}
	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}
	public Date getLeaseStartDate() {
		return leaseStartDate;
	}
	public void setLeaseStartDate(Date leaseStartDate) {
		this.leaseStartDate = leaseStartDate;
	}
	public Date getLeaseEndDate() {
		return leaseEndDate;
	}
	public void setLeaseEndDate(Date leaseEndDate) {
		this.leaseEndDate = leaseEndDate;
	}
	public double getTenure() {
		return tenure;
	}
	public void setTenure(double tenure) {
		this.tenure = tenure;
	}
	public int getPropertyManagerId() {
		return propertyManagerId;
	}
	public void setPropertyManagerId(int propertyManagerId) {
		this.propertyManagerId = propertyManagerId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lease [leaseId=");
		builder.append(leaseId);
		builder.append(", propertyName=");
		builder.append(propertyName);
		builder.append(", ownerFirstName=");
		builder.append(ownerFirstName);
		builder.append(", ownerLastName=");
		builder.append(ownerLastName);
		builder.append(", tenantFirstName=");
		builder.append(tenantFirstName);
		builder.append(", tenantLastName=");
		builder.append(tenantLastName);
		builder.append(", additionalTenant=");
		builder.append(additionalTenant);
		builder.append(", rentalId=");
		builder.append(rentalId);
		builder.append(", propertyId=");
		builder.append(propertyId);
		builder.append(", leaseType=");
		builder.append(leaseType);
		builder.append(", leaseStartDate=");
		builder.append(leaseStartDate);
		builder.append(", leaseEndDate=");
		builder.append(leaseEndDate);
		builder.append(", tenure=");
		builder.append(tenure);
		builder.append(", propertyManagerId=");
		builder.append(propertyManagerId);
		builder.append("]");
		return builder.toString();
	}
	
		
			
}
