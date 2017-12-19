package com.selsoft.lease.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LEASE")
public class Lease {
	@Id
	private String leaseId;
	private String propertyName;
	private String ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String tenantId;
	private String tenantFirstName;
	private String tenantLastName;
	private String additionalTenant;
	private String rentalId;
	private String propertyId;
	private String leaseType;
	private Date leaseStartDate;
	private Date leaseEndDate;
	private int tenure;
	private String propertyManagerId;
	private RentalDetail rentalDetail;
    private String filePath;
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
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public String getPropertyManagerId() {
		return propertyManagerId;
	}
	public void setPropertyManagerId(String propertyManagerId) {
		this.propertyManagerId = propertyManagerId;
	}
	public RentalDetail getRentalDetail() {
		return rentalDetail;
	}
	public void setRentalDetail(RentalDetail rentalDetail) {
		this.rentalDetail = rentalDetail;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lease [leaseId=");
		builder.append(leaseId);
		builder.append(", propertyName=");
		builder.append(propertyName);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", ownerFirstName=");
		builder.append(ownerFirstName);
		builder.append(", ownerLastName=");
		builder.append(ownerLastName);
		builder.append(", tenantId=");
		builder.append(tenantId);
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
		builder.append(", rentalDetail=");
		builder.append(rentalDetail);
		builder.append(", filePath=");
		builder.append(filePath);
		builder.append("]");
		return builder.toString();
	}
	
}
