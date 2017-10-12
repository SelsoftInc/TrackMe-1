package com.selsoft.pdftemplate;

import java.util.Date;

public class AccountActivity {
	private Date date;
	private String ref;
	private String description;
	private double amount;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountActivity [date=");
		builder.append(date);
		builder.append(", ref=");
		builder.append(ref);
		builder.append(", description=");
		builder.append(description);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
	

}
