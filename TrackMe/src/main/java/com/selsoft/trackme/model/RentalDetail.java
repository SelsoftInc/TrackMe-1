package com.selsoft.trackme.model;

import java.util.Date;

public class RentalDetail {
	private int rentId;
	private int deposit;
	private String leaseType;
	private Date effectiveDate;
	public int getRentId() {
		return rentId;
	}
	public void setRentId(int rentId) {
		this.rentId = rentId;
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
