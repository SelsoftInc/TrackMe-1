package com.selsoft.trackme.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TRANSACTION")
public class Transaction {
	
	@Id
	private long transactionId;
	private long propertyId;
	private long ownerId;
	private long tenantId;
	private long propertyManagerId;
	private double amount;
	private String transactionType;
	private String paidBy;
	private int paidById;
	private String paymentMode;
	private Date paidOn;
	private String transactionDesc;
	private String category;
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public long getTenantId() {
		return tenantId;
	}
	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
	}
	public long getPropertyManagerId() {
		return propertyManagerId;
	}
	public void setPropertyManagerId(long propertyManagerId) {
		this.propertyManagerId = propertyManagerId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}
	public int getPaidById() {
		return paidById;
	}
	public void setPaidById(int paidById) {
		this.paidById = paidById;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Date getPaidOn() {
		return paidOn;
	}
	public void setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
	}
	public String getTransactionDesc() {
		return transactionDesc;
	}
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [transactionId=");
		builder.append(transactionId);
		builder.append(", propertyId=");
		builder.append(propertyId);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", tenantId=");
		builder.append(tenantId);
		builder.append(", propertyManagerId=");
		builder.append(propertyManagerId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", transactionType=");
		builder.append(transactionType);
		builder.append(", paidBy=");
		builder.append(paidBy);
		builder.append(", paidById=");
		builder.append(paidById);
		builder.append(", paymentMode=");
		builder.append(paymentMode);
		builder.append(", paidOn=");
		builder.append(paidOn);
		builder.append(", transactionDesc=");
		builder.append(transactionDesc);
		builder.append(", category=");
		builder.append(category);
		builder.append("]");
		return builder.toString();
	}
	
			 
	 
}
