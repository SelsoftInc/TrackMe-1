package com.selsoft.trackme.model;

import java.util.Date;

public class RentalDetail {
	private int rent;
	private int deposit;
	private String leaseType;
	private Date effectivedate;

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
		builder.append("RentalDetail [rent=");
		builder.append(rent);
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
