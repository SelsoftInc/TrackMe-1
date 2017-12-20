package com.selsoft.lease.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LEASE")
public class Lease {
	@Id
	private String leaseId;
	private Date leaseStartDate;
	private Date leaseEndDate;
	private String moveInDate;
	private String tenure;
	private String leaseType;
	private String leaseStatus;
	private int rent;
	private int deposit;
	private String propertyId;
	private String ownerFirstName;
	private String ownerLastName;
	private String tenantId;
	private String managerId;
	private String filePath;
	public String getLeaseId() {
		return leaseId;
	}
	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
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
	public String getLeaseType() {
		return leaseType;
	}
	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}
	public String getLeaseStatus() {
		return leaseStatus;
	}
	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
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
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
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
		builder.append(", leaseStartDate=");
		builder.append(leaseStartDate);
		builder.append(", leaseEndDate=");
		builder.append(leaseEndDate);
		builder.append(", moveInDate=");
		builder.append(moveInDate);
		builder.append(", tenure=");
		builder.append(tenure);
		builder.append(", leaseType=");
		builder.append(leaseType);
		builder.append(", leaseStatus=");
		builder.append(leaseStatus);
		builder.append(", rent=");
		builder.append(rent);
		builder.append(", deposit=");
		builder.append(deposit);
		builder.append(", propertyId=");
		builder.append(propertyId);
		builder.append(", ownerFirstName=");
		builder.append(ownerFirstName);
		builder.append(", ownerLastName=");
		builder.append(ownerLastName);
		builder.append(", tenantId=");
		builder.append(tenantId);
		builder.append(", managerId=");
		builder.append(managerId);
		builder.append(", filePath=");
		builder.append(filePath);
		builder.append("]");
		return builder.toString();
	}
	
}
