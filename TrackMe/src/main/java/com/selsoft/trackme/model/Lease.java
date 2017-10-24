package com.selsoft.trackme.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lease {

	private int leaseId;
	private String propertyName;
	private String ownerFirstName;
	private String ownerLastName;
	private String tenantFirstName;
	private String tenantLastName;
	private String additionalTenant;
	private  int rentalId;
	private int propertyId;
	@JsonProperty("propertystatus")
	private String propertyStatus;
	private String leaseType;
	private Date leaseStartDate;
	private Date leaseEndDate;
	private double tenure;
	private int propertyManagerId;
	
	
	
	
	
	

	public String getPropertyStatus() {
		return propertyStatus;
	}
	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
	}
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
	
	
	
			
}
