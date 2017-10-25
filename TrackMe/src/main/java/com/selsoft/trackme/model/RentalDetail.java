package com.selsoft.trackme.model;

import java.util.Date;

public class RentalDetail {
	private int rentId;
	private int deposit;
	private String leaseType;
	private Date effectivedate;
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
	public Date getEffectivedate() {
		return effectivedate;
	}
	public void setEffectivedate(Date effectivedate) {
		this.effectivedate = effectivedate;
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
		builder.append(", effectivedate=");
		builder.append(effectivedate);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
